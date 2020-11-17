package com.prs.db;



import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.prs.business.LineItem;


	public interface LineItemRepo extends JpaRepositoryImplementation<LineItem, Integer> {

	}

