package com.bbq.db.project.dao.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.BookCategoryDao;
import com.bbq.db.project.model.BookCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)
public class BookCategoryDaoTest {
	@Autowired
	public BookCategoryDao bookCategoryDao;
	
	@Test
	public void insertBookCategory() {		
		BookCategory bookCategory = new BookCategory();
		bookCategory.setCategoryName("test");
		bookCategory.setDescription("test");
		bookCategoryDao.insert(bookCategory);
	}
	
	@Test
	public void selectAllBookCategory() {		
		List<BookCategory> bookeCategorys = bookCategoryDao.getAll();
		Assert.assertTrue(bookeCategorys.size() > 0);
	}
	
	@Test
	public void getCategoryById() {
		BookCategory bookCategory = bookCategoryDao.getCategoryById(1);
		Assert.assertTrue(bookCategory != null);
	}
	
	@Test
	public void updateBookCategory() {
		
		BookCategory bookCategory  = new BookCategory();
		bookCategory.setCategoryId(1);
		bookCategory.setCategoryName("update");
		bookCategory.setDescription("update");
		int effectCount = bookCategoryDao.update(bookCategory);
		Assert.assertTrue(effectCount > 0);
	}

}
