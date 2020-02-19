package com.chaiyining.cms.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaiyining.cms.common.JsonResult;
import com.chaiyining.cms.pojo.Settings;
import com.chaiyining.cms.service.SettingsService;

@Controller
@RequestMapping("/admin/")
public class SettingController {
	@Autowired
	private SettingsService settingsService;
	/**
	 * @Title: settings   
	 * @Description: 系统设置   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/settings")
	public String settings(Model model) {
		Settings settings = settingsService.get();
		model.addAttribute("settings", settings);
		return "admin/settings";
	}
	
	@RequestMapping("/settings/save")
	@ResponseBody
	public Object save(Model model,Settings settings) {
        System.out.println(settings);
		boolean result = settingsService.save(settings);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "修改失败");
	}
}
