<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title><%=application.getAttribute("bbstitle") %>！官方站</title>
		<%@ include file="/WEB-INF/jsp/front/public/public.jspf"%>
	</head>

	<body style="margin-top: 0px;">
		<%@ include file="/WEB-INF/jsp/front/public/top.jspf"%>
		<div id="comm_6">
			<a href="forum.html"><div id="path_1"></div></a><!-- 首页 -->
			<div id="path_2"></div>
			<div id="path_3">
				<a href="forum.html" class="a_1" style="line-height: 16px;">论坛</a>
			</div>
			
		</div>

		<!-- 分区列表 -->
		<s:iterator value="#sectionList">
			<div id="index_section">
				<div id="index_sectiontitle">
					<a href="" class="a_1"><span class="index_span2">${name}</span></a>
					<img src="${pageContext.request.contextPath}/style/common/images/collapsed_no.gif" title="收起/展开" onclick="closeDiv(this)" style="float: right;margin: 8px 15px;cursor: pointer;" />
				</div>
				<div style="width: 958px; height: auto;">
					<table cellpadding="0" cellspacing="0" class="table_1">
						<thead>
							<tr>
								<td class="td_1" style="height: 30px;">
									&nbsp;
								</td>
								<td class="td_2">
									<span class="span_3">板块</span>
								</td>
								<td class="td_3">
									<span class="span_3">主题</span>
								</td>
								<td class="td_4">
									<span class="span_3">贴数</span>
								</td>
								<td class="td_5" style="padding-left: 15px;">
									<span class="span_3">最后发表</span>
								</td>
							</tr>
						</thead>
						<!-- 板块列表 -->
						<s:iterator value="boards" status="status">
						<tr>
							<td class="td_1">
								<a href="forum-${id}.html"><img
									src="${pageContext.request.contextPath}/style/common/images/boardIcon/0${status.count}.png"
									width="40" height="40" style="border:0px;" /></a>
							</td>
							<td class="td_2">
								<div style="height: 25px;">
									<a href="forum-${id}.html" class="a_4" style="line-height: 25px;font-weight: bold;color:${highColor}">${name}</a>
								</div>
								<div style="height: 25px;">
									<span class="span_3" style="color: red;font-weight:normal;">(今日 : ${todayArticleCount})</span>
									<%--<span class="span_3" style="line-height: 25px;">版主：
										<s:if test="moderator.size() == 0">
											空缺中
										</s:if>
										<s:else>
											<s:iterator value="moderator">
												<a href="" class="a_0"><span class="span_3" style="color:#AECDE6;">${username}</span></a>
												&nbsp;
											</s:iterator>
										</s:else>
									</span> --%>
								</div>
							</td>
							<td class="td_3">
								<span class="span_3">${topicCount}</span>
							</td>
							<td class="td_4">
								<span class="span_3">${articleCount}</span>
							</td>
							<!-- 最后发表 -->
							<td class="td_5">
								<s:if test="lastTopic != null">
								<a href="topic-${lastTopic.id}.html" class="a_0">
									<span class="span_3" style="color: black;">
										<s:if test="lastTopic.title.length() >= 15">
											<s:property value="lastTopic.title.substring(0,15)"/> ...
										</s:if>
										<s:else>
											<s:property value="lastTopic.title"/>
										</s:else>
									</span>
								</a>
								<br />
								<span class="span_3" style="line-height: 25px;">by-</span>
								<a href="" class="a_0"><span class="span_3" style="color:#336699;">${lastTopic.user.username}</span></a>
								<span class="span_3" style="line-height: 25px;">-<s:date name="lastTopic.postTime" format="yyyy/MM/dd HH:mm:ss"/> </span>
							</s:if>
							<s:else>&nbsp;</s:else>
							</td>
						</tr>
						</s:iterator>
					</table>
					<!-- 板块列表（结束） -->
				</div>
			</div>
		</s:iterator>
		<!-- 分区列表（结束） -->
		<%@ include file="/WEB-INF/jsp/front/public/buttom.jspf" %>
	</body>
</html>
