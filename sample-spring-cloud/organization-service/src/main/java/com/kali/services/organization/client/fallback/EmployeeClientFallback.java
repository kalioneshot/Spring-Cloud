package com.kali.services.organization.client.fallback;

import java.util.ArrayList;
import java.util.List;

import com.kali.services.organization.client.EmployeeClient;
import com.kali.services.organization.model.Employee;

/**
 * Fallback implementation implements the interface annotated with @FeignClient.
 * 
 * For example, the fallback implementation can use cache, take a
 * look @EmployeeClient.
 * 
 * @author kali
 *
 */
//@Component("employeeClientFallbackID")
public class EmployeeClientFallback implements EmployeeClient {

	// @Autowired
	// private CacheManager cacheManager;

	@Override
	public List<Employee> findByOrganization(Long organizationId) {
		// ValueWrapper valueWrapper =
		// cacheManager.getCache("employeeList").get(organizationId);
		// if (valueWrapper != null) {
		// return (List<Employee>) valueWrapper;
		// }else { .. }
		return new ArrayList<Employee>();
	}

}
