package com.chaiyining.cms.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chaiyining.cms.dao.ArticleRepository;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ESTest {

	@Resource
	private ArticleRepository articleRepository;
	
	@Resource
	private ArticleService articleService;
	
	@Test
	public void esAdd() {
		Article article = new Article();
		article.setStatus(1);
		PageInfo<Article> pageInfo = articleService.getPageInfo(article, 1, 1000);
		List<Article> list = pageInfo.getList();
		
		articleRepository.saveAll(list);
	
		System.out.println("添加成功");
		
	}
	
}




