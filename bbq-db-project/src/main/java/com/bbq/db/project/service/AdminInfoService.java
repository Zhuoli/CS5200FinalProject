package com.bbq.db.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.dao.AdminInfoDao;
import com.bbq.db.project.model.AdminInfo;
import org.springframework.transaction.annotation.Transactional;

@Service("adminInfoService")
@Transactional
public class AdminInfoService {
	@Autowired
	private AdminInfoDao admininfoDao;
	
	public void insertAdmin(AdminInfo admin) {
		admininfoDao.insert(admin);
	}
	
	public AdminInfo getBydminName(String adminName) {
		return admininfoDao.getBydminName(adminName);
	}
	
	public void updateAdmin(AdminInfo admin) {
		admininfoDao.update(admin);
	}
	
	public void deleteAdmin(AdminInfo admin) {
		admininfoDao.delete(admin);
	}
}
