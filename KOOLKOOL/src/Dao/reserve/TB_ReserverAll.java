package Dao.reserve;

import java.io.Serializable;

public class TB_ReserverAll implements Serializable {

	public TB_RESERVE reserve;
	public TB_HOUSE house;
	public TB_ROOM room;
	
	public TB_ReserverAll() {
		// TODO Auto-generated constructor stub
		reserve = new TB_RESERVE();
		house = new TB_HOUSE();
		room = new TB_ROOM();
		
	}

}
