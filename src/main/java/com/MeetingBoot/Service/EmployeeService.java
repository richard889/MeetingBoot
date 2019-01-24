package com.MeetingBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingBoot.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAll(){		
		return employeeRepository.findAll();
	}
	
	public Employee findOne(long empID) {
		return employeeRepository.findById(empID).get();
	}
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}	

	public void delete(long empID) {
		employeeRepository.deleteById(empID);
	}
	
	public Employee findByEmpNoAndPassword(String empNo, String pwd){		
		return employeeRepository.findByEmpNoAndPassword(empNo,pwd);
	}
	
	public Employee findByEmpNo(String empNo) {
		return employeeRepository.findByEmpNo(empNo);
	}
	
}
