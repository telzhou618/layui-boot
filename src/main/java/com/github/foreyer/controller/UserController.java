package com.github.foreyer.controller;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ArrayUtils;

import com.github.foreyer.common.bean.Rest;
import com.github.foreyer.constant.UserState;
import com.github.foreyer.entity.User;
import com.github.foreyer.service.IUserService;
import com.github.foreyer.util.ShiroUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired private IUserService userService;
	
	@RequestMapping({"","/","/list"})
	public String index(){
		return "user/user-list";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Rest json(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="limit",defaultValue="10") Integer size,
			User user
			){
		Page<User> pageData = userService.page(page, size,user);
	    return Rest.okCountData(pageData.getTotalElements(), pageData.getContent());
	}
	
	@RequestMapping("/add")
	public String add(){
		return "user/user-add";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(User user,String confPassword){
		user.setCreateTime(new Date());
		if(user.getUserState() == null){
			user.setUserState(UserState.OFF.getState());
		}
		if(!confPassword.equals(user.getPassword())){
			return Rest.failure("两次输入的密码不一致");
		}
		user.setPassword(ShiroUtil.md51024Pwd(user.getPassword(), user.getUserName()));
		userService.save(user);
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
		userService.delete(ids);
		System.out.println(1/0);
		return Rest.ok();
	}
	
	@RequestMapping("/edit")
	@RequiresPermissions("user:update")
	public String edit(Long id,Model model){
		model.addAttribute("user", userService.findOne(id));
		return "user/user-edit";
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(User submitUser){
		if(submitUser.getUserState() == null){
			submitUser.setUserState(0);
		}
		userService.updateById(submitUser, submitUser.getId());
		return Rest.ok();
	}
}
