package kaan.demo.spring.crudapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
@Document(collection = "users")
@Getter @Setter
public class User {

	@Id
	public String id;
	public String firstName;
	public String lastName;
}
