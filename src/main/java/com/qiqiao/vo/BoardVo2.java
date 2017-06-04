package com.qiqiao.vo;


public class BoardVo2 {
	
	private Long id;
	private String name;//板块名称
	
	public BoardVo2() {}
	public BoardVo2(Long id, String name) {
		this.id = id;
		this.name = name;
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
	
}
