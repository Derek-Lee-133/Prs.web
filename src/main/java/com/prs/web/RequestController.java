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

	// Get a vendor by id
	@GetMapping("/{id}")
	public Optional<Request> getById(@PathVariable int id) {
		return requestRepo.findById(id);

	}

	// Add a vendor
	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}
	
//	private void recalculateCollectionValue(Request m) {
//		// get all movie collections for this user
//		// loop through them and sum a new total
//		double newTotal = 0.0;
//		List<Request> mcs = requestRepo.findByUserId(m.getUser().getId());
//		for (Request mc: mcs) {
//			newTotal += mc.getTotal()();
//		}
//		LineItem u = m.getUser();
//		u.setCollectionValue(newTotal);
//		userRepo.save(u);
//	}

	// update request
	@PutMapping("/")
	public Request updateRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}

	// submit for review
	@PutMapping("/requests/list-reveiw/{id}")
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

	// delete vendor
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
	@GetMapping("/requests/list-review/{id}")
	public List<Request> getRequestsByIdAndStatus(@PathVariable int id) {
		return requestRepo.findByUserNotAndStatus(id, "Review");

	}

	// Request Approved
	@PutMapping("/requests/approve")
	public Request approveRequest(@RequestBody Request r) {
		r.setStatus("Approve");
		r = requestRepo.save(r);

		return r;
	}

	@PutMapping("/requests/reject")
	public Request rejectRequest(@RequestBody Request r) {
		r.setStatus("Reject");
		r = requestRepo.save(r);

		return r;
	}
}
