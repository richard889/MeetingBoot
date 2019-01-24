package com.MeetingBoot.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingBoot.entity.Meeting;
import com.MeetingBoot.entity.Meetingcount;
import com.MeetingBoot.entity.Meetingdetail;

@Service
public class MeetingdetailService {

	@Autowired
	private MeetingService meetingService;	
	@Autowired
	private MeetingdetailRepository meetingdetailRepository;

	public List<Meetingdetail> findAll(){		
		return meetingdetailRepository.findAll();
	}
	
	public Meetingdetail findOne(long id) {
		return meetingdetailRepository.findById(id).get();
	}

	public Meetingdetail save(Meetingdetail meetingdetail) {
		return meetingdetailRepository.save(meetingdetail);
	}	

	public void delete(long id) {
		meetingdetailRepository.deleteById(id);
	}
	
	public List<Meetingdetail> findByMeetingdetail(long meetingID, long empID){		
		List<Meetingdetail> list = meetingdetailRepository.findByMeetingdetail(meetingID, empID);
		return list;
	}
	
	public List<Meetingcount> findByMeetingcount(){		
		List<Object[]> result = meetingdetailRepository.findByMeetingcount();		
		List<Meetingcount> list = new ArrayList<Meetingcount>();

		result.forEach(item->{
			try {			
				Meetingcount meetingcount = new Meetingcount();
				meetingcount.setMeetingID(item[0].toString());		
				String exceptdate = new SimpleDateFormat("yyyy/MM/dd").format(item[1]);
				meetingcount.setExceptDate(exceptdate);
				meetingcount.setBeginTime(item[2].toString());
				meetingcount.setEndTime(item[3].toString());
				meetingcount.setAllowNum(Integer.valueOf(item[4].toString()));
				
				long value = Long.valueOf(item[4].toString());				
				Meeting meeting = meetingService.findOne(value);
				meetingcount.setMeeting(meeting);
				
				list.add(meetingcount);
			}catch(Exception ex) {
				
			}			
		});		
		
		return list;
	}	
	
}
