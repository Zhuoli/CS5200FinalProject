package com.bbq.db.project.dao.test;


import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.AddressDao;
import com.bbq.db.project.dao.BookOrderDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)

public class BookOrderDaoTest {

	@Autowired
	public BookOrderDao bookorderDao;
	@Autowired
	public UserDao userDao;
	@Autowired
	public AddressDao addressDao;
	
	@Test
	public void insertOrder() {		
		BookOrder bookorder = new BookOrder();
		User user = userDao.getUserById(1);
		Address address = addressDao.getAddressById(3);
		bookorder.setUser(user);
		bookorder.setAddress(address);
		bookorder.setStatus("bbbb");
		bookorder.setOrderTime(new Date());
		bookorderDao.insert(bookorder);
	}

	@Test
	public void selectAllOrder() {		
		List<BookOrder> bookorders = bookorderDao.getAll();
		Assert.assertTrue(bookorders.size() > 0);
	}
	
	@Test
	public void getOrderById() {		
		BookOrder bookorder = bookorderDao.getOrderById(1);
		Assert.assertTrue(bookorder != null);
	}
	
	@Test
	public void getOrderByUserId() {
		User user = userDao.getUserById(1);
		List<BookOrder> bookorders = bookorderDao.getOrderByUserId(user);
		Assert.assertTrue(bookorders.size() > 0);
	}
	
	@Test
	public void updateBookOrder() {
		BookOrder bookorder = new BookOrder();
		bookorder.setOrderId(1);
		bookorder.setStatus("update");
		int effectCount = bookorderDao.update(bookorder);
		Assert.assertTrue(effectCount > 0);
	}
}
