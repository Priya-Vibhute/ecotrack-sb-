package com.learn.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.UserDto;
import com.learn.ecotrack.entities.Role;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.enums.AppRole;
import com.learn.ecotrack.repositories.RoleRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto registerUser(UserDto userDto) {
		
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User user = modelMapper.map(userDto, User.class);
		
		Role role = roleRepository.findByRoleName(AppRole.ROLE_USER)
		.orElseThrow(()->new RuntimeException("Role not found"));
		
		user.setRole(role);
		
		
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public boolean isEmailExists(String email) {
		
		return userRepository.existsByEmail(email);
	}

}
