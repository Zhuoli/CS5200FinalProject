package com.bbq.db.project.dao.test;


import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.MessageDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Message;
import com.bbq.db.project.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)
public class MessageDaoTest {
	@Autowired
	public MessageDao messageDao;
	@Autowired
	public UserDao userDao;
	
	@Test
	public void insertMessage() {		
		Message message = new Message();
		User sender = userDao.getUserById(2);
		User receiver = userDao.getUserById(1);
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setTitle("bbbb");
		message.setContent("bbbbbbbbbbb");
		message.setTime(new Date());
		messageDao.insert(message);
	}
	
	@Test
	public void selectAllMessage() {		
		List<Message> messages = messageDao.getAll();
		Assert.assertTrue(messages.size() > 0);
	}
	
	@Test
	public void getMessageById() {		
		Message message = messageDao.getMessageById(1);
		Assert.assertTrue(message != null);
	}
	
	@Test
	public void getMessageBySenderId() {
		User user = userDao.getUserById(1);
		List<Message> messages = messageDao.getMessageBySenderID(user);
		Assert.assertTrue(messages.size() > 0);
	}
	
	@Test
	public void getMessageByReceiverId() {
		User user = userDao.getUserById(2);
		List<Message> messages = messageDao.getMessageByReceiverID(user);
		Assert.assertTrue(messages.size() > 0);
	}
	
	@Test
	public void updateMessage() {
		Message message = new Message();
		User user = userDao.getUserById(2);
		message.setMessageId(1);
		message.setReceiver(user);
		message.setTitle("updatetitle");
		message.setContent("updatecontent");
		message.setTime(new Date());
		int effectCount = messageDao.update(message);
		Assert.assertTrue(effectCount > 0);
	}
	
	@Test
	public void deleteMessage() {
		Message message = new Message();
		message.setMessageId(2);
		int effectCount = messageDao.delete(message);
		Assert.assertTrue(effectCount > 0);
	}

}
