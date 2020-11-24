package com.prs.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.Request;

public interface RequestRepo extends JpaRepository<Request, Integer> {
	
	List<Request> findByUserNotAndStatus(int id, String status);

	// List<Request> findByUser(Integer id);
	
	
	
}
