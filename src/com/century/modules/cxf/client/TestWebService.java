package com.century.modules.cxf.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.sirdc.modules.utils.encrypt.Base64Utils;


public class TestWebService {
	 public static void main(String[] args) {  
         
		 	JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
			svr.setServiceClass(IUserInfoSyn.class);
			svr.setAddress("http://bigdatap.oicp.net:8089/xindai-p2p/webservice/userInfoSyn?wsdl"); //这个地址有可能调整，请根据实际需要配置
			
			IUserInfoSyn service = (IUserInfoSyn) svr.create();
			 UserInfo user1 = new UserInfo();  
			 String password = "";
			 String pwd = "123456";
			 password = Base64Utils.encode(pwd.getBytes());
			 user1.setLoginName("同步测试");
			 user1.setMobile("13159597328");
			 user1.setPassword(password);
			 user1.setOperate("4");
			String[] result = service.userInfoSyn(user1);
			System.out.println(result[0]);
			System.out.println(result[1]);
	          
	    }  
}
