package com.github.foreyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ArrayUtils;

import com.github.foreyer.common.bean.Rest;
import com.github.foreyer.constant.RoleState;
import com.github.foreyer.entity.Role;
import com.github.foreyer.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired private IRoleService roleService;
	
	@RequestMapping({"","/","/list"})
	public String index(){
		return "role/role-list";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Rest json(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="limit",defaultValue="10") Integer size,
			Role role
			){
		Page<Role> pageData = roleService.page(page, size,role);
	    return Rest.okCountData(pageData.getTotalElements(), pageData.getContent());
	}
	
	@RequestMapping("/add")
	public String add(){
		return "role/role-add";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(Role role){
		if(role.getRoleState() == null){
			role.setRoleState(RoleState.OFF.getState());
		}
		roleService.save(role);
		return Rest.ok();
	}
	
	/**
	 * 执行删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Rest delete(@RequestParam("ids[]") Long[] ids){
		if(ArrayUtils.isEmpty(ids)){
			return Rest.failure("客户端传入对象id为空");
		}
		roleService.delete(ids);
		return Rest.ok();
	}
	
	@RequestMapping("/edit")
	public String edit(Long id,Model model){
		model.addAttribute("role", roleService.findOne(id));
		return "role/role-edit";
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(Role submitRole){
		if(submitRole.getRoleState() == null){
			submitRole.setRoleState(RoleState.OFF.getState());
		}
		roleService.updateById(submitRole, submitRole.getId());
		return Rest.ok();
	}
}
