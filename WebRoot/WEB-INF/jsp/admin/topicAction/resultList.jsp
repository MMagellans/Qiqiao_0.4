<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查询结果页面</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
    <script type="text/javascript">
    	$(document).ready(function() {
    		$("tr[name='result']").find("td").css("border-bottom","1px solid #BED5F3");
    		$("tr[name='result']").find("td[name='bianse']").css("background-color","#f5f5f5").css("height","25px");
    	});
    </script>
  </head>
  
  <body>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >记录数量较多时可能会比较耗时，可以分次处理。</span>
    	</div>
    </div>
    <div id="ac_os1">
    	<table cellpadding="0" cellspacing="0" width="100%">
    		<tr bgcolor="#EAF2FE">
    			<th><span class="ac_span1" style="line-height:30px;">所属版块</span></th>
    			<th><span class="ac_span1" style="line-height:30px;">标题</span></th>
    			<th><span class="ac_span1" style="line-height:30px;">用户名</span></th>
    			<th><span class="ac_span1" style="line-height:30px;">回复</span></th>
    			<th><span class="ac_span1" style="line-height:30px;">查看</span></th>
    			<th><span class="ac_span1" style="line-height:30px;">发表时间</span></th>
    			<th><span class="ac_span1" style="line-height:30px;">状态 </span></th>
    		</tr>
    		<s:iterator value="recordList">
    		<tr align="center" name="result">
    			<td name="bianse"><a href="forum-${board.id }.html" target="board_${board.id }_blank" class="a_0 span_3" style="color:black;">${board.name }</a></td>
    			<td style="width: 25%;"><a href="topic-${id }.html" target="topic_${id }_blank" class="a_0 span_3" style="color:black;">${fn:substring(title,0,20) }</a></td>
    			<td name="bianse" ><label class="span_3" style="color:black;">${user.username }</label></td>
    			<td ><label class="span_3" style="color:black;">${replyCount }</label></td>
    			<td name="bianse" ><label class="span_3" style="color:black;">${visits }</label></td>
    			<td  style="width: 15%;"><label class="span_3" style="color:black;">${fn:substring(postTime,0,16) }</label></td>
    			<s:if test="state == 0">
    			<td name="bianse" ><label class="span_3" style="color:black;">正常</label></td>
    			</s:if>
    			<s:elseif test="state == 1">
    			<td name="bianse" ><label class="span_3" style="color:black;">关闭</label></td>
    			</s:elseif>
    		</tr>
    		</s:iterator>
    		<tr align="right" height="40px">
    			<td colspan="7">
	    			<span class="span_3">页次：${currentPage }/${pageCount }页</span>&nbsp;
					<span class="span_3">每页显示：${pageSize }条</span>&nbsp;
					<span class="span_3">总记录数：${recordCount }条</span>&nbsp;
    				<a href="javascript:gotoPageNum(1)" class="a_0 span_3">首页</a>&nbsp;
    				<s:if test="currentPage == 1">
    					<a disabled class="a_0 span_3">上一页</a>&nbsp;
    				</s:if>
    				<s:else>
    					<a href="javascript:gotoPageNum(${currentPage-1 })" class="a_0 span_3">上一页</a>&nbsp;
    				</s:else>
    				<s:if test="currentPage == endPageIndex">
    					<a disabled class="a_0 span_3">下一页</a>&nbsp;
    				</s:if>
    				<s:else>
    					<a href="javascript:gotoPageNum(${currentPage+1 })" class="a_0 span_3">下一页</a>&nbsp;
    				</s:else>
    				
    				<a href="javascript:gotoPageNum(${endPageIndex })" class="a_0 span_3">尾页</a>&nbsp;
    				<span class="span_3">跳转：</span>
    				<select id="pn" onchange="gotoPageNum(this.value)">
						<s:iterator begin="1" end="endPageIndex" var="num">
							<option value="${num}" >${num}</option>
						</s:iterator>
					</select>&nbsp;&nbsp;
    			</td>
    		</tr>
    	</table>
    </div>
    <s:form action="topicAction_piliangOption">
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">符合条件的主题数：${recordCount }</span></div>
    	<div id="ac_os3" >
    		<table cellpadding="0" cellspacing="0" width="100%">
    			<tr>
    				<td class="ac_td2">
    					<input id="del" type="radio" name="option" value="1" checked="checked"/>
    					<label class="span_3" style="color:black;" for="del" >批量删除</label>
    				</td>
    				<td class="ac_td3"><span class="span_3" style="color:black;">删帖但不减用户发帖数和积分</span></td>
    			</tr>
    			<%--
    			<tr>
    				<td class="ac_td2">
    					<input id="move" type="radio" name="option" value="2"/>
    					<label class="span_3" style="color:black;" for="move" >批量移动到</label>
    				</td>
    				<td class="ac_td3">
    					<s:select list="#boards" listKey="id" listValue="name" name="board.id"></s:select>
    					<label class="span_3" style="color:black;">板块</label>
    				</td>
    			</tr> --%>
    			<tr>
    				<td class="ac_td2">
    					<input id="ooc" type="radio" name="option" value="3"/>
    					<label class="span_3" style="color:black;" for="ooc" >批量打开或关闭</label>
    				</td>
    				<td class="ac_td3">
    					<input id="open" type="radio" name="state" value="1" checked="checked" />
    					<label class="span_3" style="color:black;" for="open" >打开</label>
    					<input id="close" type="radio" name="state" value="2"/>
    					<label class="span_3" style="color:black;" for="close" >关闭</label>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2">
    					<input id="top_radio" type="radio" name="option" value="4"/>
    					<label class="span_3" style="color:black;" for="top_radio" >批量置顶</label>
    				</td>
    				<td class="ac_td3">
    					<input id="top_0" type="radio" name="topScope" value="0" checked="checked" />
    					<label class="span_3" style="color:black;" for="top_0" >取消置顶</label>
    					<input id="top_1" type="radio" name="topScope" value="1"/>
    					<label class="span_3" style="color:black;" for="top_1" >板块置顶</label>
    					<input id="top_3" type="radio" name="topScope" value="3"/>
    					<label class="span_3" style="color:black;" for="top_3" >全局置顶</label>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2">
    					<input id="digest_radio" type="radio" name="option" value="5"/>
    					<label class="span_3" style="color:black;" for="digest_radio" >批量精华</label>
    				</td>
    				<td class="ac_td3">
    					<input id="digest_0" type="radio" name="digest" value="false" checked="checked" />
    					<label class="span_3" style="color:black;" for="digest_0" >取消精华</label>
    					<input id="digest_1" type="radio" name="digest" value="true"/>
    					<label class="span_3" style="color:black;" for="digest_1" >精华</label>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" align="center" style="padding: 8px 0px;height: 30px;">
    					<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" />
    				</td>
    			</tr>
    		</table>
    	</div>
    </div>
    </s:form>
    
    <s:form name="condition" action="topicAction_manager">
    	<s:hidden name="boardId"></s:hidden>
    	<s:hidden name="author"></s:hidden>
    	<s:hidden name="titleKey"></s:hidden>
    	<s:hidden name="visitsLess"></s:hidden>
    	<s:hidden name="visitsGreater"></s:hidden>
    	<s:hidden name="repliesLess"></s:hidden>
    	<s:hidden name="repliesGreater"></s:hidden>
    	<s:hidden name="createTimeLess"></s:hidden>
    	<s:hidden name="createTimeGreater"></s:hidden>
    	<s:hidden name="isDigest"></s:hidden>
    	<s:hidden name="state"></s:hidden>
    </s:form>
    
    <script type="text/javascript">
		function gotoPageNum( pageNum ){
			$(document.forms[1]).append("<input type='hidden' name='pageNum' value='" + pageNum + "'/>");
			document.forms[1].submit(); // 提交表单
		}
	</script>
  </body>
</html>
