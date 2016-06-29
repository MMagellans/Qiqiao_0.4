package com.qiqiao.vo;

import com.qiqiao.model.Section;

public class BoardVo {
	
	private Long id;
	private String name;//板块名称
	private String description;//板块描述
	private int state;//板块状态
	private String highColor;//板块高亮颜色
	private String allowRole;//板块允许访问的角色
	private Section section;//板块所属分区
	
	public BoardVo() {}
	public BoardVo(Long id, String name, String description, int state,
			String highColor, String allowRole, Section section) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.state = state;
		this.highColor = highColor;
		this.allowRole = allowRole;
		this.section = section;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getAllowRole() {
		return allowRole;
	}
	public void setAllowRole(String allowRole) {
		this.allowRole = allowRole;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
}
