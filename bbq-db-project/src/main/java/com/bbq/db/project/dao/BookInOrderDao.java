package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookInOrder;
import com.bbq.db.project.model.BookOrder;

@Service("bookinorderDao")
public class BookInOrderDao extends BatisEntityDao<BookInOrder>{
	public List<BookInOrder> getBookInOrderByOrderID(BookOrder bookorder) {
		Map<String, BookOrder> sqlMap = new HashMap<String, BookOrder>();
		sqlMap.put("bookorder", bookorder);
		return super.getSqlSession().selectList("BookInOrder.getBookInOrderByOrderID", sqlMap);
	}
	
	public BookInOrder getBookInOrderByOrderIDandBookID(BookOrder bookorder, Book book) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("bookorderId", bookorder.getOrderId());
		sqlMap.put("bookId", book.getBookId());
		return super.getSqlSession().selectOne("BookInOrder.getBookInOrderByOrderIDandBookID", sqlMap);
	}
	
	public int updateByOrderIDandBookID(BookOrder bookorder, Book book, int quantity) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("bookorderId", bookorder.getOrderId());
		sqlMap.put("bookId", book.getBookId());
		sqlMap.put("quantity", quantity);
		return super.getSqlSession().update("BookInOrder.updateByOrderIDandBookID", sqlMap);
	}
	
	public int deleteByOrderIDandBookID(BookOrder bookorder, Book book) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("bookorderId", bookorder.getOrderId());
		sqlMap.put("bookId", book.getBookId());
		return super.getSqlSession().delete("BookInOrder.deleteByOrderIDandBookID", sqlMap);
	}
	
	
	
}
