package com.learn.ecotrack.services;

public interface EmailService {
	
	void sendEmail(String to, String subject, String body);
	void sendEmail(String to[], String subject, String body);

}
