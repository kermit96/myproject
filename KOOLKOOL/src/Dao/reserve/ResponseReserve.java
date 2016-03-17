package Dao.reserve;

import java.io.Serializable;
import java.util.Vector;

import Dao.TB_USER;

public class ResponseReserve implements Serializable {

	public short message; // 메시지 종류 ,
	public boolean result; // 요청 성공, 실패
	public String reason;

	public Vector<TB_ReserverAll> data; // list 를 가져 온다.

	public ResponseReserve() {
		// TODO Auto-generated constructor stub
	}

}
