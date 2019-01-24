package com.MeetingBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingBoot.entity.Department;;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository deptRepository;

	public List<Department> findAll(){		
		return deptRepository.findAll();
	}
	
	public Department findOne(long deptID) {
		return deptRepository.findById(deptID).get();
	}
	
	public Department save(Department dept) {
		return deptRepository.save(dept);
	}	

	public void delete(long deptID) {
		deptRepository.deleteById(deptID);
	}
}
