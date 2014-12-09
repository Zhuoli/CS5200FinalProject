package com.bbq.db.project.dao.test;


import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.AdminInfoDao;
import com.bbq.db.project.model.AdminInfo;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
)
@Transactional
public class AdminInfoDaoTest {
	@Autowired
	public AdminInfoDao admininfoDao;
	
//	@Test
//	public void insertAdmin() {		
//		AdminInfo admininfo = new AdminInfo();
//		admininfo.setAdminName("bbb");
//		admininfo.setPassword("bbbbbb");
//		admininfo.setRegisterTime(new Date());
//		admininfoDao.insert(admininfo);
//	}
	
	@Test
	public void selectAllAdmin() {		
		List<AdminInfo> admininfo = admininfoDao.getAll();
		Assert.assertTrue(admininfo.size() > 0);
	}

	@Test
	public void getByAdminName() {	
		AdminInfo admininfo = admininfoDao.getBydminName("aaaa");
		Assert.assertTrue(admininfo != null);
	}
	
	@Test
	public void updateByAdminName() {
		AdminInfo admininfo = new AdminInfo();
		admininfo.setAdminName("aaaa");
		admininfo.setPassword("aaaupdate");
		admininfo.setRegisterTime(new Date());
		int effectCount = admininfoDao.update(admininfo);
		Assert.assertTrue(effectCount > 0);
	}
	
//	@Test
//	public void deleteByAdminName(){
//		AdminInfo admininfo = admininfoDao.getBydminName("bbb");
//		int effectCount = admininfoDao.delete(admininfo);
//		Assert.assertTrue(effectCount > 0);
//	}
}
