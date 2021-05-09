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
public class QueryUserPostsTest extends BaseTestContainer {
	@BeforeEach
	void init() {
		Post post1 = Post.builder().id(0).description("First Post").build();
		List<Post> posts = new ArrayList<>();
		posts.add(post1);
		User first = User.builder().id(0).name("First User").posts(posts).build();
		userRepo.save(first);
	}

	@Test
	void query_one_user() throws Exception {
		mockMvc.perform(get("/query-user-posts?userId=0")).andExpect(status().isOk())
				.andExpect(content().json("[{'id':0,'description':'First Post'}]"));
	}

	@AfterEach
	void after() {
		userRepo.deleteAll();
	}
}