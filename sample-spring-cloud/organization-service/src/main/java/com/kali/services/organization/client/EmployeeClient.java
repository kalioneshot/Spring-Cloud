package com.kali.services.organization.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kali.services.organization.model.Employee;

/**
 * Organization-service communicates with two other microservices we should
 * create two interfaces, one per single microservice. Every client’s interface
 * should be annotated with @FeignClient. One field inside annotation is
 * required – name. This name should be the same as the name of target service
 * registered in service discovery. Here’s the interface of the client that
 * calls endpoint GET /organization/{organizationId} exposed by employee-service
 * 
 * @author kali
 *
 */
//@FeignClient(name = "employee-service", fallback = EmployeeClientFallback.class, qualifier = "employeeClientID", primary = false)
@FeignClient(name = "employee-service", qualifier = "employeeClientID")
public interface EmployeeClient {

	// We can use the cache to realize a fallback implementation.
	// @CachePut("employeeList")
	@GetMapping("/organization/{organizationId}")
	List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId);

}
