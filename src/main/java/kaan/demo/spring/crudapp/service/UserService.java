package kaan.demo.spring.crudapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.spring.crudapp.model.Post;
import kaan.demo.spring.crudapp.model.User;
import kaan.demo.spring.crudapp.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User getOneUser(int id) {
		return userRepo.findById(id).orElse(null);
	}

	public void saveUser(User u) {
		userRepo.save(u);
	}

	public List<Post> queryUserPosts(int userId) {
		return userRepo.findById(userId).orElse(null).getPosts();
	}
	public void removeUser(int id) {
		userRepo.deleteById(id);
	}
}
