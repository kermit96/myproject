package Dao.review;
import java.io.Serializable;
import java.util.Vector;

import Dao.review.TB_REVIEW;

public class RequestTbHouse  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public short message;  // �޽��� ���� ,	
	public boolean result;  // ��û ����, ���� 
	public String reason;	
	public TB_HOUSE data;  // list �� ���� �´�.
	
	public RequestTbHouse() {
		// TODO Auto-generated constructor stub
	}			
	
}
