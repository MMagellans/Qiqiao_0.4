<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>用户登录</title>
    <%@ include file="/WEB-INF/jsp/front/public/public.jspf"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.5.2.min.js"></script>
	<script type="text/javascript">
		
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
		</div>
		<div id="comm_12">
		<s:form action="userAction_register">
			<table cellpadding="0" cellspacing="0" style="border: 0px;">
				<tr>
					<td class="comm_td3"><span class="span_3">用户名:</span></td>
					<td class="comm_td4"><input id="username_re" type="text" name="username" class="text_1"/></td>
					<td class="comm_td5"><span id="username_validate" class="span_7"></span></td>
				</tr>
				<tr>
					<td class="comm_td3"><span class="span_3">密码:</span></td>
					<td class="comm_td4"><input id="password_re" type="password" name="password" class="text_1"/></td>
					<td class="comm_td5"><span id="password_validate" class="span_8"></span></td>
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
