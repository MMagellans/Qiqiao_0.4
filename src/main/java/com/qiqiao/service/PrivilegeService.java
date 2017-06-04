package com.qiqiao.service;

import java.util.List;

import com.qiqiao.base.BaseDao;
import com.qiqiao.model.Privilege;

public interface PrivilegeService extends BaseDao<Privilege> {
	
	List<Privilege> findTopPrivilegeList();

	List<String> findAllPrivilegeUrls();
	
}
