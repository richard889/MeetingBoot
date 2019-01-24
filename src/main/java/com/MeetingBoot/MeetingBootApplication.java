package com.MeetingBoot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeetingBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MeetingBootApplication.class, args);
		
		System.out.println("hello world");		
	
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));	
	}
	
}
