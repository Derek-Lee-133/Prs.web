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

import com.prs.business.Request;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/requests")

public class RequestController {
	@Autowired
	private RequestRepo requestRepo;

	// get all Requests
	@GetMapping("/")
	public List<Request> getAll() {
		return requestRepo.findAll();
	}

	// Get a vendor by id
	@GetMapping("/{id}")
	public Optional<Request> getById(@PathVariable int id) {
		return requestRepo.findById(id);

	}

	// Add a vendor
	@PostMapping("/")
	public Request addRequest(@RequestBody Request v) {
		v = requestRepo.save(v);
		return v;
	}

	// update vendor
	@PutMapping("/")
	public Request updateRequest(@RequestBody Request v) {
		v = requestRepo.save(v);
		return v;
	}

	// delete vendor
	@DeleteMapping("/{id}")
	public Request deleteRequest(@PathVariable int id) {
		Optional<Request> v = requestRepo.findById(id);
		if (v.isPresent()) {

			requestRepo.deleteById(id);
		} else {
			System.out.println("Error user not found for id" + id);
		}
		return v.get();
	}

	// Request Review
	@GetMapping("/requests/list-review/{id}")
	public List <Request> getRequestsByIdAndStatus(@PathVariable int id) {
		return requestRepo.findByUserIdNotAndStatus(id, "Review");

	}
	
	// Request Approved
	@PutMapping("/requests/approve")
	public List <Request> getRequestsByIdAndStatusApp(@PathVariable int id) {
		return requestRepo.findByUserIdNotAndStatus(id, "Approved");
	
	
	
}
	@PutMapping("/requests/reject")
	public List <Request> getRequestsByIdAndStatusRjt(@PathVariable int id) {
		return requestRepo.findByUserIdNotAndStatus(id, "Reject");
	
	
	
}
}
