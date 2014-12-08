package com.bbq.db.project.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;









import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.AddressService;
import com.bbq.db.project.service.BookInOrderService;
import com.bbq.db.project.service.BookOrderService;
import com.bbq.db.project.service.BookService;
import com.opensymphony.xwork2.ActionContext;

@Namespace("/bookinorder")
public class BookInOrderAction extends BaseAction{	
	@Autowired
    private BookInOrderService bookInOrderService;
	@Autowired
    private BookOrderService bookOrderService;
	@Autowired
    private BookService bookService;
	@Autowired
    private AddressService addressService;
	
	private int bookOrderId;
	private int bookId;
	private int quantity;
	private int amount;
	private List<BookInOrder> bookInOrders;
	private List<Address> addresses;
	
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
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
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	public BookService getBookService() {
		return bookService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	@Action(value = "updateQuantity")
    public String updateQuantity(){
		Map<String, Object> map = new HashMap<String, Object>();
    	try{
    		bookInOrderService.updateByOrderIDandBookID(bookOrderService.getOrderById(bookOrderId), bookService.getBookById(bookId), quantity);
    		map.put("code", Constants.CODE_SUCCESS);
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
    		map.put("code", Constants.INNER_ERROR);
    	}
    	
    	StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
    	return null;
    	
    }
	
	@Action(value = "deleteBookInOrder")
    public String deleteBookInOrder(){
		Map<String, Object> map = new HashMap<String, Object>();
    	try{
    		bookInOrderService.deleteByOrderIDandBookID(bookOrderService.getOrderById(bookOrderId), bookService.getBookById(bookId));
    		map.put("code", Constants.CODE_SUCCESS);
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
    		map.put("code", Constants.INNER_ERROR);
    	}
    	
    	
    	StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
    	return null;
    	
    }
	
	@Action(value = "orderAmount", results = { @Result(name = "success", location = "../bookorder/checkOut.jsp")})
    public String orderAmount(){
		Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	try{
    		amount = (int) bookInOrderService.getOrderAmountByOrderId(bookOrderService.getOrderById(bookOrderId));
        	bookInOrders = bookInOrderService.getBookInOrderByOrderID(bookOrderService.getOrderById(bookOrderId));
        	addresses = addressService.getAddressByUserId(user);
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
    		//map.put("code", Constants.INNER_ERROR);
    	}
    	
    	
    	return SUCCESS;
    	
    }
	public List<BookInOrder> getBookInOrders() {
		return bookInOrders;
	}
	public void setBookInOrders(List<BookInOrder> bookInOrders) {
		this.bookInOrders = bookInOrders;
	}
}
