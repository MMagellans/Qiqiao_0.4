package com.qiqiao.service;

import java.util.List;

import com.qiqiao.base.BaseDao;
import com.qiqiao.model.User;
import com.qiqiao.vo.UserVo2;

public interface UserService extends BaseDao<User> {
	
	/**
	  * 根据用户名和密码来获取用户
	  * @param username
	  * @param password
	  * @return User
	  * @throws
	 */
	User getByLoginNameAndPassword(String username, String password) throws Exception;

	/**
	 * 将角色是该id对应的角色的用户改成上一级角色
	 * @param id角色id
	 */
	void updateRole(Long id);
	
	/**
	 * 校验用户名是否存在
	 * @return
	 */
	boolean isRegister(String username);
	
	/**
	 * 获取某一分区的所有版主
	 * @param id
	 * @return
	 */
	List<User> getModeratorsBySection(Long id);
	
	/**
	 * 通过用户名模糊查询用户
	 * @param username
	 * @return 用户列表
	 */
	List<UserVo2> selectUserByUsername(String username);
	
	/**
	 * 通过用户名模糊查询用户
	 * @param username
	 * @return 用户列表
	 */
	List<User> selectUserByUsername1(String username);

	List<String> findUsername(String username);

	void setRoletoUser(List<String> ids, String rid);

}
