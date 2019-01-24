package com.MeetingBoot.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MeetingBoot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	public Employee findByEmpNoAndPassword(String empNo, String pwd);
	
	public Employee findByEmpNo(String empNo);

}
