package com.gigsync.syncrm;

import com.gigsync.syncrm.models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.*;

@SpringBootTest
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class SyncrmApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnAEmployeeWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/employee/99", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(99);

		Double amount = documentContext.read("$.name");
		assertThat(amount).isEqualTo("john");
	}

	@Test
	void shouldNotReturnAEmployeeWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/employee/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

//	@Test
//	@DirtiesContext
//	void shouldCreateANewEmployee() {
//		Employee newEmployee = new Employee(null, "Raj");
//		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/employee", newEmployee, Void.class);
//		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//		URI locationOfNewEmployee = createResponse.getHeaders().getLocation();
//		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewEmployee, String.class);
//		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//		Number id = documentContext.read("$.id");
//		String name = documentContext.read("$.name");
//
//		assertThat(id).isNotNull();
//		assertThat(name).isEqualTo("Raj");
//	}
//
//	@Test
//	void shouldReturnAllEmployeesWhenListIsRequested() {
//		ResponseEntity<String> response = restTemplate.getForEntity("/employee", String.class);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//		DocumentContext documentContext = JsonPath.parse(response.getBody());
//		int employeeCount = documentContext.read("$.length()");
//		assertThat(employeeCount).isEqualTo(3);
//
//		JSONArray ids = documentContext.read("$..id");
//		assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);
//
//		JSONArray names = documentContext.read("$..name");
//		assertThat(names).containsExactlyInAnyOrder("John", "Sabrina", "James");
//	}


}
