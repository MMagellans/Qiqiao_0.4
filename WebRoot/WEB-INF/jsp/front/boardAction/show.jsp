<%@page import="com.qiqiao.model.Board"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.qiqiao.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>板块名称</title>
	<%@ include file="/WEB-INF/jsp/front/public/public.jspf"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/artDialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/artDialog/artDialog.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/artDialog/skins/green.css" />
	<style type="text/css">
		.sty1{background-color: #F2F2F2;font-family: microsoft yahei;padding-left: 45px;border-bottom: 1px solid #CDCDCD;}
		.sty2{background-color: #f2f2f2;font-family: microsoft yahei;font-size: 12px;padding-right: 20px;border-bottom: 0px solid #CDCDCD;}
	</style>
	<script type="text/javascript" >
  		function selectAll() {
  			var chbstatus = $("#chbstatus");
  			chbstatus.attr("checked",!chbstatus.attr("checked"));
			$("input[name='select']").each(function() {
				$(this).attr("checked",chbstatus.attr("checked"));
			});
		}
  		
  		function checkSelect() {
  			var selecttids = $("#selecttids:checked");
  			if(selecttids.length == 0) {
  				art.dialog({
  	  				title:'消息',
  	  			    content: '请选择主题！',
  	  			    icon:'warning'
  	  			});
  				return false;
  			}
  			return true
  		}
  		
  		function topTopic() {
  			if(checkSelect()) {
	  			var htmlcon = $("#selectTopScopeHtml").html();
	  			art.dialog({
	  				title:'选择置顶范围',
	  			    content: htmlcon,
	  			    width:'320px',
	  			    height:'50px',
	  			  	lock: false
	  			});
  			}
  		}
  		//置顶
  		function submitTop(scop) {
  			var tids = "";
  			if(checkSelect()) {
	  			$("#selecttids:checked").each(function() {
	  				tids = tids+$(this).val()+",";
	  			});
				$.ajax({
					type:"POST",
					url:"topicAction_topTopic.action",
					data:"scope="+scop+"&tids="+tids,
					success:function(data) {
						var aa = $(data).text();
						alert(aa);
						document.location.reload();
					},
					dataType:"xml"
				});
  			}
  		}
  		//解除置顶
  		function cancelTop() {
  			var tids = "";
  			if(checkSelect()) {
	  			$("#selecttids:checked").each(function() {
	  				tids = tids+$(this).val()+",";
	  			});
	  			art.dialog({
				    content: '你确定要解除置顶？',
				    ok: function () {
				    	$.ajax({
							type:"POST",
							url:"topicAction_topTopic.action",
							data:"scope=0&tids="+tids,
							success:function(data) {
								var aa = $(data).text();
								alert(aa);
								document.location.reload();
							},
							dataType:"xml"
						});
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
  			}
  		}
  		//精华and解除精华
  		function digest(scope) {
  			var tids = "";
  			if(checkSelect()) {
	  			$("#selecttids:checked").each(function() {
	  				tids = tids+$(this).val()+",";
	  			});
	  			var con = "";
	  			if(scope == 1) {
	  				con = "你确定要精华这些主题？";
	  			}else {
	  				con = "你确定要解除精华？";
	  			}
	  			art.dialog({
				    content: con,
				    ok: function () {
				    	$.ajax({
							type:"POST",
							url:"topicAction_digest.action",
							data:"scope="+scope+"&tids="+tids,
							success:function(data) {
								var aa = $(data).text();
								alert(aa);
								document.location.reload();
							},
							dataType:"xml"
						});
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
  			}
  		}
  		//推荐and解除推荐
  		function recommend(scope) {
  			var tids = "";
  			if(checkSelect()) {
	  			$("#selecttids:checked").each(function() {
	  				tids = tids+$(this).val()+",";
	  			});
	  			var con = "";
	  			if(scope == 1) {
	  				con = "你确定要推荐这些主题？";
	  			}else {
	  				con = "你确定要解除推荐？";
	  			}
	  			art.dialog({
				    content: con,
				    ok: function () {
				    	$.ajax({
							type:"POST",
							url:"topicAction_recommend.action",
							data:"scope="+scope+"&tids="+tids,
							success:function(data) {
								var aa = $(data).text();
								alert(aa);
								document.location.reload();
							},
							dataType:"xml"
						});
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
  			}
  		}
  		
  		function deleteTopic() {
  			var tids = "";
  			if(checkSelect()) {
	  			$("#selecttids:checked").each(function() {
	  				tids = tids+$(this).val()+",";
	  			});
	  			art.dialog({
				    content: "你确定要删除这些主题?",
				    ok: function () {
				    	$.ajax({
							type:"POST",
							url:"topicAction_deleteTopic.action",
							data:"tids="+tids,
							success:function(data) {
								var aa = $(data).text();
								alert(aa);
								document.location.reload();
							},
							dataType:"xml"
						});
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
  			}
  		}
  		
  	</script>
  </head>
  
  <body>
  	<%
  	User user = session.getAttribute("login")==null? null : ((User)session.getAttribute("login"));
  	//Board b = (Board)ActionContext.getContext().get("board");
  	boolean flag = false;
  	String[] pris = {"topicAction_topTopic","topicAction_digest","topicAction_recommend","topicAction_deleteTopic"};
  	if(user != null && user.hasPrivilegeByUrlsOr(pris)) {
  		flag = true;
  	}
  	%>
    <%@ include file="/WEB-INF/jsp/front/public/top.jspf"%>
    <div id="comm_6">
		<a href="index.html"><div id="path_1"></div></a><!-- 首页 -->
		<div id="path_2"></div>
		<div id="path_3">
			<a href="forum.html" class="a_1" style="line-height: 16px;">论坛</a>
		</div>
		<div id="path_2"></div>
		<div id="path_3">
			<a href="forum.html" class="a_1" style="line-height: 16px;">${board.section.name}</a>
		</div>
		<div id="path_2"></div>
		<div id="path_3">
			<a href="forum-${board.id}.html" class="a_1" style="line-height: 16px;">${board.name}</a>
		</div>
		
	</div>
	<div id="topic_boardrule" style="margin:0 auto;">
		<div style="width:930px;height: 55px;margin: 0 auto;">
			<s:a action="" cssClass="a_4" cssStyle="line-height: 30px;font-weight: bold;">${board.name}</s:a>
			<span class="span_3">今日：<span class="span_4">${todayArticleCount}</span></span>
			<span class="span_3">主题：<span class="span_4">${board.topicCount}</span></span>
			<img src="${pageContext.request.contextPath}/style/common/images/collapsed_no.gif" title="收起/展开" onclick="closeDiv(this)" style="float: right;margin: 10px 0px;cursor: pointer;" />
			<br />
			<span class="span_3">版主：<a href="" class="a_0"  style="color:black;">java3392</a></span>
		</div>
		<div style="width:930px;height: auto;margin: 0 auto;">
			${board.description}
		</div>
	</div>
	<div id="topic_fenye">
		<a href="topicnew-${board.id}.html"><div id="topic_fenye1"></div></a>
		<%@ include file="/WEB-INF/jsp/common/pageView.jspf"%>
	</div>
	<input type="hidden" id="actionUrl" value="forum-${id}" />
	<div id="topicListBorder">
		<table cellpadding="0" cellspacing="0" class="table_2" >
			<thead>
				<tr class="tr_11">
					<td class="td_11" style="height: 30px;">
						&nbsp;
					</td>
					<td class="td_22">
						<span class="span_3">标题</span>
					</td>
					<td class="td_33">
						<span class="span_3">作者</span>
					</td>
					<td class="td_44">
						<span class="span_3">回复/查看</span>
					</td>
					<td class="td_55" style="padding-left: 15px;">
						<span class="span_3">最后发表</span>
					</td>
					<td style="width:30px;border-bottom: 1px solid #CDCDCD;">
						<%if(flag) { %>
							<span class="span_3" style="cursor: pointer;" onclick="selectAll()">全选</span>
						<%}else { %>
							&nbsp;
						<%} %>
					</td>
				</tr>
			</thead>
			<!-- 置顶帖子区域 -->
			<tr height="20">
				<td colspan="6" class="sty1"><span style="font-size: 11px; ">置顶主题</span></td>
			</tr>
			<s:iterator value="topList" >
			<tr onmouseover="changeColor(this,0)" onmouseout="changeColor(this,1)">
				<td class="td_11"  style="height: 40px;">
					
					<s:if test="topScope == 0">
						<img
							src="${pageContext.request.contextPath}/style/common/images/folder_common.gif"
							width="17" height="17" />
					</s:if>
					<s:else>
						<img
							src="${pageContext.request.contextPath}/style/common/images/pin_${topScope}.gif"
							width="18" height="18" />
					</s:else>
				</td>
				<td class="td_22">
					<s:if test="topScope == 1">
					<a href="topic-${id}.html" class="a_4" style="font-weight: bold;color:#83C133;font-size: 14px;">${title}</a>&nbsp;&nbsp;
					</s:if>
					<s:elseif test="topScope == 2">
					<a href="topic-${id}.html" class="a_4" style="font-weight: bold;color:#00BEF1;font-size: 14px;">${title}</a>&nbsp;&nbsp;
					</s:elseif>
					<s:elseif test="topScope == 3">
					<a href="topic-${id}.html" class="a_4" style="font-weight: bold;color:red;font-size: 14px;">${title}</a>&nbsp;&nbsp;
					</s:elseif>
					<s:else>
					<a href="topic-${id}.html" class="a_4">${title}</a>&nbsp;&nbsp;
					</s:else>
					<!-- <img
						src="${pageContext.request.contextPath}/style/common/images/hot_1.gif"
						width="22" height="14" title="热门贴"/> -->
					<s:if test="digest == true">
					<img
						src="${pageContext.request.contextPath}/style/common/images/digest_1.gif"
						width="21" height="14" title="精华帖"/>
					</s:if>
					<s:if test="recommend == true">	
						<img
							src="${pageContext.request.contextPath}/style/common/images/recommend_1.gif"
							width="21" height="14" title="推荐贴"/>
					</s:if>
					<s:if test="isAttach > 0">	
						<img
							src="${pageContext.request.contextPath}/images/topic/file.gif"
							width="16" height="16" title="附件"/>
					</s:if>
				</td>
				<td class="td_33">
					<a href="" class="a_0">
						<span class="span_3" style="color: black;">${user.username}</span>
					</a>
					<br />
					<span class="span_5" style="font-family: microsoft yahei;font-size:10px;">${showPostTime }</span>
				</td>
				<td class="td_44">
					<span class="span_3" style="color:#31995E;">${replyCount}</span><span class="span_3">/${visits}</span>
				</td>
				<td class="td_55">
					<s:if test="lastReply != null">
						<a href="" class="a_0">
							<span class="span_3" style="color: black;">${lastReply.user.username}</span>
						</a>
						<br />
						<span class="span_5" style="font-family: microsoft yahei;font-size:10px;">${showLastUpdateTime }</span>
					</s:if>
					<s:else>
						<a href="" class="a_0">
						<span class="span_3" style="color: black;">${user.username}</span>
						</a>
						<br />
						<span class="span_5" style="font-family: microsoft yahei;font-size:10px;"><s:date format="yyyy/MM/dd HH:mm:ss" name="postTime"/></span>
					</s:else>
				</td>
				<td style="border-bottom: 1px solid #CDCDCD;">
					<%if(flag) { %>
						<input type="checkbox" id="selecttids" name="select" value="${id }"/>
					<%}else { %>
						&nbsp;
					<%} %>
				</td>
			</tr>
			</s:iterator>
			<!-- 置顶帖子区域  结束 -->
			<!-- 板块主题 -->
			<tr height="20">
				<td colspan="6" class="sty1"><span style="font-size: 11px; ">板块主题</span></td>
			</tr>
			<!-- 板块列表 -->
			<s:iterator value="recordList" >
			<tr onmouseover="changeColor(this,0)" onmouseout="changeColor(this,1)">
				<td class="td_11"  style="height: 40px;">
					<img src="${pageContext.request.contextPath}/style/common/images/folder_common.gif" width="17" height="17" />
				</td>
				<td class="td_22">
					<a href="topic-${id}.html" class="a_4">${title}</a>&nbsp;&nbsp;
					<!-- <img
						src="${pageContext.request.contextPath}/style/common/images/hot_1.gif"
						width="22" height="14" title="热门贴"/> -->
					<s:if test="digest == true">
					<img
						src="${pageContext.request.contextPath}/style/common/images/digest_1.gif"
						width="21" height="14" title="精华帖"/>
					</s:if>
					<s:if test="recommend == true">	
						<img
							src="${pageContext.request.contextPath}/style/common/images/recommend_1.gif"
							width="21" height="14" title="推荐贴"/>
					</s:if>
					<s:if test="isAttach > 0">	
						<img
							src="${pageContext.request.contextPath}/images/topic/file.gif"
							width="16" height="16" title="附件"/>
					</s:if>
				</td>
				<td class="td_33">
					<a href="" class="a_0">
						<span class="span_3" style="color: black;">${user.username}</span>
					</a>
					<br />
					<span class="span_5" style="font-family: microsoft yahei;font-size:10px;">${showPostTime }</span>
				</td>
				<td class="td_44">
					<span class="span_3" style="color:#31995E;">${replyCount}</span><span class="span_3">/${visits}</span>
				</td>
				<td class="td_55">
					<s:if test="lastReply != null">
						<a href="" class="a_0">
							<span class="span_3" style="color: black;">${lastReply.user.username}</span>
						</a>
						<br />
						<span class="span_5" style="font-family: microsoft yahei;font-size:10px;">${showLastUpdateTime }</span>
					</s:if>
					<s:else>
						<a href="" class="a_0">
						<span class="span_3" style="color: black;">${user.username}</span>
						</a>
						<br />
						<span class="span_5" style="font-family: microsoft yahei;font-size:10px;"><s:date format="yyyy/MM/dd HH:mm:ss" name="postTime"/></span>
					</s:else>
				</td>
				<td style="border-bottom: 1px solid #CDCDCD;">
					<%if(flag) { %>
						<input type="checkbox" id="selecttids" name="select" value="${id }"/>
					<%}else { %>
						&nbsp;
					<%} %>
				</td>
			</tr>
			</s:iterator>
			<%if(flag) { %>
			<tr height="30" align="right">
				<td colspan="6" class="sty2">
					<input type="checkbox" id="chbstatus" style="display:none;" />
					<span class="span_3" style="cursor: pointer;" onclick="selectAll()">全选</span>&nbsp;&nbsp;
					<%if(user!= null && user.hasPrivilegeByUrl("topicAction_topTopic")) { %>
					<span class="span_3" style="cursor: pointer;" onclick="topTopic()">置顶</span>&nbsp;&nbsp;
					<span class="span_3" style="cursor: pointer;" onclick="cancelTop()">解除置顶</span>&nbsp;&nbsp;
					<%} %>
					<%if(user!= null && user.hasPrivilegeByUrl("topicAction_deleteTopic")) { %>
					<span class="span_3" style="cursor: pointer;" onclick="deleteTopic()">删除</span>&nbsp;&nbsp;
					<%} %>
					<%if(user!= null && user.hasPrivilegeByUrl("topicAction_digest")) { %>
					<span class="span_3" style="cursor: pointer;" onclick="digest(1)">精华</span>&nbsp;&nbsp;
					<span class="span_3" style="cursor: pointer;" onclick="digest(0)">解除精华</span>&nbsp;&nbsp;
					<%} %>
					<%if(user!= null && user.hasPrivilegeByUrl("topicAction_recommend")) { %>
					<span class="span_3" style="cursor: pointer;" onclick="recommend(1)">推荐</span>&nbsp;&nbsp;
					<span class="span_3" style="cursor: pointer;" onclick="recommend(0)">解除推荐</span>&nbsp;&nbsp;
					<%} %>
				</td>
			</tr>
			<%} %>
		</table>
	</div>
	<%-- 分页 --%>
	<div id="topic_fenye">
		<a href="topicnew-${board.id}.html"><div id="topic_fenye1"></div></a>
		<%@ include file="/WEB-INF/jsp/common/pageView.jspf"%>
	</div>
	<%@ include file="/WEB-INF/jsp/front/public/buttom.jspf" %>
	<div id="selectTopScopeHtml" style="display:none;">
		<input type="radio" name="top" id="top1" onclick="submitTop(3)"/><label for="top1"><span class="span_3">全局置顶</span></label>&nbsp;&nbsp;
		<input type="radio" name="top" id="top2" onclick="submitTop(2)"/><label for="top2"><span class="span_3">分区置顶</span></label>&nbsp;&nbsp;
		<input type="radio" name="top" id="top3" onclick="submitTop(1)"/><label for="top3"><span class="span_3">板块置顶</span></label>
	</div>
  </body>
</html>
