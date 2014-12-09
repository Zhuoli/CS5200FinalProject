package com.bbq.db.project.dao.test;


import java.util.Date;
import java.util.List;

import bbq.db.project.dao.utils.PageInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.BookCategoryDao;
import com.bbq.db.project.dao.BookDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookCategory;
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
public class BookDaoTest {
	
	@Autowired
	public BookDao bookDao;
	@Autowired
	public UserDao userDao;
	@Autowired
	public BookCategoryDao bookCategoryDao;
	
	@Test
	public void insertBook() {		
		Book book = new Book();
		User user = userDao.getUserById(2);
		book.setUser(user);
		book.setTitle("testcat");
		book.setAuthor("testcat");
		book.setIsbn("testcat");
		book.setQuantity(4);
		book.setPrice(6600);
		book.setPublisher("testcat");
        book.setPic("testcat");
		book.setPublishTime(new Date());
		BookCategory bookcategory =  bookCategoryDao.getCategoryById(1);
		book.setCategory(bookcategory);
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
        Assert.assertTrue(book.getUser() != null);
	}
	
	@Test
	public void getBookByUserId() {
		List<Book> bookes = bookDao.getBookByUserId(3);
		Assert.assertTrue(bookes.size() > 0);
	}
	
	@Test
	public void updateBook() {
		
		Book book  = new Book();
		BookCategory bookcategory =  bookCategoryDao.getCategoryById(1);
		book.setBookId(2);
		book.setTitle("bbbupdate");
		book.setAuthor("bbbupdate");
		book.setIsbn("bbbupdate");
		book.setQuantity(4);
		book.setPrice(6600);
		book.setPublisher("bbb");
		book.setPublishTime(new Date());
		book.setPic("aaa");
		book.setCategory(bookcategory);
		int effectCount = bookDao.update(book);
		Assert.assertTrue(effectCount > 0);
	}
	
	
	@Test
	public void updateBookQuantity() {
		
		Book book  = new Book();
		book.setBookId(1);
		book.setQuantity(10);
		int effectCount = bookDao.updateQuantity(book);
		Assert.assertTrue(effectCount > 0);
	}
	

    @Test
    public void getEffectiveBooksCount() {

        int effectCount = bookDao.getEffectiveBooksCount();
        Assert.assertTrue(effectCount > 0);
    }
    

    @Test
    public void getBooksByPage() {

        PageInfo pageInfo = new PageInfo();
        List<Book> books = bookDao.getBooksByPage(pageInfo);
        Assert.assertTrue(books.size() > 0);
    }

}
