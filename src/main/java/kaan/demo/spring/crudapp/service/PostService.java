package kaan.demo.spring.crudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.spring.crudapp.repo.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;

}
