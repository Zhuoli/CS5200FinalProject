package com.bbq.db.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.BookInOrderDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;

@Service("bookInOrderService")
public class BookInOrderService {
	@Autowired
	private BookInOrderDao bookinorderDao;
	
	public void insertBookInOrder(BookInOrder bookinorder) {
		bookinorderDao.insert(bookinorder);
	}
	
	public List<BookInOrder> getBookInOrderByOrderID(BookOrder bookorder) {
		return bookinorderDao.getBookInOrderByOrderID(bookorder);
	}
	
	public BookInOrder getBookInOrderByOrderIDandBookID(BookOrder bookorder, Book book) {
		return bookinorderDao.getBookInOrderByOrderIDandBookID(bookorder, book);
	}
	
	public void updateByOrderIDandBookID(BookOrder bookorder, Book book, int quantity) {
		bookinorderDao.updateByOrderIDandBookID(bookorder, book, quantity);
	}
	
	public void deleteByOrderIDandBookID(BookOrder bookorder, Book book) {
		bookinorderDao.deleteByOrderIDandBookID(bookorder, book);
	}
	
}
