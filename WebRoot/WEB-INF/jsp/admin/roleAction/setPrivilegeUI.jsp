<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>title</title>
    <%@ include file="/WEB-INF/jsp/admin/public/public.jspf" %>
	<script type="text/javascript">
		$(function(){
			
			// 给所有的权限复选框添加事件
			$("[name=privilegeIds]").click(function(){

				// 自己选中或取消时，把所有的下级权限也都同时选中或取消
				$(this).parent().siblings().find("input").attr("checked",this.checked);
				
				// 当选中一个权限时，也要同时选中所有的直系上级权限
				if(this.checked){
					
					$(this).parents().children(":first-child").children("input").attr("checked", true);
				}
				// 当取消一个权限时，同级没有选中的权限了，就也取消他的上级权限，再向上也这样做。
				else{
					var parentId = $(this).parent().parent().attr("id");
					if( $(this).parent().parent().siblings("[id="+parentId+"]").find("input:checked").size() == 0 ){
						//alert("0");
						$(this).parent().parent().parent().children(":first-child").find("input").attr("checked", false);
						var start = $(this).parent().parent().parent();
						if(start.siblings("[id="+start.attr("id")+"]").find("input:checked").size() == 0) {
							start.parent().children(":first-child").find("input").attr("checked", false);
						}
					}
				}
			});
			
		});
		function leftOrDown( node ) {
			node = $(node);
			var node1 = node.parent().siblings();
			node1.toggle();
			var temp = node1.is(":hidden");//是否隐藏
			if(temp == true) {
				node.attr("src","${pageContext.request.contextPath}/style/common/images/left.gif");
			}else {
				node.attr("src","${pageContext.request.contextPath}/style/common/images/down.gif");
			}
		}
	</script>
  </head>
  
  <body>
  	<s:form action="roleAction_setPrivilege" >
  	<input type="hidden" name="id" value="${role.id }"/>
  	<input type="image" src="${pageContext.request.contextPath}/style/common/images/button/submit.PNG" />
  	
  	<s:iterator value="#topPrivilegeList" >
  	<!-- 第一级 -->
    <div id="privilegeListDiv1">
    	<div name="first" style="width:100%;height: 20px;">
    		<img src="${pageContext.request.contextPath}/style/common/images/down.gif" onclick="leftOrDown(this)" style="margin: 0px;"/>
    		<input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/>  style="margin: 0px;"/>
    		<label for="cb_${id}"><span class="ac_span1">${name}</span></label>
    	</div>
    	
    	<s:iterator value="children">
    	<!-- 第二级 -->
    	<div id="privilegeListDiv2" style="padding-left: 30px;">
    		
    		<div name="first" style="width:100%;height: 20px;">
    			<img src="${pageContext.request.contextPath}/style/common/images/down.gif" onclick="leftOrDown(this)" style="margin: 0px;"/>
    			<input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> style="margin: 0px;"/>
    			<label for="cb_${id}"><span class="ac_span1">${name}</span></label>
    		</div>
    		
    		<!-- 第三级 -->
    		<s:iterator value="children">
    		<div id="privilegeListDiv3" style="padding-left: 50px;display: inline;">
    			<div style="display: inline;">
	    			<input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
	    			<label for="cb_${id}"><span class="ac_span1">${name}</span></label>
	    		</div>
    		</div>
   			</s:iterator>
	    	
    	</div>
    	</s:iterator>
    </div>
    </s:iterator>
    </s:form>
  </body>
</html>
