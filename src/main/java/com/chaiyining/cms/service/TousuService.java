package com.chaiyining.cms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaiyining.cms.dao.TousuDao;
import com.chaiyining.cms.pojo.Tousu;
import com.cyn.common.utils.DateUtil;
@Service
public class TousuService {
	@Autowired
	private TousuDao tousuDao;
	@Autowired
	private ArticleService articleService;
	/**
	 * @Title: add   
	 * @Description: 添加评论   
	 * @param: @param comment
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(Tousu tousu) {
		String createdStr = DateUtil.dateTimeFormat.format(new Date());
		tousu.setCreated(createdStr);
		tousuDao.insert(tousu);
		articleService.addTousu(tousu.getArticleId());
		return true;
	}
}
