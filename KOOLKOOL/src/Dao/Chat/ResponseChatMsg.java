package Dao.Chat;

import java.io.Serializable;

public class ResponseChatMsg implements Serializable {
	
	public int message;  // �޽��� ���� 
    //  DEF_REQUEST_CHAT_MESSAGE  
   //  DEF_CHATING_MESSAGE 
   //  DEF_CHAT_LEAVE 
	public boolean result;
	
	public String   reason;
	
    public TB_Chatting  data;


	public ResponseChatMsg() {
		// TODO Auto-generated constructor stub
	}
	
}
