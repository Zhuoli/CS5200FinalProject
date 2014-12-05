package com.bbq.db.project.action;

import bbq.db.project.dao.utils.PageInfo;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.service.BookService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IndexAction extends BaseAction {

	@Autowired
    private BookService bookService;

    private PageInfo pageInfo = new PageInfo();
    private List<Book> books;

    @Action(value = "index", results = { @Result(name = "success", location = "index.jsp") })
    public String index(){
        try {
            books = bookService.getBooksByPage(pageInfo);
            logger.info(books.size() + "");
        } catch (Exception e) {
            logger.error("error: [module:IndexAction][action:index][][error:{}]", e);
        }
        return SUCCESS;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
