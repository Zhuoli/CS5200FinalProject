package com.bbq.db.project.model;

import java.util.Date;

public class BookOrder {
	private int orderId;
	private User user;
	private Address address;
	private String status;
	private Date orderTime;
	public int getOrderId() {
		return orderId;
	}
	public User getUser() {
		return user;
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
	public void setUser(User user) {
		this.user = user;
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
	public BookOrder(int orderId, User user, Address address, String status, 
			Date orderTime) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.address = address;
		this.status = status;
		this.orderTime = orderTime;
	}
	public BookOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
}
