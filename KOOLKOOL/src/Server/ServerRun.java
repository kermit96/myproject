package Server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * Receives a sequence of integers from a {@link FactorialClient} to calculate
 * the factorial of the specified integer.
 */
public final class ServerRun {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8322"));

    
    private ServerBase server;
    
    int port = 0;
    
    public ServerRun(int port,ServerBase server)
    {
    	this.port = port;
    	this.server=  server;
    }
    
    public static void main(String[] args) 
    {
    	try {
    	new ServerRun(PORT,null);
    	
    	} catch (Exception ex) {
    		
    	}
    }
    
    
    public void stop()
    {
    	
    	f.close();
    }
    
    
    public void start() 
    {
    	
    	try {
				new Thread(()-> {
					try {						
						run();		
					} catch (Exception ex) {
			
						ex.printStackTrace();
					}
										
				}).start();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	
    }
    
    
    Channel f;
    
    public void run() throws Exception
    {
    	
    	// Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new ServerInitializer(sslCtx,server));

            
           f  =   b.bind(port).sync().channel();
                                        
          f.closeFuture().sync();
                    
          
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
