<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>板块编辑页面</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
    <script src="${pageContext.request.contextPath}/script/admin/editUI.js"></script>
  </head>
  
  <body>
  	<div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >•&nbsp;删除分区会连同分区下的板块主题以及回帖一同删除。</span>
    	</div>
    </div>
  
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">编辑板块</span></div>
    	<div id="ac_os3">
    	<s:iterator value="sections">
    		<ul class="a">
    			<li class="b">
    				<span class="span_6"><a href="" class="a_0" style="color:#555555;font-family: ;">${name }</a></span>
    				<span class="span_3" style="color:black;">-显示顺序：</span><input id="${id}" class="section" type="text" name="sectionSort_${id }" value="${sortNum }" style="width: 30px;border: 1px solid #339999;color:#339999;"/>
    				<span class="span_3">-[&nbsp;<s:a cssClass="a_0" action="boardAction_sectionSetUI?sid=%{id}" cssStyle="color:black;">设置</s:a>&nbsp;]</span>
    				<span class="span_3">-[&nbsp;<a class="a_0" href="sectionAction_delete.action?id=${id }" style="color:black;" onclick="return confirm('您确定要删除该分区？');">删除</a>&nbsp;]</span>
    			</li>
    			<s:iterator value="boards">
    			<ul style="margin-top: 10px;" class="a">
	    			<li class="b">
	    				<span class="span_6"><s:a action="boardAction_show?id=%{id}" cssClass="a_0" cssStyle="color:#555555;">${name }</s:a></span>
	    				<span class="span_3" style="color:black;">-显示顺序：</span><input id="${id}" class="board" type="text" name="boardSort_${id }" value="${sortNum }" style="width: 30px;border: 1px solid #339999;color:#339999;"/>
	    				<span class="span_3">-[&nbsp;<s:a cssClass="a_0" action="boardAction_setUI?id=%{id}" cssStyle="color:black;">设置</s:a>&nbsp;]</span>
	    				<span class="span_3">-[&nbsp;<s:a cssClass="a_0" action="boardAction_delete?id=%{id}" cssStyle="color:black;" onclick="return confirm('您确定要删除该板块？');">删除</s:a>&nbsp;]</span>
	    				<span class="span_3">-[&nbsp;<s:a cssClass="a_0" action="boardAction_setModeratorUI?id=%{id}" cssStyle="color:black;">本版版主</s:a>&nbsp;]</span>
	    			</li>
    			</ul>
    			</s:iterator>
    		</ul>
    	</s:iterator>	
    	</div>
    </div>
  </body>
</html>
