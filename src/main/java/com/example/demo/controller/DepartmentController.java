package com.example.demo.controller;

import com.example.demo.models.Department;
import com.example.demo.service.DepartmentService;

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


@RestController
@RequestMapping("api/department")
public class DepartmentController {
	public final DepartmentService departmentService;
	
	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping
	public List<Department> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	@GetMapping(path = "{deptId}")
	public Optional<Department> getDeptById(@PathVariable("deptId") Integer id){
		return departmentService.getDeptById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createNewDepartment(@RequestBody Department department) {
		departmentService.createNewDepartment(department);
	}
	
	@PutMapping(path="{deptId}")
	public void updateDepartment(
			@PathVariable("deptId") Integer id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String manager
	) {
		departmentService.updateDepartment(id, name, manager);
	}
	
	@DeleteMapping(path="{deptId}")
	public void deleteDepartment(@PathVariable("deptId") Integer id) {
		departmentService.deleteDepartment(id);
	}
}
