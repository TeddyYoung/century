package com.century.modules.cxf.client;

import javax.jws.WebService;

@WebService(targetNamespace = "http://service.qfw.com/")
public interface IUserInfoSyn extends java.rmi.Remote {
	public String[] userInfoSyn(UserInfo userInfo);
}
