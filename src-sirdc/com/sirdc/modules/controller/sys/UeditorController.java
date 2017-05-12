package com.sirdc.modules.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckfinder.connector.ServletContextFactory;
import com.sirdc.modules.ueditor.UeditorActionEnter;
import com.sirdc.modules.ueditor.UeditorService;

/**
 * Ueditor后台处理入口
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name="ueditorServiceImpl")
	private UeditorService ueditoreService;

	@RequestMapping(value="/action")
	@ResponseBody
	public String execute(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String rootPath = ServletContextFactory.getServletContext().getRealPath("/");
		String resultMsg = new UeditorActionEnter(request, rootPath, this.ueditoreService).exec();
		return resultMsg;
	}
}
