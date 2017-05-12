/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysFilter.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年1月20日
 */
package com.sirdc.modules.filter.sys;

import java.util.List;

import com.sirdc.modules.core.filter.BaseFilter;

/**
 * 
 * @author: weihuang.peng
 * @version Revision: 0.0.1
 * @Date: 2015年1月20日
 */
public class SysUserFilter extends BaseFilter {
	
	private String name;//中文名称
	
	private String userId;//用户id
	
	private List<String> notInIds;//不在id内
	
	private List<String> inIds;//在id内
	
	private String roleId;
	
	private String deptId;
	
	private boolean superAdmin;//是否是超级管理员
	
	private String mobile;
	
	private String latStr;
	
	private String lngStr;
	
	private String raidus;
	
	private String minLat;
	
	private String maxLat;
	
	private String minLng;
	
	private String maxLng;
	
	/**
	 * 
	 * @author: huiyang.yu
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

	/**
	 * @author: weihuang.peng
	 * @return the superAdmin
	 */
	public boolean isSuperAdmin() {
		return superAdmin;
	}

	/**
	 * @author: weihuang.peng
	 * @param superAdmin the superAdmin to set
	 */
	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	/**
	 * @author: weihuang.peng
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @author: weihuang.peng
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @author: weihuang.peng
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @author: weihuang.peng
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	/**
	 * @author: weihuang.peng
	 * @return the notInIds
	 */
	public List<String> getNotInIds() {
		return notInIds;
	}

	/**
	 * @author: weihuang.peng
	 * @param notInIds the notInIds to set
	 */
	public void setNotInIds(List<String> notInIds) {
		this.notInIds = notInIds;
	}

	/**
	 * @author: weihuang.peng
	 * @return the inIds
	 */
	public List<String> getInIds() {
		return inIds;
	}

	/**
	 * @author: weihuang.peng
	 * @param inIds the inIds to set
	 */
	public void setInIds(List<String> inIds) {
		this.inIds = inIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @author: weihuang.peng
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @author: weihuang.peng
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the latStr
	 */
	public String getLatStr() {
		return latStr;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param latStr the latStr to set
	 */
	public void setLatStr(String latStr) {
		this.latStr = latStr;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the lngStr
	 */
	public String getLngStr() {
		return lngStr;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param lngStr the lngStr to set
	 */
	public void setLngStr(String lngStr) {
		this.lngStr = lngStr;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the raidus
	 */
	public String getRaidus() {
		return raidus;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param raidus the raidus to set
	 */
	public void setRaidus(String raidus) {
		this.raidus = raidus;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the minLat
	 */
	public String getMinLat() {
		return minLat;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param minLat the minLat to set
	 */
	public void setMinLat(String minLat) {
		this.minLat = minLat;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the maxLat
	 */
	public String getMaxLat() {
		return maxLat;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param maxLat the maxLat to set
	 */
	public void setMaxLat(String maxLat) {
		this.maxLat = maxLat;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the minLng
	 */
	public String getMinLng() {
		return minLng;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param minLng the minLng to set
	 */
	public void setMinLng(String minLng) {
		this.minLng = minLng;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @return the maxLng
	 */
	public String getMaxLng() {
		return maxLng;
	}

	/**
	 * 
	 * @author: huiyang.yu
	 * @param maxLng the maxLng to set
	 */
	public void setMaxLng(String maxLng) {
		this.maxLng = maxLng;
	}
	
	
	
}
