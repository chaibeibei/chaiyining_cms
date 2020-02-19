package com.chaiyining.cms.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
	
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("spring-beans.xml");
		System.out.println("发送成功");
		
	}
}
