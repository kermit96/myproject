package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.swing.*;

import com.netty.client.ClientBase;
import com.netty.client.ClientRead;

import Dao.Constant;
import Dao.Chat.RequestChatMsg;
import Dao.Chat.ResponseChatMsg;
import main.Chat.Chatting;
import main.board.MainBorder;
import main.board.PMainMenu;

public class KOOLKOOLMain {
	
	Login loginF;

	Global g_global = Global.getInstance();
	
	ClientRead reader ;
 
	public KOOLKOOLMain() {
				
		//  접속  ip 와  port 설정하기 
		
		InitHostAndPort();
		
		if (g_global.serverConnect == null)
		{			
		  		  g_global.serverConnect = new ClientBase(gethost(),getport()); 					
		}
		
		JFrame frame=null;
		loginF = new Login(frame);
	
		if (loginF.Select ==Login.DEF_NO_LOGIN ) 
			return;
	    
		if (g_global.isAdmin == true) {			
			new MainBorder();
		} else {			
			new PMainMenu();
		}		
		
		
		g_global.serverConnect.AddClientError(x->GeneralError(x));
		g_global.serverConnect.AddClientRead(x->ChatRead(x));

		
	}

	
	public void ChatRead(Object obj) 
	{

		if (obj instanceof ResponseChatMsg )
		{
			
			ResponseChatMsg msg = (ResponseChatMsg)obj;

			if (msg.message == Constant.DEF_RQUEST_FIRST_CHAT_MESSAGE)
			{
				Chatting chat = new Chatting();
	
				chat.Set( msg.data.fromuserid, msg.data.fromusername,msg.data.fromuseridx);
				chat.SetChatMessage(msg);
				chat.setVisible(true);
			}
							
		}
	}
	
	public void GeneralError(Throwable ex)
	{		
    	JOptionPane.showMessageDialog(null, ex.toString());
	}
	
	public static void main(String[] args) {		
		new KOOLKOOLMain();	
	}
	
	public void  InitHostAndPort()
	{
       if (getport() == 0 || gethost().isEmpty()) {
    	   JFrame frame = null;
			Setting set = new  Setting(frame,"환경 설정");			
			set.setPort(getport());
			set.setServer(gethost()); 
			
			set.setVisible(true);
			int port = set.getPort();
			
		   String Host = set.getServer();
		   
		   g_global.setPort(port);
		   g_global.setServer(Host);			
       }
	}
	
	public int getport()
	{
		return g_global.getPort();		
	}
		
	public String gethost()
	{		
		return g_global.getServer();
	}
		
}
