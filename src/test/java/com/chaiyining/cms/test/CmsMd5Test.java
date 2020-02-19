package com.chaiyining.cms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chaiyining.cms.common.CmsMd5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class CmsMd5Test {
	@Test
	public void test() {
		String string2md5 = CmsMd5Util.string2MD5("admin");
		System.out.println(string2md5);
	}
}
