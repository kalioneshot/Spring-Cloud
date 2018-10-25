package com.kali.services.organization.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kali.services.organization.model.Department;

/**
 * The second client’s interface available inside organization-service calls two
 * endpoints from department-service. First of them GET
 * /organization/{organizationId} returns organization only with the list of
 * available departments, while the second GET
 * /organization/{organizationId}/with-employees return the same set of data
 * including the list employees assigned to every department.
 * 
 * @author kali
 *
 */
@FeignClient(name = "department-service")
public interface DepartmentClient {

	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId);

	@GetMapping("/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId);

}
