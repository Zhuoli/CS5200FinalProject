package com.bbq.db.project.model;

public class Admin {
	private String adminName;
	private String password;
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
	public Admin(String adminName, String password) {
		super();
		this.adminName = adminName;
		this.password = password;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
