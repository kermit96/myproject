package Dao.reserve;

import java.io.Serializable;
import java.util.Date;

public class TB_ROOM implements Serializable {

	public int idx;
	public int house_idx;
	public int room_no;
	public String name;
	public String wifi_select;
	public String aircon_select;
	public String meal_select;
	public String vod_select;
	public String floor_select;
	public String spa_select;
	public String reserve_state;
	public String file_name;
	public int price;
	public int max_price;
	public Date reg_date = null;
	public byte[] buff;
	
	

	public TB_ROOM() {
		// TODO Auto-generated constructor stub
	}

}
