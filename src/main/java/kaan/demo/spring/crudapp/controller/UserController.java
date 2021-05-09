package kaan.demo.spring.crudapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		return new ResponseEntity<User>(userService.getOneUser(id), HttpStatus.OK);
	}

	@PostMapping("/add-user")
	private ResponseEntity saveOneUser(@RequestBody User u) {
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/query-user-posts")
	private ResponseEntity<List<Post>> queryAllPostsofAUser(@RequestParam int userId) {
		return new ResponseEntity<List<Post>>(userService.queryUserPosts(userId), HttpStatus.OK);
	}
}
