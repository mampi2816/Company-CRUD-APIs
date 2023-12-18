package com.example.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

import static com.example.demo.MampiApplication.LOGGER;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	
	
	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}
	
	@Cacheable(value = "departments")
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
	
	@Cacheable(value = "departments",key = "#id")
	public Optional<Department> getDeptById(Integer id){
		return departmentRepository.findById(id);
	}
	
	@CachePut(value = "departments", key="#department.id")
	public void createNewDepartment(Department department) {
		Optional<Department> departmentByName = departmentRepository.findDepartmentByName(department.getName());
		if(departmentByName.isPresent()) {
			LOGGER.error("Department with "+ department.getId()+ " already exists. ");
			throw new IllegalStateException("Department with " + department.getId()+" already exists.");
		}else {
			for(Employee employee:department.getEmployeeList()) {
				employee.setDepartment(department);
			}
			departmentRepository.save(department);
			LOGGER.info("Department created successfully");
		}
	}
	
	@CacheEvict(value= "departments")
	public void deleteDepartment(Integer id) {
		boolean exists = departmentRepository.existsById(id);
		if(!exists) {
			LOGGER.error("Department with " + id + " does not exists");
			throw new IllegalStateException("Department with " + id + " does not exists");
		}else {
			List<Employee> employees = employeeRepository.findEmployeeByDept(id);
			if(employees.size()>0) {
				for(Employee e:employees) {
					e.setDepartment(null);
				}
			}
			departmentRepository.deleteById(id);
			LOGGER.info("Department deleted successfully");
		}
	}
	
	@Transactional
	@CachePut(value = "departments", key="#id")
	public void updateDepartment(Integer id, String name, String manager) {
		Department department = departmentRepository.findById(id).orElseThrow(
				()->new IllegalStateException("Department with "+id+" does not exixts.")
		);
		if(name!=null && name.length()>0 && !Objects.equals(department.getName(), name)) {
			Optional<Department> optionalDepartment = departmentRepository.findDepartmentByName(name);
			if(optionalDepartment.isPresent()) {
				throw new IllegalStateException("Department with this name already exists");
			}
			else {
				department.setName(name);
			}
		}
		if(manager!=null && manager.length()>0 && !Objects.equals(department.getManager(), manager)) {
			department.setManager(manager);
		}
		LOGGER.info("Department details updated successfully");
	}
}