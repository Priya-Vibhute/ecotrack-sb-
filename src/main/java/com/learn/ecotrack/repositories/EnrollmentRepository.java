package com.learn.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.Enrollments;

public interface EnrollmentRepository extends JpaRepository<Enrollments, Integer> {

	 boolean existsByUserEmailAndWorkShopId(String userId, int workshopId);
}
