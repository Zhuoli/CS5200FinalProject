package com.bbq.db.project.model;

import java.sql.Date;

public class Order {
	private int orderId;
	private User userId;
	private Address address;
	private String status;
	private Date orderTime;
	public int getOrderId() {
		return orderId;
	}
	public User getUserId() {
		return userId;
	}
	public Address getAddress() {
		return address;
	}
	public String getStatus() {
		return status;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Order(int orderId, User userId, Address address, String status,
			Date orderTime) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.address = address;
		this.status = status;
		this.orderTime = orderTime;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
