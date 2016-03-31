
package com.netty.server;

import util.*;

import com.netty.common.MyDecoder;
import com.netty.common.MyEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.ssl.SslContext;

/**
 * Creates a newly configured {@link ChannelPipeline} for a server-side channel.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    private ServerBase server;
    public ServerInitializer(SslContext sslCtx,ServerBase server) {
        this.sslCtx = sslCtx;
        this.server= server;
    }

    @Override
    public void initChannel(SocketChannel ch) {
    	
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }

        // 압축 처리 
        pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
        pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));

        // Add the number codec first,
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyEncoder());

        // and then business logic.
        // Please note we create a handler for every new channel
        // because it has stateful properties.
        pipeline.addLast(new ServerHandler(server));
    }
}
