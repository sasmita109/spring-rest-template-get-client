package com.app.model;

import javax.validation.constraints.NotNull;

public class EmployeeModel {

	@NotNull(message = "This is a reuired field")
	private String employeeId;
	private String name;
	private String city;
	private Integer age;

	public EmployeeModel(String employeeId, String name, String city, Integer age) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.city = city;
		this.age = age;
	}

	public EmployeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "EmployeeModel [employeeId=" + employeeId + ", name=" + name + ", city=" + city + ", age=" + age + "]";
	}

}
