package Dao.reserve;

import java.io.Serializable;
import java.util.Vector;

public class ResponseRoom implements Serializable{
	public short message; // �޽��� ���� ,
	public boolean result; // ��û ����, ����
	public String reason;

	public Vector<TB_ReserverAll> data; // list �� ���� �´�.
	public ResponseRoom() {
		// TODO Auto-generated constructor stub
	}

}
