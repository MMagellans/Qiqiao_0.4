package com.qiqiao.model;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

/**
 * 用户实体
 * 
 * 
 * 
 */
public class User {

	public static final int USER_ADMIN = 0;// 管理人员（在一些用户管理中是查询不到的）
	public static final int USER_NORMAL = 1;// 普通人员

	private Long id;
	private String username;// 用户名
	private String password;// 密码
	private String name;// 姓名
	private int topicCount;// 主题数
	private int replyCount;// 回复数
	private int credits;// 积分
	private String email;// 邮箱
	private String tel;// 电话
	private String icon;// 用户个性图像
	private Date birth;// 生日
	private String gender;// 性别
	private Date createTime;// 注册时间
	private int type;// 用户类型
	private Role role;// 角色
	private Section section;// 是哪个分区的版主，为空代表不是斑竹
	private Board board;// 是哪个板块的版主，为空代表不是斑竹

	public User() {
	}

	public User(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	/**
	 * 根据权限名称判断该用户是否拥有该权限
	 * 
	 * @param name
	 *            权限名称
	 * @return
	 */
	public boolean hasPrivilegeByName(String name) {
		// 判断超级管理员
		if (isAdmin()) {
			return true;
		}

		// 判断普通用户是否拥有权限
		for (Privilege privilege : role.getPrivileges()) {
			if (name.equals(privilege.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据权限URL判断该用户是否拥有该权限
	 * 
	 * @param privilegeUrl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasPrivilegeByUrl(String privilegeUrl) {
		// 判断超级管理员
		if (isAdmin()) {
			return true;
		}
		if (privilegeUrl.endsWith("UI")) {
			privilegeUrl = privilegeUrl.substring(0, privilegeUrl.length() - 2);
		}

		// 判断普通用户是否拥有权限
		List<String> allPrivilegeUrls = (List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privilegeUrl)) {// 如果是不需要控制的权限（比如：登录，访问首页。。。。）
			return true;
		} else {// 如果是需要控制的权限
			for (Privilege privilege : role.getPrivileges()) {
				if (privilegeUrl.equals(privilege.getUrl())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是超级管理员
	 * 
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	private boolean isAdmin() {
		return "admin".equals(username);
	}

	/**
	 * 根据Url判断游客(未登录)是否拥有权限
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean guestHasPrivilegeByUrl(String url) {
		if (url.endsWith("UI")) {
			url = url.substring(0, url.length() - 2);
		}
		// 获取所有需要控制的权限
		List<String> allPrivilegeUrls = (List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (allPrivilegeUrls.contains(url)) {
			return false;
		} else
			return true;
	}

	/**
	 * 判断多个权限“或”
	 * 
	 * @param privilegeUrl
	 *            权限地址数组
	 * 
	 * @return
	 */
	public boolean hasPrivilegeByUrlsOr(String[] privilegeUrl) {
		// 判断超级管理员
		if (isAdmin()) {
			return true;
		}
		boolean flag = false;
		for (int i = 0; i < privilegeUrl.length; i++) {
			if (hasPrivilegeByUrl(privilegeUrl[i])) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 判断多个权限“且”
	 * 
	 * @param privilegeUrl
	 *            权限地址数组
	 * 
	 * @return
	 */
	public boolean hasPrivilegeByUrlsAnd(String[] privilegeUrl) {
		// 判断超级管理员
		if (isAdmin()) {
			return true;
		}
		boolean flag = true;
		for (int i = 0; i < privilegeUrl.length; i++) {
			if (!hasPrivilegeByUrl(privilegeUrl[i])) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 判断是否拥有版主的权限
	 */
	public boolean hasTopicOpreation(Board b,Section s) {
		// 判断超级管理员
		if (isAdmin()) {
			return true;
		}
		String[] pris = {"topicAction_topTopic","topicAction_digest","topicAction_recommend"};
		if(board == null && section == null) {
			return false;
		}else if(b == null) {
			return false;
		}else {
			if(hasPrivilegeByUrlsOr(pris) && (board.getId() == b.getId() || section.getId() == s.getId())) {
				return true;
			}else {
				return false;
			}
		}
		
	}

	/*********************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
