package com.chaiyining.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.chaiyining.cms.pojo.Article;



public interface ArticleRepository extends ElasticsearchRepository<Article, Integer>{
	List<Article> findByTitle(String title);
}
