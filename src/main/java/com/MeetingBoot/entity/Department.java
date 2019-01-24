package com.MeetingBoot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {

	@Id
	@Column(name = "deptID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long deptID;
	private String deptName;
	
    public Department() {
    }	
	public long getDeptID() {
		return deptID;
	}
	public void setDeptID(long depID) {
		this.deptID = depID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
