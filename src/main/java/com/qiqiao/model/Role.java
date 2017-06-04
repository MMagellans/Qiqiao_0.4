package com.qiqiao.model;

import java.util.Set;

/**
 * 角色实体
 * 
 *
 */
public class Role {
	
	public static int TYPE_VIP = 0;//会员用户组
	public static int TYPE_SYS = 0;//系统用户组
	
	private Long id;
	private String name;//名称
	private String description;//描述
	private int minCredits;//最小积分
	private int stars;//星星数
	private int roleType;//用户类型（系统用户组，会员用户组）
	private Set<Privilege> privileges;//所拥有的权限
	
	public Role() {}
	
	public Role(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinCredits() {
		return minCredits;
	}
	public void setMinCredits(int minCredits) {
		this.minCredits = minCredits;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	
}
