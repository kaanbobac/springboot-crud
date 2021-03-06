package kaan.demo.spring.crudapp.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;

import kaan.demo.spring.crudapp.model.Post;
import kaan.demo.spring.crudapp.model.User;

@AutoConfigureMockMvc
public class RemoveUserTest extends BaseTestContainer{

	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private String json;

	@BeforeEach
	void init() throws JsonProcessingException {
		Post post1 = Post.builder().description("First Post").build();
		List<Post> posts1 = new ArrayList<>();
		posts1.add(post1);
		Post post2 = Post.builder().description("Second Post").build();
		List<Post> posts2 = new ArrayList<>();
		posts2.add(post2);
		User first = User.builder().email("myemail").posts(posts1).build();
		User second = User.builder().email("Second User").posts(posts2).build();
		List<User> users = new ArrayList<>();
		users.add(first);
		users.add(second);
		userRepo.saveAll(users);
	}

	@Test
	void remove_one_user() throws Exception {
		mockMvc.perform(delete("/remove-user?email=myemail"))
				.andExpect(status().is2xxSuccessful());
	}

	@AfterEach
	void after() {
		userRepo.deleteAll();
	}
}
