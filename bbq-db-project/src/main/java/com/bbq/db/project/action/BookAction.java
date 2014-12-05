package com.bbq.db.project.action;

import com.bbq.db.project.model.Book;
import com.bbq.db.project.service.BookService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-4
 * Time: 上午12:01
 * To change this template use File | Settings | File Templates.
 */
@Namespace("/book")
public class BookAction extends BaseAction {

	@Autowired
    private BookService bookService;

    private int bookId;
    private Book book;

    @Action(value = "viewBook", results = { @Result(name = "success", location = "viewBook.jsp") })
    public String viewBook(){
        try {
            book = bookService.getBookById(bookId);
        } catch (Exception e) {
            logger.error("error: [module:BookAction][action:listMyBooks][][error:{}]", e);
        }

        return SUCCESS;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
