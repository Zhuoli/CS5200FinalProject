package com.bbq.db.project.model;

import java.util.Date;

public class User {
	private int userId;
	private String userName;
	private String password;
	private Integer gender;
	private String email;
	private String telephone;
	private Date registerTime;
    private int account;   //the money user holds in account unit is cent
	public int getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public Integer getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public String getTelephone() {
		return telephone;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public User(int userId, String userName, String password, Integer gender,
			String email, String telephone, Integer account, Date registerTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.email = email;
		this.telephone = telephone;
        this.account = account;
		this.registerTime = registerTime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
