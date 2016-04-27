package com.netty.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.net.*;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.*;

/**
 * Handler for a server-side channel.  This handler maintains stateful
 * information which is specific to a certain channel using member variables.
 * Therefore, an instance of this handler can cover only one channel.  You have
 * to create a new handler instance whenever you create a new channel and insert
 * this handler  to avoid a race condition.
 */

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	private static  final    HashSet<ServerHandler> AcceptHandle = new HashSet<ServerHandler>();  
	private ServerBase server;

	private boolean isAdmin = false;
	public String userid = "";	
	private String    host;
	public String username = "";

	private ChannelHandlerContext  ctx;
	

	
	public  ServerHandler(ServerBase server)
	{
		super();
		this.server =server;

	}

	

	

	// ���� �������� client ��  ã�´�.
	public ServerHandler GetServerHandler(String userid,boolean IsAdmin)  {

		for(ServerHandler temp:AcceptHandle ) {
			if (temp.isAdmin ==  IsAdmin && temp.userid.equals(userid) ) 
				return temp;    	      	   
		}

		return null;
	}



	// ���� 
	public void Write(Serializable obj) {

		try {
			ctx.writeAndFlush(obj);
		} catch (Exception ex) {			
			ex.printStackTrace();
			server.Log("����:"+ex);
		}
	}

		// data�� �о����� ���´�.
	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

		server.Read(ctx, msg);
		

	}





	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		server.exceptionCaught(ctx, cause);
		ctx.close();
	}
}
