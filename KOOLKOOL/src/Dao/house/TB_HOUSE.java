package Dao.house;

import java.io.Serializable;
import java.util.*;

public class TB_HOUSE implements Serializable {
	
	public String name ="";
	
	public int idx;
	
	public String host_name ="";
	public String host_tel ="";
	public String host_email="";
	public String home_intro ="";
	public String file_name ="";
	public Date reg_date =null;
	public String address ="";
	
	public byte[] buff;

	public TB_HOUSE() {
		
	}

}
