package com.bbq.db.project.action;

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

    @Action(value = "listMyBooks", results = { @Result(name = "success", location = "listMyBooks.jsp") })
    public String listMyBooks(){
        try {

        } catch (Exception e) {
            logger.error("error: [module:BookAction][action:listMyBooks][][error:{}]", e);
        }
        return SUCCESS;
    }

}
