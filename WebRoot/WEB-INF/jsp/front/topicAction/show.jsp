<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${topic.title }</title>
    <%@ include file="/WEB-INF/jsp/front/public/public.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/common/css/public.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/common/css/postlist.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/template/communal.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/wbox/wbox.css">
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/script/public.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/wbox/wbox.js"></script>
  	<script type="text/javascript">
  		//'source','|',
  		$(document).ready(function() {
  			var titletab = $("#titletab");
  			var top = titletab.offset().top;
  			var left = titletab.offset().left;
  			var picdiv = $("#picdiv");
  			picdiv.css("top",(top+99)).css("left",(left+760));
  			picdiv.fadeIn("slow");
  			//alert("----"+top+"----"+(left+760));
  		});
  		
  		KindEditor.ready(function(K) {
			var editor1 = K.create('.kindeditor', 
				{
				items : ['forecolor', 'hilitecolor', 'bold',
				        'italic', 'underline', 'strikethrough', 'removeformat', '|', 'image',
				        'emoticons'
						],
				allowImageUpload:false,
				allowFileManager : false,
			});
			//prettyPrint();
		});
  		$(window).load(function() {
  			var imgNode = $("#content_span").find("img");
  			imgNode.each(function() {
  				var width = $(this).width();
  				var imgsrc = $(this).attr("src");
  				if(width > 740) {//假如图片宽度大于740px时控制其显示宽度
  					$(this).css("width","740px");
  				}
  				$(this).css("cursor","pointer").attr("title","点击预览图片");
  				//运用wBox弹出窗图片预览功能
  				$(this).wBox({target:imgsrc,requestType:"img",title:"图片预览"});
  			});
  		});
  	</script>
  </head>
  
  <body>
  	<%@ include file="/WEB-INF/jsp/front/public/top.jspf"%>
  	<div id="comm_6">
		<a href="forum.html"><div id="path_1"></div></a><!-- 首页 -->
		<div id="path_2"></div>
		<div id="path_3">
			<a href="forum.html" class="a_1" style="line-height: 16px;">论坛</a>
		</div>
		<div id="path_2"></div>
		<div id="path_3">
			<a href="forum.html" class="a_1" style="line-height: 16px;">${topic.board.section.name}</a>
		</div>
		<div id="path_2"></div>
		<div id="path_3">
			<a href="forum-${topic.board.id}.html" class="a_1" style="line-height: 16px;">${topic.board.name}</a>
		</div>
	</div>
  	<div id="topic_fenye">
		<a href="topicnew-${topic.board.id}.html"><div id="topic_fenye1"></div></a>
		<a href="javascript:toButtom();"><div id="topic_fenye3"></div></a>
		<%@ include file="/WEB-INF/jsp/common/pageView.jspf"%>
	</div>
	<input type="hidden" id="actionUrl" value="topicAction_show.action?id=${id}" />
	<table cellpadding="0" cellspacing="0" class="pl_table1" style="margin: 0 auto;" id="titletab">
		<tbody>
			<tr style="height: 40px;">
				<td class="pl_td1">
					<div id="pl_title11"><strong>查看：</strong><span>${topic.visits}</span>&nbsp;<strong>回复：</strong><span>${topic.replyCount}</span></div>
				</td>
				<td class="pl_td2">
					<%--这里标题字数可以不限制 --%>
					<div id="pl_title12" style="padding: 10px 0px;"><span><a href="">${topic.title}</a></span></div>
				</td>
			</tr>
		</tbody>
	</table>
	<table cellpadding="0" cellspacing="0" style="width: 960px;height: 5px;margin: 0 auto;">
		<tbody>
			<tr style="height: 4px;">
				<td class="pl_td3" style="border-left:1px solid #c2d5e3;"></td>
				<td class="pl_td4"></td>
			</tr>
		</tbody>
	</table>
	<!-- --------------------主题帖，只在第一页显示----------------------- -->
	<div id="post_list">
		<table cellpadding="0" cellspacing="0" class="pl_table2">
			<tbody>
				<tr style="height: 390px;">
					<td class="pl_td1" style="vertical-align: top;">
						<div class="pl_td1_top">
							<div class="poster" style="padding-top: 10px;width: 130px;"><a href="" class="public_a1">${topic.user.username}</a></div>
						</div>
						<div class="pl_td1_center">
							<div id="userImage">
								<img src="${pageContext.request.contextPath}/style/common/images/user_defult.jpg" style="width: 120px;height: 120px;"  />
							</div>
							<div class="poster" style="width: 130px;">
								<span class="post_str2"><a href="">${topic.user.role.name}</a></span>
							</div>
							<div class="poster" style="width: 130px;margin-top: 10px;">
								<s:iterator begin="1" end="#topic.user.role.stars">
										<img src="${pageContext.request.contextPath}/style/common/images/star_1.gif" />
									</s:iterator>
							</div>
							<div class="poster" style="width: 130px;margin-top: 10px;">
								<span class="post_str2">UID&nbsp;&nbsp;${topic.user.id}</span>
							</div>
							<div class="poster" style="width: 130px;margin-top: 10px;">
								<span class="post_str2">主题&nbsp;&nbsp;<a href="">${topic.user.topicCount}</a></span>
							</div>
							<div class="poster" style="width: 130px;margin-top: 10px;">
								<span class="post_str2">帖子&nbsp;&nbsp;<a href="">${topic.user.replyCount}</a></span>
							</div>
							<div class="poster" style="width: 130px;margin-top: 10px;">
								<span class="post_str2">在线时间&nbsp;&nbsp;251小时</span>
							</div>
							<div class="poster" style="width: 130px;margin-top: 10px;">
								<span class="post_str2">注册时间&nbsp;&nbsp;<s:date name="%{#topic.user.createTime}" format="yyyy-MM-dd" /></span>
							</div>
						</div>
					</td>
					<td class="pl_td2" style="vertical-align: top;">
						<div class="pl_td2_top">
							<div class="poster" style="padding-top: 10px;width: 760px;">
								<img src="${pageContext.request.contextPath}/style/common/images/userico.png" style="width: 16px;height: 16px;"/>
								<span class="post_str2">发表于&nbsp;&nbsp;<s:date format="yyyy-MM-dd HH:mm:ss" name="%{#topic.postTime}" /></span>
							</div>
						</div>
						<div class="pl_td2_center">
							<div class="poster" style="width: 760px;padding-top: 10px;min-height: 300px;">
								<span class="post_str2" id="content_span" style="font-size: 14px;">
									${topic.content}
								</span>
							</div>
							<hr />
							<div class="poster" style="width: 760px;padding-top: 10px;">
								<s:iterator value="#topic.attaches">
									<img src="${pageContext.request.contextPath}/style/common/images/fileType/zip.gif" />
									<a href="attachAction_download.action?id=${id }" class="a_0 post_str2" >${name }</a><br/>
								</s:iterator>
							</div>
						</div>
					</td>
				</tr>
				<tr style="height: 50px;">
					<td class="pl_td1">
						
					</td>
					<td class="pl_td2" style="vertical-align: top;">
						<div class="pl_td2_sign"><%-- style="border-left: 0px;border-right: 0px;" --%>
							<div class="pl_td2_sign_1">
								<img src="${pageContext.request.contextPath}/style/common/images/sign.png" style="width: 20px;height: 20px;float: left;" />
								<img src="${pageContext.request.contextPath}/style/common/images/sign_line.jpg" style="width: 737px;height: 21px;" />
								<div class="pl_td2_sign_2">
									<span>我们可以借用一个2012年大家最熟悉的词来形容：“方舟”。</span>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr style="height: 42px;">
					<td class="pl_td1">
						
					</td>
					<td class="pl_td2" style="vertical-align: top;">
						
					</td>
				</tr>
				<tr style="height: 4px;">
					<td class="pl_td3"></td>
					<td class="pl_td4"></td>
				</tr>
			</tbody>
		</table>
	</div>	
	<!-- --------------------主题帖，只在第一页显示(结束)----------------------- -->
	
	<!-- --------------------回帖列表（开始）----------------------- -->
	<s:iterator value="recordList">
		<div id="post_list">
			<table cellpadding="0" cellspacing="0" class="pl_table2">
				<tbody>
					<tr style="height: 390px;">
						<td class="pl_td1" style="vertical-align: top;">
							<div class="pl_td1_top">
								<div class="poster" style="padding-top: 10px;width: 130px;"><a href="" class="public_a1">${user.username}</a></div>
							</div>
							<div class="pl_td1_center">
								<div id="userImage">
									<img src="${pageContext.request.contextPath}/style/common/images/user_defult.jpg" style="width: 120px;height: 120px;"  />
								</div>
								<div class="poster" style="width: 130px;">
									<span class="post_str2"><a href="">${user.role.name}</a></span>
								</div>
								<div class="poster" style="width: 130px;margin-top: 10px;">
									<s:iterator begin="1" end="%{user.role.stars}">
										<img src="${pageContext.request.contextPath}/style/common/images/star_1.gif" />
									</s:iterator>
								</div>
								<div class="poster" style="width: 130px;margin-top: 10px;">
									<span class="post_str2">UID&nbsp;&nbsp;${user.id}</span>
								</div>
								<div class="poster" style="width: 130px;margin-top: 10px;">
									<span class="post_str2">主题&nbsp;&nbsp;<a href="">${user.topicCount}</a></span>
								</div>
								<div class="poster" style="width: 130px;margin-top: 10px;">
									<span class="post_str2">帖子&nbsp;&nbsp;<a href="">${user.replyCount}</a></span>
								</div>
								<div class="poster" style="width: 130px;margin-top: 10px;">
									<span class="post_str2">在线时间&nbsp;&nbsp;251小时</span>
								</div>
								<div class="poster" style="width: 130px;margin-top: 10px;">
									<span class="post_str2">注册时间&nbsp;&nbsp;<s:date format="yyyy-MM-dd" name="%{user.createTime}" /></span>
								</div>
							</div>
						</td>
						<td class="pl_td2" style="vertical-align: top;">
							<div class="pl_td2_top">
								<div class="poster" style="padding-top: 10px;width: 760px;">
									<img src="${pageContext.request.contextPath}/style/common/images/userico.png" style="width: 16px;height: 16px;"/>
									<span class="post_str2">发表于&nbsp;&nbsp;<s:date format="yyyy-MM-dd HH:mm:ss" name="%{postTime}" /></span>
								</div>
							</div>
							<div class="pl_td2_center">
								<div class="poster" style="width: 760px;padding-top: 10px;">
									<span class="post_str2" style="font-size: 14px;">
										<s:if test="state == 0">
										${content}
										</s:if>
										<s:if test="state == 1">
											<div style="width:520px;height:50px;border:1px dashed #cdcdcd;background-color: #f2f2f2;line-height: 50px;" >
											&nbsp;&nbsp;&nbsp;
											<span style="font-size:13px;color:gray;">该回复已被屏蔽!</span>
											</div>
										</s:if>
									</span>
								</div>
							</div>
						</td>
					</tr>
					<tr style="height: 50px;">
						<td class="pl_td1">
							
						</td>
						<td class="pl_td2" style="vertical-align: top;">
							<div class="pl_td2_sign"><%-- style="border-left: 0px;border-right: 0px;" --%>
								<div class="pl_td2_sign_1">
									<img src="${pageContext.request.contextPath}/style/common/images/sign.png" style="width: 20px;height: 20px;float: left;" />
									<img src="${pageContext.request.contextPath}/style/common/images/sign_line.jpg" style="width: 737px;height: 21px;" />
									<div class="pl_td2_sign_2">
										<span>我们可以借用一个2012年大家最熟悉的词来形容：“方舟”。</span>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr style="height: 42px;">
						<td class="pl_td1">
							
						</td>
						<td class="pl_td2" style="vertical-align: top;">
							<div class="pl_td2_top" style="border: 0px;">
								<div class="poster" style="width: 760px;padding-top: 15px;">
									<span class="post_str2"><a href="">引用</a></span>&nbsp;&nbsp;
									<span class="post_str2"><s:a action="replyAction_shieldReply?id=%{id}&topicId=%{topic.id}" >屏蔽</s:a></span>
								</div>
							</div>
						</td>
					</tr>
					<tr style="height: 4px;">
						<td class="pl_td3"></td>
						<td class="pl_td4"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</s:iterator>
	<!-- --------------------回帖列表（结束）----------------------- -->
	<div id="topic_fenye">
		<a href="topicnew-${topic.board.id}.html"><div id="topic_fenye1"></div></a>
		<a href="#"><div id="topic_fenye3"></div></a>
		<%@ include file="/WEB-INF/jsp/common/pageView.jspf"%>
	</div>
	<div id="post_list" style="border: 1px solid #cdcdcd;margin-top: 15px;">
		<table cellpadding="0" cellspacing="0" class="pl_table2">
			<tbody>
				<tr style="height: 250px;">
					<td class="pl_td1" style="vertical-align: top;">
						<div class="pl_td1_center" style="height: 250px;">
							<div id="userImage">
								<img src="${pageContext.request.contextPath}/style/common/images/user_default.gif" style="width: 120px;height: 120px;"  />
							</div>
						</div>
					</td>
					<td class="pl_td2" style="vertical-align: top;">
						<s:form action="replyadd.html">
						<s:hidden name="topicId" value="%{#topic.id}"></s:hidden>
						<div class="pl_td2_center" style="margin-top: 10px;">
							<textarea class="kindeditor" name="content" style="width: 100%;height: 200px;"></textarea><br />
							<input type="submit" value="提交回复" class="post_subpost"/>
						</div>
						</s:form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@ include file="/WEB-INF/jsp/front/public/buttom.jspf" %>
	<a id="backTop"></a><!-- 回到顶部 -->
	<div style="position: absolute;width: 148px;display:none;z-index: 10000;" id="picdiv">
		<s:if test="#topic.digest == true">
			<img src="${pageContext.request.contextPath }/images/001.gif" alt="精华帖" />
		</s:if>
		<s:if test="#topic.recommend">	
			<img src="${pageContext.request.contextPath }/images/006.gif" alt="推荐贴" />
		</s:if>
		<s:if test="#topic.topScope > 0">	
			<img src="${pageContext.request.contextPath }/images/005.gif" alt="置顶帖" />
		</s:if>
	</div>
  </body>
</html>
