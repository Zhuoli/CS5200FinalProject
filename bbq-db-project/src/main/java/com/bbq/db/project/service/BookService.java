package com.bbq.db.project.service;

import java.util.Date;
import java.util.List;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.PageInfo;
import com.bbq.db.project.mongodb.MongoDBManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.BookDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.User;

@Service("bookService")
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	public String insertOrUpdateBook(Book book, User user) {

        if(book.getBookId() > 0) {
            Book dbBook = bookDao.getBookById(book.getBookId());
            book.setUser(dbBook.getUser());
            book.setPublishTime(dbBook.getPublishTime());
            if(book.getUser().getUserId() != user.getUserId() && user.getUserType() != Constants.ADMIN)
                return Constants.CAN_NOT_ACCESS;
            MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserType(), "Update Book",
                                                    JSONObject.fromObject(book).toString());
            bookDao.update(book);
        } else {
            book.setUser(user);
            book.setPublishTime(new Date());
            MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserType(), "Add Book",
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
