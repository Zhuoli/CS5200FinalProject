package com.bbq.db.project.model;

public class BookInOrder {
	private Order orderId;
	private Book bookId;
	private int quantity;
	public Order getOrderId() {
		return orderId;
	}
	public Book getBookId() {
		return bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BookInOrder(Order orderId, Book bookId, int quantity) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
	}
	public BookInOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
