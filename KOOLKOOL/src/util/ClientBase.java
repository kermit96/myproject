package util;

import java.io.Serializable;
import java.nio.channels.Channel;
import java.util.HashSet;
import java.util.Timer;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.*;


public class ClientBase 
{

	private final Semaphore readlock = new Semaphore(1);
	private final Semaphore connectlock = new Semaphore(1);
	private final Semaphore errorlock = new Semaphore(1);
	private final Semaphore disconnectlock = new Semaphore(1);

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private String host ="";
	private int port = 0;

	public ClientBase(String host,int port)
	{
		this.host= host;
		this.port = port;
	}

	public ClientBase()
	{

	}

	// 접속할 host 와 port 를 변경한다.

	public void  SethostAndPort(String host,int port )
	{
		this.host= host;
		this.port = port;
	}

	public void ChangeSetting(String host,int port, boolean isSSL) {
		this.host = host;
		this.port = port;
		this.SSL = isSSL;
	}

	private HashSet<ClientDisconnect> disconnects = new HashSet<ClientDisconnect>();
	private HashSet<ClientConnect> connects = new HashSet<ClientConnect>();

	private HashSet<ClientRead> readers = new HashSet<ClientRead>();

	private HashSet<ClientError> errors = new HashSet<ClientError>();


	public void AddClientDisconnect(ClientDisconnect disconnect)
	{

		try {
			disconnectlock.acquire();
		} catch (InterruptedException e) {
				e.printStackTrace();
			return;
		}

		try {  
			this.disconnects.add(disconnect);
		} finally {
			disconnectlock.release();
		}

	}

	public void AddClientConnect(ClientConnect connect)
	{		
		try {
			connectlock.acquire();
		} catch (InterruptedException e) {		
			e.printStackTrace();
			return;
		}

		try {  
			this.connects.add(connect);
		} finally {
			connectlock.release();
		}
	}

	public void RunConnect()
	{		
		HashSet<ClientConnect> runs = new HashSet<ClientConnect>();

		try {
			connectlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		try {

			runs.addAll(connects);

		} finally {
			connectlock.release();
		}

		for(ClientConnect connect :runs) {        		    	  
			connect.run();
		}	

		runs.clear();

	}

	// Clientread 가 등록되어 있는지 check 한다.
	public boolean CheckClientRead(ClientRead reader )
	{				
		return readers.contains(reader);
	}

	public void AddClientRead(ClientRead reader)
	{				
		try {
			readlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		try {
			this.readers.add(reader);		
		} finally {
			readlock.release();    	   
		}

	}


	public void RunRead(java.io.Serializable msg)
	{
		
		HashSet<ClientRead> readersexec; 
		try {
			readlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();	
			return;
		}

		try {

			readersexec = new HashSet<ClientRead>();
			readersexec.addAll(readers);
		} finally {
			readlock.release();   
		}

		for(ClientRead reader :readersexec) {
			try {
				reader.run(msg);
			} catch(Exception ex) {			    	
				ex.printStackTrace();			    	
			}
		}

		readersexec.clear();

	}


	public void AddClientError(ClientError error)
	{		
		try {
			errorlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		try {
			this.errors.add(error);
		} finally {
			errorlock.release();
		}

	}

	public void RunError(Throwable cause)
	{
		HashSet<ClientError> runs = new HashSet<ClientError>() ; 
		try {
			errorlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		try {
			runs.addAll(errors);

		} finally {

			errorlock.release();
		}

		for(ClientError error :runs) {        	
			error.run(cause);
		}	
		runs.clear();
	}

	public void RunDisconnect()
	{				

		HashSet<ClientDisconnect> runs = new HashSet<ClientDisconnect>() ; 
		try {
			disconnectlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		try {
			runs.addAll(disconnects);
		} finally {
			disconnectlock.release();
		}

		for(ClientDisconnect disconnect :runs) {        	
			disconnect.run();
		}
		runs.clear();
	}


	public void RemoveClientDisconnect(ClientDisconnect disconnect)
	{				
		try {
			disconnectlock.acquire();
		} catch (InterruptedException e) {			
			e.printStackTrace();
			return;
		}

		try {
			this.disconnects.remove(disconnect);
		} finally {
			disconnectlock.release();
		}

	}



	public void RemoveClientConnect(ClientConnect connect)
	{		
		try {
			connectlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		try {  
			this.connects.remove(connect);
		}finally {
			connectlock.release();
		}
	}



	public void RemoveClientRead(ClientRead reader)
	{
		try {
			readlock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		try {  

			this.readers.remove(reader);		
		} finally {
			readlock.release();
		}
	}


	public void RemoveClientError(ClientError error)
	{			
		try {
			errorlock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//	e.printStackTrace();
			return;
		}

		try {  

			this.errors.remove(error);		
		} finally {
			errorlock.release();
		}
	}

	private boolean SSL = false;

	// volatile 은 최적화 하지 않게 한다. 
	// 가끔 release 버젼에서 MultiThread 환경에서 문제가 있어서 해결함 
	private volatile  ChannelFuture f = null;

	public void start(ClientError error)
	{
		new Thread(()->run(error)).start();
	}

	public void stop()
	{
		try {
			f.channel().close();
		} catch (Exception ex) {

		}
	}

	public void write(Serializable obj) 
	{	   
		
		// f 에 변수가 할당되기 전에  write 가 실행 될수 있기 때문에 null 값이 활당되지 않게 sleep 한다.
		while (f==null) {
			try {
				Thread.sleep(1);			   
			} catch(Exception ex) {}			   
		}

		f.channel().writeAndFlush(obj);

	}

	private void run(ClientError error)
	{

		SslContext sslCtx = null;

		if (SSL) {
			try {
				sslCtx = SslContextBuilder.forClient()
						.trustManager(InsecureTrustManagerFactory.INSTANCE).build();
			} catch(Exception ex) {
			}
		} 

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ClientInitializer(sslCtx,host,port,this));

			// 10 초 동안 기다리는 것을 만든다.
			b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000*10);

			// Make a new connection.

			// f 에 변수가 할당되기 전에  write 가 실행 될수 있기 때문에 block 한다,

			f = b.connect(host, port).sync();

			// Get the handler instance to retrieve the answer.                       
			f.channel().closeFuture().sync();           
		} catch (Exception ex){        	
			ex.printStackTrace();        
			if (error != null) {        		
				error.run(ex);
			}        	
		}

		finally {
			group.shutdownGracefully();
		}

	}

}