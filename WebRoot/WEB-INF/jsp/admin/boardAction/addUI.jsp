<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>板块添加</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
	<script type="text/javascript">
		function checkform() {
			var boardName = boardinfo.name.value;
			var boardDescription = boardinfo.description.value;
			if(boardName.length == 0 || boardName == "") {
				alert("板块名称不能为空");
				return false;
			}else {
				return true;
			}
		}
		function checkform1() {
			var sectionName = sectioninfo.name.value;
			var sectionDescription = sectioninfo.description.value;
			if(sectionName.length == 0 || sectionName == "") {
				alert("分区名称不能为空");
				return false;
			}else {
				return true;
			}
		}
	</script>
  </head>
  
  <body>
  	<div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >1、添加板块只需填写板块的基本信息</span>
    		<span class="ac_span3" >2、详细设置请到板块编辑中进行</span>
    		<span class="ac_span3" >3、每个分区最多只能容纳九个板块</span>
    	</div>
    </div>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">添加新板块</span></div>
    	<s:form action="boardAction_add" name="boardinfo" onsubmit="return checkform();">
    	<div id="ac_os3">
    		<span class="ac_span2" style="margin-left:20px;">板块名称</span>
    		<input type="text" name="name" class="ac_text1"/>
    		<span class="ac_span2" style="margin-left:20px;">所属分区</span>
    		<s:select cssStyle="height:24px;width:150px;" list="#sectionVos" listKey="id" listValue="name" name="section.id" />
    		<span class="ac_span2" style="margin-left:20px;">板块描述</span>
    		<input type="text" name="description" class="ac_text1" style="width: 180px;"/>
    		<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" style="vertical-align: middle;margin-bottom: 5px;"/>
    	</div>
   		</s:form>
    </div>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">添加新分区</span></div>
    	<s:form action="sectionAction_add" name="sectioninfo" onsubmit="return checkform1();">
    	<div id="ac_os3">
    		<span class="ac_span2" style="margin-left:20px;">分区名称</span>
    		<input type="text" name="name" class="ac_text1"/>
    		<span class="ac_span2" style="margin-left:20px;">分区描述</span>
    		<input type="text" name="description" class="ac_text1" style="width: 180px;"/>
    		<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" style="vertical-align: middle;margin-bottom: 5px;"/>
    	</div>
   		</s:form>
   	</div>
  </body>
</html>
