package zipCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ZipCodeWrite {
  public ZipCodeWrite() {
	    
	  
	  File file = new File("D:\\MyStudy\\jsp_test\\addr");
	  
	  String[]  files = file.list();
	
	  Connection con  = null;
	  PreparedStatement pstmt = null;
	  
	    
	  try {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
               String sql = "insert into zipcode values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         pstmt =  con.prepareStatement(sql);
		  
	  } catch (Exception ex) {
		  
		   ex.printStackTrace();
	  }
	
	   
	  
	  for(String filename : files) {
		  
		  System.out.println(filename);
		    try (
		    		BufferedReader  br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\MyStudy\\jsp_test\\addr\\"+filename),"euc-kr")); 		
		    		
	      ) {
		     	while(true)  {
		     		String line = br.readLine();
		     		if (line == null || line.isEmpty()) 
		     			break;
		     	
		     		String[] datas = line.split("\\|");
		     		
		 
		     		
		     		for (int i=0;i<24;i++) {
                		 if (i == 11 || i==12 || i==21 || i==23) {
                			 int temp = Integer.parseInt(datas[i]);
                			 pstmt.setInt(i+1, temp); 
                			 
                		 }  else {
                			 
                			 pstmt.setString(i+1, datas[i]);
                		 }

		     		}
		     		
		//     		System.out.println(datas[0]+" =="+ datas[1]);
		     		pstmt.execute();
		     		
		     	}
		    	
		    } catch (Exception ex) {
		 	   ex.printStackTrace();
		    	
		    }
		      
	  }
	  
  }
	
  
  public static void main(String[] agrs) {
	  new ZipCodeWrite();
	  
  }
  
}
