package com.learn.ecotrack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.RequestDto;
import com.learn.ecotrack.services.RequestService;

@RestController
@RequestMapping("/requests")
public class RequestController {
	
	@Autowired
	private RequestService requestService;
    
	
	@PostMapping("/{email}/add")
	public ResponseEntity<RequestDto> 
	addRequest(@PathVariable String email ,@RequestBody RequestDto requestDto)
	{
		return new ResponseEntity<RequestDto>
		(requestService.addRequest(email, requestDto),HttpStatus.CREATED);
	}
	

	
	@PutMapping("/{id}/approve")
	public ResponseEntity<RequestDto> approveRequest(@PathVariable int id)
	{
		return ResponseEntity.ok(requestService.approveRequest(id));
	}
	

	
	@PutMapping("/{id}/reject")
	public ResponseEntity<RequestDto> rejectRequest
	(@PathVariable int id,@RequestParam String reason)
	{
		
		return ResponseEntity.ok(requestService.rejectRequest(id, reason));
	}
	
	@GetMapping
	public ResponseEntity<List<RequestDto>> requests()
	{
		return ResponseEntity.ok(requestService.getAllRequests());
	}

}
