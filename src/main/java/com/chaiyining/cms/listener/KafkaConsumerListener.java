package com.chaiyining.cms.listener;

import java.util.List;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.service.ArticleService;

@Component("kafkaConsumerListener")
public class KafkaConsumerListener implements MessageListener<String, String>{

	@Resource
	private ArticleService articleService;
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		String key = record.key();
		
		//判断key值内容
		if(key != null && key.equals("article_kafka")) {
			//添加数据
			
			//获取value
			String json = record.value();
			ValueOperations opsForValue = redisTemplate.opsForValue();
			Article article = (Article) opsForValue.get(json);
			articleService.save(article);
			System.out.println("添加成功");
			
			redisTemplate.delete(json);
			
		}
	}

}
