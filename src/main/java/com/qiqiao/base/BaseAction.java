package com.qiqiao.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiqiao.model.User;
import com.qiqiao.service.AttachService;
import com.qiqiao.service.BoardService;
import com.qiqiao.service.PrivilegeService;
import com.qiqiao.service.ReplyService;
import com.qiqiao.service.RoleService;
import com.qiqiao.service.SectionService;
import com.qiqiao.service.TopicService;
import com.qiqiao.service.UserService;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	protected SectionService sectionService;
	@Resource
	protected BoardService boardService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected UserService userService;
	@Resource
	protected ReplyService replyService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected AttachService attachService;
	
	
	protected int pageNum = 1;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 获取当前前台登陆用户
	 */
	protected User getCurrentUser() {
		return (User)ActionContext.getContext().getSession().get("login");
	}
	
	/**
	 * 获取当前后台登陆用户
	 */
	protected User getCurrentAdminUser() {
		return (User)ActionContext.getContext().getSession().get("admin");
	}
	
	protected String saveUploadTempFile(File upload) {
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload-files");
//		System.out.println("basePath=" + basePath);
		SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd/");
		String subPath = format.format(new Date());
		String path = basePath + subPath + UUID.randomUUID();
//		System.out.println("path=" + path);
		File file = new File(basePath + subPath);
		if( !file.exists() ) {
			file.mkdirs();
		}
		upload.renameTo(new File(path));
		return path;
	}
}
