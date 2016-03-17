package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}
	
	public  static String DateToString(Date date) {		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ret = format.format(date);		
		return ret;		
	}
	
	
	public  static String DateToStringTime(Date date) {		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String ret = format.format(date);		
		return ret;		
	}
	
	
	public static Date StringToDate(String str) {
				
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date= format.parse(str);
		} catch (Exception ex) {
						
		}	
				
		return date;
	}
	
	
	public static byte[]  loaddata(String filename,String savedir) 
	{
		
		String savefilename = savedir+filename;
		byte[] getdata = null;
		
		File getfile  = new File(savefilename);
		int len = (int)getfile.length();		
		try (
				FileInputStream file = new FileInputStream(savefilename);
		) {			
			getdata = new byte[len]; 
			file.read(getdata);
		   return getdata;
		} catch(Exception ex) {
						
		}

		
		return null;
	}
	
	public static  String savetmpfile(byte[] datas,String savedir ) {

		String savefilename = savedir+"";

		String retfilename = "";
		
		
		File theDir = new File(savedir);
		if (theDir.exists() == false)
		{
			try {
			theDir.mkdir();
			} catch (Exception ex) {
		      ex.printStackTrace();							
			}
		}
		
		
		int i=1;
		
		while(true) {
			retfilename = "tmp_"+i+".save";
			savefilename = savedir+retfilename;
			File file = new File(savefilename);
			
			if (file.exists() )
				continue;			
			break;
		}
				
		try (
				FileOutputStream file = new FileOutputStream(savefilename);
		) {			
			file.write(datas);
		   
		} catch(Exception ex) {
						
		}

	  return retfilename;	
	}

	
	
	public static void main(String []argv) {
		
		Date date = StringToDate("2012-2-10");		
		System.out.println(date);
		
		String str = DateToString(date);
		System.out.println(str);
		str = DateToStringTime(new Date());
	//	String str;
		str = DateToString(new Date());
		System.out.println(str);
		
	}

}
