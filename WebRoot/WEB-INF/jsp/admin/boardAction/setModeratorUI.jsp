<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>分区设置页面</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
    <script src="${pageContext.request.contextPath}/script/admin/setModerator.js"></script>
    <script type="text/javascript">
		$(document).ready(function() {
  			$("table[name='result']").find("tbody").find("tr").find("td").css("background-color","#f2f2f2");
  			$("table[name='result']").find("tbody").find("tr").find("td:odd").css("background-color","white");
  			$("table[name='result']").find("td").css("border-bottom","1px solid #BED5F3").css("height","25px");
  		});
	</script>
  </head>
  
  <body>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >•&nbsp;先输入用户名点击搜索</span><br />
    		<span class="ac_span3" >•&nbsp;为了方便管理，每个版块最多拥有5名版主</span>
    	</div>
    	
    </div>
    <div style="margin-top: 15px;">
   		<s:hidden id="boardId" name="%{#board.id}"></s:hidden>
   		<table cellpadding="0" name="result" cellspacing="0" width="100%" style="border:1px solid #BED5F3;border-bottom: 0px;">
	    	<thead>
	    		<tr class="ac_tr1"><td colspan="5" class="ac_td1">&nbsp;<span class="ac_span1">本版版主列表</span></td></tr>
	    	</thead>
	    	<tbody>
		    	<tr>
		    		<td width="25%" align="center"><span class="ac_span2">用户名</span></td>
		    		<td width="10%" align="center"><span class="ac_span2">主题/回复</span></td>
		    		<td width="10%" align="center"><span class="ac_span2">积分</span></td>
		    		<td width="30%" align="center"><span class="ac_span2">注册时间</span></td>
		    		<td width="25%" align="center"><span class="ac_span2">操作</span></td>
		    	</tr>
		    	
		    	<s:iterator value="%{#board.moderator}">
		    	<tr>
		    		<td width="25%">&nbsp;<span class="ac_span2">${username }</span></td>
		    		<td width="10%">&nbsp;<span class="ac_span2">${topicCount }/${replyCount }</span></td>
		    		<td width="10%">&nbsp;<span class="ac_span2">${credits }</span></td>
		    		<td width="30%">&nbsp;<span class="ac_span2"><fmt:formatDate value="${createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
		    		<td width="25%">&nbsp;
		    			<span class="ac_span2">
		    				【<s:a action="userAction_deleteModerator?id=%{id}" class="a_0" style="color:black;">删除</s:a>】
		    			</span>
		    		</td>
		    	</tr>
		    	</s:iterator>
	    	</tbody>
	    </table>
    </div>
    
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">查询用户</span></div>
    	<div id="ac_os3">
    		<table cellpadding="0" cellspacing="0" width="100%">
    			<tr>
    				<td class="ac_td2"><span class="span_6" style="color:black;">用户名:</span></td>
    				<td class="ac_td3">
    					<input type="text" name="findName" style="width: 200px;border: 1px solid #ABADB3;height: 18px;" />
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" align="center" style="padding: 8px 0px;">
    					<input type="image" onclick="selectUserDialog()" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" />
    				</td>
    			</tr>
    		</table>
    	</div>
    </div>
    
    <div style="display: none; ">
    	<div id="selectResult" style="width:600px; height:335px;">
    		<div style="width:250px; height:20px;margin: 0 auto;">
    			<span class="ac_span2">选中想要设置成版主的用户点击确定即可</span>
    		</div>
    		<div style="width:600px; height:315px;margin: 0 auto;">
    			<table name="result" cellpadding="0" cellspacing="0" width="100%" style="border:1px solid #BED5F3;border-bottom: 0px;">
			    	<tbody>
				    	<tr id="findUserTr">
				    		<td width="10%" align="center"><span class="ac_span2">选择</span></td>
				    		<td width="20%" align="center"><span class="ac_span2">用户名</span></td>
				    		<td width="10%" align="center"><span class="ac_span2">积分</span></td>
				    		<td width="30%" align="center"><span class="ac_span2">注册时间</span></td>
				    		<td width="30%" align="center"><span class="ac_span2">备注</span></td>
				    	</tr>
			    	</tbody>
			    </table>
    		</div>
    	</div>
    </div>
    
  </body>
</html>
