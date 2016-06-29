<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>角色列表</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
  	<link rel="stylesheet" href="${pageContext.request.contextPath }/style/common/css/admincommon.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath }/style/common/css/font.css" type="text/css"></link>
  	<script src="${pageContext.request.contextPath}/script/admin/addUser.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function() {
  			$("table").find("tbody").find("tr").find("td").css("background-color","#f2f2f2");
  			$("table").find("tbody").find("tr").find("td:odd").css("background-color","white");
  			$("td").css("border-bottom","1px solid #BED5F3").css("height","25px");
  		});
  		function addRole() {
  			$("#addrole").css("display","block");
  		}
  		function checkform() {
  			var regxp = new RegExp("^[1-9][0-9]*$");
  			var roleName = roleinfo.name.value;
  			var roleMinCredits = roleinfo.minCredits.value;
  			var roleStars = roleinfo.stars.value;
  			if(roleName.length == 0 || roleName == "") {
  				alert("角色名称不能为空！");
  				return false;
  			}else if(roleMinCredits.length == 0 || roleMinCredits == "") {
  				alert("角色最小积分不能为空！");
  				return false;
  			}else if(!regxp.test(roleMinCredits)) {
  				alert("角色的最小积分只能填数字！");
  				return false;
  			}else if(roleStars.length == 0 || roleStars == "") {
  				alert("角色星星数不能为空！");
  				return false;
  			}else if(!regxp.test(roleStars)) {
  				alert("角色的星星数只能填数字！");
  				return false;
  			}else if(roleinfo.description.value.length == 0 || roleinfo.description.value == "") {
  				alert("角色描述不能为空！");
  				return false;
  			}else return true;
  			
  		}
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
	    	<s:iterator value="vipList">
		    	<tr>
		    		<td width="25%">&nbsp;<span class="ac_span2">${name}</span></td>
		    		<td width="10%">&nbsp;<span class="ac_span2">${minCredits}</span></td>
		    		<td width="10%">&nbsp;<span class="ac_span2">${stars}</span></td>
		    		<td width="30%">&nbsp;<span class="ac_span2">${description}</span></td>
		    		<td width="25%">&nbsp;
		    			<span class="ac_span2">
		    				【<s:a action="roleAction_setPrivilegeUI?id=%{id}" cssClass="a_0" cssStyle="color:black;">设置权限</s:a>】
		    				<s:if test="#flag == 'sys'">
		    				【<s:a action="roleAction_addUser?id=%{id}" href="javascript:void(0);" onclick="addUser(%{id})" cssClass="a_0" cssStyle="color:black;">添加人员</s:a>】
		    				</s:if>
		    				<s:if test="#flag == 'vip'">
		    				【<a href="" class="a_0" style="color:black;">修改</a>】
		    				【<a href="roleAction_delete.action?id=${id}" class="a_0" style="color:black;">删除</a>】
		    				</s:if>
		    			</span>
		    		</td>
		    	</tr>
	    	</s:iterator>
    	</tbody>
    </table>
    <s:if test="#flag == 'vip'">
    <s:a href="javascript:addRole();" action="roleAction_addUI"><img src="${pageContext.request.contextPath}/style/common/images/button/addNew.PNG" style="border: 0px;"/></s:a>
    </s:if>
    <div id="addrole" style="display: none;">
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >1、角色的描述最好写成：积分从0~1000这种形式</span>
    		<span class="ac_span3" >2、角色的名称不要太长,四个字以内最好</span>
    	</div>
    </div>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">信息填写</span></div>
    	<s:form action="roleAction_add" name="roleinfo" onsubmit="return checkform();">
    	<div id="ac_os3">
    		<span class="ac_span2" style="margin-left:20px;">名称</span>
    		<input type="text" name="name" class="ac_text1"/>
    		<span class="ac_span2" style="margin-left:20px;">最小积分</span>
    		<input type="text" name="minCredits" class="ac_text1" style="width: 100px;"/>
    		<span class="ac_span2" style="margin-left:20px;">星星数</span>
    		<input type="text" name="stars" class="ac_text1" style="width: 100px;"/>
    		<span class="ac_span2" style="margin-left:20px;">描述</span>
    		<input type="text" name="description" class="ac_text1" style="width: 180px;"/>
    		<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" style="vertical-align: middle;margin-bottom: 5px;"/>
    	</div>
   		</s:form>
    </div>
    </div>
    <div id="findUserDiv" style="width:210px;height: 300px;display:none;">
    	<div id="findUserLeft" style="border:1px solid #6C84B4;width:210px;height: 300px;float: left;margin-right: 10px;">
    		<div style="height: 40px;width:210px;border-bottom: 1px solid #6C84B4;">
    			<input id="usernametext" type="text" onkeyup="findUser(this)" style="border:1px solid #cdcdcd;width:180px;height: 20px;margin-top: 8px;margin-left: 10px;"/>
    		</div>
    		<div style="height: 260px;width:180px;margin-left: 15px" id="showresultdiv" >
    			
    		</div>
    	</div>
    </div>
    
    
  </body>
</html>
