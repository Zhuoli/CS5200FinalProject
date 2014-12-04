package com.bbq.db.project.action;

import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.service.BookOrderService;
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
@Namespace("/bookorder")
public class BookOrderAction extends BaseAction {

	@Autowired
    private BookOrderService bookOrderService;

    private Integer id;
    private BookOrder bookOrder;

    @Action(value = "get", results = { @Result(name = "success", location = "get.jsp") })
    public String get(){
        try {
            if(id == null){
                logger.error("error：[module:BookOrderAction][action:get][][error:{empty params}]");
            } else {
                bookOrder = bookOrderService.getOrderById(id);
            }
        } catch (Exception e) {
            logger.error("error：[module:BookOrderAction][action:get][][error:{}]", e);
        }
        return SUCCESS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookOrder getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
    }

    public BookOrderService getBookOrderService() {
        return bookOrderService;
    }

    public void setBookOrderService(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }
}
