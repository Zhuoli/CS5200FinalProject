package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;

@Service("orderDao")
public class BookOrderDao extends BatisEntityDao<BookOrder>{
	public BookOrder getOrderById(Integer orderId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("orderId", orderId);
		return super.getSqlSession().selectOne("BookOrder.getOrderById", sqlMap);
	}
	
	public List<BookOrder> getOrderByUserId(User user) {
		Map<String, User> sqlMap = new HashMap<String, User>();
		sqlMap.put("user", user);
		return super.getSqlSession().selectList("BookOrder.getOrderByUserID", sqlMap);
	}
}
