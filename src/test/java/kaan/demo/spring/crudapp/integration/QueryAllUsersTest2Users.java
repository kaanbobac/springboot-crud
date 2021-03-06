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
public class QueryAllUsersTest2Users extends BaseTestContainer {
	@BeforeEach
	private void init() {
		Post post1 = Post.builder().description("First Post").build();
		List<Post> posts1 = new ArrayList<>();
		posts1.add(post1);
		Post post2 = Post.builder().description("Second Post").build();
		List<Post> posts2 = new ArrayList<>();
		posts2.add(post2);
		User first = User.builder().email("First User").posts(posts1).build();
		User second = User.builder().email("Second User").posts(posts2).build();
		List<User> users = new ArrayList<>();
		users.add(first);
		users.add(second);
		userRepo.saveAll(users);
	}

	@Test
	public void query_all_users() throws Exception {

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(content().json(
				"[{'email':'First User','posts':[{'description':'First Post'}]},{'email':'Second User','posts':[{'description':'Second Post'}]}]"));
	}

	@AfterEach
	private void after() {
		userRepo.deleteAll();
	}
}
