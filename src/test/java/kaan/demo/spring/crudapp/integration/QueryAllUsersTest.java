package kaan.demo.spring.crudapp.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import kaan.demo.spring.crudapp.model.User;
import kaan.demo.spring.crudapp.repo.UserRepository;

@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class QueryAllUsersTest {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MockMvc mockMvc;
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	public void query_all_users() throws Exception {
		mongoDBContainer.start();
		User first = new User();
		first.setId(0);
		first.setName("first User");
		first.setPosts(null);
		userRepo.save(first);
		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(content()
				.string(org.hamcrest.Matchers.containsString("[{\"id\":0,\"name\":\"first User\",\"posts\":null}]")));
	}

	@AfterEach
	private void after() {
		userRepo.deleteAll();
	}
}
