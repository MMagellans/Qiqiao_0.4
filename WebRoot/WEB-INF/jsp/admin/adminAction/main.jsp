<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>论坛后台管理</title>
	<link href="${pageContext.request.contextPath}/jQuery ligerUI/Source/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="${pageContext.request.contextPath}/script/jquery-1.5.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/jQuery ligerUI/Source/lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/jQuery ligerUI/Source/indexdata.js" type="text/javascript"></script>
    <script type="text/javascript">
         var tab = null;
         var accordion = null;
         var tree = null;
         $(function ()
         {

             //布局
             $("#layout1").ligerLayout({ leftWidth: 190, height: '100%',heightDiff:-34,space:4, onHeightChanged: f_heightChanged });

             var height = $(".l-layout-center").height();

             //Tab
             $("#framecenter").ligerTab({ height: height });

             //面板
             $("#accordion1").ligerAccordion({ height: height - 24, speed: null });

             $(".l-link").hover(function ()
             {
                 $(this).addClass("l-link-over");
             }, function ()
             {
                 $(this).removeClass("l-link-over");
             });
             //树
             $("#tree1").ligerTree({
                 data : indexdata,
                 checkbox: false,
                 slide: false,
                 nodeWidth: 120,
                 attribute: ['nodename', 'url'],
                 onSelect: function (node)
                 {
                     if (!node.data.url) return;
                     var tabid = $(node.target).attr("tabid");
                     if (!tabid)
                     {
                         tabid = new Date().getTime();
                         $(node.target).attr("tabid", tabid)
                     } 
                     f_addTab(tabid, node.data.text, node.data.url);
                 }
             });

             tab = $("#framecenter").ligerGetTabManager();
             accordion = $("#accordion1").ligerGetAccordionManager();
             tree = $("#tree1").ligerGetTreeManager();
             $("#pageloading").hide();

         });
         function f_heightChanged(options)
         {
             if (tab)
                 tab.addHeight(options.diff);
             if (accordion && options.middleHeight - 24 > 0)
                 accordion.setHeight(options.middleHeight - 24);
         }
         function f_addTab(tabid,text, url)
         { 
             tab.addTabItem({ tabid : tabid,text: text, url: url });
         } 
    </script> 
	<style type="text/css"> 
	    body,html{height:100%;}
	    body{ padding:0px; margin:0;   overflow:hidden;}  
	    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:none; color:#333;}
	    .l-link2{text-decoration:none; color:white; margin-left:2px;margin-right:2px;}
	    .l-layout-top{background:#102A49; color:White;}
	    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
	    #pageloading{position:absolute; left:0px; top:0px; background:white url('loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
	    .l-link{ display:block; line-height:22px; height:22px; padding-left:16px;border:1px solid white; margin:4px;}
	    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;} 
	    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
	    .space{ color:#E7E7E7;}
	    /* 顶部 */ 
	    .l-topmenu{ margin:0; padding:0; height:100px; line-height:31px; background:url('style/common/images/admin/adminlogin_bg.jpg') repeat 0px 0px;  position:relative; border-top:1px solid #1D438B;  }
	    .l-topmenu-logo{width:350px;height:80px; background:url('style/common/images/admin/logo.png') no-repeat 0px 0px;}
	    .l-topmenu-welcome{  position:absolute; height:24px; line-height:24px;  right:30px; top:2px;color:#070A0C;}
	    .l-topmenu-welcome a{ color:#E7E7E7; text-decoration:none;} 
	    .l-topmenu-welcome a:HOVER{text-decoration:underline;}
	 </style>
   </head>
	<body style="padding:0px;background:#EAEEF5;">  
		<div id="pageloading"></div>  
		<div id="topmenu" class="l-topmenu">
		    <div><img src="${pageContext.request.contextPath}${bbsadminicon}" width="200px" height="100px" /></div>
		    <div class="l-topmenu-welcome">
		        <label class="l-link2" style="text-decoration: none;">欢迎您，管理员</label>
		        <span class="space">|</span>
		        <s:a action="adminAction_logout" cssClass="l-link2">退出</s:a> 
		        <span class="space">|</span>
		         <a href="forum.html" class="l-link2" target="_blank">论坛首页</a>
		    </div> 
		</div>
  		<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; "> 
	        <div position="left"  title="主要菜单" id="accordion1"> 
	          <s:iterator value="#application.topPrivilegeList">
	          <s:if test="%{#session.admin.hasPrivilegeByName(name)}">
		          <div title="${ name}">
		          	<div style=" height:7px;"></div>
		          		<s:iterator value="children">
		          		<s:if test="%{#session.admin.hasPrivilegeByName(name)}">
		              		<a class="l-link" href="javascript:f_addTab('${url}','${name}','${url}.action')">${name}</a> 
		              	</s:if>
		              	</s:iterator>
		          </div>
		      </s:if>
	          </s:iterator>
             </div>   
	        <div position="center" id="framecenter">
	            <div tabid="home" title="我的主页" style="height:300px" >
	                <iframe frameborder="0" name="home" id="home" src="index.jsp"></iframe><%--显示首页地址 --%>
	            </div> 
	        </div> 
    	</div>
    	<div  style="height:32px; line-height:32px; text-align:center;">
            Copyright ©<%=new GregorianCalendar().get(Calendar.YEAR) %> <%=application.getAttribute("bbstitle") %>.All Rights Reserved
    	</div>
    	<div style="display:none"></div>
	</body>
</html>
