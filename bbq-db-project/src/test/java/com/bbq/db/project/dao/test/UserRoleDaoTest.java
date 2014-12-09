package com.bbq.db.project.dao.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.UserRoleDao;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.User;
import com.bbq.db.project.model.UserRole;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)
@Transactional
public class UserRoleDaoTest {
	@Autowired
	public UserRoleDao userRoleDao;
	
	@Test
	public void insertUserRole() {
		
	/*	UserRole userRole = new UserRole();
		userRole.setRoleName("test");
		userRole.setCreateTime(new Date());
		userRoleDao.insert(userRole);*/
	}
	
	@Test
	public void selectAllUserRole() {
		
		List<UserRole> userRoles = userRoleDao.getAll();
		Assert.assertTrue(userRoles.size() > 0);
	}
	
	@Test
	public void getUserRoleById() {
		
		UserRole userRole = userRoleDao.getUserRoleById(1);
		Assert.assertTrue(userRole != null);
	}
	
	@Test
	public void updateUserRole() {
		
		UserRole userRole  = new UserRole();
		userRole.setRoleId(2);
		userRole.setRoleName("update");
		userRole.setCreateTime(new Date());
		int effectCount = userRoleDao.update(userRole);
		Assert.assertTrue(effectCount > 0);
	}

}
