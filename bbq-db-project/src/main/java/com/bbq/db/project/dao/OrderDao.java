package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.Order;
import com.bbq.db.project.model.User;

@Service("orderDao")
public class OrderDao extends BatisEntityDao<Book>{
	public Order getOrderById(Integer orderId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("orderId", orderId);
		return super.getSqlSession().selectOne("Order.getOrderById", sqlMap);
	}
	
	public List<Order> getOrderByUserId(User user) {
		Map<String, User> sqlMap = new HashMap<String, User>();
		sqlMap.put("user", user);
		return super.getSqlSession().selectList("Order.getOrderByUserID", sqlMap);
	}
}
