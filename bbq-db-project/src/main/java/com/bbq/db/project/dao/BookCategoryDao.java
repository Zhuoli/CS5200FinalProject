package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.BookCategory;

@Service("bookCategoryDao")
public class BookCategoryDao extends BatisEntityDao<BookCategory>{
	public BookCategory getCategoryById(Integer categoryId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("categoryId", categoryId);
		return super.getSqlSession().selectOne("BookCategory.getCategoryById", sqlMap);
	}
}
