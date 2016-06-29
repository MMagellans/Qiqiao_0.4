<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>板块添加</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
	<script type="text/javascript">
		function checkform() {
			var sourceBoardVal = $("select[name='sourcebid']").val();
			var objBoardVal = $("select[name='objbid']").val();
			if(sourceBoardVal == objBoardVal) {
				alert("源板块和目标板块不能相同！");
				return false;
			}else {
				return confirm("你确定将源板块内容合并到目标版块？");
			}
		}
	</script>
  </head>
  
  <body>
  	<div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >•&nbsp;合并版块可将源版块的帖子全部转入目标版块，同时删除源版块。</span><br />
    		<span class="ac_span3" >•&nbsp;合并版块一旦提交立即生效，并无法恢复，请仔细选择目标版块和源版块。</span>
    	</div>
    </div>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">添加新板块</span></div>
    	<s:form action="boardAction_merger" name="boardinfo" onsubmit="return checkform();">
    	<div id="ac_os3" style="padding: 8px 0px;">
    		<span class="ac_span2" style="margin-left:20px;">源板块</span>
    		<s:select cssStyle="height:24px;width:150px;" list="#boards" listKey="id" listValue="name" name="sourcebid" />
    		<span class="ac_span2" style="margin-left:20px;">目标板块</span>
    		<s:select cssStyle="height:24px;width:150px;" list="#boards" listKey="id" listValue="name" name="objbid" />
    		<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" style="vertical-align: middle;margin-bottom: 5px;"/>
    	</div>
   		</s:form>
    </div>
  </body>
</html>
