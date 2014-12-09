package com.bbq.db.project.model;

import java.util.Date;

public class UserRole {
	private int roleId;
	private String roleName;
	private Date createTime;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public UserRole(int roleId, String roleName, Date createTime) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.createTime = createTime;
	}
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
