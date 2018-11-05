package com.kali.services.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.kali.services.employee.model.Employee;
import com.kali.services.employee.repository.EmployeeRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The main class of application that enables Discovery Client for the
 * microservice.
 * 
 * @author kali
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	/**
	 * Initialize the repository.
	 * 
	 * @return a {@link EmployeeRepository} object.
	 */
	@Bean
	EmployeeRepository repository() {
		EmployeeRepository repository = new EmployeeRepository();
		repository.add(new Employee(1L, 1L, "John Smith", 34, "Analyst"));
		repository.add(new Employee(1L, 1L, "Darren Hamilton", 37, "Manager"));
		repository.add(new Employee(1L, 1L, "Tom Scott", 26, "Developer"));
		repository.add(new Employee(1L, 2L, "Anna London", 39, "Analyst"));
		repository.add(new Employee(1L, 2L, "Patrick Dempsey", 27, "Developer"));
		repository.add(new Employee(2L, 3L, "Kevin Price", 38, "Developer"));
		repository.add(new Employee(2L, 3L, "Ian Scott", 34, "Developer"));
		repository.add(new Employee(2L, 3L, "Andrew Campton", 30, "Manager"));
		repository.add(new Employee(2L, 4L, "Steve Franklin", 25, "Developer"));
		repository.add(new Employee(2L, 4L, "Elisabeth Smith", 30, "Developer"));
		return repository;
	}

}
