package com.MeetingBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.MeetingBoot.Service.MeetingdetailService;
import com.MeetingBoot.entity.Meetingcount;

@Controller
public class MeetingCountController {

	@Autowired
	private MeetingdetailService meetingdetailService;
	
	@GetMapping("/Meetingcount")
	public String getAll(Model model) {
		List<Meetingcount> meetingcountList = meetingdetailService.findByMeetingcount();
		model.addAttribute("meetingcountList", meetingcountList);
		
		return "MeetingCount";		
	}

}
