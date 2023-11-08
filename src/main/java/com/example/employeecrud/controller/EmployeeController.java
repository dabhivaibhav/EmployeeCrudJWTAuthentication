package com.example.employeecrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.employeecrud.model.Employee;
import com.example.employeecrud.service.EmployeeService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/add")
	public String addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@GetMapping("/getall")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/get/{id}")
	public Optional<Employee> findEmployeeByID(@PathVariable("id") int id) {
		return employeeService.findEmployeeById(id);
	}

	@PutMapping("/{id}")
	@Transactional
	public String updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(employee, id);
	}

	@DeleteMapping("/delete/{id}")
	@Transactional
	public String deleteEmployee(@PathVariable("id") int id) {
		return employeeService.deleteEmployee(id);
	}
}
