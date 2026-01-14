package com.learn.ecotrack.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(NotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex) {

	        Map<String, Object> body = new HashMap<>();
	        body.put("status", 404);
	        body.put("error", "Not Found");
	        body.put("message", ex.getMessage());
	        body.put("timestamp", Instant.now());

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	    }

}
