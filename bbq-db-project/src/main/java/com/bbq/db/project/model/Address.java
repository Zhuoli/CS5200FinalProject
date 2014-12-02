package com.bbq.db.project.model;

public class Address {
	private int addressId;
	private User user;
	private String street;
	private String city;
	private String country;
	private int zip_code;
	public int getAddressId() {
		return addressId;
	}
	public User getUser() {
		return user;
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
		return zip_code;
	}
	public void setAddressId(int id) {
		addressId = id;
	}
	public void setUser(User user) {
		this.user = user;
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
		this.zip_code = zipcode;
	}
	public Address(int id, User user, String street, String city,
			String country, int zipcode) {
		super();
		this.addressId = id;
		this.user = user;
		this.street = street;
		this.city = city;
		this.country = country;
		this.zip_code = zipcode;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
