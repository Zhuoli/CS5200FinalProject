package com.bbq.db.project.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.base.BatisEntityDao;
import com.bbq.db.project.model.UserRole;


@Service("userRoleDao")
public class UserRoleDao extends BatisEntityDao<UserRole>{
	public UserRole getUserRoleById(Integer roleId) {
		Map<String, Integer> sqlMap = new HashMap<String, Integer>();
		sqlMap.put("roleId", roleId);
		return super.getSqlSession().selectOne("UserRole.getUserRoleById", sqlMap);
	}
}
