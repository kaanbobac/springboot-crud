package kaan.demo.spring.crudapp.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;

import kaan.demo.spring.crudapp.repo.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTestContainer {
	@Autowired
	protected UserRepository userRepo;
	@Autowired
	protected MockMvc mockMvc;
	static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	static {
		mongoDBContainer.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

}
