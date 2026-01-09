package com.learn.ecotrack.services;

import com.learn.ecotrack.dtos.EnrollmentDto;

public interface EnrollmentService {
	
	 EnrollmentDto enroll(String userId,int workshopId);

	  
}
