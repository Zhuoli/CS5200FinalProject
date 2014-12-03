package com.bbq.db.project.dao.test;


import java.util.Date;

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
		Address address = addressDao.getAddressById(1);
		bookorder.setUser(user);
		bookorder.setAddress(address);
		bookorder.setStatus("delivered");
		bookorder.setOrderTime(new Date());
		bookorderDao.insert(bookorder);
	}

}
