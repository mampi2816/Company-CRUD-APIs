package com.example.demo.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee")
public class Employee implements Serializable{
	@Id
	@SequenceGenerator(
			name="employee_sequence",
			sequenceName = "employee_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "employee_sequence" 
	)
	
	private int id;
	private String name;
	private String email;
	private LocalDate dob;
	@Transient
	private int age;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "dept_id")
	private Department department;
	
	public Employee() {
		
	}

	public Employee(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public int getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
		
	}
	
	public Department getDepartment() {
		return department;
	}
	
	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", dob=" + dob + 
				", age=" + age + 
				'}';
	}
}
