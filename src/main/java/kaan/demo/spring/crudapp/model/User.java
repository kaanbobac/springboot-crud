package kaan.demo.spring.crudapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "users")
@Data
@Builder
public class User {

	@Id
	private int id;
	private String name;
	private List<Post> posts;
}
