package com.prs.web;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.Product;
import com.prs.db.ProductRepo;

@CrossOrigin
@RestController
@RequestMapping("/products")

public class ProductController {
	@Autowired
	private ProductRepo productRepo;
	
	// get all Products
	@GetMapping("/")
	public List<Product> getAll() {
		return productRepo.findAll();
	}
	// Get a Product by id
	@GetMapping("/{id}")
	public Optional<Product> getById(@PathVariable int id) {
		return productRepo.findById(id);
	
	
	
	}
	// Add a Product
	@PostMapping("/")
	public Product addProduct(@RequestBody Product p) {
	p =	productRepo.save(p);
	return p;
	}
	
	// update Product
	@PutMapping("/")
	public Product updateProduct(@RequestBody Product p) {
		p = productRepo.save(p);
		return p;
	}
	// delete Product
	@DeleteMapping("/{id}")
	public Product deleteProduct(@PathVariable int id) {
		Optional<Product> p = productRepo.findById(id);
		if (p.isPresent()) {

		productRepo.deleteById(id);
		}
		else {
			System.out.println("Error user not found for id" + id);
		}
		return p.get();
	}

}
