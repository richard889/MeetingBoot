package com.MeetingBoot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MeetingBoot.Service.EmployeeService;
import com.MeetingBoot.Service.MeetingService;
import com.MeetingBoot.Service.MeetingdetailService;
import com.MeetingBoot.Service.RoomService;
import com.MeetingBoot.entity.Employee;
import com.MeetingBoot.entity.Meeting;
import com.MeetingBoot.entity.Meetingdetail;
import com.MeetingBoot.entity.Room;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MeetingController {

	@Autowired
	private MeetingService meetingService;
	@Autowired
	private RoomService roomService;	
	@Autowired
	private MeetingdetailService meetingdetailService;
	@Autowired
	private EmployeeService employeeService;	

	@GetMapping("/Meeting")
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
		return "MeetingINQ";
	}

	@GetMapping("/Meeting/add")
	public String add(Model model) {		
		List<Room> roomList = roomService.findAll();
		model.addAttribute("roomList", roomList);		
		List<Employee> hostList = employeeService.findAll();
		model.addAttribute("hostList", hostList);
		
		Meeting meeting = new Meeting();
		meeting.setBeginDate(new Date());
		meeting.setEndDate(new Date());
		model.addAttribute("meeting", meeting);
		return "MeetingADD";
	}

	@GetMapping("/Meeting/{id}/mod")
	public String update(@PathVariable long id, Model model) throws ParseException, JsonMappingException, IOException  {
		List<Room> roomList = roomService.findAll();
		model.addAttribute("roomList", roomList);
		List<Employee> hostList = employeeService.findAll();
		model.addAttribute("hostList", hostList);
		
		Meeting meeting = meetingService.findOne(id);
//		ObjectMapper mapper = new ObjectMapper();
//	    JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Employee.class);
//	    List<Employee> list = mapper.readValue(meeting.getParticipant(), type);
//	    String msg = "";
//	    for (Employee employee : list) {
//	    	msg += employee.getEmpNo() +" " + employee.getName() +" " + employee.getEmail() + ";\n" ;
//		}
	    
	    meeting.setParticipant(meeting.getParticipant() + "\n");
		model.addAttribute("meeting", meeting);
		return "MeetingMOD";
	}

	@PostMapping("/Meeting/addSave")
	public String addSave(Meeting meeting, BindingResult result, Model model, final RedirectAttributes attributes, @SessionAttribute("LoginUser") Employee loginUser) {
		if(result.hasErrors()) {		
			List<Room> roomList = roomService.findAll();
			model.addAttribute("roomList", roomList);	
			List<Employee> hostList = employeeService.findAll();
			model.addAttribute("hostList", hostList);
			
			return "MeetingADD";
		}
		
		meeting.setCreatePID(loginUser.getEmpID());
		meeting.setCreateDate(new Date());
		
		//同時新增至參與者通知		
		List<Employee> empList = toParticipant(meeting.getParticipant());
		for(Employee employee : empList) {
			for(int i=0;i<=2;i++) {
				Meetingdetail detailObj = new Meetingdetail();
				detailObj.setEmployee(employee); 
				detailObj.setCreatePID(loginUser.getEmpID());
				detailObj.setCreateDate(new Date());
				meeting.addMeetingdetail(detailObj);				
			}
		}
		
		Meeting meetingSave = meetingService.save(meeting);
		return "redirect:/Meeting";	
	}
	
	@PostMapping("/Meeting/updateSave")
	public String updateSave(Meeting meeting, BindingResult result, Model model, final RedirectAttributes attributes, @SessionAttribute("LoginUser") Employee loginUser) {
		if(result.hasErrors()) {
			List<Room> roomList = roomService.findAll();
			model.addAttribute("roomList", roomList);	
			List<Employee> hostList = employeeService.findAll();
			model.addAttribute("hostList", hostList);
			
			attributes.addFlashAttribute("meeting", meeting);
			return "MeetingMOD";
		}
		
		Meeting meetingObj = meetingService.findOne(meeting.getMeetingID());
		meeting.setCreatePID(meetingObj.getCreatePID());
		meeting.setCreateDate(meetingObj.getCreateDate());
		meeting.setUpdatePID(loginUser.getEmpID());
		meeting.setUpdateDate(new Date());
						
		//同時新增至參與者通知		
		List<Employee> empList = toParticipant(meeting.getParticipant());
		for(Employee employee : empList) {
			int num = meetingdetailService.findByMeetingdetail(meeting.getMeetingID(), employee.getEmpID()).size();
			if (num == 0) {
				//新加入
				for(int i=0;i<=2;i++) {
					Meetingdetail detailObj = new Meetingdetail();
					detailObj.setEmployee(employee); 
					detailObj.setCreatePID(loginUser.getEmpID());
					detailObj.setCreateDate(new Date());
					meeting.addMeetingdetail(detailObj);				
				}				
			}
		}	
				
		Meeting meetingSave = meetingService.save(meeting);
 		return "redirect:/Meeting";
	}
	
	@GetMapping("/Meeting/{id}/delete")
	public String delete(@PathVariable long id, final RedirectAttributes attributes ) {
		meetingService.delete(id);
		return "redirect:/Meeting";
	}
	
	@GetMapping("/Meetingdetail/{id}")
    public String MeetingDetail(@PathVariable long id, Model model) throws ParseException{
		Meeting meeting = meetingService.findOne(id);
		model.addAttribute("meeting", meeting);		
        return "MeetingDetail";
    }
	
	@GetMapping("/JsGrid")
    public String displayGrid(){
        return "JsGrid";
    }
	
	public String convetJson(String participant) {
		StringBuffer sb = new StringBuffer();
		String[] tokens = participant.split(";");
		for (String token:tokens) {
			if(!token.trim().equals("")){
				String[] emplist = token.split(" ");
				String json =  ",{\"empNo\":\""+emplist[0].trim()+"\",\"name\":\""+emplist[1].trim()+"\",\"email\":\""+emplist[2].trim()+"\"}";
				sb.append(json);
            }
		}
		
		if(sb.length()>0){		
			sb.replace(0, 1, "[");	
			sb.append("]");
		}
		
		return sb.toString();
	}
	
	public List<Employee> toParticipant(String participant) {
		List<Employee> list = new ArrayList<Employee>();
		String[] tokens = participant.split(";");
		for (String token:tokens) {
			if(!token.trim().equals("")){
				String[] emplist = token.split(" ");
				String empNo = emplist[0].trim();
				Employee employee = employeeService.findByEmpNo(empNo);
				list.add(employee);
            }
		}		
		
		return list;	
	}
	
}
