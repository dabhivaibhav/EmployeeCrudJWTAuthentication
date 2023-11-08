package com.example.employeecrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeecrud.model.Role;
import com.example.employeecrud.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/add")
	public String addRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}
}
