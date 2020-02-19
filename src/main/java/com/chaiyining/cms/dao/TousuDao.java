package com.chaiyining.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaiyining.cms.pojo.Comment;
import com.chaiyining.cms.pojo.Tousu;

public interface TousuDao {
	/**
	 * @Title: selectById   
	 * @Description: 根据Id，查询对象   
	 * @param: @param id
	 * @param: @return      
	 * @return: Comment      
	 * @throws
	 */
	Comment selectById(@Param("id") Integer id);
	/**
	 * @Title: select   
	 * @Description: 根据Comment查询列表  
	 * @param: @param comment
	 * @param: @return      
	 * @return: List<Comment>      
	 * @throws
	 */
	List<Comment> select(@Param("tousu") Tousu tousu);
	/**
	 * @Title: count   
	 * @Description: 查询数据条数   
	 * @param: @param comment
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int count(@Param("tousu") Tousu tousu);
	/**
	 * @Title: insert   
	 * @Description: 插入一条记录   
	 * @param: @param comment
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int insert(@Param("tousu") Tousu tousu);
	/**
	 * @Title: update   
	 * @Description: 根据Id更新记录 
	 * @param: @param comment
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int update(@Param("tousu") Tousu tousu);
	/**
	 * @Title: deleteById   
	 * @Description: 根据Id删除记录   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int deleteById(@Param("id") Integer id);
	/**
	 * @Title: deleteByIds   
	 * @Description: 根据Ids批量删除记录   
	 * @param: @param ids "1,2,3"
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int deleteByIds(@Param("ids") String ids);
}