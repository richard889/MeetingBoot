package com.MeetingBoot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.MeetingBoot.Service.BookCategoryRepository;
import com.MeetingBoot.Service.EmployeeService;
import com.MeetingBoot.Service.MeetingService;
import com.MeetingBoot.Service.MeetingdetailService;
import com.MeetingBoot.entity.Book;
import com.MeetingBoot.entity.BookCategory;
import com.MeetingBoot.entity.Employee;
import com.MeetingBoot.entity.Meeting;
import com.MeetingBoot.entity.Meetingdetail;
import com.MeetingBoot.entity.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet; 
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 

@RestController
public class RestAPIs {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@GetMapping(value = "/api/employee/all")
	public List<Employee> getEmployeeAll() {
		List<Employee> empList = empService.findAll();
		for (Employee employee : empList) {
			System.out.print(employee.getName());
		}		
		
		return empList;
	}	
	
}