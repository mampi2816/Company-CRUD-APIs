package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	@Query("select d from Department d where d.name=?1")
	Optional<Department> findDepartmentByName(String name);
}
