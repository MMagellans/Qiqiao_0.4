<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>板块设置界面</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
	<script type="text/javascript">
		function closeDiv(node) {
			imgNode = $(node);
			var divNode = imgNode.parent().next();
			var isHide = divNode.css("display");
			divNode.toggle("normal");
			if(isHide == "block") {
				imgNode.attr("src","${pageContext.request.contextPath}/style/common/images/collapsed_yes.gif");
			}else {
				imgNode.attr("src","${pageContext.request.contextPath}/style/common/images/collapsed_no.gif");
			}
		}
		
	</script>
  </head>
  
  <body>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">主题查询</span>
    		<img src="${pageContext.request.contextPath}/style/common/images/collapsed_no.gif" title="收起/展开" onclick="closeDiv(this)" style="float: right;margin: 8px 15px;cursor: pointer;" />
    	</div>
    	<div id="ac_os3">
    	<s:form action="topicAction_manager">
    		<s:hidden name="id"></s:hidden>
    		<table cellpadding="0" cellspacing="0" width="100%">
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">所在版块</span></td>
    				<td class="ac_td3">
    					<s:select cssStyle="height:24px;width:150px;" list="#boards" listKey="id" listValue="name" name="boardId"></s:select>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">主题作者</span></td>
    				<td class="ac_td3">
    					<input type="text" name="author" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">标题关键字</span></td>
    				<td class="ac_td3">
    					<input type="text" name="titleKey" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">浏览次数小于</span></td>
    				<td class="ac_td3">
    					<input type="text" name="visitsLess" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">浏览次数大于</span></td>
    				<td class="ac_td3">
    					<input type="text" name="visitsGreater" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">回复次数小于</span></td>
    				<td class="ac_td3">
    					<input type="text" name="repliesLess" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">回复次数大于</span></td>
    				<td class="ac_td3">
    					<input type="text" name="repliesGreater" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">发表日期早于</span></td>
    				<td class="ac_td3">
    					<input type="text" name="createTimeLess" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">发表日期晚于</span></td>
    				<td class="ac_td3">
    					<input type="text" name="createTimeGreater" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width: 200px;border: 1px solid #ABADB3;height: 18px;"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">是否是精华</span></td>
    				<td class="ac_td3">
    					<input type="radio" id="all" name="isDigest" value="2" checked="checked" /><label for="all" class="span_3">无限制</label>
    					<input type="radio" id="digest" name="isDigest" value="1" /><label for="digest" class="span_3">是</label>
    					<input type="radio" id="nodigest" name="isDigest" value="0" /><label for="nodigest" class="span_3">否</label>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_3" style="color:black;">主题状态</span></td>
    				<td class="ac_td3">
    					<input type="radio" id="normal" name="state" value="0"  checked="checked" /><label for="normal" class="span_3">正常</label>
    					<input type="radio" id="close" name="state" value="1" /><label for="close" class="span_3">已关闭</label>
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
