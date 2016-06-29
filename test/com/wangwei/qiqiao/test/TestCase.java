package com.wangwei.qiqiao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qiqiao.model.Board;
import com.qiqiao.model.Role;
import com.qiqiao.model.User;
import com.qiqiao.service.BoardService;
import com.qiqiao.service.RoleService;
import com.qiqiao.service.SectionService;
import com.qiqiao.service.UserService;
import com.qiqiao.vo.BoardVo2;
import com.qiqiao.vo.SectionVo;
import com.qiqiao.vo.UserVo2;


public class TestCase {
	
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void testVipList() {
		RoleService roleService = (RoleService)ac.getBean("roleServiceImpl");
		List<Role> list = roleService.findVip();
		for(Role role : list) {
			System.out.println(role.getName() + "-----" + role.getMinCredits() + "------" + role.getRoleType());
		}
	}
	
	@Test
	public void getSectionVos() {
		SectionService sectionService = (SectionService)ac.getBean("sectionServiceImpl");
		List<SectionVo> list = sectionService.getSectionVos();
		for(SectionVo s : list) {
			System.out.println(s.getId()+"---------"+s.getName());
		}
		
	}
	
	@Test
	public void testSelectUserByUsername() {
		UserService userService = (UserService)ac.getBean("userServiceImpl");
		List<UserVo2> users = userService.selectUserByUsername("bb");
		System.out.println("查询结果有："+users.size());
		for(UserVo2 u : users) {
			System.out.println(u.getUsername());
		}
	}
	
	@Test
	public void testSelectUserByUsername1() {
		UserService userService = (UserService)ac.getBean("userServiceImpl");
		List<User> users = userService.selectUserByUsername1("a");
		System.out.println("查询结果有："+users.size());
		for(User u : users) {
			System.out.println(u.getUsername());
		}
	}
	
	@Test
	public void testFindAllBoards() {
		BoardService boardService = (BoardService)ac.getBean("boardServiceImpl");
		List<Board> boards = boardService.findAll();
		for(Board b : boards) {
			System.out.println(b.getName());
		}
	}
	
	@Test
	public void testFindAllBoardsVo2() {
		BoardService boardService = (BoardService)ac.getBean("boardServiceImpl");
		List<BoardVo2> boards = boardService.findAllBoardsVo2();
		for(BoardVo2 b : boards) {
			System.out.println(b.getName());
		}
	}
	
	public void testMerger() {
		BoardService boardService = (BoardService)ac.getBean("boardServiceImpl");
		
	}
}
