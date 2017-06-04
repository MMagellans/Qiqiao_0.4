package com.qiqiao.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.ModelDrivenBaseAction;
import com.qiqiao.model.Privilege;
import com.qiqiao.model.Role;

@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role> {
	
	private static final long serialVersionUID = 1L;
	private Long[] privilegeIds;
	/**
	 * 角色列表
	 * @return
	 */
	public String list() throws Exception {
		//准备数据，角色列表
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	public String vipList() throws Exception {
		//准备数据，会员用户组列表
		List<Role> roleList = roleService.findVip();
		ActionContext.getContext().put("vipList", roleList);
		ActionContext.getContext().put("flag", "vip");
		return "vipList";
	}
	
	public String sysList() throws Exception {
		//准备数据，系统用户组列表
		List<Role> roleList = roleService.findSys();
		ActionContext.getContext().put("vipList", roleList);
		ActionContext.getContext().put("flag", "sys");
		return "vipList";
	}
	
	/**
	 * 会员角色添加页面
	 * @return
	 */
	public String addUI() throws Exception {
		
		return "addUI";
	}

	/**
	 * 会员角色添加
	 * @return
	 */
	public String add() throws Exception {
		//设置属性,基本都是自动封装的信息（描述，名称，最小积分，星星数）
		//手动设置的属性：角色类型
		System.out.println("进入角色添加方法");
		model.setRoleType(0);
		roleService.save(model);
//		System.out.println(model.getName());
//		System.out.println(model.getDescription());
//		System.out.println(model.getMinCredits());
//		System.out.println(model.getStars());
		return "toVipList";
	}
	
	/**
	 * 角色删除
	 * @return
	 */
	public String delete() throws Exception {
		//将拥有该角色的用户改成该角色的上一级角色
		userService.updateRole(model.getId());//
		//删除角色信息
		roleService.delete(model.getId());
		// TODO 删除角色信息时应考虑删除条件（比如只剩下一个角色是时，是不可以删除的）
		return "toVipList";
	}
	
	/**
	 * 角色修改页面
	 * @return
	 */
	public String updateUI() throws Exception {
		
		return "updateUI";
	}
	
	/**
	 * 角色修改
	 * @return
	 */
	public String update() throws Exception {
		
		return "toList";
	}
	
	/**
	 * 设置权限页面
	 * @return
	 */
	public String setPrivilegeUI() throws Exception {
		//准备数据
		List<Privilege> topPrivilegeList = privilegeService.findTopPrivilegeList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		//回显数据
		Set<Privilege> privileges = role.getPrivileges();
		int index = 0;
		privilegeIds = new Long[privileges.size()];
		for(Privilege p : privileges) {
			privilegeIds[index++] = p.getId();
		}
		return "setPrivilegeUI";
	}
	
	/**
	 * 设置角色权限
	 * @return
	 */
	public String setPrivilege() throws Exception {
		//从数据库中获取源对象角色
		Role role = roleService.getById(model.getId());
		//给对象设置新的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		//更新到数据库
		roleService.update(role);
		return "toVipList";
	}
	
//------------------------------------
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
}
