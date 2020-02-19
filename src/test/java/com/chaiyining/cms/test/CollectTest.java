package com.chaiyining.cms.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chaiyining.cms.common.CmsConstant;
import com.chaiyining.cms.common.JsonResult;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.pojo.Collect;
import com.chaiyining.cms.pojo.User;
import com.chaiyining.cms.service.ArticleService;
import com.chaiyining.cms.service.CollectService;
import com.cyn.common.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class CollectTest {

	@Resource
	private CollectService collectService;
	@Resource
	private ArticleService articleService;
	//列表查询
	@Test
	public void selectTest() {
		List<Collect> collectList = collectService.collectList();
		for (Collect collect : collectList) {
			System.out.println(collect);
		}
	}
	
	//删除
	@Test
	public void deleteTest() {
		boolean deleColl = collectService.deleColl(40);
		if(deleColl) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}
	
	//添加
	@Test
	public void addTest() {
	
		
	
		//根据id查找文章
		Article article = articleService.getById(7);
		Collect collect = new Collect();
		collect.setText(article.getTitle());
		Date date = new Date();
		collect.setCreated(date);
		
		
		String requestURI = "dfnlsdnf;lnadf;dns";
		if(StringUtil.isHttpUrl(requestURI)) {
			collect.setUrl(requestURI);
		}else {
			System.out.println("url不规范");
		}
	
		
		collect.setUser_id(196);
		boolean result=collectService.addCollect(collect);
		
		System.out.println("添加成功");
	}
}
