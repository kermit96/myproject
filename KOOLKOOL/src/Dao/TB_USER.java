package Dao;

import java.io.Serializable;
import java.util.Date;

public class TB_USER implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String   user_id ="";
	public String   user_pwd = "";
	public String   user_name = "";

	public int       idx;
	
	public String   user_address = "";
	public String   user_tel = "";
	public String   user_email = "";
	public Date    reg_date = null;	
	
	
	public TB_USER() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
	//	return super.toString();
		
		return user_name;
	}
		
}
