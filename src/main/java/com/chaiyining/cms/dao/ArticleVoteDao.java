package com.chaiyining.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaiyining.cms.pojo.ArticleVote;

public interface ArticleVoteDao {
	/**
	 * @Title: selectById   
	 * @Description: 根据Id，查询对象   
	 * @param: @param id
	 * @param: @return      
	 * @return: ArticleVote      
	 * @throws
	 */
	ArticleVote selectById(@Param("id") Integer id);
	/**
	 * @Title: select   
	 * @Description: 根据ArticleVote查询列表  
	 * @param: @param articleVote
	 * @param: @return      
	 * @return: List<ArticleVote>      
	 * @throws
	 */
	List<ArticleVote> select(@Param("articleVote") ArticleVote articleVote);
	/**
	 * @Title: count   
	 * @Description: 查询数据条数   
	 * @param: @param articleVote
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int count(@Param("articleVote") ArticleVote articleVote);
	/**
	 * @Title: insert   
	 * @Description: 插入一条记录   
	 * @param: @param articleVote
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int insert(@Param("articleVote") ArticleVote articleVote);
	/**
	 * @Title: update   
	 * @Description: 根据Id更新记录 
	 * @param: @param articleVote
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int update(@Param("articleVote") ArticleVote articleVote);
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