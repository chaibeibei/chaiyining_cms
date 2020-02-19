package com.chaiyining.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaiyining.cms.common.CmsConstant;
import com.chaiyining.cms.common.CmsMd5Util;
import com.chaiyining.cms.common.JsonResult;
import com.chaiyining.cms.pojo.User;
import com.chaiyining.cms.service.UserService;
import com.cyn.common.utils.StringUtil;



/** 
* @author 作者:
* @version 创建时间：2019年12月10日 下午6:29:19 
* 类功能说明 
*/
@Controller
@RequestMapping("/log/")
public class LoginController {
	@Autowired
	UserService service;
	
	@RequestMapping("/tologin")
	public String tologin() {
		return "admin/login";
	}
	
	@RequestMapping("login")
	@ResponseBody
	public Object login(User user,HttpSession session) {
		
		//判断用户名和密码
		if(StringUtil.isBlank(user.getUsername()) || StringUtil.isBlank(user.getPassword())) {
			return JsonResult.fail(1000, "用户名和密码不能为空");
		}
		//查询用户
		User userInfo = service.getByUsername(user.getUsername());
		//用户为空
		if(userInfo==null) {
			return JsonResult.fail(1000, "用户名或密码错误");
		}
		if(userInfo.getLocked()==1) {
			return JsonResult.fail(1000, "用户禁用");
		}
		//是否管理员
		if(!userInfo.isAdmin()) {
			return JsonResult.fail(1000, "权限不够");
		}
		//判断密码
		String string2md5 = CmsMd5Util.string2MD5(user.getPassword());
		System.out.println(string2md5);
		if(string2md5.equals(userInfo.getPassword())) {
			session.setAttribute(CmsConstant.UserAdminSessionKey, userInfo);
			session.setAttribute("username", userInfo.getUsername());
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "未知错误");
	}
	
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		session.invalidate();
		return "redirect:tologin";
	}
	
	@RequestMapping("/reg")
	public String exit(User user) {
		boolean num=service.register(user);
		return "redirect:tologin";
	}
}
