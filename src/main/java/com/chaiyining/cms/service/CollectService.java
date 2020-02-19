package com.chaiyining.cms.service;

import java.util.List;

import com.chaiyining.cms.pojo.Collect;

public interface CollectService {

	List<Collect> collectList();

	boolean addCollect(Collect collect);

	Collect selectByid(Integer id);

	boolean deleColl(Integer id);

}
