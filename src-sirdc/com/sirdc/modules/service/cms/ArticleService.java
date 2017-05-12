package com.sirdc.modules.service.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirdc.modules.core.dao.BaseDao;
import com.sirdc.modules.dao.cms.ArticleDao;
import com.sirdc.modules.entity.cms.Article;
import com.sirdc.modules.filter.cms.ArticleFilter;
import com.sirdc.modules.sys.base.StringPKService;
import com.sirdc.modules.sys.service.SysTableService;

@Service
public class ArticleService extends StringPKService<Article> {

	@Autowired
	private ArticleDao dao;
	
	@Autowired
	private SysTableService tableService;
	
	@Override
	protected BaseDao<Article, String> getDao() {
		return dao;
	}
	
	@Override
	protected void beforeSave(Article obj) {
		obj.setSysId(tableService.updateMaxSysId("t_sys_file", null));
	}

	public List<Article> query(ArticleFilter filter) {
		return dao.query(filter);
	}
}