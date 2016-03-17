package Server;
import java.awt.*;
import java.util.*;

import javax.swing.*;

import main.Global;

import java.io.*;
public class Server extends util.MyJFrame  {

	private ServerRun serverrun=null;

	private JMenuItem start;
	private JMenuItem stop;
	JTextArea area;
	Global g_global = Global.getInstance();
	
	public void Log(String msg)
	{		
		area.append(msg+"\r\n");	
	}
	
	public Server() {
  
		// �޴� ������ �Ѵ�.
		JMenuBar menubar = new JMenuBar();
		JMenu   menu  = new JMenu("����");
		 start = new JMenuItem("����");

		// ����  �޴��� ������ ����Ǵ� �Լ��� ���� 
		start.addActionListener(x->start() );
		stop = new JMenuItem("����");
		
		// ����  �޴��� ������ ����Ǵ� �Լ��� ����
		stop.addActionListener(x->stop() );
		JMenuItem setting = new JMenuItem("����");

		// ����   �޴��� ������ ����Ǵ� �Լ��� ����

		setting.addActionListener(x->setting() );
		menu.add(start);
		menu.add(stop);
		menu.addSeparator();
		menu.add(setting);

		this.setLayout(new BorderLayout());

		area = new JTextArea(40,20);

		JScrollPane pan  = new JScrollPane(area);

		this.add(pan);

		menubar.add(menu);
		this.setJMenuBar(menubar);

		
		setSize(400,400);
		

		start.setEnabled(true);
		stop.setEnabled(false);
		
		if (getport() !=0 && getsaveDir().trim().isEmpty()== false) 
			start();
		else 
			setting(); 
		// TODO Auto-generated constructor stub
	}

	private String getsaveDir() {
		// TODO Auto-generated method stub
		String dir =	g_global.getSaveDir();				
		return dir;
	}

	public void start()
	{						
		serverrun = new ServerRun(getport(),this);
		
		Log("Starting  port==>"+getport());
		
		serverrun.start();
		
		start.setEnabled(false);
		stop.setEnabled(true);
		
	}

	public void stop()
	{
		serverrun.stop();
		
		Log("Stop  Server");
		
		start.setEnabled(true);
		stop.setEnabled(false);
		
	}
	
	public int getport()
	{		
		return	g_global.getPort();				
	}
		
	public void setting()
	{
		Setting set = new  Setting(this,"���� ����");
		set.setPort(getport());
		set.setSaveDir(getsaveDir());
		set.setVisible(true);
	
		int port = set.getPort();
	    String savedir = set.getSaveDir();
	    g_global.setPort(port);
	    g_global.setSaveDir(savedir);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Server().setVisible(true);;
	}

}