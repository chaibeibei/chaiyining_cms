package com.chaiyining.cms.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaiyining.cms.common.JsonResult;
import com.chaiyining.cms.pojo.Link;
import com.chaiyining.cms.service.LinkService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/link/")
public class LinkController {
	@Autowired
	private LinkService linkService;
	/**
	 * @Title: user   
	 * @Description: 友情链接管理   
	 * @param: @param link
	 * @param: @param model
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("list")
	public String list(Link link,Model model,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		PageInfo<Link> pageInfo = linkService.getPageInfo(link,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "link/list";
	}
	/**
	 * @Title: update   
	 * @Description: 编辑   
	 * @param: @param id
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public String update(Integer id,Model model) {
		if(id!=null) {
			Link link = linkService.getById(id);
			model.addAttribute("link", link);
		}
		return "link/edit";
	}
	/**
	 * @Title: save   
	 * @Description: 保存  
	 * @param: @param link
	 * @param: @param model
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@RequestMapping("save")
	@ResponseBody
	public JsonResult save(Link link,Model model) {
		linkService.save(link);
		return JsonResult.sucess();
	}
	/**
	 * @Title: delByIds   
	 * @Description: 批量删除   
	 * @param: @param ids
	 * @param: @param model
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@RequestMapping("delByIds")
	@ResponseBody
	public JsonResult delByIds(String ids,Model model) {
		linkService.delByIds(ids);
		return JsonResult.sucess();
	}
}
