package Dao.review;
import java.io.Serializable;
import java.util.Vector;

public class ResponseTbHouse implements Serializable {
	
	public short message;  // �޽��� ���� ,
	
	public boolean result;  // ��û ����, ���� 
	public String reason;
	
	public Vector<TB_HOUSE> data;  // list �� ���� �´�.
			
	public ResponseTbHouse() {
		// TODO Auto-generated constructor stub				
	}
		
}
 
