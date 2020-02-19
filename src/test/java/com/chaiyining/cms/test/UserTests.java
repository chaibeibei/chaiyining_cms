package com.chaiyining.cms.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chaiyining.cms.dao.ArticleDao;
import com.chaiyining.cms.dao.ArticleVoteDao;
import com.chaiyining.cms.dao.CategoryDao;
import com.chaiyining.cms.dao.ChannelDao;
import com.chaiyining.cms.dao.CommentDao;
import com.chaiyining.cms.dao.SettingsDao;
import com.chaiyining.cms.dao.SlideDao;
import com.chaiyining.cms.dao.TagDao;
import com.chaiyining.cms.dao.UserDao;
import com.chaiyining.cms.dao.VoteContentDao;
import com.chaiyining.cms.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring-beans.xml")
public class UserTests {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleVoteDao articleVoteDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private SettingsDao settingsDao;
	@Autowired
	private SlideDao slideDao;
	@Autowired
	private TagDao tagDao;
	@Autowired
	private VoteContentDao voteContentDao;
	
	@Test
	public void select() {
		List<User> userList = userDao.select(null);
		System.out.println(userList);
		
		User user = new User();
		user.setUsername("lisi");
		user.setPassword("123456");
		userDao.deleteByIds("170");
		
	}
}
