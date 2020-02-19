package com.chaiyining.cms.service;

import com.chaiyining.cms.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	/**
	 * @Title: register   
	 * @Description: 注册新增用户   
	 * @param: @param user
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean register(User user);
	/**
	 * @Title: getByUsername   
	 * @Description: 根据登录名称，查询用户   
	 * @param: @param username
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	User getByUsername(String username);
	/**
	 * @Title: locked   
	 * @Description: 锁用户   
	 * @param: @param userId
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean locked(Integer userId);
	/**
	 * @Title: unLocked   
	 * @Description: 解锁用户   
	 * @param: @param userId
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean unLocked(Integer userId);
	/**
	 * @Title: addScore   
	 * @Description: 给用户添加积分，返回用户总积分。 
	 * @param: @param userId
	 * @param: @param score
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int addScore(Integer userId,int score);
	/**
	 * @Title: getPageInfo   
	 * @Description: 查询用户列表（PageInfo）   
	 * @param: @param user
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: PageInfo<User>      
	 * @throws
	 */
	PageInfo<User> getPageInfo(User user, int pageNum,int pageSize);
	/**
	 * @Title: update   
	 * @Description: 更新用户信息
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	boolean update(User user);
	/**
	 * @Title: isExist   
	 * @Description: 根据用户名判断用户是否存在   
	 * @param: @param username
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean isExist(String username);
	/**
	 * @Title: getById   
	 * @Description: 根据用户Id查询User   
	 * @param: @param id
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	User getById(Integer id);
	
	
}
