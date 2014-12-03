package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.User;


@Service("bookDao")
public class BookDao extends BatisEntityDao<Book>{
	public Book getBookById(Integer bookId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("bookId", bookId);
		return super.getSqlSession().selectOne("Book.getBookById", sqlMap);
	}
	
	public List<Book> getBookByUserId(User user) {
		Map<String, User> sqlMap = new HashMap<String, User>();
		sqlMap.put("user", user);
		return super.getSqlSession().selectList("Book.getBookByUserID", sqlMap);
	}
}
