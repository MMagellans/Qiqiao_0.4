package com.qiqiao.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiqiao.cfg.Configuration;
import com.qiqiao.model.Privilege;
import com.qiqiao.service.PrivilegeService;
import com.qiqiao.util.ReadXmlUtil;

public class InitServletContextListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent ace) {
		ServletContext application = ace.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		List<Privilege> topPrivilegeList = privilegeService.findTopPrivilegeList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("-------------已准备好顶级权限数据-------------");
		
		List<String> allPrivilegeUrls = privilegeService.findAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("-------------已准备好所有权限URL数据-------------");
		application.setAttribute("style", Configuration.getStyle());
		System.out.println("-------------已准备好论坛风格-------------");
		
		//准备论坛的各种参数
		application.setAttribute("topImage", "/images/config/"+ReadXmlUtil.getImagePathNode("topImage"));//论坛顶部图片路径
		application.setAttribute("buttomImage", "/images/config/"+ReadXmlUtil.getImagePathNode("buttomImage"));//论坛底部图片路径
		application.setAttribute("bbsicon", "/images/config/"+ReadXmlUtil.getImagePathNode("bbsicon"));//论坛logo
		application.setAttribute("bbsadminicon", "/images/config/"+ReadXmlUtil.getImagePathNode("bbsadminicon"));//论坛后台顶部logo
		application.setAttribute("bbsname", ReadXmlUtil.getNameNode("bbsname"));//论坛名称
		application.setAttribute("authorname", ReadXmlUtil.getNameNode("authorname"));//论坛名称
		application.setAttribute("bbstitle", ReadXmlUtil.getNameNode("bbstitle"));//论坛名称
		
		System.out.println("----------------论坛参数加载完毕-------------------------");
		
		
		
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
