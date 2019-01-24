package com.MeetingBoot.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Meetingcount {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private String meetingID;
	private String exceptDate;	
	private String beginTime;
	private String endTime;
	private int allowNum;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "meetingID")
	private Meeting meeting;

	public Meetingcount() {
    }
	public String getMeetingID() {
		return meetingID;
	}
	public void setMeetingID(String meetingID) {
		this.meetingID = meetingID;
	}
	public String getExceptDate() {
		return exceptDate;
	}
	public void setExceptDate(String exceptDate) {
		this.exceptDate = exceptDate;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getAllowNum() {
		return allowNum;
	}
	public void setAllowNum(int allowNum) {
		this.allowNum = allowNum;
	}
	public Meeting getMeeting() {
		return meeting;
	}
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
}
