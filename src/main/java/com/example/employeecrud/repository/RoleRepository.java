package com.example.employeecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeecrud.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
