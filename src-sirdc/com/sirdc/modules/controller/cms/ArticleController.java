/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: ArticleController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月20日
 */
package com.sirdc.modules.controller.cms;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sirdc.modules.core.exceptions.ServiceException;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.entity.cms.Article;
import com.sirdc.modules.filter.cms.ArticleFilter;
import com.sirdc.modules.service.cms.ArticleService;

/**
 * 系统文章表维护
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年1月20日
 */
@Controller
@RequestMapping(value = "/modules/cms/article")
public class ArticleController  extends JsonBaseController {
	
	@Autowired
	private ArticleService articleService;
	
	@Override
	protected String getView(String view) {
		return "/modules/cms/article/" + view;
	}

	/**
	 * 初始化界面
	 * 
	 * @return url
	 */
	@RequiresPermissions("modules:cms:article:query")
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出文章表
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequiresPermissions("modules:cms:article:query")
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute ArticleFilter filter) {
		List<Article> articles = articleService.query(filter);
		return coverJqGrid(filter, articles);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	@RequiresPermissions("modules:cms:article:query")
	public String filter(@ModelAttribute ArticleFilter filter) {
		return getView("filter");
	}
	
	/**
	 * 创建文章表字段
	 * 
	 * @param Article
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:cms:article:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute Article article) {
		articleService.save(article);
		return coverMessage("200");
	}

	/**
	 * 初始化创建界面
	 * @param model
	 * @return url
	 */
	@RequiresPermissions("modules:cms:article:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showAddView(@ModelAttribute  Article article, Model model) {
		return getView("form");
	}

	/**
	 * 初始化更新文章表界面
	 * 
	 * @param model
	 * @param id
	 * @return url
	 */
	@RequiresPermissions("modules:cms:article:edit")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditView(Model model, @PathVariable String id) {
		Article article = articleService.getById(id);
		model.addAttribute("article", article);
		return getView("form");
	}
	
	/**
	 * 更新文章表操作
	 * @param Article
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:cms:article:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute Article article) {
		articleService.update(article);
		return coverMessage("200");
	}

	/**
	 * 查询文章表并跳转至详情界面
	 * @param id
	 * @param model
	 * @return url
	 */
	@RequiresPermissions("modules:cms:article:detail")
	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable String id, Model model) {
		Article Article = articleService.getById(id);

		if (Article == null) {
			throw new ServiceException("message");
		}

		model.addAttribute("Article", Article);
		return getView("detail");
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:cms:article:delete")
	@RequestMapping(value = "/delete/{id}")
	public Message delete(@PathVariable List<String> id) {
		articleService.batchDelete(id);
		return coverMessage("200");
	}
	

}