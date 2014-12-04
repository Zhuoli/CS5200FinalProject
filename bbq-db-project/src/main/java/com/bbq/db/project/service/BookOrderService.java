package com.bbq.db.project.service;

import java.util.List;

import com.bbq.db.project.dao.BookOrderDao;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;

public class BookOrderService {
	private BookOrderDao bookorderDao = new BookOrderDao();
	
	public void insertBookOrder(BookOrder bookorder) {
		bookorderDao.insert(bookorder);
	}
	
	public BookOrder getOrderById(Integer orderId) {
		return bookorderDao.getOrderById(orderId);
	}
	
	public List<BookOrder> getOrderByUserId(User user) {
		return bookorderDao.getOrderByUserId(user);
	}
	
	public void updateBookOrder(BookOrder bookorder) {
		bookorderDao.update(bookorder);
	}
}
