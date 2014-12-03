package com.bbq.db.project.model;

import java.util.Date;


public class Book {
	private int bookId;
	private User user;
	private String title;
	private String author;
	private String bookIsbn;
	private int quantity;
	private double price;
	private String publisher;
	private Date publishTime;
	public int getBookId() {
		return bookId;
	}
	public User getUser() {
		return user;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getIsbn() {
		return bookIsbn;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public String getPublisher() {
		return publisher;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setIsbn(String isbn) {
		this.bookIsbn = isbn;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Book(int bookId, User user, String title, String author,
			String isbn, int quantity, double price, String publisher,
			Date publishTime) {
		super();
		this.bookId = bookId;
		this.user = user;
		this.title = title;
		this.author = author;
		this.bookIsbn = isbn;
		this.quantity = quantity;
		this.price = price;
		this.publisher = publisher;
		this.publishTime = publishTime;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
