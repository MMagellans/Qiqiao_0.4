package com.qiqiao.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.qiqiao.base.BaseDaoImpl;
import com.qiqiao.model.Role;
import com.qiqiao.service.RoleService;

@Service
public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService {
	
	@SuppressWarnings("unchecked")
	public List<Role> findVip() {
		return getSession().createQuery(//
		"FROM Role r WHERE r.roleType=? ORDER BY r.minCredits ASC")
		.setParameter(0, 0)
		.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findSys() {
		return getSession().createQuery(//
		"FROM Role r WHERE r.roleType=?")
		.setParameter(0, 1)
		.list();
	}

}
