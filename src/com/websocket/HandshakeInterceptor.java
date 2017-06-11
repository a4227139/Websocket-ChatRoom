/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.websocket;

import java.util.Map;
import java.util.Random;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author wuwenhai
 * @since JDK1.6
 * @history 2017-6-3 wuwenhai 新建
 * 类说明
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{  
	@Override  
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> attributes) throws Exception {  
        System.out.println("Before handshake "+request.getRemoteAddress().toString());
        //attributes是session里面的所有属性的map表示
        attributes.put("user", getRandomNickName());
        return super.beforeHandshake(request, response, handler, attributes);  
    } 
	
    @Override  
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {  
        System.out.println("After handshake "+request.getRemoteAddress().toString());  
        super.afterHandshake(request, response, wsHandler, ex);  
    }        
    
    //这里没做控制，所以聊天室内的人物名称可能发生重复
    public String getRandomNickName(){
    	String[] nickNameArray={"Captain America","Deadpool","Hawkeye","Hulk","Iron Man","Spider Man","Thor","Wolverine","Black Panther","Colossus"};
    	Random random=new Random();
    	return nickNameArray[random.nextInt(10)];
    }
}  
