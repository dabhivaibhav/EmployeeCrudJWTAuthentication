package com.example.employeecrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeecrud.model.Role;
import com.example.employeecrud.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public String addRole(Role role) {
		roleRepository.save(role);
		return "Role added successfully!!";
	}
}
