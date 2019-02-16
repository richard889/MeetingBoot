package com.MeetingBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MeetingBoot.Service.EmployeeService;
import com.MeetingBoot.entity.Employee;
import com.MeetingBoot.entity.Login;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

	@Autowired
	private EmployeeService empService;

    @GetMapping("/Login")
    public String loginForm() {
        return "Login";
    }

    @PostMapping("/LoginValid")
    public String loginValid(Login login, Model model, HttpSession session,	final RedirectAttributes attributes) {
		
		String empNo = login.getEmpNo();
		String password = login.getPassword();		
		Employee  employee = empService.findByEmpNoAndPassword(empNo, password);
	
		if (employee==null) {
			model.addAttribute("error", "fail logged in");
			return "Login";
		}			
		else {
			session.setAttribute("LoginUser", employee);
			return "redirect:/Emp";
		}
    }
    
    @GetMapping("/Logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("LoginUser"); 
    	return "redirect:/Login";
    }
    
    
    

//    @PostMapping("/register")
//    public String register(@Valid UserForm userForm, BindingResult result) {
//        boolean boo = true;
//        if (!userForm.confirmPassword()) {
//            result.rejectValue("confirmPassword","confirmError","两次密码不一致");
//            boo = false;
//        }
//        if (result.hasErrors()) {
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            for (FieldError error : fieldErrors) {
//                System.out.println(error.getField() + " : " + error.getDefaultMessage() + " : " + error.getCode());
//            }
//            boo = false;
//        }
//
//        if (!boo) {
//            return "register";
//        }
//        User user = userForm.convertToUser();
//        userRepository.save(user);
//        return "redirect:/login";
//    }


}
