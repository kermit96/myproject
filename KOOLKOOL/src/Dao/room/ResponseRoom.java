package Dao.room;

import java.io.Serializable;
import java.util.*;

public class ResponseRoom implements Serializable  {
	
	public short message;  // �޽��� ���� ,
	public boolean result;  // ��û ����, ���� 
	public String reason;
	
	public Vector<TB_ROOM> data;  // list �� ���� �´�.
	
	
	public ResponseRoom() {
		// TODO Auto-generated constructor stub
	}

}
