package com.bbq.db.project.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.AddressDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.User;
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
public class AddressDaoTest {
	
	@Autowired
	public AddressDao addressDao;
	@Autowired
	public UserDao userDao;

	@Test
	public void insertAddress() {
		
	/*	Address address = new Address();
		User user = userDao.getUserById(1);
		address.setUser(user);
		address.setStreet("bbbb");
		address.setCity("bbb");
		address.setCountry("bbb");
		address.setZipcode(222);
		addressDao.insert(address);*/
	}

	@Test
	public void selectAllAddress() {
		
		List<Address> addresses = addressDao.getAll();
		Assert.assertTrue(addresses.size() > 0);
	}
	
	@Test
	public void getAddressById() {
		
		Address address = addressDao.getAddressById(1);
		Assert.assertTrue(address != null);
	}
	
	@Test
	public void getAddressByUserId() {
		User user = userDao.getUserById(1);
		List<Address> addresses = addressDao.getAddressByUserId(user);
		Assert.assertTrue(addresses.size() > 0);
	}
	
	@Test
	public void updateAddress() {
		
		Address address  = new Address();
		User user = userDao.getUserById(1);
		address.setAddressId(1);
		address.setUser(user);
		address.setStreet("aaa2");
		address.setCity("aaa2");
		address.setCountry("aaa2");
		address.setZipcode(222222);
		int effectCount = addressDao.update(address);
		Assert.assertTrue(effectCount > 0);
	}
}
