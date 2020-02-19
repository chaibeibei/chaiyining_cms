package com.chaiyining.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaiyining.cms.dao.SettingsDao;
import com.chaiyining.cms.pojo.Settings;

@Service
public class SettingsService {
	@Autowired
	private SettingsDao settingsDao;
	/**
	 * @Title: get   
	 * @Description: 查询系统设置   
	 * @param: @return      
	 * @return: Settings      
	 * @throws
	 */
	public Settings get() {
		List<Settings> setList = settingsDao.select(null);
		if(setList==null || setList.size()==0) {
			Settings settings = new Settings();
			settingsDao.insert(settings);
			return settings;
		}
		return setList.get(0);
	}
	/**
	 * @Title: save   
	 * @Description: 保存系统设置   
	 * @param: @param settings
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean save(Settings settings) {
		Settings set = settingsDao.selectById(settings.getId());
		set.setSiteDomain(settings.getSiteDomain());
		set.setSiteName(settings.getSiteName());
		System.out.println("settings:"+set);
		return settingsDao.update(set)>0;
	}
}
