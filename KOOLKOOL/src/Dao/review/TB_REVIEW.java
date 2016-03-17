package Dao.review;

import java.io.Serializable;
import java.util.Date;

public class TB_REVIEW implements Serializable {
	
	public int      idx;
	public int   	house_idx;
	public String   title = "";
	public String   content ="";
	public String   user_name = "";	
	public int   	star;	
	public Date		reg_date = null;		
	
	public TB_REVIEW() {
		// TODO Auto-generated constructor stub
	}

}
