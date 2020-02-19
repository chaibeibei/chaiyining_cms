package com.chaiyining.cms.common;

import com.cyn.common.utils.Md5Utils;

public class CmsMd5Util {
	/**
	 * @Title: stringToMd5   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @param str
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String string2MD5(String str) {
		return Md5Utils.string2MD5(str+"_cmsAdmin");
	}
}
