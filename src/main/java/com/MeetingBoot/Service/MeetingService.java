package com.MeetingBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingBoot.entity.Meeting;

@Service
public class MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;

	public List<Meeting> findAll(){		
		return meetingRepository.findAll();
	}
	
	public Meeting findOne(long meetingID) {
		return meetingRepository.findById(meetingID).get();
	}
	
	public Meeting save(Meeting meeting) {
		return meetingRepository.save(meeting);
	}	

	public void delete(long meetingID) {
		meetingRepository.deleteById(meetingID);
	}
	
}
