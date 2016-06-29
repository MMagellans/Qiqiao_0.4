package com.qiqiao.model;


public class Attach {
	
	private Long id;
	private String path;
	private String name;//附件名称
	private String suffix;//附件后缀名
	private Topic topic;//附件所属主题
	//private Long boardId;//板块ID
	//private Long sectionId;//分区ID
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
	
}
