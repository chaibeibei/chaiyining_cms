package com.chaiyining.cms.dao;

import java.util.List;

import com.chaiyining.cms.pojo.Collect;

public interface CollectDao {

	List<Collect> collectList();

	boolean addCollect(Collect collect);

	Collect selectByid(Integer id);

	boolean deleColl(Integer id);

}
