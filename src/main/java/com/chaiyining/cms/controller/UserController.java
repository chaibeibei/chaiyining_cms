package com.chaiyining.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaiyining.cms.common.CmsConstant;
import com.chaiyining.cms.common.CmsMd5Util;
import com.chaiyining.cms.common.CookieUtil;
import com.chaiyining.cms.common.JsonResult;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.pojo.Channel;
import com.chaiyining.cms.pojo.User;
import com.chaiyining.cms.service.ArticleService;
import com.chaiyining.cms.service.UserService;
import com.cyn.common.utils.StringUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	
	/**
	 * @Title: login   
	 * @Description: 用户登录界面   
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public Object login() {
		
		return "/user/login";
	}
	/**
	 * @Title: login   
	 * @Description: 用户登录接口   
	 * @param: @param user
	 * @param: @param session
	 * @param: @return      
	 * @return: Object    
	 * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(User user,HttpSession session,HttpServletResponse response) {
		//判断用户名和密码
		if(StringUtil.isBlank(user.getUsername()) || StringUtil.isBlank(user.getPassword())) {
			return JsonResult.fail(1000, "用户名和密码不能为空");
		}
		//查询用户
		User userInfo = userService.getByUsername(user.getUsername());
		//用户为空
		if(userInfo==null) {
			return JsonResult.fail(1000, "用户名或密码错误");
		}
		//判断密码
		String string2md5 = CmsMd5Util.string2MD5(user.getPassword());
		if(string2md5.equals(userInfo.getPassword())) {
			session.setAttribute(CmsConstant.UserSessionKey, userInfo);
			//记住密码
			if("1".equals(user.getIsMima())) {
				int maxAge = 1000*60*60*24;
				CookieUtil.addCookie(response, "username", user.getUsername(), null, null, maxAge);
			}
			return JsonResult.sucess();
		}
		return JsonResult.fail(1000, "用户名或密码错误");
	}
	/**
	 * @Title: logout   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @param response
	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping("logout")
	public Object logout(HttpServletResponse response,HttpSession session) {
		session.removeAttribute(CmsConstant.UserSessionKey);
		CookieUtil.addCookie(response, "username", null, null, null, 0);
		return "redirect:/";
	}
	
	/**
	 * @Title: register   
	 * @Description: 用户注册页面   
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public Object register() {
		return "/user/register";
	}
	/**
	 * @Title: register   
	 * @Description: 用户注册接口   
	 * @param: @param user
	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public @ResponseBody Object register(User user,HttpSession session) {
		//判断用户名是否存在
		boolean result = userService.isExist(user.getUsername());
		if(result) {
			return JsonResult.fail(10001, "用户名已存在");
		}
		//用户注册
		boolean register = userService.register(user);
		if(register) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "未知错误");
	}
	
	@RequestMapping("center")
	public String center(HttpServletResponse response,HttpSession session) {
		return "user/center";
	}
	
	/**
	 * @Title: settings   
	 * @Description: 设置用户信息   
	 * @param: @param response
	 * @param: @param session
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="settings",method=RequestMethod.GET)
	public String settings(HttpServletResponse response,HttpSession session,Model model) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		/** 查询用户信息 **/
		User user = userService.getByUsername(userInfo.getUsername());
		model.addAttribute("user", user);
		return "user/settings";
	}
	/**
	 * @Title: settings   
	 * @Description: 保存用户信息  
	 * @param: @param user
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="settings",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult settings(User user,HttpSession session) {
		//修改用户信息
		boolean result = userService.update(user);
		if(result) {
			//跟新session中的用户信息
			User userInfo = userService.getById(user.getId());
			session.setAttribute(CmsConstant.UserSessionKey, userInfo);
			return JsonResult.sucess();
		}
		return JsonResult.fail(100002, "修改失败");
	}
	
	@RequestMapping("comment")
	public String comment(HttpServletResponse response,HttpSession session) {
		return "user/comment";
	}
	
	@RequestMapping("article")
	public String article(Article article,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		//设置用户Id
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		article.setUserId(userInfo.getId());
		//查询文章
		PageInfo<Article> pageInfo = articleService.getPageInfo(article,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		return "user/article";
	}
	/**
	 * @Title: isLogin   
	 * @Description: 验证用户是否登录   
	 * @param: @param session
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value="isLogin",method=RequestMethod.POST)
	public @ResponseBody Object isLogin(HttpSession session) {
		Object userInfo = session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo!=null) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(CmsConstant.unLoginErrorCode, "未登录");
	}
	
	
}
