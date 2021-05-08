package kaan.demo.spring.crudapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import kaan.demo.spring.crudapp.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {
}
