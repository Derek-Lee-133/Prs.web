package com.prs.web;

import java.time.LocalDateTime;
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

	// Get a request by id
	@GetMapping("/{id}")
	public Optional<Request> getById(@PathVariable int id) {
		return requestRepo.findById(id);

	}

	// Add a request
	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}

	// update request
	@PutMapping("/")
	public Request updateRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}

	// submit for review
	@PutMapping("/list-reveiw/{id}")
	public Request submitForReview(@RequestBody Request r) {

		if (r.getTotal() <= 50) {
			r.setStatus("Approved");
		} else {
			r.setStatus("review");
		}

		// set submitted date to current date

		r.setSubmittedDate(LocalDateTime.now());
		r = requestRepo.save(r);
		return r;
	}

	// delete request
	@DeleteMapping("/{id}")
	public Request deleteRequest(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);
		if (r.isPresent()) {

			requestRepo.deleteById(id);
		} else {
			System.out.println("Error user not found for id" + id);
		}
		return r.get();
	}

	// Request Review
	@GetMapping("/list-review/{id}")
	public List<Request> getRequestsByIdAndStatus(@PathVariable int id) {
		return requestRepo.findByUserNotAndStatus(id, "Review");

	}

	// Request Approved
	@PutMapping("/approve")
	public Request approveRequest(@RequestBody Request r) {
		r.setStatus("Approve");
		r = requestRepo.save(r);

		return r;
	}

	// request rejected
	@PutMapping("/reject")
	public Request rejectRequest(@RequestBody Request r) {
		r.setStatus("Reject");
		r = requestRepo.save(r);

		return r;
	}
}
