<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>title</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
    <script src="${pageContext.request.contextPath}/script/admin/validateUpload.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/colorbox/jquery.colorbox-min.js" type="text/javascript"></script> 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/colorbox/colorbox.css" />
    <script type="text/javascript">
    	$(function() {
	    	document.getElementById('${applicationScope.style}').checked=true;
	    	$(".yulan").colorbox({rel:'yulan'});
    	});
    	
    	function paramSet() {
    		var regxp = new RegExp("^[0-9]*$");
    		var pageSize = $("input[name='pageSize']").val();
    		if(!regxp.test(pageSize.trim())) {
    			alert('请输入数字！');
    			return false;
    		}else if(pageSize == null || pageSize.trim() == "") {
    			alert('输入不能为空！');
    			return false;
    		}else if(pageSize >= 20) {
    			alert('每页最大能显示20条！');
    			return false;
    		}else if(pageSize <= 5) {
    			alert('每页最小能显示5条！');
    			return false;
    		}else {
    			return true;
    		}
    	}
    	
    </script>
    <style type="text/css">
    	.yulan{font-size: 11px;text-decoration: none;}
    	.yulan:HOVER {color: red;}
    </style>
  </head>
  <body>
    <s:div>
    	<table cellpadding="0" cellspacing="0" width="100%">
    		<tr>
    			<td width="80px"><hr class="hr_1"/></td>
    			<td width="80px" align="center"><span class="span_6">风格设置</span></td>
    			<td><hr class="hr_1"/></td>
    		</tr>
    		<tr>
    			<td colspan="3" height="40px" style="padding-left: 20px;">
    				<s:form action="adminAction_setStyle">
	    				<input type="radio" id="default" name="style1" value="default"/><label for="default"><span class="span_1">蓝色天空</span></label>
	    				&nbsp;&nbsp;&nbsp;
	    				<input type="radio" id="red" name="style1" value="red"/><label for="red"><span class="span_1">红色海洋</span></label>
	    				&nbsp;&nbsp;&nbsp;
	    				<input type="radio" id="cyan" name="style1" value="cyan"/><label for="cyan"><span class="span_1">绿色田园</span></label>
	    				&nbsp;&nbsp;&nbsp;
	    				<input type="radio" id="yellow" name="style1" value="yellow"/><label for="yellow"><span class="span_1">黄色殿堂</span></label>
	    				&nbsp;&nbsp;&nbsp;
	    				<input type="radio" id="purple" name="style1" value="purple"/><label for="purple"><span class="span_1">紫色情怀</span></label>
	    				&nbsp;&nbsp;&nbsp;
	    				<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG"/>
    				</s:form>
    			</td>
    		</tr>
    	</table>
    </s:div>
    <s:div>
    	<table cellpadding="0" cellspacing="0" width="100%">
    		<tr>
    			<td width="80px"><hr class="hr_1"/></td>
    			<td width="100px" align="center"><span class="span_6">论坛参数设置</span></td>
    			<td><hr class="hr_1"/></td>
    		</tr>
    		<s:form action="adminAction_setPageSize.action" enctype="multipart/form-data">
    		<tr>
    			<td colspan="3" style="padding: 10px 20px;">
    				<table>
    					<tr style="height: 30px;">
    						<td><span class="span_3">分页中每页显示&nbsp;</span>
    						<s:textfield name="pageSize" cssStyle="width: 50px;"></s:textfield><span class="span_3">&nbsp;&nbsp;条记录</span></td>
    					</tr>
    					<tr>
    						<td>
    							<span class="span_3">论坛的名称&nbsp;</span>
    							<input type="text" name="bbsname" value="${bbsname }" style="width:200px;border: 1px solid #a5a5a5; "/>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<span class="span_3">论坛的作者&nbsp;</span>
    							<input type="text" name="authorname" value="${authorname }" style="width:200px;border: 1px solid #a5a5a5; "/>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<span class="span_3">论坛的标题&nbsp;</span>
    							<input type="text" name="bbstitle" value="${bbstitle }" style="width:200px;border: 1px solid #a5a5a5; "/>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<span class="span_3">论坛LOGO(<font color="red">图片大小最好是120*55</font>)&nbsp;</span>
    							<input type="file" name="bbsicon" onchange="validatePic(this)" style="width:400px;height: 20px;border: 1px solid #a5a5a5; "/>
    							<a class="yulan" href="${pageContext.request.contextPath}${bbsicon}">预览</a>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<span class="span_3">论坛顶部的图片(<font color="red">图片宽度必须是960像素</font>)&nbsp;</span>
    							<input type="file" name="topImage" onchange="validatePic(this)" style="width:400px;height: 20px;border: 1px solid #a5a5a5; "/>
    							<a class="yulan" href="${pageContext.request.contextPath}${topImage}">预览</a>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<span class="span_3">论坛底部的图片(<font color="red">图片宽度必须是960像素</font>)&nbsp;</span>
    							<input type="file" name="buttomImage" onchange="validatePic(this)" style="width:400px;height: 20px;border: 1px solid #a5a5a5; "/>
    							<a class="yulan" href="${pageContext.request.contextPath}${buttomImage}">预览</a>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<span class="span_3">论坛后台logo(<font color="red">图片宽度必须是100</font>)&nbsp;</span>
    							<input type="file" name="bbsadminicon" onchange="validatePic(this)" style="width:400px;height: 20px;border: 1px solid #a5a5a5; "/>
    							<a class="yulan" href="${pageContext.request.contextPath}${bbsadminicon}">预览</a>
    						</td>
    					</tr>
    					<tr style="height: 30px;">
    						<td>
    							<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" onclick="return paramSet();"/>
    						</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    		</s:form>
    	</table>
    </s:div>
    <table cellpadding="0" cellspacing="0" width="100%">
   		<tr>
   			<td width="80px"><hr class="hr_1"/></td>
   			<td width="80px" align="center"><span class="span_6">敏感词汇设置</span></td>
   			<td><hr class="hr_1"/></td>
   		</tr>
   		<tr>
   			<td height="40px" style="padding-left: 20px;">
   				<span class="span_3">敏感词</span>
   			</td>
   			<td colspan="2" height="40px" style="padding-left: 20px;">
   				<textarea rows="5" cols="40" name="words" style="border:1px solid #a5a5a5;" ></textarea>
   			</td>
   		</tr>
   		<tr>
   			<td height="40px" style="padding-left: 20px;">
   				<span class="span_3">替换为</span>
   			</td>
   			<td colspan="2" height="40px" style="padding-left: 20px;">
   				<input type="text" name="replaceWord" style="border:1px solid #a5a5a5;" />
   			</td>
   		</tr>
   	</table>
    <s:div>
    	<table cellpadding="0" cellspacing="0" width="100%">
    		<tr>
    			<td width="80px"><hr class="hr_1"/></td>
    			<td width="80px" align="center"><span class="span_6">广告设置</span></td>
    			<td><hr class="hr_1"/></td>
    		</tr>
    		<tr>
    			<td colspan="3" height="40px" style="padding-left: 20px;">
    				
    			</td>
    		</tr>
    	</table>
    </s:div>
  </body>
</html>
