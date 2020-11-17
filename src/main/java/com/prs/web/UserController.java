package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.User;
import com.prs.db.UserRepo;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepo userRepo;
	
	// get all Users
	@GetMapping("/")
	public List<User> getAll() {
		return userRepo.findAll();
	}
	// Get a user by id
	@GetMapping("/{id}")
	public Optional<User> getById(@PathVariable int id) {
		return userRepo.findById(id);
	
	
	
	}
	// Add a user
	@PostMapping("/")
	public User addUeser(@RequestBody User u) {
	u =	userRepo.save(u);
	return u;
	}
	
	// update user
	@PutMapping("/")
	public User updateUser(@RequestBody User u) {
		u = userRepo.save(u);
		return u;
	}
	// update user
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable int id) {
		Optional<User> u = userRepo.findById(id);
		if (u.isPresent()) {

		userRepo.deleteById(id);
		}
		else {
			System.out.println("Error user not found for id" + id);
		}
		return u.get();
	}
	// login via GET with requestparms
	@GetMapping("/login")
	public Optional<User> login(@RequestParam String userName, 
					  @RequestParam String passWord) {
		Optional<User> u = userRepo.findByUserNameAndPassWord(userName, passWord);
		if (u.isPresent()) {
			return u;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
	// login via POST
	@PostMapping("/login")
	public Optional<User> login(@RequestBody User u) {
		Optional<User> user = userRepo.findByUserNameAndPassWord(u.getUserName(), u.getPassWord());
		if (user.isPresent()) {
			return user;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
}
