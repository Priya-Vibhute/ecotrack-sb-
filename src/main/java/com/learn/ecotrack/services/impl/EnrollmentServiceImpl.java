package com.learn.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.EnrollmentDto;
import com.learn.ecotrack.entities.Enrollments;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.enums.PaymentStatus;
import com.learn.ecotrack.exceptions.NotFoundException;
import com.learn.ecotrack.repositories.EnrollmentRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.repositories.WorkshopRepository;
import com.learn.ecotrack.services.EmailService;
import com.learn.ecotrack.services.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private WorkshopRepository workshopRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public EnrollmentDto enroll(String email, int workshopId) {
		
		if(enrollmentRepository.existsByUserEmailAndWorkShopId(email, workshopId))
	        throw new NotFoundException("user has already enrolled");
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(()->new NotFoundException("user not found"));
				
		Workshop workshop = workshopRepository.findById(workshopId)
				.orElseThrow(()->new NotFoundException("workshop not found"));
		
		Enrollments enrollments = new Enrollments();
		enrollments.setUser(user);
		enrollments.setWorkShop(workshop);
		enrollments.setAmount(workshop.getPrice());
		enrollments.setPaymentStatus(PaymentStatus.CREATED);
	
		
		Enrollments savedEnrollment = enrollmentRepository.save(enrollments);
		
		emailService.sendEmail("priyanka.vibhute@itvedant.com", "Enrollment Completed", "Enrollment done successfully");
			
		return modelMapper.map(savedEnrollment, EnrollmentDto.class);
	}

}
