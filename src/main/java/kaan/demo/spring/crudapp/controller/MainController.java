package kaan.demo.spring.crudapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kaan.demo.spring.crudapp.model.User;
import kaan.demo.spring.crudapp.service.MainService;

@RestController
public class MainController {
	@Autowired
	private MainService service;
	@GetMapping("/list-all")
	public String showUser() {
		return "200 OK";
	}
	
	@PostMapping("/add-user")
	public String addUser(@RequestBody User user) {
		return service.saveUser(user);
	}
}
