package com.bbq.db.project.model;

import java.sql.Date;

public class Message {
	private int messageId;
	private User sender;
	private User receiver;
	private String title;
	private String content;
	private Date time;
	public int getMessageId() {
		return messageId;
	}
	public User getSender() {
		return sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Date getTime() {
		return time;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Message(int messageId, User sender, User receiver, String title,
			String content, Date time) {
		super();
		this.messageId = messageId;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.content = content;
		this.time = time;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
