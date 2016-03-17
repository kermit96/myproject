package Dao.reserve;

import java.io.Serializable;
import java.util.Date;

public class TB_RESERVE implements Serializable {

	public int idx;
	public int user_idx;
	public int house_idx;
	public int room_idx;
	public Date start_date = null;
	public Date end_date = null;
	public String reserve_state;
	public Date reg_date = null;

	public TB_RESERVE() {
		// TODO Auto-generated constructor stub
	}

}