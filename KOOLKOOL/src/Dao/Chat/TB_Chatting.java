package Dao.Chat;

import java.io.Serializable;
import java.util.*;

public class TB_Chatting implements Serializable {
	
	public  int   seq    ;
	public  String   toUserid;
	public  String   toUserName;
	
    public  int	  fromuseridx  ;
	public  String   Msg  ;     
	
	public  boolean fromisAdmin;
	
	//  �μ����� ������ 
	public  String  fromuserid;
	public  String  fromusername;
	
	public TB_Chatting() {
		// TODO Auto-generated constructor stub
	}

}
