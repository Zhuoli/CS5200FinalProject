package com.bbq.db.project.service;

import java.util.List;

import bbq.db.project.dao.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.BookDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.User;

@Service("bookService")
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	public void insertBook(Book book) {
		bookDao.insert(book);
	}
	
	public Book getBookById(Integer bookId) {
		return bookDao.getBookById(bookId);
	}
	
	public List<Book> getBookByUserId(Integer userId) {
		return bookDao.getBookByUserId(userId);
	}
	
	public void updateBook(Book book) {
		bookDao.update(book);
	}
	
	public void deleteBook (Book book) {
		bookDao.delete(book);
	}

    public List<Book> getBooksByPage(PageInfo pageInfo) {

        pageInfo.setTotalCount(this.getEffectiveBooksCount());
        return bookDao.getBooksByPage(pageInfo);
    }

    public Integer getEffectiveBooksCount() {

        return bookDao.getEffectiveBooksCount();
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
