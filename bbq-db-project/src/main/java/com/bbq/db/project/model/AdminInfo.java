package com.bbq.db.project.model;

import java.util.Date;

public class AdminInfo {
	private String adminName;
	private String password;
	private Date registerTime;
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public String getAdminName() {
		return adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public AdminInfo(String adminName, String password, Date registerTime) {
		super();
		this.adminName = adminName;
		this.password = password;
		this.registerTime = registerTime;
	}
	public AdminInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
}
