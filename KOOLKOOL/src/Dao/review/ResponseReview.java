package Dao.review;
import java.io.Serializable;
import java.util.Vector;

public class ResponseReview implements Serializable {
	
	public short message;  // �޽��� ���� ,
	public boolean result;  // ��û ����, ���� 
	public String reason;
	
	public Vector<TB_REVIEW> data;  // list �� ���� �´�.
			
	public ResponseReview() {
		// TODO Auto-generated constructor stub				
	}
		
}
 
