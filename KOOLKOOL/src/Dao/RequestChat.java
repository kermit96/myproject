package Dao;

import java.io.Serializable;

public class RequestChat implements Serializable {

	public int message;  // �޽��� ���� 
	                         //  DEF_REQUEST_CHAT_MESSAGE  
	                        //  DEF_CHATING_MESSAGE 
	                        //  DEF_CHAT_LEAVE 
    public String fromId;  // ��û�� id 
    public String fromName;  // ��û�� �̸�
    public boolean fromIsAdmin;  //  ��û�� ����� ����  true �̸�  
	public String msg ;
	public String toId;
	
	public RequestChat() {
		// TODO Auto-generated constructor stub
	}

}
