package Dao.house;

import java.io.Serializable;
import java.util.Vector;

import Dao.reserve.*;

public class ResponseHouse implements Serializable {

	
	public short message;  // �޽��� ���� ,
	public boolean result;  // ��û ����, ���� 
	public String reason;
	
	public Vector<TB_HOUSE> data;  // list �� ���� �´�.
			
	public ResponseHouse() {
		// TODO Auto-generated constructor stub
	}

}
