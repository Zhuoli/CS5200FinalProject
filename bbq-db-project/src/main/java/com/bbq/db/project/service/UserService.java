package com.bbq.db.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.User;
import com.bbq.db.project.model.UserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void insertUser(User user){
		userDao.insert(user);
	}
	
	public User getUserById(Integer userId){
		return userDao.getUserById(userId);
	}
	
	public User getUserByUserNameAndPassword(String userName, String password){
		return userDao.getUserByUserNameAndPassword(userName, password);
	}

    public List<User> getUsersByType(UserRole type){
        return userDao.getUsersByType(type);
    }
	
	public void updateUserAccount(User user) {
		userDao.updateUserAccount(user);
	}
	
	public void updateUser(User user){
		userDao.update(user);
	}

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
