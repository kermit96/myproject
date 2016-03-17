package Server;

import io.netty.channel.ChannelHandlerContext;

public interface ServerBase {
	
	public void Log(String msg);
	public void Inactive(ChannelHandlerContext ctx);
	public void Active(ChannelHandlerContext ctx);
    public void Read(ChannelHandlerContext ctx, final java.io.Serializable msg);
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause);
    
}

