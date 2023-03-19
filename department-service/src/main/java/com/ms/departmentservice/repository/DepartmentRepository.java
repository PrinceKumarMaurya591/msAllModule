package com.ms.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.departmentservice.entity.Department;

public interface DepartmentRepository  extends JpaRepository<Department, Long> {
	
}