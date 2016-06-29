package com.qiqiao.view.action;

import java.io.File;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.BaseAction;
import com.qiqiao.cfg.Configuration;
import com.qiqiao.model.User;
import com.qiqiao.util.ReadXmlUtil;

@Controller
@Scope("prototype")
public class AdminAction extends BaseAction {
	
	public String getBbstitle() {
		return bbstitle;
	}
	public void setBbstitle(String bbstitle) {
		this.bbstitle = bbstitle;
	}
	public File getBbsadminicon() {
		return bbsadminicon;
	}
	public void setBbsadminicon(File bbsadminicon) {
		this.bbsadminicon = bbsadminicon;
	}
	public String getBbsadminiconFileName() {
		return bbsadminiconFileName;
	}
	public void setBbsadminiconFileName(String bbsadminiconFileName) {
		this.bbsadminiconFileName = bbsadminiconFileName;
	}
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String style1;
	private String result;
	private String yzm;
	//-----基本参数-----------------
	private int pageSize;
	private String bbsname;
	private String bbstitle;
	private String authorname;
	private File topImage;
	private String topImageFileName;
	private File buttomImage;
	private String buttomImageFileName;
	private File bbsicon;
	private String bbsiconFileName;
	private File bbsadminicon;
	private String bbsadminiconFileName;
	
	/**
	 * 后台登陆页面
	 * @return
	 * @throws Exception
	 */
	public String loginUI() throws Exception {
		//检测用户是否已经登陆
		if(getCurrentAdminUser() != null) {//已登录
			return SUCCESS;
			
		}else {//还未登陆
			//检测前台是否登录
			User user = getCurrentUser();//获取当前前台登陆用户
			if(user == null) {//若用户前台未登录,则跳转到前台登陆页面
				return "frontLoginUI";
			}else {//若已登录，则检查该用户是否有登陆后台的权限
				if(user.hasPrivilegeByUrl("adminAction_loginUI")) {
					return "loginUI";//跳转到后台登陆界面
				}else {
					return "noPrivilegeError";
				}
			}
		}
		
	}
	/**
	 * 后台登陆
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		//System.out.println("用户名："+username+"  密码："+password);
		if(username == null || password == null || username.length() == 0 || password.length() == 0) {
			addFieldError("loginerror", "用户名或密码不能为空！");
			return "loginUI";
		}
		//判断验证码
		String yzm1 = ActionContext.getContext().getSession().get("yzm").toString();
		if(!yzm1.equals(yzm)) {
			addFieldError("loginerror", "验证码输入错误!");
			return "loginUI";
		}
		User user = userService.getByLoginNameAndPassword(username,password);
		if(user == null) {
			addFieldError("loginerror", "用户名或密码错误！");
			return "loginUI";
		}else {
			ActionContext.getContext().getSession().put("admin", user);
			ActionContext.getContext().put("operationInfo", "登陆成功");
			ActionContext.getContext().put("toWhere", "adminAction_loginUI.action");
			return "toForward";
		}
	}
	
	/**
	 * 退出后台登陆
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("admin");
		return "toLoginUI";
	}
	
	/**
	 * 基本设置页面
	 * @return
	 */
	public String basicSet() {
		//准备一些回显数据
		style1 = Configuration.getStyle();//论坛风格
		pageSize = Configuration.getPageSize();//每页显示几条
		return "basicSet";
	}
	
	/**
	 * 设置论坛风格
	 * @return
	 */
	public String setStyle() {
		Configuration.setStyle(style1);
		ActionContext.getContext().getApplication().put("style", style1);
		return "toBasicSet";
	}
	
