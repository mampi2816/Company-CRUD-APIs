package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query("Select e from Employee e where e.email=?1")
	Optional<Employee> findEmployeeByEmail(String email);
	
	@Query("select e from Employee e where e.department.id=?1")
	List<Employee> findEmployeeByDept(Integer deptId);

}
