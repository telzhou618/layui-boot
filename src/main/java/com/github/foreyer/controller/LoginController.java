package com.github.foreyer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author jameszhou
 *
 */
@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}
