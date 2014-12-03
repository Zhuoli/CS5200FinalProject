package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.AdminInfo;

@Service("admininfoDao")
public class AdminInfoDao extends BatisEntityDao<AdminInfo>{
	public AdminInfo getBydminName(String adminName) {
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("adminName", adminName);
		return super.getSqlSession().selectOne("AdminInfo.getBydminName", sqlMap);
	}
}
