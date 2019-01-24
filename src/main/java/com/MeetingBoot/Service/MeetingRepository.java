package com.MeetingBoot.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MeetingBoot.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long>{
	
}
