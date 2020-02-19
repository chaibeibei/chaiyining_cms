package com.chaiyining.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaiyining.cms.common.CmsConstant;
import com.chaiyining.cms.common.JsonResult;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.pojo.Collect;
import com.chaiyining.cms.pojo.User;
import com.chaiyining.cms.service.ArticleService;
import com.chaiyining.cms.service.CollectService;
import com.cyn.common.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/collect/")
public class CollectController {

	@Resource
	private CollectService collectService;
	
	@Resource
	private ArticleService articleService;
	
	//列表显示
	@RequestMapping(value="collectList")
	public String collectList(Model m,@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="5") Integer pagesize) {
		PageHelper.startPage(pageNum,5);
		List<Collect> list=collectService.collectList();
		PageInfo<Collect> pageInfo = new PageInfo<>(list);
		m.addAttribute("pageInfo",pageInfo);
		return "user/collect";
	}
	
	//添加
	@RequestMapping(value="add")
	@ResponseBody
	public JsonResult collectAdd(Model m,Integer articleId,HttpServletRequest request,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		
	
		//根据id查找文章
		Article article = articleService.getById(articleId);
		Collect collect = new Collect();
		collect.setText(article.getTitle());
		Date date = new Date();
		collect.setCreated(date);
		String requestURI = request.getRequestURI();
		if(StringUtil.isHttpUrl(requestURI)) {
			collect.setUrl(requestURI);
		}else {
			return JsonResult.fail(10000, "url不规范");
		}
	
		Integer id = userInfo.getId();
		collect.setUser_id(id);
		boolean result=collectService.addCollect(collect);
		
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(10000, "未知错误");
	}
	
	//删除
	@RequestMapping(value="deleColl")
	@ResponseBody
	public JsonResult deleColl(Integer id,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		Integer id2 = userInfo.getId();
		//根据传的id查询用户id
		Collect collect=collectService.selectByid(id);
		int user_id = collect.getUser_id();
		if(user_id == id2) {
			//执行删除
			boolean result=	collectService.deleColl(id);
			if(result) {
				return JsonResult.sucess();
			}
		}
			return JsonResult.fail(10000, "不是同一用户");
		
	}
}
