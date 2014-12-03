package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.Message;
import com.bbq.db.project.model.User;


@Service("messageDao")
public class MessageDao extends BatisEntityDao<Message>{
	public Message getMessageById(Integer messageId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("messageId", messageId);
		return super.getSqlSession().selectOne("Message.getMessageById", sqlMap);
	}
	
	public List<Message> getMessageBySenderID(User user) {
		Map<String, User> sqlMap = new HashMap<String, User>();
		sqlMap.put("user", user);
		return super.getSqlSession().selectList("Message.getMessageBySenderID", sqlMap);
	}
	
	public List<Message> getMessageByReceiverID(User user) {
		Map<String, User> sqlMap = new HashMap<String, User>();
		sqlMap.put("user", user);
		return super.getSqlSession().selectList("Message.getMessageByReceiverID", sqlMap);
	}
	
	
}
