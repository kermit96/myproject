package com.netty.client;
import com.netty.common.MyDecoder;
import com.netty.common.MyEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.ssl.SslContext;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    
    private String host;
    private int  port;
    
    private ClientBase base;
    public ClientInitializer(SslContext sslCtx,String host,int port,ClientBase base) {
    	super();
    	this.host = host;
    	this.port  = port;
        this.sslCtx = sslCtx;
        this.base = base;
    }
  

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc(), host, port));
        }

        // Enable stream compression (you can remove these two if unnecessary)
        pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
        pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));

        // Add the Object 
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyEncoder());
        // and then business logic.
        pipeline.addLast(new Clienthandler(base));                
    }
	


}
