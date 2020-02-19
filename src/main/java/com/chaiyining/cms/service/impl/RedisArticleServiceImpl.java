package com.chaiyining.cms.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.chaiyining.cms.dao.ArticleDao;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.service.RedisArticleService;
@Service
public class RedisArticleServiceImpl implements RedisArticleService{

	@Resource
	private RedisTemplate redisTemplate;
	
	@Resource
	private KafkaTemplate kafkaTemplate;
	
	@Resource
	private ArticleDao articleDao;
	
	@Override
	public void save(Article article) {
		
		/*ListOperations opsForList = redisTemplate.opsForList();
		opsForList.leftPushAll("article", article);*/
		ValueOperations opsForValue = redisTemplate.opsForValue();
		opsForValue.set("article", article);
		kafkaTemplate.sendDefault("article_kafka","article");
	}

	
}
