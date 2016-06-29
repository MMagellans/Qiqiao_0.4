<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>分区设置页面</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
  </head>
  
  <body>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >•&nbsp;分区编辑</span>
    	</div>
    	
    </div>
    <div id="ac_os1">
    	<div id="ac_os3">
    	<s:form action="boardAction_sectionSet">
    		<s:hidden name="sid" value="%{#sectionVo.id}"></s:hidden>
    		<table cellpadding="0" cellspacing="0" width="100%">
    			<tr>
    				<td class="ac_td2"><span class="span_6" style="color:black;">分区名称:</span></td>
    				<td class="ac_td3">
    					<s:textfield name="sname" value="%{#sectionVo.name}" cssStyle="width: 200px;border: 1px solid #ABADB3;height: 18px;" />
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" align="center" style="padding: 8px 0px;">
    					<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" />
    				</td>
    			</tr>
    		</table>
    	</s:form>
    	</div>
    </div>
    
  </body>
</html>
