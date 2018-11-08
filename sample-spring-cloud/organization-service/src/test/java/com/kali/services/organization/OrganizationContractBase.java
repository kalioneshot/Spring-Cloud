package com.kali.services.organization;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kali.services.organization.api.OrganizationController;
import com.kali.services.organization.client.EmployeeClient;
import com.kali.services.organization.model.Employee;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * The important points - Supplier side : <br>
 * 
 * 1. Importation of the library to verify that our controller implements the
 * contract (spring-cloud-starter-contract-verifier)<br>
 * 
 * 2. A plugin (spring-cloud-contract-maven-plugin) that will allow to generate
 * the tests from the contract and generate the stubs that will be used on the
 * consumer side.<br>
 * 
 * 3. Creation of a contract (in yaml format or with a groovy DSL)<br>
 * 
 * 4. This supplier-side contract will be stored in the
 * src/test/resources/contracts directory with the body of the response.
 * 
 * Base class used for generated contract tests (see
 * target/generated-test-sources)
 * 
 * It will usually be necessary to create as many basic classes as you have of
 * Controllers.
 *
 * @author kali
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureJsonTesters
public class OrganizationContractBase {

	// The entry point.
	@Autowired
	private OrganizationController controller;

	// The supplier.
	@MockBean
	private EmployeeClient employeeClient;

	@Autowired
	private JacksonTester<List<Employee>> employeesJacksonTester;

	/**
	 * Initialization step.
	 * 
	 * @throws IOException
	 */
	@Before
	public void setup() throws IOException {
		// when:
		// In this example the repository is already initialized by the main class.
		given(employeeClient.findByOrganization(anyLong())).willReturn(employeesJacksonTester
				.readObject(getClass().getResourceAsStream("/contracts/employees_response.json")));

		// Initializing a MockMvc environment with RestAssuredMockMvc:
		RestAssuredMockMvc.standaloneSetup(controller);
	}

	@Test
	public void emptyTest() {
	}

}
