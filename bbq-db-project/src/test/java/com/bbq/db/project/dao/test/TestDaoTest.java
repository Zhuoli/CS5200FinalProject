package com.bbq.db.project.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbq.db.project.dao.TestDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={
		"/mybatis/mapper/*",
		"/mybatis/mybatis-config.xml",
		"/spring/spring.xml"
}
) 
public class TestDaoTest {
	
	@Autowired
	public TestDao testDao;
	
	@Test
	public void findByIdTest() {
		
		com.bbq.db.project.model.Test test = testDao.selById(1);
		Assert.assertTrue(test != null);
	}
}
