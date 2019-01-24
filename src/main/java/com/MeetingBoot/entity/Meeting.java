package com.MeetingBoot.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Meeting implements Serializable{

	@Id
	@Column(name = "meetingID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO )
	private long meetingID;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date beginDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date endDate;
	private String participant;
	private String discussItem;
	private long createPID;
	//@CreatedDate
	private Date createDate;
	private long updatePID;
	//@LastModifiedDate
	private Date updateDate;
		
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "hostID")
	private Employee host;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "roomID")
	private Room room;

	@OneToMany(mappedBy="meeting", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})	
	private List<Meetingdetail> meetingdetails = new ArrayList<>();

	public void addMeetingdetail(Meetingdetail meetingdetail) {
		meetingdetail.setMeeting(this);
		meetingdetails.add(meetingdetail);
	}
	
	public Meeting() {
    }	
	public long getMeetingID() {
		return meetingID;
	}
	public void setMeetingID(long meetingID) {
		this.meetingID = meetingID;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}	
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public String getDiscussItem() {
		return discussItem;
	}
	public void setDiscussItem(String discussItem) {
		this.discussItem = discussItem;
	}
	public String getParticipantView() {		
		StringBuilder sb = new StringBuilder();
		String[] tokens = participant.split(";");
		for (String token:tokens) {
			if(!token.trim().equals("")){
				String[] emplist = token.split(" ");
				String emp = emplist[0].trim() + " " + emplist[1].trim();
				sb.append(emp);
				sb.append("\r\n");
            }
		}
		
		return sb.toString();
	}
	public long getCreatePID() {
		return createPID;
	}
	public void setCreatePID(long createPID) {
		this.createPID = createPID;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getUpdatePID() {
		return updatePID;
	}
	public void setUpdatePID(long updatePID) {
		this.updatePID = updatePID;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    public Employee getHost() {
		return host;
	}
	public void setHost(Employee host) {
		this.host = host;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Meetingdetail> getMeetingdetails() {
		return meetingdetails;
	}
	public void setMeetingdetails(List<Meetingdetail> meetingdetails) {
		this.meetingdetails = meetingdetails;
	}
	
}
