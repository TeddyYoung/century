package com.century.modules.controller.ne;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.sirdc.modules.core.exceptions.SystemException;
import com.sirdc.modules.core.util.ExcelUtil;
import com.sirdc.modules.core.web.JsonBaseController;
import com.sirdc.modules.core.web.model.JqGrid;
import com.sirdc.modules.core.web.model.Message;
import com.sirdc.modules.sys.entity.SysBas;
import com.sirdc.modules.sys.filter.SysBasFilter;
import com.sirdc.modules.sys.service.SysBasService;
import com.sirdc.modules.utils.date.DateUtils;

/**
 *         文化程度设置
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月19日
 */
@Controller
@RequestMapping("/modules/ne/category")
public class NeCategoryController extends JsonBaseController {
	
	@Autowired
	private SysBasService sysBasService;

	@Override
	protected String getView(String view) {
		return "/modules/ne/category/" + view;
	}

	/**
	 * 初始化系统参数维护列表界面
	 * 
	 * @return url
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		return getView("query");
	}

	/**
	 * 输出json系统参数维护
	 * 
	 * @param filter
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public JqGrid query(@ModelAttribute SysBasFilter filter) {
		filter.setClaId("Category");
		List<SysBas> sysBass = sysBasService.queryByClaId(filter.getClaId(),false);
		return coverJqGrid(filter, sysBass);
	}
	
	/**
	 * 跳转至filter界面
	 * @param filter
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(@ModelAttribute SysBasFilter filter) {
		return getView("filter");
	}

	/**
	 * 创建系统参数维护
	 * 
	 * @param sysBas
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:ne:category:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Message create(@ModelAttribute SysBas sysBas) {
		sysBasService.save(sysBas);
		sysBasService.refreshCache();
		return coverMessage("200");
	}

	
	
	/**
	 * 初始化创建新建小类界面
	 * 
	 * @param model
	 * @return url
	 */
	@RequiresPermissions("modules:ne:category:create")
	@RequestMapping(value = "/createBas", method = RequestMethod.GET)
	public String showBasAddView(Model model) {
		SysBas sysBas = new SysBas();
		sysBas.setClaId("Category");
		model.addAttribute("sysBas", sysBas);
		return getView("formBas");
	}

	/**
	 * 初始化更新系统参数维护界面
	 * 
	 * @param model
	 * @param id
	 * @return url
	 */
	@RequiresPermissions("modules:ne:category:edit")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditView(Model model, @PathVariable Long id) {
		SysBas sysBas = sysBasService.getById(id);
		if (sysBas == null) {
			throw new ServiceException("object is empty");
		}
		model.addAttribute("sysBas", sysBas);
		return getView("formBas");
	}
	
	/**
	 * 更新系统参数维护操作
	 * 
	 * @param sysBas
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:ne:category:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message update(@ModelAttribute SysBas sysBas) {
		sysBasService.update(sysBas);
		sysBasService.refreshCache();
		return coverMessage("200");
	}

	
	/**
	 * 删除系统参数维护
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequiresPermissions("modules:ne:category:delete")
	@RequestMapping(value = "/delete/{id}")
	public Message delete(@PathVariable List<Long> id) {
		sysBasService.batchDelete(id);
		sysBasService.refreshCache();
		return coverMessage("200");
	}
	
	@RequiresPermissions("modules:ne:category:excel")
	@RequestMapping(value = "/export")
	public void export(HttpServletResponse response, @ModelAttribute SysBasFilter filter) {
		List<SysBas> sysBas = sysBasService.queryWithExport(filter);
		String filename = "基本表信息" + DateUtils.getDate();
		try {
			ExcelUtil.getInstance().exportObj2Excel(response, filename, sysBas, SysBas.class, false);
		} catch (Exception e) {
			throw new SystemException("export fail");
		}
	}
}
