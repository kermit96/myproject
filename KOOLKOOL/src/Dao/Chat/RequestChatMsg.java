package Dao.Chat;

import java.io.Serializable;

public class RequestChatMsg implements Serializable {

	public int message;  // �޽��� ���� 
	                         //  DEF_REQUEST_CHAT_MESSAGE  
	                        //  DEF_CHATING_MESSAGE 
	                        //  DEF_CHAT_LEAVE 

	public TB_Chatting  data;
	
	public RequestChatMsg() {
		// TODO Auto-generated constructor stub
	}

}
