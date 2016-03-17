package Dao.Chat;

import java.io.Serializable;
import java.util.*;

public class ResponseSaveChatMsg  implements Serializable {

	public short message;
	public boolean result;
	public String reason;
	
	public  Vector<TB_SaveMsg>  data;
	
	public ResponseSaveChatMsg() {
		// TODO Auto-generated constructor stub
	}

}
