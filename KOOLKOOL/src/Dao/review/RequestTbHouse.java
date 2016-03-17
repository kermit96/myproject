package Dao.review;
import java.io.Serializable;
import java.util.Vector;

import Dao.review.TB_REVIEW;

public class RequestTbHouse  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public short message;  // 메시지 종류 ,	
	public boolean result;  // 요청 성공, 실패 
	public String reason;	
	public TB_HOUSE data;  // list 를 가져 온다.
	
	public RequestTbHouse() {
		// TODO Auto-generated constructor stub
	}			
	
}
