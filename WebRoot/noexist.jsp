<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>板块不存在</title>
		<%@ include file="/WEB-INF/jsp/front/public/public.jspf"%>
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
			<span class="span_6" style="line-height:30px;margin-left:15px;">系统提示</span>
		</div>
		<div id="comm_12">
			<table cellpadding="0" cellspacing="0" style="border: 0px solid red;width: 80%;height: 100%;">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr style="height:70px;">
					<td><img src="${pageContext.request.contextPath }/images/info.gif" width="100" height="100"/></td>
					<td><font style="font-family: microsoft yahei;">该板块不存在！</font></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
		</div>
		<%@ include file="/WEB-INF/jsp/front/public/buttom.jspf" %>
	</body>
</html>
