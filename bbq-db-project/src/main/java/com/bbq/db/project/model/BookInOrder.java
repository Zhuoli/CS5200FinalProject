package com.bbq.db.project.model;

public class BookInOrder {
	private BookOrder bookorder;
	private Book book;
	private int quantity;
	
	public BookOrder getBookorder() {
		return bookorder;
	}
	public Book getBook() {
		return book;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setBookorder(BookOrder bookorder) {
		this.bookorder = bookorder;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BookInOrder(BookOrder bookorder, Book book, int quantity) {
		super();
		this.bookorder = bookorder;
		this.book = book;
		this.quantity = quantity;
	}
	public BookInOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
