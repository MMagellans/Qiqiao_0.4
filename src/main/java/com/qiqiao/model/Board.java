package com.qiqiao.model;

import java.util.Date;
import java.util.Set;

/**
 * 板块实体
 *
 *
 */
public class Board {

	public static final int STATE_NORMAL = 0;//普通状态（默认）
	public static final int STATE_HIDE = 1;//隐藏状态
	public static final int STATE_DELETE = 2;//删除
	
	private Long id;
	private String name;//板块名称
	private String description;//板块描述
	private int topicCount;//板块主题数
	private int articleCount;//板块文章数
	private Date createTime;//板块创建时间
	private int sortNum;//板块排序号
	private int state;//板块状态
	private String highColor;//板块高亮颜色
	private String icon;//板块图标
	private String allowRole;//板块允许访问的角色
	private int todayArticleCount;//今日文章数，该属性不放入数据库，只是在取的时候设置进去的
	private Topic lastTopic;//板块最后主题
	private Section section;//板块所属分区
	private Set<User> moderator;//板块版主
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getHighColor() {
		return highColor;
	}
	public void setHighColor(String highColor) {
		this.highColor = highColor;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAllowRole() {
		return allowRole;
	}
	public void setAllowRole(String allowRole) {
		this.allowRole = allowRole;
	}
	public Topic getLastTopic() {
		return lastTopic;
	}
	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Set<User> getModerator() {
		return moderator;
	}
	public void setModerator(Set<User> moderator) {
		this.moderator = moderator;
	}
	public int getTodayArticleCount() {
		return todayArticleCount;
	}
	public void setTodayArticleCount(int todayArticleCount) {
		this.todayArticleCount = todayArticleCount;
	}
	
}
