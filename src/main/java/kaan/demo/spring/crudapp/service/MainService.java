package kaan.demo.spring.crudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.spring.crudapp.model.User;
import kaan.demo.spring.crudapp.repo.UserRepository;

@Service
public class MainService {
	@Autowired
	private UserRepository repo;
	public String saveUser(User user) {
		repo.save(user);
		return "200 OK";
	}
}
