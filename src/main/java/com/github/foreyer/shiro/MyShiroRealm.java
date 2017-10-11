package com.github.foreyer.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.foreyer.constant.UserState;
import com.github.foreyer.entity.Menu;
import com.github.foreyer.entity.Role;
import com.github.foreyer.entity.User;
import com.github.foreyer.service.IUserService;
import com.google.common.collect.Lists;

/**
 * 认证和授权
 * @author jameszhou
 *
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired private IUserService userService;
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		User user = userService.findByName(upt.getUsername());
		
		if(user == null){
			throw new UnknownAccountException();
		}
		if(user.getUserState() == UserState.OFF.getState()){
			throw new LockedAccountException();
		}
		ByteSource byteSource = ByteSource.Util.bytes(user.getUserName());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),byteSource,getName());
		return info;
	}
	
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User repUser = userService.findByName(user.getUserName());
		
		/**
		 * 设置角色
		 */
		Set<Role> roleSet = repUser.getRoles();
		Set<String> roleResources = new HashSet<String>(Lists.transform(new ArrayList<Role>(roleSet),r-> r.getResource()));
		info.setRoles(roleResources);
		
		/**
		 * 设置权限
		 */
		Set<Menu> menuSet = repUser.getMenus();
		roleSet.forEach(role->{ menuSet.addAll(role.getMenus());});
		Set<String> menuResources = new HashSet<String>(Lists.transform(new ArrayList<Menu>(menuSet),r-> r.getResource()));
		info.setStringPermissions(menuResources);
		
		return info;
	}

	/**
	 * 密码加密 测试
	 * @param args
	 */
	public static void main(String[] args) {
		// MD5,"原密码","盐",加密次数
		String pwd =  new SimpleHash("MD5", "123456", "admin", 1024).toString();
		System.out.println(pwd);
		
	}

}