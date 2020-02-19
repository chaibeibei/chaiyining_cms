package com.chaiyining.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaiyining.cms.common.CmsConstant;
import com.chaiyining.cms.common.JsonResult;
import com.chaiyining.cms.pojo.Comment;
import com.chaiyining.cms.pojo.User;
import com.chaiyining.cms.service.CommentService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/comment/")
public class CommentController {
	@Autowired
	private CommentService commentService;
	/**
	 * @Title: add   
	 * @Description: 添加评论 
	 * @param: @param comment
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public @ResponseBody JsonResult add(Comment comment,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		comment.setUserid(userInfo.getId());
		boolean result = commentService.add(comment);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(10000, "未知错误");
	}
	/**
	 * @Title: getPageInfo   
	 * @Description: 评论列表   
	 * @param: @param articleId
	 * @param: @param pageNum
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String getPageInfo(@RequestParam(value="articleId") int articleId,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,Model model) {
		PageInfo<Comment> pageInfo = commentService.getPageInfo(articleId, pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "comment/list";
	}
}
