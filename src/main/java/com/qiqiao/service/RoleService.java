package com.qiqiao.service;


import java.util.List;

import com.qiqiao.base.BaseDao;
import com.qiqiao.model.Role;

public interface RoleService extends BaseDao<Role> {

	/**
	 * 查询会员用户组
	 * @return
	 */
	List<Role> findVip();
	
	/**
	 * 查询系统用户组
	 * 
	 * @return
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	List<Role> findSys();

}
