package com.example.employeecrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employeecrud.config.MyUserDetailService;
import com.example.employeecrud.config.MyUserDetails;
import com.example.employeecrud.model.AuthenticationRequest;
import com.example.employeecrud.model.AuthenticationResponse;
import com.example.employeecrud.model.Employee;
import com.example.employeecrud.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		UserDetails myUserDetails = myUserDetailService.loadUserByUsername(request.getEmail());
		var jwtToken = jwtService.generateToken(myUserDetails);
		return AuthenticationResponse.builder().jwtToken(jwtToken).build();
	}

}
