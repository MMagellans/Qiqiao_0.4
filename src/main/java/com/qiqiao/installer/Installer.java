package com.qiqiao.installer;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qiqiao.model.Privilege;
import com.qiqiao.model.User;

@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// ===================================================
		// 一、超级管理员
		User user = new User();
		user.setName("超级管理员");
		user.setUsername("admin");
		user.setPassword(DigestUtils.md5Hex("admin")); // 要使用MD5摘要
		session.save(user); // 保存

		// ===================================================
		// 二、权限数据
		Privilege menu, menu1, menu2, menu3, menu4;

		menu = new Privilege(null, "论坛管理", null);
		menu1 = new Privilege("roleAction_list", "论坛风格", menu);
		menu2 = new Privilege("departmentAction_list", "版块管理",menu);
		menu3 = new Privilege("userAction_list", "分区管理",menu);
		menu4 = new Privilege("userAction_list", "用户管理",menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		
		menu = new Privilege(null, "系统管理", null);
		menu1 = new Privilege("roleAction_list", "广告设置", menu);
		menu2 = new Privilege("departmentAction_list", "词语过滤",menu);
		menu3 = new Privilege("departmentAction_list", "权限管理",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		

	}

	public static void main(String[] args) {
		System.out.println("正在执行安装...");

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();

		System.out.println("== 安装完毕   ==");
	}
}

