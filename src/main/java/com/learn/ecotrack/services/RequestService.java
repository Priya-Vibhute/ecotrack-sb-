package com.learn.ecotrack.services;

import java.util.List;

import com.learn.ecotrack.dtos.RequestDto;
import com.learn.ecotrack.entities.Request;

public interface RequestService {
	
	
	RequestDto addRequest(String email,RequestDto requestDto);
	RequestDto approveRequest(int requestId);
    RequestDto rejectRequest(int requestId,String reason);
    List<RequestDto> getAllRequests();
    List<RequestDto> getRequestsByEmail(String email);

}
