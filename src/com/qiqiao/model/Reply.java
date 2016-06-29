package com.qiqiao.model;

import java.util.Date;

/**
 * 主题回复实体
 * 
 *
 */
public class Reply {

	public static final int STATE_NORMAL = 0;//普通状态
	public static final int STATE_DELETE = 1;//删除状态
	
	private Long id;//
	private String content;//回复内容
	private Date postTime;//回复发表时间
	private String ipAddr;//发表回复的ip地址
	private int state;//回复状态
	private Long boardId;//回复所在版块
	private Long sectionId;//回复所在分区
	private Topic topic;//回帖所属主题
	private User user;//回帖人
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	
	
}
