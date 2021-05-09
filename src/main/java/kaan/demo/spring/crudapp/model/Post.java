package kaan.demo.spring.crudapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
	private int id;
	private String description;
}
