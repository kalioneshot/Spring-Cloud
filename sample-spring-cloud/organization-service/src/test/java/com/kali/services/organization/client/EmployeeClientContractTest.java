package com.kali.services.organization.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.netflix.ribbon.StaticServerList;

import com.kali.services.organization.model.Employee;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

/**
 * The @AutoConfigureStubRunner annotation will configure the stub, looking for
 * the Maven dependency specified by the ids attribute (in the format groupId:
 * artifactId: version: classifier) ​​and exposing it on port 8090.
 * 
 * 
 * By using the + as the version number, we make sure to take the most recent
 * one, which makes it possible to verify that our test always passes despite
 * the evolutions of the supplier.
 * 
 * @author kali
 *
 */
//@ImportAutoConfiguration({ RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class,
//FeignAutoConfiguration.class })
//@SpringBootTest(classes = { EmployeeClient.class, ConfigurationFeign.class })
//@AutoConfigureStubRunner(ids = {
//"com.kali:organization-service:+:stubs:8090" }, stubsMode = StubRunnerProperties.StubsMode.LOCAL)

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.NONE)
//@AutoConfigureStubRunner(ids = {
//		"com.kali:organization-service:+:stubs:8090" }, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class EmployeeClientContractTest {

//	@Autowired
	private EmployeeClient client;

//	@Test
	public void findByOrganizationTest() {
		// given

		// when
		List<Employee> employees = client.findByOrganization(Long.valueOf(1));

		// then
		assertNotNull(employees);
		assertEquals(3, employees.size());
	}

//	@TestConfiguration
	public static class ServerConfiguration {
//		@Autowired
		private StubFinder stubFinder;

//		@Bean
		public ServerList<Server> staticServerList() {
			return new StaticServerList<>(
					new Server("localhost", stubFinder.findStubUrl("com.kali:organization-service").getPort()));
		}
	}

}
