package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.model.User;

@Service("addressDao")
public class AddressDao extends BatisEntityDao<Address>{
	public Address getAddressById(Integer addressId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("addressId", addressId);
		return super.getSqlSession().selectOne("Address.getAddressById", sqlMap);
	}
	
	public List<Address> getAddressByUserId(User user) {
		Map<String, User> sqlMap = new HashMap<String, User>();
		sqlMap.put("user", user);
		return super.getSqlSession().selectList("Address.getAddressByUserID", sqlMap);
	}	
}
