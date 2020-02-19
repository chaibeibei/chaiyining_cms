package com.chaiyining.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaiyining.cms.dao.LinkDao;
import com.chaiyining.cms.pojo.Link;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LinkService {
	@Autowired
	private LinkDao linkDao;
	/**
	 * @Title: getPageInfo   
	 * @Description: 列表   
	 * @param: @param link
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: PageInfo<Link>      
	 * @throws
	 */
	public PageInfo<Link> getPageInfo(Link link, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Link> userList = linkDao.select(link);
		return new PageInfo<>(userList);
	}
	/**
	 * @Title: save   
	 * @Description: 保存   
	 * @param: @param link
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean save(Link link) {
		link.setCreated(new Date());
		if(link.getId()==null) {
			return linkDao.insert(link)>0;
		}
		return linkDao.update(link)>0;
	}
	/**
	 * @Title: getById   
	 * @Description: 根据Id查询友情链接   
	 * @param: @param id
	 * @param: @return      
	 * @return: Link      
	 * @throws
	 */
	public Link getById(Integer id) {
		return linkDao.selectById(id);
	}
	/**
	 * @Title: delByIds   
	 * @Description: 批量删除   
	 * @param: @param ids      
	 * @return: void      
	 * @throws
	 */
	public boolean delByIds(String ids) {
		return linkDao.deleteByIds(ids)>0;
	}

}
