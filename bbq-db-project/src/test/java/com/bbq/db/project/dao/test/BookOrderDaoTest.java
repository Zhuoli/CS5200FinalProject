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
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookOrder;
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
public class BookOrderDaoTest {

	@Autowired
	public BookOrderDao bookOrderDao;
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
		bookorder.setOrderStatus("bbbb");
		bookorder.setOrderTime(new Date());
		bookOrderDao.insert(bookorder);
        Assert.assertTrue(bookorder.getOrderId() > 0);
	}

	@Test
	public void selectAllOrder() {		
		List<BookOrder> bookorders = bookOrderDao.getAll();
		Assert.assertTrue(bookorders.size() > 0);
	}
	
	@Test
	public void getOrderById() {		
		BookOrder bookOrder = bookOrderDao.getOrderById(1);
		Assert.assertTrue(bookOrder != null);
	}
	
	@Test
	public void getOrderByUserId() {
		List<BookOrder> bookOrders = bookOrderDao.getOrderByUserId(1);
		Assert.assertTrue(bookOrders.size() > 0);
	}
	

    @Test
	public void getOrderByUserIDandOrderStatus() {
		
    	BookOrder bookOrder = bookOrderDao.getOrderByUserIDandOrderStatus(2, "unprocess");
    	Assert.assertTrue(bookOrder != null);
	}

	
	@Test
	public void updateBookOrder() {
		BookOrder bookorder = bookOrderDao.getOrderById(1);
		BookOrder neworder =  new BookOrder();
		Address address = addressDao.getAddressById(3);
		neworder.setOrderStatus("test11");
		neworder.setOrderId(bookorder.getOrderId());
		neworder.setAddress(address);
		int effectCount = bookOrderDao.update(neworder);
		Assert.assertTrue(effectCount > 0);
	}
}
