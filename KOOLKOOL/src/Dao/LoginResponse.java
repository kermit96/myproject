package Dao;
import java.io.Serializable;


public class LoginResponse  implements Serializable   {
	

	public   TB_USER data;
	
	public boolean isAdmin ;
	
	public boolean   result ;
	//  실패 이유 
	public String reason = "";
	
	public LoginResponse() {
		// TODO Auto-generated constructor stub
	 }
				
}

