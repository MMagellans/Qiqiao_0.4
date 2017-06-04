package com.qiqiao.base;

import java.util.List;

import com.qiqiao.model.PageBean;
import com.qiqiao.util.HqlHelper;

public interface BaseDao<T> {
	
	public void save(T entity);
	
	public void delete(Long id);
	
	public T getById(Long id);
	
	public List<T> getByIds(Long[] ids);
	
	public List<T> findAll();
	
	public void update(T entity);
	
//	/**
//	 * 公共的获取分页信息的方法
//	 * @param pageNum
//	 * @param queryString 查询的HQL语句
//	 * @param object 参数列表，应和HQL语句中的问号顺序对应
//	 * @return
//	 */
//	public PageBean getPageBean(int pageNum , String queryString , Object object[]);
	
	/**
	 * 公共的查询分页信息的方法
	 * 
	 * @param pageNum
	 * @param hqlHelper
	 *            查询条件（HQL语句与参数列表）
	 * @return
	 */
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper);
	
}
