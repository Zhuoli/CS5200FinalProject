package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.Test;

@Service("testDao")
public class TestDao extends BatisEntityDao<Test>{

	public Test selById(Integer id) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("id", id);
		return super.getSqlSession().selectOne("Test.selById", sqlMap);
	}
}
