package Dao.review;
import java.io.Serializable;
import java.util.Vector;

public class ResponseTbHouse implements Serializable {
	
	public short message;  // 메시지 종류 ,
	
	public boolean result;  // 요청 성공, 실패 
	public String reason;
	
	public Vector<TB_HOUSE> data;  // list 를 가져 온다.
			
	public ResponseTbHouse() {
		// TODO Auto-generated constructor stub				
	}
		
}
 
