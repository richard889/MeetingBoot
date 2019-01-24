package com.MeetingBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="error")
public class ErrorBaseeController implements ErrorController {
	
	private static final Logger log = LoggerFactory.getLogger(ErrorBaseeController.class);
	
    @GetMapping("/404")
    public String notFound() {
        return "404";
    }
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		
		return "error/error";		
	}
	
	@RequestMapping
	public String error(){
		return getErrorPath();
	}

}
