package com.bbq.db.project.dao.test;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.AddressDao;
import com.bbq.db.project.dao.OrderDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.Order;
import com.bbq.db.project.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)

public class OrderDaoTest {

	@Autowired
	public OrderDao orderDao;
	@Autowired
	public UserDao userDao;
	@Autowired
	public AddressDao addressDao;
	
	@Test
	public void insertOrder() {		
		Order order = new Order();
		User user = userDao.getUserById(1);
		Address address = addressDao.getAddressById(1);
		order.setUser(user);
		order.setAddress(address);
		order.setStatus("delivered");
		order.setOrderTime(new Date());
		orderDao.insert(order);
	}

}
