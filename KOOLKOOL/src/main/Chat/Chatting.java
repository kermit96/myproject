package main.Chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import Dao.*;
import Dao.Chat.*;
import util.*;
import main.*;


public class Chatting extends JFrame {


	private  int ChattingUserIdx = 0;

	Global g_global = Global.getInstance();
	
	ClientRead reader;
	
	
    
	
	private void Write(Serializable obj) 
	{
		g_global.serverConnect.write(obj);
		
	}
	
	private String ChattingUserid ="";
	
	private String ChattingUserName = "";
	
	public void Set(String ChattingUserid,String ChattingUserName,int ChattingUserIdx) {
		
		this.ChattingUserid= ChattingUserid;
		this.ChattingUserName= ChattingUserName;
		this.ChattingUserIdx = ChattingUserIdx;
		label2.setText(ChattingUserName);
	}
	
	private JLabel label2;
	
	private JTextArea input;
	
	JTextArea area;
	
	private void Process(ResponseChatMsg chatmsg) {
		// TODO Auto-generated method stub
		

		
		if (chatmsg.message == Constant.DEF_CHAT_LEAVE)
    	{
    		this.ChatLeave();
    		return;
    	}
		
	
		this.SetChatMessage(chatmsg);
	}
	
	public Chatting()
	{
	   super();
		this.setLayout(new BorderLayout());
	
		setTitle("  상담중 ");
		JPanel p1 = new JPanel(new BorderLayout());
		JLabel label = new JLabel("  상담원과 상담중 ",JLabel.CENTER);

				
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		label2 = new JLabel("이름 ",JLabel.CENTER);
		
		p2.add(label2);
		
		JButton exit = new JButton("나가기");
		
		
		exit.addActionListener(x->ChatLeave()  );
		
		p1.add(p2,"West");
		
		p1.add(exit,"East");
		
		area = new JTextArea (15,30);
		
		JScrollPane sp1 = new JScrollPane(area);
		
		area.setEditable(false);
		
		this.add(p1,"North");
		
		this.add(sp1,"Center");
		
		
		input = new JTextArea (4,30);
		
		JScrollPane sp2 = new JScrollPane(input);
		
		input.addKeyListener( new KeyAdapter() {
		
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub				
				if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown())
				{
					Send();
				}				
			}

		} );
		
		reader = new ClientRead( ) {
			
			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub
				if (obj instanceof ResponseChatMsg ) {
					ResponseChatMsg chatmsg = (ResponseChatMsg)obj ;
				   Process(chatmsg);		
				}
				
				
			}


		};
		
		
		g_global.serverConnect.AddClientRead(reader);
		
		JPanel pannel  =  new JPanel(new BorderLayout());
		
		JButton sendbutton = new JButton("전송");
		
		sendbutton.addActionListener(x->Send());				
		
		pannel.add("West",sp2);
		pannel.add("East",sendbutton);
		
		this.add(pannel,"South");
		pack();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
	//			dispose();
				ChatLeave();
				
			}
		});
		
	}
	
	
	private void ChatLeave() {
		// TODO Auto-generated method stub
		
		Close();
	}
	
	
	private void Save()
	{
		
		// 채팅방이 없어지면
	   // 상담원 일떄는 자동 저장   	  

		if (g_global.isAdmin == false || area.getText().isEmpty()  ) { 			
			return;
		}
		
        RequestSaveChatMsg msg = new RequestSaveChatMsg();
        msg.message= Constant.DEF_INSERT; 
        msg.data = new TB_SaveMsg();
        msg.data.adminusername = g_global.getUsername(); 
        msg.data.adminuserid = g_global.getUserid();
        msg.data.Msg = area.getText();
        msg.data.userid = this.ChattingUserid; 
        msg.data.username = this.ChattingUserName;
        msg.data.useridx = this.ChattingUserIdx;
        Write(msg);
	}
	
	boolean isClosing = false;
	
	private void Close()
	{
	
		if (isClosing== true)
			return;
		isClosing = true;
		Save();
		
		RequestChatMsg msg = new RequestChatMsg();
		msg.message = Constant.DEF_CHAT_LEAVE;
		msg.data = new TB_Chatting();
		msg.data.fromisAdmin = g_global.isAdmin;
		msg.data.fromuserid = g_global.getUserid();
		msg.data.fromusername = g_global.getUsername();
		msg.data.fromuseridx = g_global.getIdx();
		msg.data.toUserid =  this.ChattingUserid;
		msg.data.toUserName =  this.ChattingUserName;

		Write(msg);
		
		g_global.serverConnect.RemoveClientRead(reader);
		
		this.setVisible(false);
		this.dispose();
	}
			
	// 처음 메시지를 보낼지 check 
	boolean isFirst = true;
	
	
	public void SetChatMessage(ResponseChatMsg msg)
	{
	    isFirst = false;			
		
		String str = "["+msg.data.fromusername+"]"+"  "+ msg.data.Msg+"\r\n";
		
		area.append(str);;
	}
	
	public void SetChatMessage(RequestChatMsg msg)
	{
	    isFirst = false;			
		
		String str = "["+msg.data.fromusername+"]"+"  "+ msg.data.Msg+"\r\n";
		
		area.append(str);;
	}
	
	
	public void Send()
	{
		
		if (this.input.getText().trim().isEmpty())
			return;
		
		RequestChatMsg msg = new RequestChatMsg();
		msg.data = new TB_Chatting();
		
		if (isFirst == true ) {
			isFirst = false;
    		msg.message=  Constant.DEF_RQUEST_FIRST_CHAT_MESSAGE;
		}
		else 
			msg.message=  Constant.DEF_CHATTING_MESSAGE;
		
		msg.data.fromuserid =  g_global.getUserid();
		msg.data.fromusername = g_global.getUsername();
		msg.data.fromisAdmin =  g_global.isAdmin;
		msg.data.fromuseridx = g_global.getIdx();
		
		msg.data.toUserid = this.ChattingUserid;
		msg.data.toUserName = this.ChattingUserName;
		msg.data.Msg = this.input.getText();
		
		
		String str = "["+g_global.getUsername()+"]"+"  "+ msg.data.Msg+"\r\n";
		area.append(str);;
		
		Write(msg);
		
		
		input.setText("");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	//	new Chatting().setVisible(true);;
	}
	
}
