package com.bbq.db.project.model;

import java.util.Date;


public class Book {
	private int bookId;
	private User user;
	private String title;
	private String author;
	private String isbn;
	private int quantity;
	private int price;
	private String publisher;
	private Date publishTime;
    private String pic;
    private BookCategory category;
	public int getBookId() {
		return bookId;
	}
	

	public BookCategory getCategory() {
		return category;
	}


	public void setCategory(BookCategory category) {
		this.category = category;
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
		return isbn;
	}
	public int getQuantity() {
		return quantity;
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
		this.isbn = isbn;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    

	public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
	

    public Book(int bookId, User user, String title, String author,
			String isbn, int quantity, int price, String publisher,
			Date publishTime, String pic, BookCategory category) {
		super();
		this.bookId = bookId;
		this.user = user;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.quantity = quantity;
		this.price = price;
		this.publisher = publisher;
		this.publishTime = publishTime;
		this.pic = pic;
		this.category = category;
	}


	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
