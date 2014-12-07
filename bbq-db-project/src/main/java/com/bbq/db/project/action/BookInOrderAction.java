package com.bbq.db.project.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Book;
import com.bbq.db.project.service.BookInOrderService;
import com.bbq.db.project.service.BookOrderService;
import com.bbq.db.project.service.BookService;

@Namespace("/bookinorder")
public class BookInOrderAction extends BaseAction{	
	@Autowired
    private BookInOrderService bookInOrderService;
	@Autowired
    private BookOrderService bookOrderService;
	@Autowired
    private BookService bookService;
	
	private int bookOrderId;
	private int book;
	private int quantity;
	public BookInOrderService getBookInOrderService() {
		return bookInOrderService;
	}
	public void setBookInOrderService(BookInOrderService bookInOrderService) {
		this.bookInOrderService = bookInOrderService;
	}
	public int getBookOrderId() {
		return bookOrderId;
	}
	public void setBookOrderId(int bookOrderId) {
		this.bookOrderId = bookOrderId;
	}
	
	public BookOrderService getBookOrderService() {
		return bookOrderService;
	}
	public void setBookOrderService(BookOrderService bookOrderService) {
		this.bookOrderService = bookOrderService;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Action(value = "updateQuantity")
    public String updateQuantity(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	try{
    		bookInOrderService.updateByOrderIDandBookID(bookOrderService.getOrderById(bookOrderId), bookService.getBookById(book), quantity);
    		map.put("code", Constants.CODE_SUCCESS);
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
    	}
    	
    	StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
    	return null;
    	
    }
}
