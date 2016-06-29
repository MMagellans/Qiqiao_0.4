<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>角色列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.5.2.min.js"></script>
  	<link rel="stylesheet" href="${pageContext.request.contextPath }/style/common/css/admincommon.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath }/style/common/css/font.css" type="text/css"></link>
  	<script type="text/javascript">
  		$(document).ready(function() {
  			$("table").find("tbody").find("tr").find("td").css("background-color","#f2f2f2");
  			$("table").find("tbody").find("tr").find("td:odd").css("background-color","white");
  			$("td").css("border-bottom","1px solid #BED5F3").css("height","25px");
  		});
  	</script>
  </head>
  
  <body>
    <table cellpadding="0" cellspacing="0" width="100%" style="border:1px solid #BED5F3;border-bottom: 0px;margin-bottom: 10px;">
    	<thead>
    		<tr class="ac_tr1"><td colspan="5" class="ac_td1">&nbsp;<span class="ac_span1">角色列表</span></td></tr>
    	</thead>
    	<tbody>
	    	<tr>
	    		<td width="25%" align="center"><span class="ac_span2">名称</span></td>
	    		<td width="10%" align="center"><span class="ac_span2">最小积分</span></td>
	    		<td width="10%" align="center"><span class="ac_span2">星星数</span></td>
	    		<td width="30%" align="center"><span class="ac_span2">描述</span></td>
	    		<td width="25%" align="center"><span class="ac_span2">操作</span></td>
	    	</tr>
	    	<s:iterator value="roleList">
		    	<tr>
		    		<td width="25%">&nbsp;<span class="ac_span2">${name}</span></td>
		    		<td width="10%">&nbsp;<span class="ac_span2">${minCredits}</span></td>
		    		<td width="10%">&nbsp;<span class="ac_span2">${stars}</span></td>
		    		<td width="30%">&nbsp;<span class="ac_span2">${description}</span></td>
		    		<td width="25%">&nbsp;
		    			<span class="ac_span2">
		    				<s:a action="roleAction_setPrivilegeUI?id=%{id}" cssClass="a_0" cssStyle="color:black;">设置权限</s:a>
		    				<a href="" class="a_0" style="color:black;">修改</a>
		    			</span>
		    		</td>
		    	</tr>
	    	</s:iterator>
    	</tbody>
    </table>
    <a href=""><img src="${pageContext.request.contextPath}/style/common/images/button/addNew.PNG" /></a>
  </body>
</html>
