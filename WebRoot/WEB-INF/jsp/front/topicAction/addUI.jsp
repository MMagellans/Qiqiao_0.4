<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>发表新主题</title>
	<%@ include file="/WEB-INF/jsp/front/public/public.jspf"%>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
  	<script type="text/javascript">
  		//'source','|',
  		KindEditor.ready(function(K) {
			var editor1 = K.create('.kindeditor', 
				{
				items : ['undo', 'redo', '|', 'preview','code',
				        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen',
				        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
				        'table', 'hr', 'emoticons',
				        'anchor', 'link', 'unlink',
						],
				uploadJson : '${pageContext.request.contextPath}/kindeditor/jsp/upload_json.jsp',
				allowFileManager : false,
			});
			prettyPrint();
		});
  		
  		function addFile() {
  			var filetbody = $("#addfile_tbody");
  			var filetr = "<tr><td height='40px' width='300px'><input type='file' name='uploads' style='margin-left: 15px;'/></td><td><input type='button' value='删除' onclick='delFile(this)'/></td></tr>";
  			filetbody.append(filetr);
  		}
  		function delFile(node) {
  			var filetr = $(node).parent().parent();
  			filetr.remove();
  		}
  		
  	</script>
  </head>
  
  <body>
  	<%@ include file="/WEB-INF/jsp/front/public/top.jspf"%>
  	<div id="comm_6">
		<a href=""><div id="path_1"></div></a><!-- 首页 -->
		<div id="path_2"></div>
		<div id="path_3">
			<a href="" class="a_1" style="line-height: 16px;">论坛</a>
		</div>
		<div id="path_2"></div>
		<div id="path_3">
			<a href="" class="a_1" style="line-height: 16px;">${board.section.name}</a>
		</div>
		<div id="path_2"></div>
		<div id="path_3">
			<a href="" class="a_1" style="line-height: 16px;">${board.name}</a>
		</div>
	</div>
	<form action="topicAction_add.action" method="post" name="addTopic_form" enctype="multipart/form-data">
	<s:hidden name="boardId" value="%{#board.id}"></s:hidden>
	<div id="comm_9">
		<table cellpadding="0" cellspacing="0" class="table_1">
			<thead>
				<tr class="comm9_tr1">
					<td style="border-top: 1px solid white;">
						<span class="span_3" style="margin-left: 25px;">发表新主题</span>
					</td>
					<td style="border-top: 1px solid white;">&nbsp;</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="comm9_td1">
						<span class="span_3" style="margin-left: 25px;">标题</span>
					</td>
					<td class="comm9_td2">
						<input type="text" name="title" class="input_1" style="width: 400px;"/>
					</td>
				</tr>
				<tr>
					<td class="comm9_td1" style="border: 0px;" valign="top">
						<span class="span_3" style="margin-left: 25px;">内容</span>
					</td>
					<td class="comm9_td2" style="border: 0px;padding: 10px 0px;padding-right: 15px;">
						<textarea class="kindeditor" name="content" style="width: 100%;height: 400px;"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		
	</div>
	<table cellpadding="0" cellspacing="0" class="table_1" style="margin: 10px auto;border: 1px solid #cdcdcd;">
		<thead>
			<tr class="comm9_tr1">
				<td style="border-top: 1px solid white;border-bottom: 1px solid #cdcdcd;">
					<span class="span_3" style="margin-left: 25px;">上传附件</span>
				</td>
				<td style="border-top: 1px solid white;border-bottom: 1px solid #cdcdcd;"">
					<img width="22px" height="22px" onclick="addFile()" src="${pageContext.request.contextPath}/style/common/images/addFile.gif" />
				</td>
			</tr>
		</thead>
		<tbody id="addfile_tbody">
			<tr>
				<td height="40px" width="300px">
					<input type="file" name="uploads" style="margin-left: 15px;"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div style="width: 960px;margin:10px auto;">
		<input type="submit" class="submit_1 post_subpost" value="发表帖子" />
	</div>
	</form>
	<%@ include file="/WEB-INF/jsp/front/public/buttom.jspf" %>
  </body>
</html>
