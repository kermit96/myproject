package Dao.reserve;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import Dao.Constant;
import Dao.MyJDBCDao;

public class RoomSql {

	public RoomSql() {
		// TODO Auto-generated constructor stub
	}
	// 예약된 데이터 불러오기
	public ResponseRoom reserveShow(TB_ReserverAll data) {
		MyJDBCDao dao = null;
		ResponseRoom res = new ResponseRoom();
		
		res.message = ReserveConstant.DEF_IDX_LIST;
		try {
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("SELECT r.idx, h.name, h.host_name, r.room_no, r.wifi_select, r.aircon_select, r.meal_select, r.vod_select,r.floor_select, r.spa_select,r.price, r.file_name FROM TB_ROOM r, TB_USER u, TB_HOUSE h, TB_RESERVE r2 WHERE r2.user_idx = u.idx  and r2.house_idx = h.idx  and r2.room_idx = r.idx and r2.reserve_state= '?' and r2.user_idx = ?");
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			pstat.setString(1, data.reserve.reserve_state);
			pstat.setInt(2, data.reserve.idx);

			ResultSet result = pstat.executeQuery();
			
			res.data = new Vector<TB_ReserverAll>();
			
			while (result.next()) {
				TB_ReserverAll temp = new TB_ReserverAll();
				temp.room.idx = result.getInt(1);
				temp.house.name = result.getString(2);				
				temp.house.host_name = result.getString(3);
				temp.room.room_no = result.getInt(4);
				temp.room.wifi_select = result.getString(5);
				temp.room.aircon_select = result.getString(6);
				temp.room.meal_select = result.getString(7);
				temp.room.vod_select = result.getString(8);
				temp.room.floor_select = result.getString(9);
				temp.room.spa_select = result.getString(10);
				temp.room.price = result.getInt(11);
				temp.room.file_name = result.getString(12);
				res.data.add(temp);
			}
			res.result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
		} finally {
			if(dao != null) {
				dao.closeAll();
			}
		}
		return res;
	}
	// 예약 데이터 수정하기
	public ResponseRoom ModifyReserve(TB_ReserverAll data) {
		
		Dao.reserve.ResponseReserve res = new Dao.reserve.ResponseReserve();
		res.message = Constant.DEF_UPDATE;
		
		MyJDBCDao dao = null;
		try {
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("UPDATE TB_RESERVE SET start_date = ?, end_date = ? WHERE idx = ?");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			DateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sFormat.format(data.reserve.start_date);
			String eDate = sFormat.format(data.reserve.end_date);
			
			pstat.setString(1, sDate);
			pstat.setString(2, eDate);
			
			pstat.execute();
			
			res.result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
		}
		finally {
			if(dao != null) {
				dao.closeAll();
			}		
		}
		return null;
	}
}
	
