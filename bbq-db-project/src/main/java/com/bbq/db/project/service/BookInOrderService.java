package com.bbq.db.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.BookDao;
import com.bbq.db.project.dao.BookInOrderDao;
import com.bbq.db.project.dao.UserDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;
import org.springframework.transaction.annotation.Transactional;

@Service("bookInOrderService")
@Transactional
public class BookInOrderService {
	@Autowired
	private BookInOrderDao bookinorderDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;
	
	public void insertBookInOrder(BookInOrder bookinorder) {
        BookInOrder oldBookInOrder = bookinorderDao.getBookInOrderByOrderIDandBookID(bookinorder.getBookorder(),bookinorder.getBook());
        if(oldBookInOrder != null)
        {int quantity = oldBookInOrder.getQuantity()+ bookinorder.getQuantity();
            BookInOrder newbookinorder = new BookInOrder();
            newbookinorder.setBook(bookinorder.getBook());
            newbookinorder.setBookorder(bookinorder.getBookorder());
            newbookinorder.setQuantity(quantity);
            bookinorderDao.update(newbookinorder);}
        else{
		    bookinorderDao.insert(bookinorder);   }
	}
	
	public List<BookInOrder> getBookInOrderByOrderID(BookOrder bookorder) {
		return bookinorderDao.getBookInOrderByOrderID(bookorder);
	}
	
	public BookInOrder getBookInOrderByOrderIDandBookID(BookOrder bookorder, Book book) {
		return bookinorderDao.getBookInOrderByOrderIDandBookID(bookorder, book);
	}
	
	public void updateByOrderIDandBookID(BookOrder bookorder, Book book, int quantity) {
		bookinorderDao.updateByOrderIDandBookID(bookorder, book, quantity);
	}
	
	public void deleteByOrderIDandBookID(BookOrder bookorder, Book book) {
		bookinorderDao.deleteByOrderIDandBookID(bookorder, book);
	}
	
	public int getOrderAmountByOrderId(BookOrder bookorder) {
		List<BookInOrder> bookinorders = bookinorderDao.getBookInOrderByOrderID(bookorder);
		List<Integer>  bookIds = new ArrayList<Integer>();
		List<Integer>  bookQuantitys = new ArrayList<Integer>();
		for(BookInOrder obj : bookinorders){
			bookIds.add(obj.getBook().getBookId());
			bookQuantitys.add(obj.getQuantity());
		}
		List<Integer> bookPrices = new ArrayList<Integer>();
		for(int bookId : bookIds){
			bookPrices.add(bookDao.getBookById(bookId).getPrice());
		}
		int amount = 0;
		for(int i =0; i<bookPrices.size(); i++){
			amount += bookPrices.get(i) * bookQuantitys.get(i);
		}
		return amount;
	}
	
	public void updateBookQuantityByBookandQuantity(Book book, int quantity){
		Book newbook = new Book();
		newbook.setBookId(book.getBookId());
		newbook.setQuantity(book.getQuantity()-quantity);
		bookDao.updateQuantity(newbook);
	}
	
	public void updateUserAmountByBookandQuantity(Book book, int quantity){
		User user = book.getUser();
		int income = book.getPrice() * quantity;
		User newuser = new User();
		newuser.setUserId(user.getUserId());
		newuser.setAccount(user.getAccount() + income);
		userDao.updateUserAccount(newuser);
	}
	
	
	
}
