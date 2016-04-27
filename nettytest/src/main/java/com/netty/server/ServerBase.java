package com.netty.server;

import io.netty.channel.ChannelHandlerContext;

public interface ServerBase {
	
	public void Log(String msg);  
	public void Inactive(ChannelHandlerContext ctx); // client �� ���������� �Ҹ��� ���� �Լ� 
	public void Active(ChannelHandlerContext ctx);   // client �� ���� ������ �Ҹ��� ���� �Լ� 
    public void Read(ChannelHandlerContext ctx, final  Object msg); // �����͸� �о����� �Ҹ��� ���� �Լ� 
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause); // ������ ���Ë� �Ҹ��� ���� �Լ� 
    
}

