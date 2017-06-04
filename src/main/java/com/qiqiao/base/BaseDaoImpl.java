package com.qiqiao.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qiqiao.cfg.Configuration;
import com.qiqiao.model.PageBean;
import com.qiqiao.model.Reply;
import com.qiqiao.util.HqlHelper;


@SuppressWarnings("unchecked")
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		//通过反射得到T的真实对象
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) pt.getActualTypeArguments()[0];
	}
	
	/**
	 * 保存实体
	 * 
	 */
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 删除实体
	 * 
	 */
	public void delete(Long id) {
		Object obj = getSession().get(clazz, id);
		getSession().delete(obj);
	}
	
	/**
	 * 获取实体 若id为空，则返回空
	 * 
	 */
	public T getById(Long id) {
		if(id == null) return null;
		return (T)getSession().get(clazz, id);
	}
	
	/**
	 * 获取实体集
	 * 
	 */
	public List<T> getByIds(Long[] ids) {
		if(ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)
				.list();
	}

	/**
	 * 获取所有实体
	 * 
	 */
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM "	+clazz.getSimpleName())//
				.list();
	}

	/**
	 * 更新实体
	 * 
	 */
	public void update(T entity) {
		getSession().update(entity);
	}
	
	/**
	 * 获取当前session
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
//	
//	/**
//	 * 公共的获取分页信息
//	 */
//	public PageBean getPageBean(int pageNum, String queryString, Object[] object) {
//		System.out.println("---------------------------->BaseDaoImpl.getPageBean()");
//		int pageSize = Configuration.getPageSize();
//		Query queryList = getSession().createQuery(queryString);//
//		if(object != null && object.length >0) {
//			for(int i=0;i<object.length;i++) {
//				queryList.setParameter(i,object[i]);
//			}
//		}
//		queryList.setFirstResult( (pageNum - 1) * pageSize );
//		queryList.setMaxResults(pageSize);
//		List<Reply> recordList = queryList.list();
//		
//		Query queryCount = getSession().createQuery("SELECT COUNT(*) " + queryString);//
//		if(object != null && object.length >0) {
//			for(int i=0;i<object.length;i++) {
//				queryCount.setParameter(i,object[i]);
//			}
//		}
//		Long recordCount = (Long) queryCount.uniqueResult();
//		
//		return new PageBean(pageNum, pageSize, recordList, recordCount.intValue());
//	}

	/**
	 * 最终版获取分页信息
	 */
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		//System.out.println("---------------------------->BaseDaoImpl.getPageBean(int pageNum, HqlHelper hqlHelper)");
		int pageSize = Configuration.getPageSize();
		System.out.println(hqlHelper.getQueryListHql());
		Query queryList = getSession().createQuery(hqlHelper.getQueryListHql());//
		List<Object> parameters = hqlHelper.getParameters();
		if(parameters != null && parameters.size() >0) {
			for(int i=0;i<parameters.size();i++) {
				queryList.setParameter(i,parameters.get(i));
			}
		}
		queryList.setFirstResult( (pageNum - 1) * pageSize );
		queryList.setMaxResults(pageSize);
		List<Reply> recordList = queryList.list();
		Query queryCount = getSession().createQuery(hqlHelper.getQueryCountHql());//
		if(parameters != null && parameters.size() >0) {
			for(int i=0;i<parameters.size();i++) {
				queryCount.setParameter(i,parameters.get(i));
			}
		}
		Long recordCount = (Long) queryCount.uniqueResult();
		
		return new PageBean(pageNum, pageSize, recordList, recordCount.intValue());
	}
	
}
