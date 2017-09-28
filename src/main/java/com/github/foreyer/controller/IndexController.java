package com.github.foreyer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author jameszhou
 *
 */
@Controller
public class IndexController {
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping({"/","/index",""})
	public String index(Model model){
		model.addAttribute("name", "Admin");
		return "index";
	}
	/**
	 * 欢迎页
	 * @param model
	 * @return
	 */
	@RequestMapping({"/welcome"})
	public String welcome(Model model){
		return "welcome";
	}
	
}
