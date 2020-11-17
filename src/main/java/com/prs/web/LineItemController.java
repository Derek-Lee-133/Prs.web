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
import com.prs.db.LineItemRepo;

@CrossOrigin
@RestController
@RequestMapping("/lineItems")
public class LineItemController {
	
	
		@Autowired
		private LineItemRepo lineItemRepo;
		
		// get all LineItems
		@GetMapping("/")
		public List<LineItem> getAll() {
			return lineItemRepo.findAll();
		}
		// Get a pLineItem by id
		@GetMapping("/{id}")
		public Optional<LineItem> getById(@PathVariable int id) {
			return lineItemRepo.findById(id);
		
		
		
		}
		// Add a pLineItem
		@PostMapping("/")
		public LineItem addLineItem(@RequestBody LineItem p) {
		p =	lineItemRepo.save(p);
		return p;
		}
		
		// update pLineItem
		@PutMapping("/")
		public LineItem updateLineItem(@RequestBody LineItem p) {
			p = lineItemRepo.save(p);
			return p;
		}
		// delete pLineItem
		@DeleteMapping("/{id}")
		public LineItem deleteLineItem(@PathVariable int id) {
			Optional<LineItem> p = lineItemRepo.findById(id);
			if (p.isPresent()) {

			lineItemRepo.deleteById(id);
			}
			else {
				System.out.println("Error user not found for id" + id);
			}
			return p.get();
		}


}
