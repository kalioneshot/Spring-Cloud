package com.kali.services.employee.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kali.services.employee.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Employee Repository.
 * 
 * @author kali
 *
 */
public class EmployeeRepository {

	private List<Employee> employees = new ArrayList<>();

	/**
	 * Add a employee.
	 * 
	 * @param employee : The employee to add.
	 * @return a {@link Employee} object.
	 */
	public Employee add(Employee employee) {
		employee.setId((long) (employees.size() + 1));
		employees.add(employee);
		return employee;
	}

	/**
	 * Find an employee from ID.
	 * 
	 * @param id : The emplyee's ID.
	 * @return a {@link Employee} object.
	 */
	@HystrixCommand(fallbackMethod = "getDefaultEmployee")
	public Employee findById(Long id) {
		if (id.equals(Long.valueOf(100))) {
			throw new RuntimeException("ID Employee = 100 --> call Fallback function. ");
		}	
		Optional<Employee> employee = employees.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (employee.isPresent()) {
			return employee.get();
		} else
			return null;
	}

	/**
	 * The fallback function.
	 * 
	 * @return a {@link Employee} object.
	 */
	public Employee getDefaultEmployee(Long id) {
		return new Employee(Long.valueOf(0), "fallback", 0, "");
	}

	/**
	 * Get all employees.
	 * 
	 * @return a {@link List<Employee>} object.
	 */
	public List<Employee> findAll() {
		return employees;
	}

	/**
	 * Get employees from the organization ID.
	 * 
	 * @param organizationId : The organization ID.
	 * @return a {@link List<Employee>} object.
	 */
	public List<Employee> findByOrganization(Long organizationId) {
		return employees.stream().filter(a -> a.getOrganizationId().equals(organizationId))
				.collect(Collectors.toList());
	}

}
