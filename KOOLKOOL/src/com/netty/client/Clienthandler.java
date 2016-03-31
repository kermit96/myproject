package com.netty.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.*;

public class Clienthandler  extends SimpleChannelInboundHandler<Object> {

    private ClientBase base;
	public Clienthandler(ClientBase base) {
		// TODO Auto-generated constructor stub
		
		this.base = base;
	}
	
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
    	
        base.RunDisconnect();
        
    }
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) {   	
        base.RunConnect();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, final Object msg) {

    	base.RunRead(msg);
    	
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {    	
    	    	
        cause.printStackTrace();
        base.RunError(cause);
    	
    }
	      
}
