/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysDictPostProcessor.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月26日
 */
package com.sirdc.modules.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sirdc.modules.sys.service.SysBasService;
import com.sirdc.modules.sys.service.SysDeptService;
import com.sirdc.modules.sys.service.SysMenuService;
import com.sirdc.modules.sys.service.SysMsgService;
import com.sirdc.modules.sys.service.SysScheduleJobService;


/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月26日
 */
@Component
public class SysApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private SysMsgService sysMsgService;
	
	@Autowired
	private SysBasService sysBasService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysDeptService sysDeptService;
	
	@Autowired
	private SysScheduleJobService scheduleService;

	/**
     * 在web 项目中(spring mvc),系统会存在两个容器,<br>
     * 一个是root application context,<br>
     * 另一个就是我们自己的 projectName-servlet  context(作为root application context的子容器).<br>
     * 这种情况下,就会造成onApplicationEvent方法被执行两次。<br>
     * 为了避免上面提到的问题,我们可以只在root application context初始化完成后调用逻辑代码,其他的容器的初始化完成,则不做任何处理<br>
     */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) { 
			System.out.println("**************************多语系初始化开始**************************");
			
			sysMsgService.refreshCache();
			
			System.out.println("**************************多语系初始化结束**************************");
			System.out.println("**************************数据字典初始化开始**************************");
			
			//注入数据字典
			sysBasService.refreshCache();
			
			System.out.println("**************************数据字典初始化结束**************************");
			System.out.println("**************************菜单初始化开始**************************");
			
			//注入菜单信息
			sysMenuService.refreshCache();
			
			System.out.println("**************************菜单初始化结束**************************");
			System.out.println("**************************任务调度初始化开始**************************");
			
			try {
				scheduleService.initScheduleJob();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("**************************任务调度初始化结束**************************");
			System.out.println("**************************部门信息初始化开始**************************");
			
			//注入部门信息
			sysDeptService.refreshCache();
			
			System.out.println("**************************部门信息初始化结束**************************");
		}
	}
}
