package main;

import java.util.Properties;

import util.*;


// sinegle 톤 설정 
public class Global {

	public ClientBase serverConnect = null;
	
	private Global() {
		// TODO Auto-generated constructor stub		
		  property = new PropertiFile("reserver.property");		 		 
	}
	
	// 기본적인 setting 
	PropertiFile property;
	// server ip
	

	
	
	public String getServer() {
		return property.GetHost();
	}

	public void setServer(String server) {
		property.SetHost(server);		
	}

	public int getPort() {
		return property.GetPort();
	}

	public void setPort(int port) {
		property.SetPort(port);
		
	}


	private String userid;
	
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdx() {
		return idx;
	}

	
	public int getIdk() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	private String username = "";

	
	// tb_user idx 값 
	private int idx = 0;
	
	//  일반 user 인지 , 관리자인지 구분 
	public boolean isAdmin  = false;
	
	public boolean GetIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public    static    Global g_global = null;

	public static  Global getInstance()
	{		
		if (g_global == null) {
		   synchronized (Global.class) {			
			   if (g_global == null) { 				   
				   g_global =  new Global();
			   }			   
		   }				
		}			 
		return g_global;	
	}

	 private String password;
	
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}
	
	public String getPssword()
	{
		return password;
	}		
	
	public String getSaveDir() {
		return property.getSaveDir();
	}

	public void setSaveDir(String saveDir) {
	//	this.saveDir = saveDir;
		property.setSaveDir(saveDir);
	}
		
}
