package com.learn.ecotrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.EnrollmentDto;
import com.learn.ecotrack.services.EnrollmentService;

@RequestMapping("/enrollments")
@RestController
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping("/{workshopId}")
	public ResponseEntity<EnrollmentDto> enroll
	(@RequestParam String email,@PathVariable int workshopId)
	{
		
		return ResponseEntity.
				ok(enrollmentService.enroll(email, workshopId));
	}
	
	

}
