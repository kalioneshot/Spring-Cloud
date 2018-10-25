package com.kali.services.employee.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kali.services.employee.model.Employee;

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
	public Employee findById(Long id) {
		Optional<Employee> employee = employees.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (employee.isPresent())
			return employee.get();
		else
			return null;
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
	 * Get employees from the department ID.
	 * 
	 * @param departmentId : The department ID.
	 * @return a {@link List<Employee>} object.
	 */
	public List<Employee> findByDepartment(Long departmentId) {
		return employees.stream().filter(a -> a.getDepartmentId().equals(departmentId)).collect(Collectors.toList());
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
