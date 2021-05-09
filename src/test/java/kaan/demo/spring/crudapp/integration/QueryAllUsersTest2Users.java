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
public class QueryAllUsersTest2 extends BaseTestContainer {
	@BeforeEach
	private void init() {
		Post post1 = Post.builder().id(0).description("First Post").build();
		List<Post> posts1 = new ArrayList<>();
		posts1.add(post1);
		Post post2 = Post.builder().id(1).description("Second Post").build();
		List<Post> posts2 = new ArrayList<>();
		posts2.add(post2);
		User first = User.builder().id(0).name("First User").posts(posts1).build();
		User second = User.builder().id(1).name("Second User").posts(posts2).build();
		List<User> users = new ArrayList<>();
		users.add(first);
		users.add(second);
		userRepo.saveAll(users);
	}

	@Test
	public void query_all_users() throws Exception {

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(content().json(
				"[{'id':0,'name':'First User','posts':[{'id':0,'description':'First Post'}]},{'id':1,'name':'Second User','posts':[{'id':1,'description':'Second Post'}]}]"));
	}

	@AfterEach
	private void after() {
		userRepo.deleteAll();
	}
}
