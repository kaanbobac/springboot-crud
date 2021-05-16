package kaan.demo.spring.crudapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kaan.demo.spring.crudapp.exception.UserNotFoundException;
import kaan.demo.spring.crudapp.model.Post;
import kaan.demo.spring.crudapp.model.User;
import kaan.demo.spring.crudapp.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	private ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/user")
	private ResponseEntity<User> getOneUser(@RequestParam int id) {
		if (userService.getOneUser(id) == null)
			throw new UserNotFoundException("id: " + id + " not found");
		return new ResponseEntity<User>(userService.getOneUser(id), HttpStatus.OK);
	}

	@PostMapping("/add-user")
	private ResponseEntity saveOneUser(@Valid @RequestBody User u) {
		userService.saveUser(u);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@GetMapping("/query-user-posts")
	private ResponseEntity<List<Post>> queryAllPostsofAUser(@RequestParam int userId) {
		return new ResponseEntity<List<Post>>(userService.queryUserPosts(userId), HttpStatus.OK);
	}
	@DeleteMapping("/remove-user")
	private ResponseEntity removeUser(@RequestParam int id) {
		if (userService.getOneUser(id) == null)
			throw new UserNotFoundException("id: " + id + " not found");
		return new ResponseEntity(HttpStatus.OK);
	}
}
