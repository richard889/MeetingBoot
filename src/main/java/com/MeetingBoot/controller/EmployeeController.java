package com.MeetingBoot.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MeetingBoot.Service.DepartmentService;
import com.MeetingBoot.Service.EmployeeService;
import com.MeetingBoot.entity.Department;
import com.MeetingBoot.entity.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/Emp")
	public String getAll(Model model) {
		List<Employee> empList = empService.findAll();
		model.addAttribute("empList", empList);
		return "EmployeeINQ";
	}

	@GetMapping("/Emp/add")
	public String add(Model model) {	
		List<Department> deptList = deptService.findAll();
		model.addAttribute("deptList", deptList);		
		
		model.addAttribute("employee", new Employee());
		return "EmployeeADD";
	}

	@GetMapping("/Emp/{id}/mod")
	public String update(@PathVariable long id, Model model) throws ParseException {
		List<Department> deptList = deptService.findAll();
		model.addAttribute("deptList", deptList);		
		
		Employee employee = empService.findOne(id);
		model.addAttribute("employee", employee);
		return "EmployeeMOD";
	}

	@PostMapping("/Emp/addSave")
	public String addSave(@Valid Employee emp, BindingResult result, Model model, final RedirectAttributes attributes, HttpSession session) {
		if(result.hasErrors()) {			
			List<Department> deptList = deptService.findAll();
			model.addAttribute("deptList", deptList);
			return "EmployeeADD";
		}	
		
		Employee loginUser =(Employee)session.getAttribute("LoginUser");
		emp.setCreatePID(loginUser.getEmpID());
		emp.setCreateDate(new Date());
		Employee empSave = empService.save(emp);
 		return "redirect:/Emp";
	}
	
	@PostMapping("/Emp/updateSave")
	public String updateSave(@Valid Employee emp, BindingResult result, Model model, final RedirectAttributes attributes, HttpSession session) {
		if(result.hasErrors()) {
			List<Department> deptList = deptService.findAll();
			model.addAttribute("deptList", deptList);
			
			attributes.addFlashAttribute("employee", emp);
			return "EmployeeMOD";
		}
		
		Employee empObj = empService.findOne(emp.getEmpID());
		emp.setCreatePID(empObj.getCreatePID());
		emp.setCreateDate(empObj.getCreateDate());
		
		Employee loginUser =(Employee)session.getAttribute("LoginUser");
		emp.setUpdatePID(loginUser.getEmpID());
		emp.setUpdateDate(new Date());
		
		Employee empSave = empService.save(emp);
 		return "redirect:/Emp";
	}
	
	@GetMapping("/Emp/{id}/delete")
	public String delete(@PathVariable long id, final RedirectAttributes attributes ) {
		empService.delete(id);
		return "redirect:/Emp";
	}
	
	@GetMapping(value="/index2")
    public String homepage(){
        return "index2";
    }
	
	@GetMapping(value="/Pickdate")
    public String homepage2(){
        return "Pickdate";
    }	

}
