package com.prs.db;



import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.prs.business.Product;

public interface ProductRepo extends JpaRepositoryImplementation<Product, Integer> {

}
