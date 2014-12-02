package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.Address;

@Service("addressDao")
public class AddressDao extends BatisEntityDao<Address>{
	public Address getAddressById(Integer addressId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("addressId", addressId);
		return super.getSqlSession().selectOne("Address.getAddressById", sqlMap);
	}
	
	public List<Address> getAddressByUserId(Integer userId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("userId", userId);
		return super.getSqlSession().selectList("User.getUserByUserId", sqlMap);
	}	
}
