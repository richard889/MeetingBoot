package com.MeetingBoot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Meetingdetail implements Serializable{

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date exceptDate;	
	private String beginTime;
	private String endTime;
	private String remark;	
	private long createPID;
	private Date createDate;
	private long updatePID;
	private Date updateDate;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "empID")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="meetingID")
	private Meeting meeting;
	
	public Meetingdetail() {
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getExceptDate() {
		return exceptDate;
	}
	public void setExceptDate(Date exceptDate) {
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Meeting getMeeting() {
		return meeting;
	}
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
}
