package com.learn.ecotrack.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.RequestDto;
import com.learn.ecotrack.entities.Request;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.enums.RequestStatus;
import com.learn.ecotrack.exceptions.NotFoundException;
import com.learn.ecotrack.repositories.RequestRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.services.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RequestDto addRequest(String email, RequestDto requestDto) {
		
		User user = userRepository.findByEmail(email)
		.orElseThrow(()->new NotFoundException("email not found"));
		
		Request request = modelMapper.map(requestDto,Request.class);
		request.setStatus(RequestStatus.PENDING);
		request.setUser(user);
		Request savedRequest = requestRepository.save(request);
		
		return modelMapper.map(savedRequest, RequestDto.class);
	}

	@Override
	public RequestDto approveRequest(int requestId) {
		
		Request request = requestRepository.findById(requestId)
		.orElseThrow(()->new NotFoundException("Request not found"));
		
		request.setStatus(RequestStatus.APPROVED);
		Request savedRequest = requestRepository.save(request);
		
		return modelMapper.map(savedRequest, RequestDto.class);
	}

	@Override
	public RequestDto rejectRequest(int requestId, String reason) {
		
		Request request = requestRepository.findById(requestId)
				.orElseThrow(()->new NotFoundException("Request not found"));
		
		request.setStatus(RequestStatus.REJECTED);
		request.setReason(reason);
		
		Request savedRequest = requestRepository.save(request);
		
		return modelMapper.map(savedRequest, RequestDto.class);
	}

	@Override
	public List<RequestDto> getAllRequests(int pageNumber,int pageSize) {
		
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		
		List<RequestDto> requests = requestRepository.findAll(pageRequest)
		.stream()
		.map(r->modelMapper.map(r,RequestDto.class))
		.toList();
		
		return requests;
	}

	@Override
	public List<RequestDto> getRequestsByEmail(String email) {
		
		userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Email not found"));
		
		List<RequestDto> requests = requestRepository.findByUserEmail(email)
		.stream()
		.map(r->modelMapper.map(r, RequestDto.class))
		.toList();
		
		
		return requests;
	}

}
