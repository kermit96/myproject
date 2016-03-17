package Dao.review;
import java.io.Serializable;
import java.util.Vector;

public class ResponseReview implements Serializable {
	
	public short message;  // 메시지 종류 ,
	public boolean result;  // 요청 성공, 실패 
	public String reason;
	
	public Vector<TB_REVIEW> data;  // list 를 가져 온다.
			
	public ResponseReview() {
		// TODO Auto-generated constructor stub				
	}
		
}
 
