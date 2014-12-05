package com.bbq.db.project.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.BookInOrderService;
import com.bbq.db.project.service.BookOrderService;
import com.bbq.db.project.service.BookService;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-4
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
@Namespace("/bookorder")
public class BookOrderAction extends BaseAction {

	@Autowired
    private BookOrderService bookOrderService;
	@Autowired
    private BookService bookService;
	@Autowired
    private BookInOrderService bookInOrderService;

    private Integer id;
    private BookOrder bookOrder;
    private BookInOrder bookInOrder;
    private Integer bookId;
    private Integer quantity;
    

    @Action(value = "get", results = { @Result(name = "success", location = "get.jsp") })
    public String get(){
        try {
            if(id == null){
                logger.error("error: [module:BookOrderAction][action:get][][error:{empty params}]");
            } else {
                bookOrder = bookOrderService.getOrderById(id);
            }
        } catch (Exception e) {
            logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
        }
        return SUCCESS;
    }
    
    @Action(value = "addBookToOrder")
    public String addBookToOrder(){
    	Map<String, Object> map = new HashMap<String, Object>();
        try {
        	User user = (User) ActionContext.getContext().getSession().get("user");
            if(user == null){
            	map.put("code", Constants.NO_DATA);
            } else if (bookId == null){
            		logger.error("error::module:UserAction][action:login][][error:{empty params}]");
                    map.put("code", Constants.INVALID_PARAMS);
            	}else
            	{
                Book book = bookService.getBookById(bookId);
                bookOrder = bookOrderService.getOrderByUserIDandOrderStatus(user.getUserId(), "unprocess");
                if (bookOrder == null) {
                	bookOrder.setUser(user);
                	bookOrder.setAddress(null);
                	bookOrder.setStatus("unprocess");
                	bookOrder.setOrderTime(new Date());
                	//Integer bookorderID = bookOrderService.insertBookOrder(bookOrder);
                	bookOrderService.insertBookOrder(bookOrder);
                	bookInOrder.setBook(book);
                	bookInOrder.setBookorder(bookOrder);
                	bookInOrder.setQuantity(quantity);
                	bookInOrderService.insertBookInOrder(bookInOrder);
                	map.put("code", Constants.CODE_SUCCESS);
                	}
            }
        } catch (Exception e) {
            logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
        }
        StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
        return null;
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
