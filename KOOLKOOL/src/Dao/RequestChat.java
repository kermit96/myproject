package Dao;

import java.io.Serializable;

public class RequestChat implements Serializable {

	public int message;  // 메신지 종류 
	                         //  DEF_REQUEST_CHAT_MESSAGE  
	                        //  DEF_CHATING_MESSAGE 
	                        //  DEF_CHAT_LEAVE 
    public String fromId;  // 요청한 id 
    public String fromName;  // 요청한 이름
    public boolean fromIsAdmin;  //  요청한 사람의 권한  true 이면  
	public String msg ;
	public String toId;
	
	public RequestChat() {
		// TODO Auto-generated constructor stub
	}

}
