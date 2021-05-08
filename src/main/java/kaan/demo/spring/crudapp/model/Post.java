package kaan.demo.spring.crudapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("posts")
@Data
public class Post {
	private int id;
	private String description;
}
