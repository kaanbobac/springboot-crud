package kaan.demo.spring.crudapp.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import kaan.demo.spring.crudapp.model.Post;
import kaan.demo.spring.crudapp.model.User;

@AutoConfigureMockMvc
public class QueryUserNotExist extends BaseTestContainer {
	@BeforeEach
	void init() {
		Post post1 = Post.builder().description("First Post").build();
		List<Post> posts = new ArrayList<>();
		posts.add(post1);
		User first = User.builder().email("mymail").posts(posts).build();
		userRepo.save(first);
	}

	@Test
	void query_one_user() throws Exception {
		mockMvc.perform(get("/user?email=notexistmail")).andExpect(status().isNotFound())
				.andExpect(content().json("{'message':'email notexistmail not found','details':'uri=/user\'}"));
	}

	@AfterEach
	void after() {
		userRepo.deleteAll();
	}
}
