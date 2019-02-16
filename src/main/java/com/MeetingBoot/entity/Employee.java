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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Employee {

	@Id
	@Column(name = "empID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long empID;
	
	@NotBlank(message="員工代號不得空白")
	private String empNo;  //應該要可以自動產生
	@Length(min=6, max=10, message="密碼至少6 ~ 10位")
	private String password;
	@NotBlank(message="姓名不得空白")
	private String name;
	@NotBlank(message="性別不得空白")
	private String sex;
	@NotBlank(message="所屬角色不得空白")
	private String role;
	@NotBlank(message="電話不得空白")
	private String phone;
	@NotBlank(message="地址不得空白")
	private String address;
	@NotBlank(message="Email 不得空白")
	@Email(message="Email 格式有錯")
	private String email;
	private long createPID;
	private Date createDate;
	private long updatePID;
	private Date updateDate;
	
	@OneToOne
	@JoinColumn(name = "deptID")
	private Department dept;
	
	public Employee() {
    }	
	public long getEmpID() {
		return empID;
	}
	public void setEmpID(long empID) {
		this.empID = empID;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}

}
