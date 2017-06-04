package com.qiqiao.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qiqiao.base.BaseDaoImpl;
import com.qiqiao.model.Privilege;
import com.qiqiao.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {
	
	@SuppressWarnings("unchecked")
	public List<Privilege> findTopPrivilegeList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL and p.flag=0")//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<String> findAllPrivilegeUrls() {
		return getSession().createQuery(//
		"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL and p.flag=0")//
		.list();
	}
	
}
