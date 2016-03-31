package com.netty.server;

import io.netty.channel.ChannelHandlerContext;

public interface ServerBase {
	
	public void Log(String msg);  
	public void Inactive(ChannelHandlerContext ctx); // client 가 끊어졌을때 불리어 지는 함수 
	public void Active(ChannelHandlerContext ctx);   // client 가 접속 했을떄 불리어 지는 함수 
    public void Read(ChannelHandlerContext ctx, final  Object msg); // 데이터를 읽어을떄 불리어 지는 함수 
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause); // 에러가 나올떄 불리어 지는 함수 
    
}

