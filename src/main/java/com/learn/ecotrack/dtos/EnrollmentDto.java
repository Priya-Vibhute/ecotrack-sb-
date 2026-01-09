package com.learn.ecotrack.dtos;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.enums.PaymentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
	
	
	private User user;
	private Workshop workShop;
	private LocalDateTime enrolledAt;
	private Double amount;
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private PaymentStatus paymentStatus;

}
