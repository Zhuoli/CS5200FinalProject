package com.bbq.db.project.dao.base;

import java.io.Serializable;
import java.util.List;

import bbq.db.project.dao.utils.GenericsUtils;
import bbq.db.project.dao.utils.PageInfo;

public class BatisEntityDao<T> extends BatisGenericDao {

	protected Class<T> entityClass;

	protected String primaryKeyName;

	@SuppressWarnings("unchecked")
	public BatisEntityDao() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	
	public List<T> findBy(String statementName, Object parameterObject) {
		return findBy(getEntityClass(), statementName, parameterObject);
	}
	
	public T findUniqueBy(String statementName, Object parameterObject) {
		return findUniqueBy(getEntityClass(), statementName, parameterObject);
	}
	
	public int updateBy(String statementName, Object parameterObject) {
		return updateBy(getEntityClass(), statementName, parameterObject);
	}

	public T get(Serializable id) {
		return get(getEntityClass(), id);
	}

	public List<T> getAll() {
		return getAll(getEntityClass());
	}
	
	public int deleteAll() {
		return deleteAll(getEntityClass());
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

	public List<T> pagedQuery(String statementName, Object parameterObject, PageInfo page) {
		return pagedQuery(getEntityClass(), statementName, parameterObject, page);
	}
	
}