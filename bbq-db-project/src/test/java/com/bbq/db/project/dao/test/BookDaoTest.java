package com.bbq.db.project.dao.test;


import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.BookDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)
public class BookDaoTest {
	
	@Autowired
	public BookDao bookDao;
	@Autowired
	public UserDao userDao;
	
	@Test
	public void insertBook() {		
		Book book = new Book();
		User user = userDao.getUserById(2);
		book.setUser(user);
		book.setTitle("bbb");
		book.setAuthor("bbb");
		book.setIsbn("bbb");
		book.setQuantity(4);
		book.setPrice(66.7);
		book.setPublisher("bbb");
		book.setPublishTime(new Date());
		bookDao.insert(book);
	}
	
	@Test
	public void selectAllBook() {
		
		List<Book> bookes = bookDao.getAll();
		Assert.assertTrue(bookes.size() > 0);
	}
	
	@Test
	public void getBookById() {
		
		Book book = bookDao.getBookById(1);
		Assert.assertTrue(book != null);
	}
	
	@Test
	public void getBookByUserId() {
		User user = userDao.getUserById(1);
		List<Book> bookes = bookDao.getBookByUserId(user);
		Assert.assertTrue(bookes.size() > 0);
	}
	
	@Test
	public void updateBook() {
		
		Book book  = new Book();
		book.setBookId(2);
		book.setTitle("bbbupdate");
		book.setAuthor("bbbupdate");
		book.setIsbn("bbbupdate");
		book.setQuantity(4);
		book.setPrice(66.7);
		book.setPublisher("bbb");
		book.setPublishTime(new Date());
		int effectCount = bookDao.update(book);
		Assert.assertTrue(effectCount > 0);
	}

}
