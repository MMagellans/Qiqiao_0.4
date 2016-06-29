<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>板块设置界面</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
  	<script src="${pageContext.request.contextPath}/script/iColorPicker.js" type="text/javascript"></script>
  	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript">
	KindEditor.ready(function(K) {
			var editor1 = K.create('.kindeditor', 
				{
				items : ['justifyleft', 'justifycenter', 'justifyright',
				        'justifyfull', 'insertorderedlist', 'insertunorderedlist',
				        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				        'italic', 'underline','removeformat', 'lineheight'
						],
				uploadJson : '${pageContext.request.contextPath}/kindeditor/jsp/upload_json.jsp',
				allowFileManager : false,
			});
			prettyPrint();
		});
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
    	<div id="ac_os2">&nbsp;<span class="ac_span1" style="line-height:30px;">操作提示</span></div>
    	<div id="ac_os3">
    		<span class="ac_span3" >•&nbsp;实际访问权限由用户组的权限和此处的版块权限设置共同决定，即某一权限只有在用户组的权限和版块权限均许可的情况下才有效。</span>
    		<span class="ac_span3" style="display:block;">•&nbsp;注意：基本设置和扩展设置是分开提交的。</span>
    	</div>
    </div>
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">板块基本设置</span>
    		<img src="${pageContext.request.contextPath}/style/common/images/collapsed_no.gif" title="收起/展开" onclick="closeDiv(this)" style="float: right;margin: 8px 15px;cursor: pointer;" />
    	</div>
    	<div id="ac_os3">
    	<s:form action="boardAction_set">
    		<s:hidden name="id"></s:hidden>
    		<table cellpadding="0" cellspacing="0" width="100%">
    			<tr>
    				<td class="ac_td2"><span class="span_6" style="color:black;">板块名称:</span></td>
    				<td class="ac_td3">
    					<s:textfield name="name" cssStyle="width: 200px;border: 1px solid #ABADB3;height: 18px;" />
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2">
    					<span class="span_6" style="color:black;">显示板块:</span><br />
    					<span class="span_3" style="font-family:;line-height:20px;">选择“否”将暂时隐藏版块不显示，但管理员和版主仍可见此版块，且用户仍可通过 URL  直接访问到此版块的内容。利用此功能可以将一些主题暂时隐藏</span>
    				</td>
    				<td class="ac_td3">
    					<s:if test="state == 0">
    					<input type="radio" name="state" value="0" id="state0" checked="checked"/><label class="span_3" for="state0">是</label>
    					<input type="radio" name="state" value="1" id="state1"/><label class="span_3" for="state1">否</label>
    					</s:if>
    					<s:else>
    					<input type="radio" name="state" value="0" id="state0"/><label class="span_3" for="state0">是</label>
    					<input type="radio" name="state" value="1" id="state1" checked="checked"/><label class="span_3" for="state1">否</label>
    					</s:else>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2"><span class="span_6" style="color:black;">所属分区:</span></td>
    				<td class="ac_td3">
    					<s:select cssStyle="height:24px;width:150px;" list="#sectionVos" listKey="id" listValue="name" name="section.id"></s:select>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2">
    					<span class="span_6" style="color:black;">板块高亮颜色:</span><br />
    					<span class="span_3" style="font-family:;line-height:20px;">版块名称高亮显示的颜色值，以六位 16 进制值表示，空值表示以默认颜色显示版块名称</span>
    				</td>
    				<td class="ac_td3">
    					<s:textfield name="highColor" cssStyle="width: 200px;border: 1px solid #ABADB3;height: 18px;background-color:%{highColor};" cssClass="iColorPicker"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="ac_td2">
    					<span class="span_6" style="color:black;">板块规则:</span><br />
    					<span class="span_3" style="font-family:;line-height:20px;">提示用户该板块有什么规则,不符合规则的将会进行处理(转移或删除)</span>
    				</td>
    				<td class="ac_td3">
    					<textarea class="kindeditor" name="description" style="width: 100%;height: 200px;">${description}</textarea>
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
    <%--
    <div id="ac_os1">
    	<div id="ac_os2">&nbsp;&nbsp;&nbsp;<span class="ac_span1" style="line-height:30px;">扩展设置</span>
    		<img src="${pageContext.request.contextPath}/style/common/images/collapsed_no.gif" title="收起/展开" onclick="closeDiv(this)" style="float: right;margin: 8px 15px;cursor: pointer;" />
    	</div>
    	<div id="ac_os3">
    		<table cellpadding="0" cellspacing="0" width="100%">
    			<tr>
    				<td class="ac_td2"><span class="span_6" style="color:black;">板块名称:</span></td>
    				<td class="ac_td3">
    					<s:textfield name="name" cssStyle="width: 200px;border: 1px solid #ABADB3;height: 18px;" />
    				</td>
    			</tr>
    		</table>
    	</div>
    </div>
     --%>
  </body>
</html>
