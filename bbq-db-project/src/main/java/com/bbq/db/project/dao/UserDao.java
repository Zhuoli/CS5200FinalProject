package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.User;

@Service("userDao")
public class UserDao extends BatisEntityDao<User>{

	public User getUserById(Integer userId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("userId", userId);
		return super.getSqlSession().selectOne("User.getUserById", sqlMap);
	}
	
	public User getUserByUserNameAndPassword(String userName, String password) {
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("userName", userName);
		sqlMap.put("password", password);
		return super.getSqlSession().selectOne("User.getUserByUserNameAndPassword", sqlMap);
	}
}
