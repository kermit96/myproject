package Dao.Chat;

import java.io.Serializable;
import java.util.Date;

public class TB_SaveMsg implements Serializable {
	public  int   seq    ;
	public  String   adminuserid;
	public  String   adminusername;
    public  int	  useridx  ;
	public  String   Msg  ;     
	public   Date reqdate;   

	
	//  부수적인 데이터 
	public  String  userid;
	public  String  username;
	
	
	public Date enddate;
	
	public TB_SaveMsg() {
		// TODO Auto-generated constructor stub
	}

}
