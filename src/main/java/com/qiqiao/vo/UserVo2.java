package com.qiqiao.vo;

import java.util.Date;

import com.qiqiao.model.Board;

public class UserVo2 {
	private Long id;
	private String username;
	private int credits;
	private Date createTime;
	private Board board;
	
	public UserVo2() {}

	public UserVo2(Long id, String username, int credits, Date createTime,
			Board board) {
		this.id = id;
		this.username = username;
		this.credits = credits;
		this.createTime = createTime;
		this.board = board;
	}
	public UserVo2(Long id, String username, int credits) {
		this.id = id;
		this.username = username;
		this.credits = credits;
	}

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

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	
	
	
}
