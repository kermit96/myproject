package Dao;

import java.io.Serializable;
import java.util.Vector;


public class ResponseMember implements Serializable {
		
	public short message;  // �޽��� ���� ,	
	public boolean result;  // ��û ����, ���� 
	public String reason;	
	
	public Vector<TB_USER> data;  // list �� ���� �´�.
			
	public ResponseMember() {
		// TODO Auto-generated constructor stub				
	}
		
}


