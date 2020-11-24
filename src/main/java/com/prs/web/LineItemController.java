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

import com.prs.business.LineItem;
import com.prs.business.Request;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/lineItems")
public class LineItemController {
	
	
		@Autowired
		private LineItemRepo lineItemRepo;
		@Autowired
		private RequestRepo requestRepo;
		
		// get all LineItems
		@GetMapping("/")
		public List<LineItem> getAll() {
			return lineItemRepo.findAll();
		}
		// Get a LineItem by id
		@GetMapping("/{id}")
		public Optional<LineItem> getById(@PathVariable int id) {
			 return lineItemRepo.findById(id);
		
		
		
		}
		// Add a LineItem
		@PostMapping("/")
		public LineItem addLineItem(@RequestBody LineItem lI) {
			recalculateTotal(lI);
		lI =	lineItemRepo.save(lI);
		return lI;
		}
		private void recalculateTotal(LineItem lI) {
			// get all lineItems for request
			double newTotal = 0.0;
		
			
			
			
			List<LineItem> lineItems = lineItemRepo.findByRequestId(lI.getRequest().getId());
					// loop through them and sum a new total
			for (LineItem line: lineItems) {
				newTotal += line.getProduct().getPrice() * line.getQuantity();
			}
			// set new total in request
			// save request
			Request request = lI.getRequest();
			request.setTotal(newTotal);
			requestRepo.save(request);
		}
		
		// update LineItem
		@PutMapping("/")
		public LineItem updateLineItem(@RequestBody LineItem lI) {
			recalculateTotal(lI);
			lI = lineItemRepo.save(lI);
			return lI;
		}
		// delete LineItem
		@DeleteMapping("/{id}")
		public LineItem deleteLineItem(@PathVariable int id) {
			Optional<LineItem> lI = lineItemRepo.findById(id);
			
			if ( lI.isPresent()) {
				recalculateTotal(lI.get());

			lineItemRepo.deleteById(id);
			}
			else {
				System.out.println("Error user not found for id" + id);
			}
			return lI.get();
		}


}
