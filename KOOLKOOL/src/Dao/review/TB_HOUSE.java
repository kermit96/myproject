package Dao.review;

import java.io.Serializable;
import java.util.Date;

public class TB_HOUSE  implements Serializable {
	
	public int      idx;
	public String   name = "";
	public String   host_name ="";
	public String   host_tel = "";
	public String   host_email ="";
	public String   home_intro = "";	
	public String   file_name = "";	
	public Date		reg_date = null;		
	
	public TB_HOUSE() {
		// TODO Auto-generated constructor stub
	}

}
