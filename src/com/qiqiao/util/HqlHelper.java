package com.qiqiao.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.BaseDao;
import com.qiqiao.model.PageBean;


public class HqlHelper {
	
	private String fromClause;//from子句
	private String whereClause = "";//where子句
	private String orderByClause = "";//排序子句
	List<Object> parameters = new ArrayList<Object>();//参数
	/**
	 * 生成FROM子句，别名默认为“o”
	 * @param clazz
	 */
	public HqlHelper(Class clazz) {
		this.fromClause = "FROM "+clazz.getSimpleName()+" o";
	}
	
	/**
	 * 生成FROM子句，别名为alias
	 * @param clazz 查找的实体类
	 * @param alias 别名
	 */
	public HqlHelper(Class clazz,String alias) {
		this.fromClause = "FROM "+clazz.getSimpleName() + " "+alias;
	}
	
	/**
	 * 拼接WHERE子句
	 * @param condition 条件
	 * @param params 参数
	 * @return
	 */
	public HqlHelper addCondition(String condition,Object... params) {
		if(whereClause.length() == 0) {
			whereClause += " WHERE " + condition;
		}else {
			whereClause += " AND "+condition;
		}
		
		if(params != null && params.length > 0) {
			for(Object obj : params) {
				parameters.add(obj);
			}
		}
		return this;
	}
	
	/**
	 * 拼接WHERE子句
	 * @param condition 条件
	 * @return
	 */
	public HqlHelper addCondition(String condition) {
		if(whereClause.length() == 0) {
			whereClause += " WHERE " + condition;
		}else {
			whereClause += " AND "+condition;
		}
		return this;
	}
	
	/**
	 * 若第一个参数为true则拼接where子句
	 * @param condition 条件
	 * @param params 参数
	 * @return
	 */
	public HqlHelper addCondition(boolean append,String condition,Object... params) {
		if(append) {
			addCondition(condition, params);
		}
		return this;
	}
	
	/**
	 * 拼接排序子句
	 * @param propertyName 属性名
	 * @param isAsc 是升序吗
	 * @return
	 */
	public HqlHelper addOrder(String propertyName , boolean isAsc) {
		if(orderByClause.length() == 0) {
			orderByClause += " ORDER BY "+propertyName+ (isAsc ? " ASC":" DESC");
		}else {
			orderByClause += " , "+propertyName+ (isAsc ? " ASC":" DESC");
		} 
		return this;
	}
	
	/**
	 * 若第一个参数为true则拼接排序子句
	 * @param propertyName 属性名
	 * @param isAsc 是升序吗
	 * @return
	 */
	public HqlHelper addOrder(boolean append , String propertyName , boolean isAsc) {
		if(append) {
			addOrder(propertyName, isAsc);
		}
		return this;
	}
	
	/**
	 * 获取拼接好了的查询列表的hql语句
	 * @return
	 */
	public String getQueryListHql() {
		
		return fromClause + whereClause + orderByClause;
	}
	
	/**
	 * 获取拼接好了的查询记录条数的hql语句
	 * @return
	 */
	public String getQueryCountHql() {
		
		return "SELECT COUNT(*) "+fromClause + whereClause;
	}
	
	/**
	 * 获取参数集合
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
	
	/**
	 * 查询并准备分页信息（放到栈顶）
	 * 
	 * @param pageNum
	 * @param service
	 * @return
	 */
	public HqlHelper buildPageBeanForStruts2(int pageNum, BaseDao<?> service) {
		PageBean pageBean = service.getPageBean(pageNum , this);
		//System.out.println("页数"+pageBean.getPageCount());
		//System.out.println("记录数"+pageBean.getRecordCount());
		//System.out.println("每页个数"+pageBean.getPageSize());
		//System.out.println("----查询语句------"+this.getQueryListHql());
		ActionContext.getContext().getValueStack().push(pageBean);
		return this;
	}
	
}
