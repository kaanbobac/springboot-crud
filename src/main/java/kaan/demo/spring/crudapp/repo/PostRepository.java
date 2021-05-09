package kaan.demo.spring.crudapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import kaan.demo.spring.crudapp.model.Post;

public interface PostRepository extends MongoRepository<Post, Integer> {
}
