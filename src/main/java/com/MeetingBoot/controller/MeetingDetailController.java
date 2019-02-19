package com.MeetingBoot.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.MeetingBoot.Service.EmployeeService;
import com.MeetingBoot.Service.MeetingService;
import com.MeetingBoot.Service.MeetingdetailService;
import com.MeetingBoot.entity.Employee;
import com.MeetingBoot.entity.Meeting;
import com.MeetingBoot.entity.Meetingdetail;
import com.fasterxml.jackson.core.JsonProcessingException;


@Controller
public class MeetingDetailController {

	@Autowired
	private MeetingService meetingService;	
	@Autowired
	private MeetingdetailService meetingdetailService;	
	@Autowired
	private EmployeeService employeeService;	
	
	@GetMapping("/Meetingdetail")
	public String getAll(Model model, @SessionAttribute("LoginUser") Employee loginUser) {
		List<Meeting> meetingList = meetingService.findAll();
		if (!"1".equals(loginUser.getRole())) {
		    meetingList = meetingList.stream()
			.filter(meeting -> (
				(meeting.getMeetingdetails().stream().map(Meetingdetail::getEmployee).collect(Collectors.toList())).stream()
				.map(Employee::getEmpNo).collect(Collectors.toList()).contains(loginUser.getEmpNo())))	
			.collect(Collectors.toList());
		}
		
		model.addAttribute("meetingList", meetingList);
		return "MeetingDetailINQ";
	}
	
	@GetMapping("/Meetingdetail/{id}/mod")
	public String update(@PathVariable long id, Model model) throws ParseException  {
		Meeting meeting = meetingService.findOne(id);
		model.addAttribute("meeting", meeting);
		return "MeetingDetailMOD";
	}
	
	@GetMapping(value = "/api/meetingdetail/all")
	public List<Meetingdetail> getScheduleAll() {
		List<Meetingdetail> meetingdetailList = meetingdetailService.findAll();	
		return meetingdetailList;
	}	
	
	@GetMapping(value = "/api/meetingdetail/meetingid")
	@ResponseBody
	public List<Meetingdetail> getScheduleByID(long meetingID, @SessionAttribute("LoginUser") Employee loginUser) throws JsonProcessingException {
		Meeting meeting = meetingService.findOne(meetingID);
		List<Meetingdetail> result = meeting.getMeetingdetails();
		if (!"1".equals(loginUser.getRole()))
			result = meeting.getMeetingdetails().stream()
		    .filter(detail -> detail.getEmployee().getEmpID() == loginUser.getEmpID()).collect(Collectors.toList());

		List<Meetingdetail> detailList = new ArrayList<>();
		for(Meetingdetail detail:result) {
			Meetingdetail detailObj = new Meetingdetail();
			detailObj.setId(detail.getId());
			detailObj.setEmployee(detail.getEmployee());
			detailObj.setExceptDate(detail.getExceptDate());
			detailObj.setBeginTime(detail.getBeginTime());
			detailObj.setEndTime(detail.getEndTime());
			detailObj.setRemark(detail.getRemark());
			detailList.add(detailObj);			
		}
		
		return detailList;
	}
	
	@PostMapping(value = "/api/meetingdetail/addSave")
	@ResponseBody
	public String addSave(@RequestBody Meetingdetail detail, @SessionAttribute("LoginUser") Employee loginUser) {
		Meeting meeting = meetingService.findOne(detail.getMeeting().getMeetingID());
		Employee employee = employeeService.findByEmpNo(detail.getEmployee().getEmpNo()) ;
		Meetingdetail detailObj = new Meetingdetail();
		detailObj.setMeeting(meeting);
		detailObj.setEmployee(employee);
		detailObj.setExceptDate(detail.getExceptDate());
		detailObj.setBeginTime(detail.getBeginTime());
		detailObj.setEndTime(detail.getEndTime());
		detailObj.setRemark(detail.getRemark());
		detailObj.setCreatePID(loginUser.getEmpID());
		detailObj.setCreateDate(new Date());		
		
		Meetingdetail meetingSave = meetingdetailService.save(detailObj);
		return "ok";		
	}	
	
	@PostMapping(value = "/api/meetingdetail/updateSave")
	@ResponseBody
	public Meetingdetail updateSave(@RequestBody Meetingdetail detail, @SessionAttribute("LoginUser") Employee loginUser) {
		Meetingdetail detailObj = meetingdetailService.findOne(detail.getId());
		detailObj.setExceptDate(detail.getExceptDate());
		detailObj.setBeginTime(detail.getBeginTime());
		detailObj.setEndTime(detail.getEndTime());
		detailObj.setRemark(detail.getRemark());
		detailObj.setUpdatePID(loginUser.getEmpID());
		detailObj.setUpdateDate(new Date());
		
		Meetingdetail meetingSave = meetingdetailService.save(detailObj);
		return detail;		
	}	
}
