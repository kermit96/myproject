package Dao.room;

import java.io.Serializable;
import java.util.*;

public class ResponseRoom implements Serializable  {
	
	public short message;  // 메시지 종류 ,
	public boolean result;  // 요청 성공, 실패 
	public String reason;
	
	public Vector<TB_ROOM> data;  // list 를 가져 온다.
	
	
	public ResponseRoom() {
		// TODO Auto-generated constructor stub
	}

}
