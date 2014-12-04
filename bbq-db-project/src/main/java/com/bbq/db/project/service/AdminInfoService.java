package com.bbq.db.project.service;

import com.bbq.db.project.dao.AdminInfoDao;
import com.bbq.db.project.model.AdminInfo;

public class AdminInfoService {
	private AdminInfoDao admininfoDao = new AdminInfoDao();
	
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
