<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>用户注册</title>
    <%@ include file="/WEB-INF/jsp/front/public/public.jspf"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.5.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var regxp = new RegExp("^[0-9a-zA-Z_]*$");
			var emailRegxp = new RegExp("^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$");
			var usernameNode = $("#username_re");
			var passwordNode = $("#password_re");
			var confirmNode = $("#confirm_re");
			var emailNode = $("#email_re");
			usernameNode.focus(function() {
				$("#username_validate").attr("class","span_7").text("用户名由3到15个数字、字母或下划线组成");
			}).blur(function() {
				if(usernameNode.val().length < 3 || usernameNode.val() == "" ) {
					$("#username_validate").attr("class","span_8").text("用户名不得小于3个字符");
				}else if(!regxp.test(usernameNode.val())) {
					$("#username_validate").attr("class","span_8").text("用户名只能由数字、字母或下划线组成");
				}else {
					$.ajax({
						type: "POST",
						url: "usernameValidate.action?username="+usernameNode.val(),
						success:function(data) {
	  						var json = eval("("+data+")"); 
	  						if(json.result == "yes") {
	  							$("#username_validate").attr("class","span_8").text("该用户名已被注册");
	  						}else {
	  							$("#username_validate").text("").append("<img src=${pageContext.request.contextPath}/style/common/images/access_allow.gif />");
	  						}
  						},
  					dataType:"json"
					});
				}
			});
			
			passwordNode.focus(function() {
				$("#password_validate").attr("class","span_7").text("请填写密码,最小长度为8个字符");
			}).blur(function() {
				if(passwordNode.val().length < 8 || passwordNode.val() == "" ) {
					$("#password_validate").attr("class","span_8").text("密码太短,请勿少于8个字符");
				}else {
					$("#password_validate").text("").append("<img src=${pageContext.request.contextPath}/style/common/images/access_allow.gif />");
				}
			});
			
			confirmNode.focus(function() {
				$("#confirm_validate").attr("class","span_7").text("请再输一次密码");
			}).blur(function() {
				if(confirmNode.val() != passwordNode.val()) {
					$("#confirm_validate").attr("class","span_8").text("两次输入的密码不一致");
				}else {
					$("#confirm_validate").text("").append("<img src=${pageContext.request.contextPath}/style/common/images/access_allow.gif />");
				}
			});
			
			emailNode.focus(function() {
				$("#email_validate").attr("class","span_7").text("请输入正确的邮箱地址");
			}).blur(function() {
				if(!emailRegxp.test(emailNode.val())) {
					$("#email_validate").attr("class","span_8").text("邮箱格式不正确");
				}else {
					$("#email_validate").text("").append("<img src=${pageContext.request.contextPath}/style/common/images/access_allow.gif />");
				}
			});
		});
	</script>
  </head>
  
  <body>
  	<%@ include file="/WEB-INF/jsp/front/public/top.jspf"%>
  	<div id="comm_6">
		<a href=""><div id="path_1"></div></a><!-- 首页 -->
		<div id="path_2"></div>
		<div id="path_3">
			<a href="" class="a_1" style="line-height: 16px;">论坛</a>
		</div>
	</div>
	
	<div id="comm_10">
		<div id="comm_11">
			<span class="span_6" style="line-height:30px;margin-left:15px;">立即登录</span>
			<span class="span_2" style="line-height:30px;margin-right:15px;float:right;"><a class="a_0" href="">已有账号？现在登录</a></span>
		</div>
		<div id="comm_12">
		<s:form action="userAction_register">
			<table cellpadding="0" cellspacing="0" style="border: 0px;">
				<tr>
					<td class="comm_td3"><span style="color:red;">*</span><span class="span_3">用户名:</span></td>
					<td class="comm_td4"><input id="username_re" type="text" name="username" class="text_1"/></td>
					<td class="comm_td5"><span id="username_validate" class="span_7"></span></td>
				</tr>
				<tr>
					<td class="comm_td3"><span style="color:red;">*</span><span class="span_3">密码:</span></td>
					<td class="comm_td4"><input id="password_re" type="password" name="password" class="text_1"/></td>
					<td class="comm_td5"><span id="password_validate" class="span_8"></span></td>
				</tr>
				<tr>
					<td class="comm_td3"><span style="color:red;">*</span><span class="span_3">确认密码:</span></td>
					<td class="comm_td4"><input id="confirm_re" type="password" name="confirm" class="text_1"/></td>
					<td class="comm_td5"><span id="confirm_validate" class="span_8"></span></td>
				</tr>
				<tr>
					<td class="comm_td3"><span style="color:red;">*</span><span class="span_3">邮箱:</span></td>
					<td class="comm_td4"><input id="email_re" type="text" name="email" class="text_1"/></td>
					<td class="comm_td5"><span id="email_validate" class="span_7"></span></td>
				</tr>
				<tr>
					<td class="comm_td3" style="border: 0px;">&nbsp;</td>
					<td class="comm_td4" style="border: 0px;">
						<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.jpg" style="margin-left: 15px;"/>
					</td>
					<td class="comm_td5" style="border: 0px;">&nbsp;</td>
				</tr>
			</table>
		</s:form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/front/public/buttom.jspf" %>
  </body>
</html>
