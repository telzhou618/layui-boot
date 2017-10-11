package com.github.foreyer.config;

import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.foreyer.constant.RoleState;
import com.github.foreyer.constant.UserState;
import com.github.foreyer.entity.Menu;
import com.github.foreyer.entity.Role;
import com.github.foreyer.entity.User;
import com.github.foreyer.repository.MenuRepository;
import com.github.foreyer.repository.RoleRepository;
import com.github.foreyer.repository.UserRepository;
import com.github.foreyer.util.ShiroUtil;
import com.google.common.collect.Sets;

@Configuration
public class InitDB {
	
	@Autowired UserRepository	userRepository;
	@Autowired RoleRepository	roleRepository;
	@Autowired MenuRepository	menuRepository;
	
	@Bean
	@Transactional
	public int initUser(){
		User user = userRepository.findByUserName("admin");
		if(user==null){
			
			//初始化菜单
			Menu menu = new Menu(null,"系统管理","#","system",0L,"",1,1);
			menuRepository.save(menu);
			
			Menu menu1 = new Menu(null,"用户管理","/user/list","user",menu.getId(),"",1,2);
			Menu menu2 = new Menu(null,"角色管理","/role/list","role",menu.getId(),"",2,2);
			Menu menu3 = new Menu(null,"菜单管理","/menu/list","menu",menu.getId(),"",3,2);
			Menu menu4 = new Menu(null,"部门管理","/dept/list","dept",menu.getId(),"",4,2);
			menuRepository.save(menu1);
			menuRepository.save(menu2);
			menuRepository.save(menu3);
			menuRepository.save(menu4);
			
			
			Menu menu11 = new Menu(null,"用户列表","/user/list","user:list",menu1.getId(),"",1,3);
			Menu menu22 = new Menu(null,"新增用户","/user/add","user:add",menu1.getId(),"",2,3);
			Menu menu33 = new Menu(null,"编辑用户","/user/update","user:update",menu1.getId(),"",3,3);
			Menu menu44 = new Menu(null,"删除用户","/user/delete","user:delete",menu1.getId(),"",4,3);
			menuRepository.save(menu11);
			menuRepository.save(menu22);
			menuRepository.save(menu33);
			menuRepository.save(menu44);
			
			//初始化角色
			
			Set<Menu> menuSet = Sets.newHashSet(menu1,menu2,menu3,menu4,menu11,menu22,menu33,menu44);
			Role role = new Role(null, "管理员", "admin", "管理员哈哈", RoleState.ON.getState(), menuSet);
			roleRepository.save(role);
			
			
			//初始化用户
			user = new User();
			user.setCreateTime(new Date());
			user.setUserName("admin");
			user.setUserState(UserState.ON.getState());
			user.setPassword(ShiroUtil.md51024Pwd("123456", user.getUserName()));
			
			Set<Menu> menuSet2 = Sets.newHashSet(menu44);
			user.setMenus(menuSet2);
			
			Set<Role> roleSet = Sets.newHashSet(role);
			user.setRoles(roleSet);
			
			userRepository.save(user);
		}
		return 1;
	}
	
}
