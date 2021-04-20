package kaan.demo.spring.crudapp.repo;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import kaan.demo.spring.crudapp.model.User;
public interface UserRepository extends MongoRepository<User, String> {
	  public List<User> findByFirstName(String firstName);
	  public List<User> findByLastName(String lastName);
}
