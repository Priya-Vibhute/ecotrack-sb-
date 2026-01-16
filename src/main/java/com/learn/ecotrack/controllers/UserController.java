package com.learn.ecotrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.UserDto;
import com.learn.ecotrack.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	/users/register
	
	@PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
    {
    	UserDto savedUser = userService.registerUser(userDto);
    	return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
    }
    
    
	@GetMapping("/exists")
	public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
	    boolean exists = userService.isEmailExists(email);
	    return ResponseEntity.ok(exists);
	}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	

}
