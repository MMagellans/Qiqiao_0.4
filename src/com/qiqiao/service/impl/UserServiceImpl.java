package com.qiqiao.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.qiqiao.base.BaseDaoImpl;
import com.qiqiao.model.Role;
import com.qiqiao.model.User;
import com.qiqiao.service.UserService;
import com.qiqiao.vo.UserVo2;

@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {
	/*
	 * 后台校验登录信息(non-Javadoc)
	 * @see com.qiqiao.service.UserService#getByLoginNameAndPassword(java.lang.String, java.lang.String)
	 */
	public User getByLoginNameAndPassword(String username, String password) {
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.username=? AND u.password=?")//
				.setParameter(0, username)//
				.setParameter(1, DigestUtils.md5Hex(password))//
				.uniqueResult();
	}

	public void updateRole(Long id) {
		// 获取该角色信息
		Role role = (Role) getSession().createQuery(//
				"FROM Role r WHERE r.id=?")//
				.setParameter(0, id)//
				.uniqueResult();
		//System.out.println("--------要删除的角色积分id---------"+role.getMinCredits()+"-----"+role.getId());
		// 查找上一级角色
		Role other = (Role) getSession()
				.createQuery(//
						"FROM Role r WHERE r.minCredits<? ORDER BY r.minCredits DESC")//
				.setParameter(0, role.getMinCredits())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		//System.out.println("--------上一级角色积分id---------"+other.getMinCredits()+"-----"+other.getId());
		// 更改用户的角色信息
		getSession().createQuery(//
				"UPDATE User u SET u.role=? WHERE u.role=?")//
				.setParameter(0, other)//
				.setParameter(1, role)//
				.executeUpdate();

	}

	/**
	 * 校验该用户名是否已经被注册
	 */
	public boolean isRegister(String username) {
		User user = (User)getSession().createQuery(//
			"FROM User u WHERE u.username=?")//
			.setParameter(0, username)//
			.uniqueResult();
		if(user != null) {
			return true;
		}
		else return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> getModeratorsBySection(Long id) {
		return getSession().createQuery(//
				"SELECT new com.wangwei.qiqiao.model.User(u.id,u.username) FROM User u WHERE u.section.id=?")//
				.setParameter(0, id)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<UserVo2> selectUserByUsername(String username) {
		List<UserVo2> users = getSession().createQuery(//
				"SELECT new com.wangwei.qiqiao.vo.UserVo2(u.id,u.username,u.credits,u.createTime,u.board) FROM User u WHERE u.username LIKE :name")//
				.setString("name", "%"+username+"%")
				.list();
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> selectUserByUsername1(String username) {
		List<User> users = getSession().createQuery(//
				"FROM User u WHERE u.type NOT IN (0) AND u.username LIKE :name")//
				.setString("name", "%"+username+"%")
				.list();
		return users;
	}

	public List<String> findUsername(String username) {
		List list = getSession().createSQLQuery(//
				"select username from qiqiao_user where type=1 and username like '%"+username+"%'")//
				.list();
		return list;
	}

	public void setRoletoUser(List<String> ids, String rid) {
		Long a = Long.parseLong(rid);
		String id = "";
		for(int i=0;i<ids.size();i++) {
			if(i == (ids.size()-1)) {
				id += "'"+ids.get(i)+"'";
			}else id += "'"+ids.get(i)+"',";
		}
		getSession().createQuery(//
			"UPDATE User u SET u.role.id=? WHERE u.username in ("+id+")")//
			.setParameter(0, a)//
			.executeUpdate();
	}
	
}
