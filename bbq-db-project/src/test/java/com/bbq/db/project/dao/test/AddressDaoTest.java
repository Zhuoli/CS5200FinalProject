package com.bbq.db.project.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.AddressDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
) 
public class AddressDaoTest {
	
	@Autowired
	public AddressDao addressDao;
	@Autowired
	public UserDao userDao;

	@Test
	public void insertAddress() {
		
		Address address = new Address();
		User user = userDao.getUserById(1);
		address.setUser(user);
		address.setStreet("aaaa");
		address.setCity("aaa");
		address.setCountry("aaa");
		address.setZipcode(111);
		addressDao.insert(address);
	}

}
