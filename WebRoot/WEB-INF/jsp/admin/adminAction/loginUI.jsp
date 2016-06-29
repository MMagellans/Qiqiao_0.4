<%@page import="javax.imageio.ImageIO"%>
<%@page import="com.qiqiao.util.ImageUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head runat="server">
		<title>后台管理登录界面</title>
		<link
			href="${pageContext.request.contextPath}/style/common/css/admin.css"
			rel="stylesheet" type="text/css" />
		
	</head>
	<body>
		<form id="form1" action="adminAction_login.action" method="post">
			<div class="Main">
				<ul>
					<li class="top"></li>
					<li class="top2"></li>
					<li class="topA"></li>
					<li class="topB">
						<span> <img
								src="${pageContext.request.contextPath}/style/common/images/admin/login/logo.jpg"
								alt="" style="" /> </span>
					</li>
					<li class="topC"></li>
					<li class="topD">
						<ul class="login">
							<li>
								<span class="left">用户名：</span>
								<span style=""> <input id="Text1" type="text" name="username" class="txt" />
								</span>
							</li>
							<li>
								<span class="left">密 码：</span>
								<span style=""> <input id="Text2" type="password" name="password" class="txt" />
								</span>
							</li>
							<li>
								
								<div >
									
									<table cellpadding="0" cellspacing="0" width="200" height="35">
										<tr>
											<td><span class="left">验证码：</span></td>
											<td style="padding-left: 4px;"><img src="imageCode.action" width="100" height="30" onclick="this.src='imageCode.action?time-'+(new Date()).getTime()"/></td>
											<td style="padding-left: 5px;"><input id="Text3" type="text" class="txtCode" name="yzm" /></td>
										</tr>
									</table>
								</div>
								
							</li>
							<li>
								<!-- <s:fielderror name="loginerror" /> -->
								<span class="left" style="color:red;width:200px;"><s:fielderror name="loginerror" /></span>
							</li>
						</ul>
					</li>
					<li class="topE"></li>
					<li class="middle_A"></li>
					<li class="middle_B"></li>
					<li class="middle_C">
						<span class="btn"> <input type="image"
								src="${pageContext.request.contextPath}/style/common/images/admin/login/btnlogin.gif" />
						</span>
					</li>
					<li class="middle_D"></li>
					<li class="bottom_A"></li>
					<li class="bottom_B"></li>
				</ul>
			</div>
		</form>
	</body>
</html>