	public String setPageSize() {
		try{
			//修改分页大小
			Configuration.setPageSize(pageSize+"");
			if(topImage != null) {
				String topImage_str = saveUploadFile(topImage,topImageFileName);
				if(topImage_str != null) {
					ActionContext.getContext().getApplication().put("topImage", "/images/config/"+topImage_str);
					ReadXmlUtil.setImagePathNode("topImage", topImage_str);
				}
			}
			if(buttomImage != null) {
				String buttomImage_str = saveUploadFile(buttomImage,buttomImageFileName);
				if(buttomImage_str != null) {
					ActionContext.getContext().getApplication().put("buttomImage", "/images/config/"+buttomImage_str);
					ReadXmlUtil.setImagePathNode("buttomImage", buttomImage_str);
				}
			}
			if(bbsicon != null) {
				String bbsicon_str = saveUploadFile(bbsicon,bbsiconFileName);
				if(bbsicon_str != null) {
					ActionContext.getContext().getApplication().put("bbsicon", "/images/config/"+bbsicon_str);
					ReadXmlUtil.setImagePathNode("bbsicon", bbsicon_str);
				}
			}
			if(bbsadminicon != null) {
				String bbsicon_str = saveUploadFile(bbsadminicon,bbsadminiconFileName);
				if(bbsicon_str != null) {
					ActionContext.getContext().getApplication().put("bbsadminicon", "/images/config/"+bbsicon_str);
					ReadXmlUtil.setImagePathNode("bbsadminicon", bbsicon_str);
				}
			}
			if(bbsname != null && bbsname.trim().length() > 0) {
				ActionContext.getContext().getApplication().put("bbsname", bbsname);
				ReadXmlUtil.setNameNode("bbsname", bbsname);
			}
			if(authorname != null && authorname.trim().length() > 0) {
				ActionContext.getContext().getApplication().put("authorname", authorname);
				ReadXmlUtil.setNameNode("authorname", authorname);
			}
			if(bbstitle != null && bbstitle.trim().length() > 0) {
				ActionContext.getContext().getApplication().put("bbstitle", bbstitle);
				ReadXmlUtil.setNameNode("bbstitle", bbstitle);
			}
			//result = "{result:'修改成功！'}";//组建成json格式
			ActionContext.getContext().put("operationInfo", "修改成功");
			ActionContext.getContext().put("toWhere", "adminAction_basicSet.action");
			return "toForward";
		}catch(Exception e) {
			//result = "{result:'修改失败！'}";//组建成json格式
			e.printStackTrace();
			return "exception";
		}
	}
	
	protected String saveUploadFile(File upload,String filename) {
		if(upload == null) {
			return null;
		}else {
			String [] s = filename.split("\\.");
			String uuid = UUID.randomUUID().toString();
			String basePath = ServletActionContext.getServletContext().getRealPath("\\images\\config\\");
			String path = basePath +"\\"+ uuid +"."+s[s.length-1];
			File file = new File(basePath);
			if( !file.exists() ) {
				file.mkdirs();
			}
			upload.renameTo(new File(path));
			return uuid+"."+s[s.length-1];
		}
	}
	
	//--------------------------
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStyle1() {
		return style1;
	}
	public void setStyle1(String style1) {
		this.style1 = style1;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBbsname() {
		return bbsname;
	}
	public void setBbsname(String bbsname) {
		this.bbsname = bbsname;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public File getTopImage() {
		return topImage;
	}
	public void setTopImage(File topImage) {
		this.topImage = topImage;
	}
	public File getButtomImage() {
		return buttomImage;
	}
	public void setButtomImage(File buttomImage) {
		this.buttomImage = buttomImage;
	}
	public File getBbsicon() {
		return bbsicon;
	}
	public void setBbsicon(File bbsicon) {
		this.bbsicon = bbsicon;
	}
	public String getTopImageFileName() {
		return topImageFileName;
	}
	public void setTopImageFileName(String topImageFileName) {
		this.topImageFileName = topImageFileName;
	}
	public String getButtomImageFileName() {
		return buttomImageFileName;
	}
	public void setButtomImageFileName(String buttomImageFileName) {
		this.buttomImageFileName = buttomImageFileName;
	}
	public String getBbsiconFileName() {
		return bbsiconFileName;
	}
	public void setBbsiconFileName(String bbsiconFileName) {
		this.bbsiconFileName = bbsiconFileName;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	
	
}
