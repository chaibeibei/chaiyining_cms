package com.chaiyining.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaiyining.cms.pojo.VoteContent;

public interface VoteContentDao {
	/**
	 * @Title: selectById   
	 * @Description: 根据Id，查询对象   
	 * @param: @param id
	 * @param: @return      
	 * @return: VoteContent      
	 * @throws
	 */
	VoteContent selectById(@Param("id") Integer id);
	/**
	 * @Title: select   
	 * @Description: 根据VoteContent查询列表  
	 * @param: @param voteContent
	 * @param: @return      
	 * @return: List<VoteContent>      
	 * @throws
	 */
	List<VoteContent> select(@Param("voteContent") VoteContent voteContent);
	/**
	 * @Title: count   
	 * @Description: 查询数据条数   
	 * @param: @param voteContent
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int count(@Param("voteContent") VoteContent voteContent);
	/**
	 * @Title: insert   
	 * @Description: 插入一条记录   
	 * @param: @param voteContent
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int insert(@Param("voteContent") VoteContent voteContent);
	/**
	 * @Title: update   
	 * @Description: 根据Id更新记录 
	 * @param: @param voteContent
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int update(@Param("voteContent") VoteContent voteContent);
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