package com.bbq.db.project.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.BookDao;
import com.bbq.db.project.dao.BookInOrderDao;
import com.bbq.db.project.dao.BookOrderDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)
public class BookInOrderDaoTest {
	@Autowired
	public BookInOrderDao bookinorderDao;
	@Autowired
	public BookOrderDao bookorderDao;
	@Autowired
	public BookDao bookDao;
	
	@Test
	public void insertBookInOrder() {		
		BookInOrder bookinorder = new BookInOrder();
		BookOrder bookorder = bookorderDao.getOrderById(2);
		Book book = bookDao.getBookById(1);
		bookinorder.setBookorder(bookorder);
		bookinorder.setBook(book);
		bookinorder.setQuantity(1);
		bookinorderDao.insert(bookinorder);
	}
	
	@Test
	public void selectAllBookInOrder() {		
		List<BookInOrder> bookinorders = bookinorderDao.getAll();
		Assert.assertTrue(bookinorders.size() > 0);
	}
	
	@Test
	public void getBookInOrderByOrderID() {	
		BookOrder bookorder = bookorderDao.getOrderById(1);
		List<BookInOrder> bookinorders = bookinorderDao.getBookInOrderByOrderID(bookorder);
		Assert.assertTrue(bookinorders.size() > 0);
	}

	@Test
	public void getBookInOrderByOrderIDandBookID() {
		BookOrder bookorder = bookorderDao.getOrderById(1);
		Book book = bookDao.getBookById(1);
		BookInOrder bookinorder = bookinorderDao.getBookInOrderByOrderIDandBookID(bookorder, book);
		Assert.assertTrue(bookinorder != null);
	}
	
	@Test
	public void updateByOrderIDandBookID() {
		BookOrder bookorder = bookorderDao.getOrderById(1);
		Book book = bookDao.getBookById(1);
		int quantity = 666;
		int updateNum = bookinorderDao.updateByOrderIDandBookID(bookorder, book, quantity);
		Assert.assertTrue(updateNum == 1);
	}
	
	@Test
	public void deleteByOrderIDandBookID() {
		BookOrder bookorder = bookorderDao.getOrderById(1);
		Book book = bookDao.getBookById(2);
		int updateNum = bookinorderDao.deleteByOrderIDandBookID(bookorder, book);
		Assert.assertTrue(updateNum == 1);
	}
}
