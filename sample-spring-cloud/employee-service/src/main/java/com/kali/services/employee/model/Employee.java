package com.kali.services.employee.model;

/**
 * Employee model.
 * 
 * @author kali
 *
 */
public class Employee {

	private Long id;
	private Long organizationId;
	private String name;
	private int age;
	private String position;

	public Employee() {

	}

	public Employee(Long organizationId, String name, int age, String position) {
		this.organizationId = organizationId;
		this.name = name;
		this.age = age;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", organizationId=" + organizationId + ", name=" + name + ", position=" + position
				+ "]";
	}

}
