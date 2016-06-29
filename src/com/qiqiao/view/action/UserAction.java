package com.qiqiao.view.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.ModelDrivenBaseAction;
import com.qiqiao.model.Board;
import com.qiqiao.model.Role;
import com.qiqiao.model.Section;
import com.qiqiao.model.User;

@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {

	private static final long serialVersionUID = 1L;
	private String result;

	/** 登陆页面 */
	public String loginUI() {
		return "loginUI";
	}

	/** 登录 */
	public String login() throws Exception {
		User user = userService//
				.getByLoginNameAndPassword(model.getUsername(), model.getPassword());
		if (user == null) {
			result = "{result:'用户名或密码错误！'}";// 组建成json格式
			return SUCCESS;
		} else {
			result = "{result:'登陆成功！'}";// 组建成json格式
			Board b = null;
			Section s = null;
			if(user.getBoard() != null) {
				b = boardService.getById(user.getBoard().getId());
			}
			if(user.getSection() != null) {
				s = sectionService.getById(b.getSection().getId());
			}
			user.setBoard(b);
			user.setSection(s);
			ActionContext.getContext()//
					.getSession().put("login", user);
			return SUCCESS;
		}
	}

	/** 退出登录 */
	public String logout() throws Exception {
		result = "{result:'退出成功！'}";// 组建成json格式
		ActionContext.getContext().getSession().remove("login");
		ActionContext.getContext().getSession().remove("admin");
		return SUCCESS;
	}

	/**
	 * 用户注册页面
	 * 
	 * @return
	 */
	public String registerUI() throws Exception {

		return "registerUI";
	}

	public String usernameValidate() throws Exception {
		// System.out.println("用户名校验："+model.getUsername());
		if (userService.isRegister(model.getUsername())) {
			result = "{result:'yes'}";
		} else {
			result = "{result:'no'}";
		}
		return SUCCESS;
	}

	public String register() {
		try{
			// 添加新用户，自动注入的属性有：用户名、密码、邮箱
			model.setPassword(DigestUtils.md5Hex(model.getPassword()));// 密码需经过加密处理
			// 手动注入属性如下：
			model.setTopicCount(0);
			model.setReplyCount(0);
			model.setCredits(0);
			model.setCreateTime(new Date());
			model.setRole(new Role(2L));
			model.setIcon("user_default.gif");
			model.setType(User.USER_NORMAL);
			userService.save(model);
			ActionContext.getContext().put("operationInfo", "注册成功!");
			ActionContext.getContext().put("toWhere", "sectionAction_list.action");
			return "toForward";
		}catch(Exception e) {
			ActionContext.getContext().put("operationInfo", "注册失败!");
			ActionContext.getContext().put("toWhere", "sectionAction_list.action");
			return "toForward";
		}
		
	}

	/**
	 * 查询用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String selectUserByUsername() throws Exception {
		String username = model.getUsername();
		List<User> users = userService.selectUserByUsername1(username);
		StringBuilder sb = new StringBuilder();
		// System.out.println("查询结果长度");
		if (users.size() <= 0) {
			sb.append("<tr><td colspan='5' align='center'><span class='ac_span2'>查无结果</span></td></tr>");
		} else {
			for (User u : users) {
				sb.append("<tr>").append("<td width='10%' align='center'><input type='checkbox' name='userId' value='").append(u.getId()).append("' /></td>")
						.append("<td width='20%' align='center'>&nbsp;<span class='ac_span2'>").append(u.getUsername()).append("</span></td>")
						.append("<td width='10%' align='center'>&nbsp;<span class='ac_span2'>").append(u.getCredits()).append("</span></td>")
						.append("<td width='20%' align='center'>&nbsp;<span class='ac_span2'>").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(u.getCreateTime())).append("</span></td>")
						.append("<td width='20%' align='center'>&nbsp;<span class='ac_span2'>");
				if (u.getBoard() != null) {
					sb.append("该用户已是" + u.getBoard().getName() + "板块版主");
				}

				sb.append("</span></td>").append("</tr>");
			}
		}
		result = sb.toString();
		// System.out.println(result);
		return "selectUserByUsername";
	}

	/**
	 * 板块版主删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteModerator() throws Exception {
		// 获取用户信息
		User user = userService.getById(model.getId());
		if (user != null) {
			user.setBoard(null);
			userService.update(user);
			ActionContext.getContext().put("operationInfo", "版主删除成功");
		} else {
			ActionContext.getContext().put("operationInfo", "版主删除失败");
		}
		ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
		return "toForward";
	}
	
	public String selectUser() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String xmlStrat = "<xml><span>";
		String xmlEnd = "</span></xml>";
		String xmlDoc = "";
		try{
			List<String> names = userService.findUsername(model.getUsername());
			StringBuffer sb = new StringBuffer("");
			if(names != null) {
				for(int i=0;i<names.size();i++) {
					if(i == names.size()-1) {
						sb.append(names.get(i));
					}else {
						sb.append(names.get(i)+",");
					}
				}
			}
			xmlDoc = sb.toString();
		}catch(Exception e) {
			e.printStackTrace();
			xmlDoc = "false";
		}
		out.write(xmlStrat + xmlDoc + xmlEnd);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 删除主题
	 * 
	 * @return
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	public String setRoletoUser() {
		HttpServletResponse response = ServletActionContext.getResponse();
		String rid = ServletActionContext.getRequest().getParameter("rid");
		String tids = ServletActionContext.getRequest().getParameter("tids");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String xmlStrat = "<xml><span>";
		String xmlEnd = "</span></xml>";
		String xmlDoc = "";
		try{
			String[] tIds = tids.split(",");
			List<String> ids = new ArrayList<String>();
			for(int i=0;i<tIds.length;i++){
				if(tIds[i] != null && !"".equals(tIds[i])) {
					ids.add(tIds[i]);
				}
			}
			userService.setRoletoUser(ids,rid);
				xmlDoc = "操作成功！";
		}catch(Exception e) {
			e.printStackTrace();
			xmlDoc = "操作失败！";
		}
		out.write(xmlStrat + xmlDoc + xmlEnd);
		out.flush();
		out.close();
		return null;
	}
	

	/*************************************/

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
