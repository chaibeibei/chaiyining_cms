package com.chaiyining.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.chaiyining.cms.pojo.Article;
import com.chaiyining.cms.pojo.Category;
import com.chaiyining.cms.pojo.Channel;
import com.chaiyining.cms.pojo.Slide;
import com.chaiyining.cms.pojo.User;
import com.chaiyining.cms.service.ArticleService;
import com.chaiyining.cms.service.SlideService;
import com.chaiyining.cms.service.UserService;
import com.chaiyining.cms.util.ESUtils;
import com.github.pagehelper.PageInfo;

import scala.concurrent.ops;

@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SlideService slideService;
	
	@Resource
	private KafkaTemplate kafkaTemplate;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Resource
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Resource
	private RedisTemplate redisTemlate;
	@RequestMapping(value="/")
	public String index(Model model) {
		return index(1, model);
	}
	
	@RequestMapping(value="/hot/{pageNum}.html")
	public String index(@PathVariable Integer pageNum, Model model) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 轮播图 */
		List<Slide> slideList = slideService.getAll();
		model.addAttribute("slideList", slideList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 热点文章 **/
		if(pageNum==null) {
			pageNum=1;
		}
		PageInfo<Article> pageInfo =  articleService.getHotList(pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	
	
	
	@RequestMapping("/{channelId}/{cateId}/{pageNo}.html")
	public String channel(@PathVariable Integer channelId, Model model,
			@PathVariable Integer cateId,@PathVariable Integer pageNo) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 分类 */
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		PageInfo<Article> pageInfo =  articleService.getListByChannelIdAndCateId(channelId,cateId,pageNo);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	
	
	//设置查询以及高亮显示'
	@RequestMapping("/articleSearch")
	public String search(Model model,String name) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		
		AggregatedPage<Article> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, null, 0, 100, "id", new String[] {"title"}, name);
		List<Article> list = selectObjects.getContent();
		PageInfo pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	@RequestMapping("article/{id}.html")
	public String articleDetail(@PathVariable Integer id,Model model,HttpServletRequest request) {
		/** 查询文章 **/
		Article article = articleService.getById(id);
		model.addAttribute("article", article);
		/** 查询用户 **/
		User user = userService.getById(article.getUserId());
		model.addAttribute("user", user);
		//添加浏览量
				articleService.addHot(article.getId());
				String json=JSON.toJSONString(article.getId());
				kafkaTemplate.sendDefault("1709d",json);
				
				String ip=request.getRemoteAddr();
				ValueOperations opsForValue = redisTemlate.opsForValue();
				String string = (String) opsForValue.get("Hits_${"+id+"}_${"+ip+"}");
				if(("Hits_${"+id+"}_${"+ip+"}").equals(string)) {
					
					
				}else {
					opsForValue.set("Hits_${"+id+"}_${"+ip+"}", "Hits_${"+id+"}_${"+ip+"}",5,TimeUnit.MINUTES);
					boolean addHot = articleService.addHot(id);
					threadPoolTaskExecutor.execute(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
						}
					});
				
				}
		/** 查询相关文章 **/
		List<Article> articleList = articleService.getListByChannelId(article.getChannelId(),id,10);
		model.addAttribute("articleList", articleList);
		return "article/detail";
	}

}
