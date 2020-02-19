package com.chaiyining.cms.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.pojo.Category;
import com.chaiyining.cms.pojo.Channel;
import com.chaiyining.cms.service.ArticleService;
import com.cyn.common.utils.DateUtil;
import com.cyn.common.utils.FileUtil;
import com.cyn.common.utils.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class Producer2Test {

	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private KafkaTemplate kafkaTemplate;
	
	@Test
	public void sendArticle() throws FileNotFoundException {
		
		//创建目录对象
		File file = new File("D:\\1709DJsoup\\myJsoup");
		
		File[] listFiles = file.listFiles();
		//遍历
		for (File file2 : listFiles) {
			String line = FileUtil.readTextFileByLine(file2.getPath());
			Article article = new Article();
			article.setContent(line);
			String replace = file2.getName().replace(".txt", "");
			article.setTitle(replace);
			
			int randomNum = RandomUtil.randomNum(0, 1000);
			article.setHits(randomNum);
			
			int randomNum2 = RandomUtil.randomNum(0, 100);
			article.setHot(randomNum2);
			
			List<Channel> channelList = articleService.getChannelList();
			int randomNums = RandomUtil.randomNum(0, channelList.size()-1);
			
			Channel channel = channelList.get(randomNum2);
			
			article.setChannelId(channel.getId());
			List<Category> cateListByChannelId = articleService.getCateListByChannelId(channel.getId());
			int randomNum3 = RandomUtil.randomNum(0, cateListByChannelId.size()-1);
			Category category = cateListByChannelId.get(randomNum3);
			
			article.setCategoryId(category.getChannelId());
			
			
			Date randomDate = DateUtil.randomDate("2020-01-01", "2020-02-14");
			article.setCreated(randomDate);
			
			article.setUpdated(randomDate);
			
			
			article.setStatus(0);
			article.setDeleted(0);
			article.setCommentcnt(0);
			
			String jsonString = JSON.toJSONString(article);
			
			kafkaTemplate.sendDefault("article",jsonString);
			
			
			
			
		}
		}
}
