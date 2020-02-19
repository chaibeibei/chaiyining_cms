package com.chaiyining.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaiyining.cms.pojo.Settings;

public interface SettingsDao {
	/**
	 * @Title: selectById   
	 * @Description: 根据Id，查询对象   
	 * @param: @param id
	 * @param: @return      
	 * @return: Settings      
	 * @throws
	 */
	Settings selectById(@Param("id") Integer id);
	/**
	 * @Title: select   
	 * @Description: 根据Settings查询列表  
	 * @param: @param settings
	 * @param: @return      
	 * @return: List<Settings>      
	 * @throws
	 */
	List<Settings> select(@Param("settings") Settings settings);
	/**
	 * @Title: count   
	 * @Description: 查询数据条数   
	 * @param: @param settings
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int count(@Param("settings") Settings settings);
	/**
	 * @Title: insert   
	 * @Description: 插入一条记录   
	 * @param: @param settings
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int insert(@Param("settings") Settings settings);
	/**
	 * @Title: update   
	 * @Description: 根据Id更新记录 
	 * @param: @param settings
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int update(@Param("settings") Settings settings);
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