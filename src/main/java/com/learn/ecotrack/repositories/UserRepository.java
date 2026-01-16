package com.learn.ecotrack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

}
