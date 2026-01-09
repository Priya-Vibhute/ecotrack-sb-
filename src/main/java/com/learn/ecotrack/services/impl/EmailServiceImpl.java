package com.learn.ecotrack.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(String to, String subject, String body) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		
		
		
	}

	@Override
	public void sendEmail(String[] to, String subject, String body) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		
	}

}
