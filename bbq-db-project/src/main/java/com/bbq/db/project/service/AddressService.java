package com.bbq.db.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.AddressDao;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.User;
import org.springframework.transaction.annotation.Transactional;

@Service("addressService")
@Transactional
public class AddressService {
	@Autowired
	private AddressDao addressDao; 
	
	public void insertAddress(Address address) {
		addressDao.insert(address);
	}
	
	public Address getAddressById(Integer addressId) {
		return addressDao.getAddressById(addressId);
	}
	
	public List<Address> getAddressByUserId(User user) {
		return addressDao.getAddressByUserId(user);
	}
	
	public void updateAddress(Address address) {
		addressDao.update(address);
	}
	
	public void deleteAddress(Address address) {
		addressDao.delete(address);
	}
}
