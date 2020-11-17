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

import com.prs.business.Vendor;
import com.prs.db.VendorRepo;

@CrossOrigin
@RestController
@RequestMapping("/vendors")
public class VendorController {
	@Autowired
	private VendorRepo vendorRepo;
	
	// get all Vendors
	@GetMapping("/")
	public List<Vendor> getAll() {
		return vendorRepo.findAll();
	}
	// Get a vendor by id
	@GetMapping("/{id}")
	public Optional<Vendor> getById(@PathVariable int id) {
		return vendorRepo.findById(id);
	
	
	
	}
	// Add a vendor
	@PostMapping("/")
	public Vendor addVendor(@RequestBody Vendor v) {
	v =	vendorRepo.save(v);
	return v;
	}
	
	// update vendor
	@PutMapping("/")
	public Vendor updateVendor(@RequestBody Vendor v) {
		v = vendorRepo.save(v);
		return v;
	}
	// delete vendor
	@DeleteMapping("/{id}")
	public Vendor deleteVendor(@PathVariable int id) {
		Optional<Vendor> v = vendorRepo.findById(id);
		if (v.isPresent()) {

		vendorRepo.deleteById(id);
		}
		else {
			System.out.println("Error user not found for id" + id);
		}
		return v.get();
	}

}
