package com.bbq.db.project.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bbq.db.project.mongodb.MongoDBManager;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;




import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.AddressService;
import com.bbq.db.project.service.BookInOrderService;
import com.bbq.db.project.service.BookOrderService;
import com.bbq.db.project.service.BookService;
import com.bbq.db.project.service.UserService;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;


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
	@Autowired
    private AddressService addressService;

	
    private int bookId;
    private int quantity;
    private int bookOrderId;
    private int amount;
    private int addressId;
	private BookOrder bookOrder;
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	private List<BookOrder> bookOrders;
	private List<BookInOrder> bookInOrders;
	
	public List<BookInOrder> getBookInOrders() {
		return bookInOrders;
	}

	public void setBookInOrders(List<BookInOrder> bookInOrders) {
		this.bookInOrders = bookInOrders;
	}
    
    public int getBookOrderId() {
		return bookOrderId;
	}

	public void setBookOrderId(int bookOrderId) {
		this.bookOrderId = bookOrderId;
	}

	@Action(value = "addBookToOrder")
    public String addBookToOrder(){
    	//BookOrder bookOrder= new BookOrder();
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	try{
    		if (user == null){
    			logger.error("error::module:UserAction][action:login][][error:{empty params}]");
    			map.put("code", Constants.NO_DATA);
    		} else {
    	    	System.out.println(user.getUserName().toString());
    			bookOrder = bookOrderService.getOrderByUserIDandOrderStatus(user.getUserId(), "unprocess");
    			if (bookOrder == null) {
                    bookOrder = new BookOrder();
    				bookOrder.setUser(user);
    				bookOrder.setOrderStatus("unprocess");
    				bookOrder.setOrderTime(new Date());
    				bookOrder.setAddress(null);
    				bookOrderService.insertBookOrder(bookOrder);
                    MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserRole().getRoleId(), "addBookToOrder",
                            JSONObject.fromObject(bookOrder).toString());
    			}

				map.put("code", Constants.CODE_SUCCESS);
    		}
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
    	}
    	
    	StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
    	return null;
    	
    }
    
    @Action(value = "getAllOrders", results = { @Result(name = "success", location = "viewBookOrder.jsp")})
    public String getAllOrders(){
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	try{
    		bookOrders = bookOrderService.getOrderByUserId(user.getUserId());
    	}catch (Exception e){
    		
    	}
    	
    	return SUCCESS;
    	
    }
    
    @Action(value = "orderDetail", results = { @Result(name = "success_unprocess", location = "viewUnProcessOrderDetail.jsp"),
                                               @Result(name = "success_view", location = "viewOrderDetail.jsp")})
    public String orderDetail(){
    	try{
    		bookInOrders = bookInOrderService.getBookInOrderByOrderID(bookOrderService.getOrderById(bookOrderId));
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
    	}
    	if (bookOrderService.getOrderById(bookOrderId).getOrderStatus().equals("unprocess"))
    		return "success_unprocess";
    	else
    	    return "success_view";
    	
    }
    
    @Action(value = "checkOut", results = { @Result(name = "success", location = "viewBookOrder.jsp")})
    public String checkOut(){
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	try{
    		int user_amount =  user.getAccount();
    		bookOrder = bookOrderService.getOrderById(bookOrderId);
    		bookInOrders = bookInOrderService.getBookInOrderByOrderID(bookOrderService.getOrderById(bookOrderId));
    		for(BookInOrder obj : bookInOrders){
    			bookInOrderService.updateBookQuantityByBookandQuantity(obj.getBook(),obj.getQuantity());
    			bookInOrderService.updateUserAmountByBookandQuantity(obj.getBook(),obj.getQuantity());
    		}
    		BookOrder neworder = new BookOrder();
    		Address address = addressService.getAddressById(addressId);
    		neworder.setAddress(address);
    		neworder.setOrderStatus("check out");
    		neworder.setOrderId(bookOrderId);
    		bookOrderService.updateBookOrder(neworder);
    		user.setAccount(user_amount-amount);
    		userService.updateUserAccount(user);
            MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserRole().getRoleId(), "Check Out Order",
                    JSONObject.fromObject(bookInOrders).toString());
    		bookOrders = bookOrderService.getOrderByUserId(user.getUserId());
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
    		return "error";
    	}
    	
    	return SUCCESS;

}
    
    @Action(value = "checkQuantityandAmount")
    public String checkQuantityandAmount(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
		try{
			int user_amount =  user.getAccount();
			if(user_amount < amount){
				map.put("code", Constants.NO_ENOUGH_AMOUNT);
				StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
				return null;
		    }else{
				bookInOrders = bookInOrderService.getBookInOrderByOrderID(bookOrderService.getOrderById(bookOrderId));
				for(BookInOrder obj : bookInOrders){
					if(obj.getBook().getQuantity() < obj.getQuantity()){
						map.put("code", Constants.NO_ENOUGH_BOOK);
						StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
						return null;
					}
				}
		    }
			map.put("code", Constants.CODE_SUCCESS);
		}catch (Exception e){
			logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
			map.put("code", Constants.INNER_ERROR);
		}	
		

    	StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
		return null;
	}
    
    public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	@Action(value = "updateStatus", results = { @Result(name = "success", location = "viewBookOrder.jsp")})
    public String updateStatus(){
    	try{
    		bookOrder = new BookOrder();
    		bookOrder.setOrderId(bookOrderId);
    		bookOrder.setOrderStatus("cancled");
    		bookOrderService.updateBookOrder(bookOrder);
    		Map<String, Object> session = ActionContext.getContext().getSession();
        	User user = (User)session.get("user");
        	bookOrders = bookOrderService.getOrderByUserId(user.getUserId());
            MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserRole().getRoleId(), "Update Book Order",
                    JSONObject.fromObject(bookOrder).toString());
    	}catch (Exception e){
    		logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
    	}
    	
    	return SUCCESS;

}

	public List<BookOrder> getBookOrders() {
		return bookOrders;
	}

	public void setBookOrders(List<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

	public BookOrder getBookOrder() {
		return bookOrder;
	}

	public BookOrderService getBookOrderService() {
		return bookOrderService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public BookInOrderService getBookInOrderService() {
		return bookInOrderService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setBookOrderService(BookOrderService bookOrderService) {
		this.bookOrderService = bookOrderService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public void setBookInOrderService(BookInOrderService bookInOrderService) {
		this.bookInOrderService = bookInOrderService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
