package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping(path = "/dept/{deptId}")
	public List<Employee> getEmployeesByDept(@PathVariable("deptId") Integer deptId){
		return employeeService.getEmployeesByDept(deptId);
	}
	
	@GetMapping(path="/id/{empId}")
	public Optional<Employee> getEmployeeById(
			@PathVariable("empId") Integer id
	){
		return employeeService.getEmployeeById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewEmployee(@RequestBody Employee employee) {
		 employeeService.addNewEmployee(employee);
	}
	
	@PutMapping(path="{empId}")
	public void updateEmployee(
			@PathVariable("empId") Integer id,
			@RequestParam(required = false)String name,
			@RequestParam(required = false)String email
	) {
		employeeService.updateEmployee(id, name, email);
	}
	
	@DeleteMapping(path="{empId}")
	public void deleteEmployee(@PathVariable("empId") Integer id) {
		employeeService.deleteEmployee(id);
	}
}
