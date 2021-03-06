package com.bbq.db.project.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.dao.UserRoleDao;
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
public class UserDaoTest {
	
	@Autowired
	public UserDao userDao;
	@Autowired
	public UserRoleDao userRoleDao;
	
	@Test
	public void insertUser() {
		
/*		User user = new User();
		user.setUserName("Jack");
		user.setPassword("aswewejk");
		user.setGender(2);
		user.setEmail("jack@gmail.com");
		user.setTelephone("20688666688");
		user.setRegisterTime(new Date());
		userDao.insert(user);*/
	}
	
	@Test
	public void selectAllUser() {
		
		List<User> users = userDao.getAll();
		Assert.assertTrue(users.size() > 0);
	}
	
	@Test
	public void getUserById() {
		
		User user = userDao.getUserById(1);
		Assert.assertTrue(user != null);
	}
	
	@Test
	public void getUserByUserNameAndPassword() {
		
		User user = userDao.getUserByUserNameAndPassword("John2", null);
		Assert.assertTrue(user != null);
	}
	
	@Test
	public void updateUser() {
		
		User user = new User();
		UserRole userRole = userRoleDao.getUserRoleById(1);
		user.setUserId(3);
		user.setUserName("JohnUpdate");
		user.setPassword("asdfghjk");
        user.setUserRole(userRole);
		user.setGender(1);
		user.setEmail("johnUpdate@gmail.com");
		user.setTelephone("2068888888");
        user.setAccount(23);
		user.setRegisterTime(new Date());
		int effectCount = userDao.update(user);
		Assert.assertTrue(effectCount > 0);
	}
	
	@Test
	public void updateUserAccount() {
		
		User user = new User();
		user.setUserId(4);
        user.setAccount(55);
		int effectCount = userDao.updateUserAccount(user);
		Assert.assertTrue(effectCount > 0);
	}

    @Test
    public void getUsersByType() {
    	UserRole userRole = userRoleDao.getUserRoleById(1);
        List<User> users = userDao.getUsersByType(userRole);
        Assert.assertTrue(users.size() > 0);
    }
}
