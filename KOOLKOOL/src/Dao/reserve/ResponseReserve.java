package Dao.reserve;

import java.io.Serializable;
import java.util.Vector;

import Dao.TB_USER;

public class ResponseReserve implements Serializable {

	public short message; // �޽��� ���� ,
	public boolean result; // ��û ����, ����
	public String reason;

	public Vector<TB_ReserverAll> data; // list �� ���� �´�.

	public ResponseReserve() {
		// TODO Auto-generated constructor stub
	}

}
