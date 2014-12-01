package com.bbq.db.project.model;

import java.sql.Date;

public class Book {
	private int bookId;
	private User userId;
	private String title;
	private String author;
	private String isbn;
	private int quantity;
	private float price;
	private String publisher;
	private Date publishTime;
	public int getBookId() {
		return bookId;
	}
	public User getUserId() {
		return userId;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getIsbn() {
		return isbn;
	}
	public int getQuantity() {
		return quantity;
	}
	public float getPrice() {
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
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Book(int bookId, User userId, String title, String author,
			String isbn, int quantity, float price, String publisher,
			Date publishTime) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
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
