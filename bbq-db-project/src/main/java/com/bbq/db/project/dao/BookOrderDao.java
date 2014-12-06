package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.BookOrder;
import com.bbq.db.project.model.User;

@Service("bookOrderDao")
public class BookOrderDao extends BatisEntityDao<BookOrder>{

	public BookOrder getOrderById(Integer orderId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("orderId", orderId);
		return super.getSqlSession().selectOne("BookOrder.getOrderById", sqlMap);
	}
	
	public List<BookOrder> getOrderByUserId(Integer userId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("userId", userId);
		return super.getSqlSession().selectList("BookOrder.getOrderByUserID", sqlMap);
	}
	
	public BookOrder getOrderByUserIDandOrderStatus(Integer userId, String orderStatus) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("userId", userId);
		sqlMap.put("orderStatus", orderStatus);
		return super.getSqlSession().selectOne("BookOrder.getOrderByUserIDandOrderStatus", sqlMap);
	}
}
