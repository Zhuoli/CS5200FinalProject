package com.bbq.db.project.service;

import java.util.List;

import com.bbq.db.project.dao.BookOrderDao;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookOrderService")
public class BookOrderService {

    @Autowired
	private BookOrderDao bookorderDao;
	
	public int insertBookOrder(BookOrder bookorder) {
		return (Integer) bookorderDao.insert(bookorder);
	}
	
	public BookOrder getOrderById(Integer orderId) {
		return bookorderDao.getOrderById(orderId);
	}
	
	public List<BookOrder> getOrderByUserId(Integer userId) {
		return bookorderDao.getOrderByUserId(userId);
	}
	
	public void updateBookOrder(BookOrder bookorder) {
		bookorderDao.update(bookorder);
	}
	
	public BookOrder getOrderByUserIDandOrderStatus(Integer userId, String status) {
		return bookorderDao.getOrderByUserIDandOrderStatus(userId, status);
	}

    public BookOrderDao getBookorderDao() {
        return bookorderDao;
    }

    public void setBookorderDao(BookOrderDao bookorderDao) {
        this.bookorderDao = bookorderDao;
    }
}
