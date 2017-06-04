package com.qiqiao.intercepter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.qiqiao.model.User;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7478403551812472189L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
//		System.out.println("--------------->之前");
//		
//		invocation.invoke();
//		
//		System.out.println("--------------->之后");
		// 获取当前用户
		User user = (User) ActionContext.getContext().getSession().get("login");

		// 获取当前访问的URL，并去掉当前应用程序的前缀（也就是 namespaceName + actionName ）
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privilegeUrl = null;
		if (namespace.endsWith("/")) {
			privilegeUrl = namespace + actionName;
		} else {
			privilegeUrl = namespace + "/" + actionName;
		}

		// 要去掉开头的'/'
		if (privilegeUrl.startsWith("/")) {
			privilegeUrl = privilegeUrl.substring(1);
		}

		// 如果未登录用户
		if (user == null) {
			
			if(User.guestHasPrivilegeByUrl(privilegeUrl)) {
				System.out.println("没登陆，有权限！");
				return invocation.invoke();
			}else {
				if (privilegeUrl.startsWith("userAction_login")) { // userAction_login, userAction_loginUI
					// 如果是正在使用登录功能，就放行
					
					return invocation.invoke();
				} else {
					// 如果不是去登录，就转到登录页面
					System.out.println("没登陆，转入前台登陆页面！");
					return "frontLoginUI";
				}
			}
		}
		// 如果已登录用户（就判断权限）
		else {
			if (user.hasPrivilegeByUrl(privilegeUrl)) {
				// 如果有权限，就放行
				System.out.println("登陆，有权限！");
				return invocation.invoke();
			} else {
				// 如果没有权限，就转到提示页面
				System.out.println("登陆，没有权限！");
				return "noPrivilegeError";
			}
		}
		
	}

}
