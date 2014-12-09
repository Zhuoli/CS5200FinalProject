package com.bbq.db.project.service;

import java.util.Date;
import java.util.List;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.PageInfo;

import com.bbq.db.project.mongodb.MongoDBManager;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.BookCategoryDao;
import com.bbq.db.project.dao.BookDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookCategory;
import com.bbq.db.project.model.User;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
@Transactional
public class BookService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookCategoryDao bookCategoryDao;
	
	public String insertOrUpdateBook(Book book, User user, int categoryId) {
        BookCategory bookcategory = bookCategoryDao.getCategoryById(categoryId);
        book.setCategory(bookcategory);
        if(book.getBookId() > 0) {
            Book dbBook = bookDao.getBookById(book.getBookId());
            book.setUser(dbBook.getUser());
            book.setPublishTime(dbBook.getPublishTime());
            if(book.getUser().getUserId() != user.getUserId() && user.getUserRole().getRoleId() != Constants.ADMIN)
                return Constants.CAN_NOT_ACCESS;
            MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserRole().getRoleId(), "Update Book",
                                                    JSONObject.fromObject(book).toString());
            bookDao.update(book);
        } else {
            book.setUser(user);
            book.setPublishTime(new Date());
            MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserRole().getRoleId(), "Add Book",
                                                    JSONObject.fromObject(book).toString());
            bookDao.insert(book);
        }

        return Constants.CODE_SUCCESS;
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
