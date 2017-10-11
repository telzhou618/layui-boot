package com.github.foreyer.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.github.foreyer.common.controller.SuperController;

/**
 * 首页
 * 
 * @author jameszhou
 *
 */
@Controller
public class LoginController extends SuperController{

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	 @RequestMapping(value = "/doLogin",method=RequestMethod.POST)  
	    public  String doLogin(String userName,String password, String captcha,String return_url,RedirectAttributesModelMap model){
		
		 	if(StringUtils.isBlank(userName)){
		 		model.addFlashAttribute("error", "用户名不能为空");
            	return redirectTo("/login");
		 	}
		 	
		 	if(StringUtils.isBlank(password)){
		 		model.addFlashAttribute("error", "密码不能为空");
            	return redirectTo("/login");
		 	}
		 
	    	Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			
			 if (!currentUser.isAuthenticated()) {
		          // token.setRememberMe(true);
		            try {
		                currentUser.login(token);
		            } catch (UnknownAccountException uae) {
		            	model.addFlashAttribute("error", "未知用户");
		            	return redirectTo("/login");
		            } catch (IncorrectCredentialsException ice) {
		            	model.addFlashAttribute("error", "密码错误");
		            	return redirectTo("/login");
		            } catch (LockedAccountException lae) {
		            	model.addFlashAttribute("error", "账号已锁定");
		            	return redirectTo("/login");
		            }
		            catch (AuthenticationException ae) {
		                //unexpected condition?  error?
		            	model.addFlashAttribute("error", "服务器繁忙");
		            	return redirectTo("/login");
		            }
		        }
			/**
			 * 记录登录日志
			 */
		/*	 Subject subject = SecurityUtils.getSubject();
			 SysUser sysUser = (SysUser) subject.getPrincipal();
			 sysLogService.insertLog("用户登录成功",sysUser.getUserName(),request.getRequestURI(),"");*/
			 return redirectTo("/");
	    }  
}
