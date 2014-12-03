package com.bbq.db.project.model;

public class BookInOrder {
	private BookOrder bookorder;
	private Book bookId;
	private int quantity;
	public BookOrder getOrder() {
		return bookorder;
	}
	public Book getBookId() {
		return bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setOrderId(BookOrder bookorder) {
		this.bookorder = bookorder;
	}
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BookInOrder(BookOrder bookorder, Book bookId, int quantity) {
		super();
		this.bookorder = bookorder;
		this.bookId = bookId;
		this.quantity = quantity;
	}
	public BookInOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
