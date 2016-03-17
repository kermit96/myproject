package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


// property 파일 관리 
public class PropertiFile {

	
	private String host;
	private int  port = 0;
	
	private String SaveDir ="" ;
	
	
	
	public String getSaveDir() {
		return SaveDir;
	}

	public void setSaveDir(String saveDir) {
		SaveDir = saveDir;
		System.out.println(saveDir);
	    props.put("savedir", SaveDir);
	    Save();
		
	}

	Properties props = new Properties();
	String filename = "";
	public PropertiFile(String filename ) {
		// TODO Auto-generated constructor stub
			
		this.filename =filename; 
		InputStream is = null;
		try {
			is = new FileInputStream(filename);

		} catch (Exception ex) {
		}		
		try {
			props.load(is);
		} catch (Exception e) {
			System.err.println("Load failed");
		}
		
		String portstr =	props.getProperty("port", "0");
		port = Integer.parseInt(portstr);
	    host =  props.getProperty("host", "");		
	    
	    SaveDir =  props.getProperty("savedir", "");		
	}
	
	public String  GetHost()
	{		
		return host;
	}
	
	public  int GetPort ()
	{		
		return port;
	}
	
	public void SetHost(String host)
	{
		System.out.println(host);
	    this.host = host;
	    props.put("host", host);
	    Save();
		
	}
	
	public void SetPort(int port)
	{
		this.port = port;
		props.put("port", Integer.toString(port));
	    Save();				
	}
	
	public void Save()
	{
		try {
			props.store(new FileOutputStream(filename), null);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
		
}
