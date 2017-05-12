package com.easemob.server.comm;

import java.util.ArrayList;
import java.util.List;

import com.easemob.server.httpclient.apidemo.EasemobMessages;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MessageUtil {

	
	private static final String  from = "系统消息";
	
	private static final String  targetType = "users";
	
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	
	public static boolean sendSystemMessage(List<String> users,String msg){
        
        ObjectNode ext = factory.objectNode();
        ArrayNode targetusers = factory.arrayNode();
        for (String user : users) {
        	targetusers.add(user);
		}
        ObjectNode txtmsg = factory.objectNode();
	    txtmsg.put("msg", msg);
	    txtmsg.put("type","txt");
	    ObjectNode result= EasemobMessages.sendMessages(targetType, targetusers, txtmsg, from, ext);
	    if(result.get("success").booleanValue())return true;
	    else return false;
	   
	}
	
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("18033940592");
		list.add("18859274020");
		sendSystemMessage(list, "郑总是猪！！！！！");
	}
	
	
}
