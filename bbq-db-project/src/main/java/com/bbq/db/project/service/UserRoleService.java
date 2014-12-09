package com.bbq.db.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.UserRoleDao;
import com.bbq.db.project.model.UserRole;

@Service("userRoleService")
public class UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void insertUserRole(UserRole userRole) {
		userRoleDao.insert(userRole);
	}
	
	public UserRole getUserRoleById(Integer roleId) {
		return userRoleDao.getUserRoleById(roleId);
	}
	
	public void updateUserRole(UserRole userRole) {
		userRoleDao.update(userRole);
	}
	
	public void deleteUserRole(UserRole userRole) {
		userRoleDao.delete(userRole);
	}
}
