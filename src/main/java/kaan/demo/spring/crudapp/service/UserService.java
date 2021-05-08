package kaan.demo.spring.crudapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.spring.crudapp.model.User;
import kaan.demo.spring.crudapp.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
}
