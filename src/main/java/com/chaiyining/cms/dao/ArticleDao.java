package com.chaiyining.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaiyining.cms.pojo.Article;

public interface ArticleDao {
	/**
	 * @Title: selectById   
	 * @Description: 根据Id，查询对象   
	 * @param: @param id
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	Article selectById(@Param("id") Integer id);
	/**
	 * @Title: select   
	 * @Description: 根据User查询列表  
	 * @param: @param user
	 * @param: @return      
	 * @return: List<User>      
	 * @throws
	 */
	List<Article> select(@Param("article") Article article);
	/**
	 * @Title: count   
	 * @Description: 查询数据条数   
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int count(@Param("article") Article article);
	/**
	 * @Title: insert   
	 * @Description: 插入一条记录   
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int insert(@Param("article") Article article);
	/**
	 * @Title: update   
	 * @Description: 根据Id更新记录 
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int update(@Param("article") Article article);
	/**
	 * @Title: deleteById   
	 * @Description: 根据Id删除记录   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateDeletedById(@Param("id") Integer id);
	/**
	 * @Title: deleteByIds   
	 * @Description: 根据Ids批量删除记录   
	 * @param: @param ids "1,2,3"
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateDeletedByIds(@Param("ids") String ids);
	/**
	 * @Title: updateStatus   
	 * @Description: 修改文章状态   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateStatus(@Param("id") Integer id,@Param("status") Integer status);
	/**
	 * @Title: addHot   
	 * @Description: 热点字段+1   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int addHot(@Param("id") Integer id);
	/**
	 * @Title: selectByIds   
	 * @Description: 根据Ids查询文章  
	 * @param: @param ids
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectByIds(@Param("ids") String ids);
	/**
	 * @Title: selectListByChannelId   
	 * @Description: 根据频道Id查询文章   
	 * @param: @param channelId
	 * @param: @param aritcleId 文章Id不能等于该Id
	 * @param: @param num 文章条数
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectListByChannelId(@Param("channelId") Integer channelId, @Param("articleId") Integer articleId, @Param("num") int num);
	/**
	 * @Title: selectByHot   
	 * @Description: 查询热点文章   
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectByHot();
	/**
	 * @Title: selectListByChannelIdAndCateId   
	 * @Description: 根据频道Id和分类Id查询文章   
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param pageNo
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectListByChannelIdAndCateId(@Param("channelId") Integer channelId, @Param("cateId") Integer cateId);
	/**
	 * @Title: selectNewList   
	 * @Description: 查询最新文章   
	 * @param: @param num
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectNewList(@Param("num") int num);
	/**
	 * @Title: addTousu   
	 * @Description: 投诉+1   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int addTousu(Integer id);
}