package Dao;

import java.io.Serializable;
import java.util.Date;

public class LoginMemberData implements Serializable {

	public String   user_id ="";
	public String   user_pwd = "";
	public String   user_name = "";
	public boolean  isadmin = false;
	
	public String   user_address = "";
	public String   user_tel = "";
	public String   user_email = "";
	public Date    reg_date = null;
	
	public LoginMemberData() {
		// TODO Auto-generated constructor stub				
	}

}
