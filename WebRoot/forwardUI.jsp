
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>跳转页面</title>
    <style type="text/css">
    	.forward1{
    		position:absolute; 
    		top:50%; 
    		left:50%; 
    		margin:-150px 0 0 -400px;
    		background-color:#f5f5f5; 
    		width:800px;
    		height:auto; 
    		border:1px solid #8c8c8c; 
    		padding: 15px 0px;
    		text-align: center;
    	}
    	.span1{
    		font-size:12px;
    		color:#005EAC;
    	}
    	a{text-decoration: none;}
    	a:HOVER {background-color: #ACCE95;}
    </style>
	<script type="text/javascript">
		setTimeout("window.location.href='${pageContext.request.contextPath}/${toWhere}'",1000);
	</script>
  </head>
  
  <body>
    <div class="forward1">
    	<span class="span1" style="color:black;"><s:property value="operationInfo"/>，正在跳转...</span><br/><br />
    	<span class="span1"><a href="${toWhere}">如果您的浏览器没有自动跳转，请点击这里</a></span>
    </div>
    
  </body>
</html>
