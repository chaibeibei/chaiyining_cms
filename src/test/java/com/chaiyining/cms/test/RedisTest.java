package com.chaiyining.cms.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.pojo.Category;
import com.chaiyining.cms.pojo.Channel;
import com.chaiyining.cms.service.ArticleService;
import com.chaiyining.cms.service.RedisArticleService;
import com.cyn.common.utils.DateUtil;
import com.cyn.common.utils.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class RedisTest {

	
	@Resource
	private ArticleService articleService;
	
	
	
	@Resource
	private RedisArticleService redisArticleService;
	
	
	@Test
	public void sendRedis() throws FileNotFoundException {
		ArrayList<Article> arrayList = new ArrayList<>();
		File file = new File("D:\\1709DJsoup\\myJsoup");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			Article article = new Article();
			String read = FileUtil.readTextFileByLine(file2.getPath());
			article.setContent(read);
			
			String replace = file2.getName().replace(".txt", "");
			article.setTitle(replace);
			
			if(read.length()>140) {
				String zy=read.substring(0,140);
			}
			
			
			int hits = new  Random().nextInt(1000);
			article.setHits(hits);
			
			int hot = new  Random().nextInt(100);
			article.setHot(hot);
			
			List<Channel> channelList = articleService.getChannelList();
			int rand = new  Random().nextInt(8)+1;
			Channel channel = channelList.get(rand);
			article.setChannelId(channel.getId());
			
			List<Category> cateListByChannelId = articleService.getCateListByChannelId(channel.getId());
			int dom = new  Random().nextInt(31)+1;
			article.setCategoryId(dom);
			
			Date randomDate = DateUtil.randomDate("2019-1-1", "2020-2-17");
			article.setCreated(randomDate);
			article.setUpdated(randomDate);
			article.setStatus(2);
			article.setDeleted(0);
			article.setCommentcnt(0);
			article.setUserId(196);
			System.out.println(article);
			
			
			arrayList.add(article);
		
			redisArticleService.save(article);
			
		}
	}
	
	
}
