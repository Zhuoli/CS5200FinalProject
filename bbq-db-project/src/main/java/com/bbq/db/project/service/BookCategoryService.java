package com.bbq.db.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.BookCategoryDao;
import com.bbq.db.project.model.BookCategory;


@Service("bookCategoryService")
public class BookCategoryService {
	@Autowired
	private BookCategoryDao bookCategoryDao;
	
	public BookCategory getCategoryById(Integer categoryId) {
		return bookCategoryDao.getCategoryById(categoryId);
	}
	
	public List<BookCategory> getAllCategory() {
		return bookCategoryDao.getAll();
	}
	
	public void updateCategory (BookCategory bookcategory){
		bookCategoryDao.update(bookcategory);
	}
	
	public void deleteCategory (BookCategory bookcategory){
		bookCategoryDao.delete(bookcategory);
	}
}
