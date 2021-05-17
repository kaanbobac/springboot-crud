package kaan.demo.spring.crudapp.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import kaan.demo.spring.crudapp.model.User;

@AutoConfigureMockMvc
public class SaveUserTest extends BaseTestContainer {
	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private String json;

	@BeforeEach
	void init() throws JsonProcessingException {
		User user = User.builder().email("myemail").build();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(user);
	}

	@Test
	void query_one_user() throws Exception {
		mockMvc.perform(post("/add-user").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isCreated());
	}

	@AfterEach
	void after() {
		userRepo.deleteAll();
	}
}
