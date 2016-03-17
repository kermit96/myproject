package Dao.room;

import java.io.Serializable;
import java.util.Date;

import Dao.house.TB_HOUSE;

public class TB_ROOM implements Serializable {

	public int idx;
	public int house_idx;
	public int room_no;
	public String name ="";
	public String wifi_select ="";
	public String aircon_select ="";
	public String meal_select = "";
	public String vod_select ="";
	public String floor_select ="";
	public String spa_select ="";
	public String reserve_state ="";
	public String file_name = "";
	public int price;
	public Date reg_date;
	public byte[] buff;
	
	
	// ¼÷¹Ú½Ã¼³ ÂÊ 
	
	public String host_name = "";
	public String brand_name= "";
	public int host_idx;
	
	
	public TB_ROOM() {
		// TODO Auto-generated constructor stub
		
		TB_HOUSE house = new TB_HOUSE();

	}

}
