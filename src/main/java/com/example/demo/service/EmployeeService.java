package com.example.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repository.EmployeeRepository;

import static com.example.demo.MampiApplication.LOGGER;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public List<Employee> getEmployeesByDept(Integer deptId){
		return employeeRepository.findEmployeeByDept(deptId);
	}
	
	public Optional<Employee> getEmployeeById(Integer id){
		return employeeRepository.findById(id);
	}
	
	public void addNewEmployee(Employee employee) {
		Optional<Employee> optionalEmployee=employeeRepository.findEmployeeByEmail(employee.getEmail());
		if(optionalEmployee.isPresent()) {
			LOGGER.error("Employee with this email already exists");
			throw new IllegalStateException("Employee with this email already exists");
		}else {
			employeeRepository.save(employee);
			LOGGER.info("Employee added successfully");
		}
	}
	
	public void deleteEmployee(Integer id) {
		boolean exists = employeeRepository.existsById(id);
		if(!exists) {
			LOGGER.error("Employee with "+id+" does not exists. ");
			throw new IllegalStateException("Employee with "+id+" does not  exists.");
		}else {
			employeeRepository.deleteById(id);
			LOGGER.info("Employee deleted successfully");
		}
	}
	
	@Transactional
	public void updateEmployee(Integer id, String name, String email) {
		Employee employee=employeeRepository.findById(id).orElseThrow(
				()->new IllegalStateException("Employee with "+id+" does not exists.")
		);
		if(name!=null && name.length()>0 && !Objects.equals(employee.getName(), name)) {
			employee.setName(name);
		}
		if(email!=null && name.length()>0 && !Objects.equals(employee.getEmail(), email)) {
			Optional<Employee> optionalEmployee=employeeRepository.findEmployeeByEmail(email);
			if(optionalEmployee.isPresent()) {
				throw new IllegalStateException("Employee with this email already exists.");
			}else {
				employee.setEmail(email);
			}
		}
		LOGGER.info("Employee details updated successfully");	
	}
}
