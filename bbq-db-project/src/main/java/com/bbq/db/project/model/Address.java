package com.bbq.db.project.model;

public class Address {
	private int Id;
	private User userId;
	private String street;
	private String city;
	private String country;
	private int zipcode;
	public int getId() {
		return Id;
	}
	public User getUserId() {
		return userId;
	}
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public Address(int id, User userId, String street, String city,
			String country, int zipcode) {
		super();
		Id = id;
		this.userId = userId;
		this.street = street;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
