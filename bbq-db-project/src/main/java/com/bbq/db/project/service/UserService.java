package com.bbq.db.project.service;

import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public void insertUser(User user){
		userDao.insert(user);
	}
	
	public User getUserById(Integer userId){
		return userDao.getUserById(userId);
	}
	
	public User getUserByUserNameAndPassword(String userName, String password){
		return userDao.getUserByUserNameAndPassword(userName, password);
	}
	
	public void updateUser(User user){
		userDao.update(user);
	}
	
}
