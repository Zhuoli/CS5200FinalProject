package com.bbq.db.project.service;

import java.util.List;

import com.bbq.db.project.dao.MessageDao;
import com.bbq.db.project.model.Message;
import com.bbq.db.project.model.User;

public class MessageService {
	private MessageDao messageDao = new MessageDao();
	
	public void insertMessage(Message message) {
		messageDao.insert(message);
	}
	
	public Message getMessageById(Integer messageId) {
		return messageDao.getMessageById(messageId);
	} 
	
	public List<Message> getMessageBySenderID(User user) {
		return messageDao.getMessageBySenderID(user);
	}
	
	public List<Message> getMessageByReceiverID(User user) {
		return messageDao.getMessageByReceiverID(user);
	}
	
	public void updateMessage (Message message) {
		messageDao.update(message);
	}
	
	public void deleteMessage (Message message) {
		messageDao.delete(message);
	}
}
