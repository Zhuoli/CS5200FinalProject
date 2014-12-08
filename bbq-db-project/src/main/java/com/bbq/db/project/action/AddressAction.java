package com.bbq.db.project.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.AddressService;
import com.opensymphony.xwork2.ActionContext;

@Namespace("/address")
public class AddressAction extends BaseAction{
	@Autowired
    private AddressService addressService;
	
	private String street;
	private String city;
	private String country;
	private int zipcode;
	
	
	@Action(value = "addNewAddress")
	public String addNewAddress(){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	try{
    		Address address = new Address();
    		address.setStreet(street);
    		address.setCity(city);
    		address.setCountry(country);
    		address.setZipcode(zipcode);
    		address.setUser(user);
    		addressService.insertAddress(address);
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
}
