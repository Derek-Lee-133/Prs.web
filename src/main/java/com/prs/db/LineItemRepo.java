package com.prs.db;



import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.prs.business.LineItem;


	public interface LineItemRepo extends JpaRepositoryImplementation<LineItem, Integer> {

		List<LineItem> findByRequestId(int id);
		
	
		
	}

