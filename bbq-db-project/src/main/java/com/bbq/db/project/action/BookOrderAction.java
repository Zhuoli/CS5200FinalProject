package com.bbq.db.project.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.BookInOrderService;
import com.bbq.db.project.service.BookOrderService;
import com.bbq.db.project.service.BookService;
import com.bbq.db.project.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.sun.net.httpserver.HttpContext;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Autowired
    private UserService userService;

    private Integer id;
    private BookOrder bookOrder;
    private BookInOrder bookInOrder;
    //private Book book;
    private User user;
    private Integer bookId;
    private Number quantity;
    private HttpServletRequest req;
    

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
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	user = (User)session.get("user");
    	//try{
    		if (user == null || bookId == null || quantity == null){
    			map.put("code", Constants.NO_DATA);
    		} else {
    			//bookOrder = bookOrderService.getOrderByUserIDandOrderStatus(user.getUserId(), "unprocess");
    			//if (bookOrder == null) {
    				bookOrder.setUser(user);
    				bookOrder.setOrderStatus("unprocess");
    				bookOrder.setOrderTime(new Date());
    				bookOrder.setAddress(null);
    				bookOrderService.insertBookOrder(bookOrder);
    				map.put("code", Constants.CODE_SUCCESS);
    			//}
    			
    		}
    	/*}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
    	}*/
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
