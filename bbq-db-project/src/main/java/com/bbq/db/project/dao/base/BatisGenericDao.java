package com.bbq.db.project.dao.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.orm.ObjectRetrievalFailureException;

import bbq.db.project.dao.utils.PageInfo;

public class BatisGenericDao extends SqlSessionDaoSupport {
	
	protected static final String DOT = ".";
	protected static final String UNDERLINE = "_";

	protected static final String POSTFIX_INSERT = "insert";
	protected static final String POSTFIX_UPDATE = "update";
	protected static final String POSTFIX_DELETE = "delete";
	protected static final String POSTFIX_SELECT = "select";
	protected static final String PAGEQUERY_COUNT = "count";
	protected static final String POSTFIX_SELECT_ALL = "select_all";
	protected static final String POSTFIX_DELETE_ALL = "delete_all";
	private static final String PARAMNAME_CUSTOMTABLENAME = "tablename";
	private static final String PARAMNAME_PARAMETER = "params";
	
	private boolean useSimpleName = true;
	
	private boolean useNamespace = true;
	
	private boolean customTableName = false;
	
	public boolean isUseSimpleName() {
		return useSimpleName;
	}

	public void setUseSimpleName(boolean useSimpleName) {
		this.useSimpleName = useSimpleName;
	}

	public boolean isUseNamespace() {
		return useNamespace;
	}

	public void setUseNamespace(boolean useNamespace) {
		this.useNamespace = useNamespace;
	}

	@SuppressWarnings("rawtypes")
	protected String getSqlStatementName(Class entityClass, String statementName) {
		if(useNamespace) return getSqlMapNamespace(entityClass) + DOT + statementName;
		else return statementName;
	}
	
	@SuppressWarnings("rawtypes")
	protected String getSqlMapNamespace(Class entityClass) {
		if(useSimpleName) return entityClass.getSimpleName();
		else return entityClass.getName();
	}
	
	protected String getTableName(){
		return null;
	}
	
	protected Object getParameterObject(Object parameter){
		if (customTableName) {
			Map<String,Object> result=new HashMap<String, Object>();
			String tablename=getTableName();
			if (tablename==null)
				try {
					throw new Exception("...");
				} catch (Exception e) {
//					log.error("å¼?å¸¸ï??", e.getMessage());
				}
			result.put(PARAMNAME_CUSTOMTABLENAME, tablename);
			result.put(PARAMNAME_PARAMETER, parameter);
			return result;
		}else{
			return parameter;
		}
		
	}

	@SuppressWarnings("unchecked")
	protected <T> T get(Class<T> entityClass, Serializable id) {

		T o = (T) getSqlSession().selectOne(
				getSqlStatementName(entityClass, POSTFIX_SELECT), getParameterObject(id));
		if (o == null)
			throw new ObjectRetrievalFailureException(entityClass, id);
		return o;
	}

	protected <T> List<T> getAll(Class<T> entityClass) {
		return getSqlSession().selectList(
				getSqlStatementName(entityClass, POSTFIX_SELECT_ALL), getParameterObject(null));
	}
	
	protected <T> List<T> findBy(Class<T> entityClass, String statementName, Object parameterObject) {
		return getSqlSession().selectList(
				getSqlStatementName(entityClass, statementName), getParameterObject(parameterObject));
	}
	
	protected <T> T findUniqueBy(Class<T> entityClass, String statementName, Object parameterObject) {
		return uniqueElement(findBy(entityClass, statementName, parameterObject));
	}
	
	static <T> T uniqueElement(List<T> list){
		int size = list.size();
		if (size==0) return null;
		T first = list.get(0);
		for ( int i=1; i<size; i++ ) {
			if ( list.get(i)!=first ) {
				try {
					throw new Exception("ä¸?æ­?ä¸???¡ç?????ï¼?"+ list.size() );
				} catch (Exception e) {
				}
			}
		}
		return first;
	}
	
	protected int updateBy(Class<?> entityClass, String statementName, Object parameterObject) {
		return getSqlSession().update(
				getSqlStatementName(entityClass, statementName), getParameterObject(parameterObject));
	}

	public Object insert(Object o) {
		return getSqlSession().insert(
				getSqlStatementName(o.getClass(), POSTFIX_INSERT), getParameterObject(o));
	}

	public int update(Object o) {
		return getSqlSession().update(
				getSqlStatementName(o.getClass(), POSTFIX_UPDATE), getParameterObject(o));
	}

	public int delete(Object o) {
		int rows = getSqlSession().delete(
				getSqlStatementName(o.getClass(), POSTFIX_DELETE), getParameterObject(o));
		return rows;
	}
	
	public int deleteAll(Class<?> entityClass) {
		int rows = getSqlSession().delete(
				getSqlStatementName(entityClass, POSTFIX_DELETE_ALL),getParameterObject(null));
		return rows;
	}

	protected <T> List<T> pagedQuery(Class<T> entityClass, String statementName, Object parameterObject, PageInfo page) {
		return rawPagedQuery(getSqlStatementName(entityClass, statementName), parameterObject, page);
	}
	
	public <T> List<T> rawPagedQuery(String statementName, Object parameterObject, PageInfo page) {
		if(page!=null){
			Number totalCount = (Number) getSqlSession().selectOne(statementName + UNDERLINE + PAGEQUERY_COUNT, 
					getParameterObject(parameterObject));
			page.setTotalCount(totalCount==null?0:totalCount.intValue());
		}
		return getSqlSession().selectList(statementName, getParameterObject(parameterObject));
	}

	public void setCustomTableName(boolean customTableName) {
		this.customTableName = customTableName;
	}

	public boolean isCustomTableName() {
		return customTableName;
	}

}