package iedu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class util {
	
	   public static  String  GetSha512(String str) {

			return GetSha(str,"SHA-512");			   
	   }
	   
	   
	   public static String GetSha(String str,String shatype ) 
	   {
		   String SHA = ""; 

			try{

//				MessageDigest sh = MessageDigest.getInstance("SHA-512"); 
				MessageDigest sh = MessageDigest.getInstance(shatype);
				
				sh.update(str.getBytes()); 

				byte byteData[] = sh.digest();

				StringBuffer sb = new StringBuffer(); 

				for(int i = 0 ; i < byteData.length ; i++){

					sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));

				}
				SHA = sb.toString();				

			}catch(NoSuchAlgorithmException e){

				e.printStackTrace(); 

				SHA = null; 

			}

			return SHA;

		   
		   
	   }
	
   public static  String  GetSha256(String str) {
			
			return GetSha(str,"SHA-256");
							   
   }
}
