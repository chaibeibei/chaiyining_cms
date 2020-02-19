package com.chaiyining.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chaiyining.cms.dao.CollectDao;
import com.chaiyining.cms.pojo.Collect;
import com.chaiyining.cms.service.CollectService;
@Service
public class CollectServiceImpl implements CollectService{

	@Resource
	private CollectDao collectDao;

	@Override
	public List<Collect> collectList() {
		
		return collectDao.collectList();
	}

	@Override
	public boolean addCollect(Collect collect) {
		
		return collectDao.addCollect(collect);
	}

	@Override
	public Collect selectByid(Integer id) {
		
		return collectDao.selectByid(id);
	}

	@Override
	public boolean deleColl(Integer id) {
		// TODO Auto-generated method stub
		return collectDao.deleColl(id);
	}
}
