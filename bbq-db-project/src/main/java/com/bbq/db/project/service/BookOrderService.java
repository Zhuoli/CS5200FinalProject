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

    public BookOrderDao getBookorderDao() {
        return bookorderDao;
    }

    public void setBookorderDao(BookOrderDao bookorderDao) {
        this.bookorderDao = bookorderDao;
    }
}
