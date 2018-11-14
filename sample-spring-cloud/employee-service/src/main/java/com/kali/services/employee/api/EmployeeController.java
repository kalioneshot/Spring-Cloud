package com.kali.services.employee.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kali.services.employee.model.Employee;
import com.kali.services.employee.repository.EmployeeRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Employee API
 * 
 * @author kali
 *
 */
@RestController
public class EmployeeController {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * Repository.
	 */
	@Autowired
	private EmployeeRepository repository;

	/**
	 * Add Employee.
	 * 
	 * @param employee : The employee to add.
	 * @return a {@link Employee} object.
	 */
	@PostMapping("/")
	public Employee add(@RequestBody Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return repository.add(employee);
	}

	/**
	 * Find a employee
	 * 
	 * @HystrixCommand(fallbackMethod = "defaultStores")
	 * 
	 * @param id : The employee's ID.
	 * @return a {@link Employee}
	 */
	@GetMapping("/{id}")
	@HystrixCommand
	public Employee findById(@PathVariable("id") Long id) {
		LOGGER.info("Employee find: id={}", id);
		return repository.findById(id);
	}

	/**
	 * Get all employees.
	 * 
	 * @return a {@link List<Employee>} object.
	 */
	@GetMapping("/")
	public List<Employee> findAll() {
		LOGGER.info("Employee find");
		return repository.findAll();
	}

	/**
	 * Get employees by ID of organization.
	 * 
	 * @param organizationId : The ID of organization.
	 * @return a {@link List<Employee>} object.
	 */
	@GetMapping("/organization/{organizationId}")
	public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}

}
