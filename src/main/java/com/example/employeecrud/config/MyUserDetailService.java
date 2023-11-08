package com.example.employeecrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.employeecrud.model.Employee;
import com.example.employeecrud.repository.EmployeeRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmailAddress(username);
		if (employee == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(employee);
	}

}
