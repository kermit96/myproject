package Dao.room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import Dao.Constant;
import Dao.MyJDBCDao;
import Dao.house.ResponseHouse;
import Dao.house.TB_HOUSE;
import main.Global;

public class RoomSql {

	
	public RoomSql() {
		// TODO Auto-generated constructor stub
	}

	
	// 방 리스트 출력
	
	
	public ResponseRoom selectRoomList(TB_ROOM data) {
		
		ResponseRoom res = new ResponseRoom();
		res.message = RoomConstant.DEF_LIST;
		
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("select r.idx, r.name, h.host_name, r.reg_date, r.wifi_select, r.aircon_select, ");
			buffer.append(" meal_select, vod_select, floor_select, spa_select");
			buffer.append("	FROM TB_ROOM r , TB_HOUSE h	");
			buffer.append(" where r.house_idx = h.idx" );
			buffer.append("	order by idx	");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			ResultSet result = pstat.executeQuery();
			
			res.data = new Vector<TB_ROOM>();
			
			while (result.next()) {
				
				TB_ROOM test = new TB_ROOM();

				test.idx = result.getInt(1);
				test.name = result.getString(2);
				test.host_name = result.getString(3);
				test.reg_date = result.getDate(4);
				test.wifi_select = result.getString(5);
				test.aircon_select = result.getString(6);
				test.meal_select = result.getString(7);
				test.vod_select = result.getString(8);
				test.floor_select = result.getString(9);
				test.spa_select = result.getString(10);
				
				res.data.add(test);
				
			}
			
			res.result = true;
			
			
		} catch (Exception e) {
			res.result = false;
			e.printStackTrace();
			res.reason = e.toString();
		} finally {
		
			if (dao != null) {
				dao.closeAll();
			}
		}
		return res;
		
	}
	

	// 방 등록 
	
	public ResponseRoom insertRoom(TB_ROOM data) {
		
		
		ResponseRoom res = new ResponseRoom();
		res.message = RoomConstant.DEF_INSERT;
		
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			
			buffer.append("	insert into TB_ROOM(idx, house_idx, room_no, name, wifi_select, aircon_select, meal_select, vod_select, floor_select, spa_select, file_name , price )"	);
			buffer.append(" values (	(SELECT NVL(MAX(idx), 0) + 1 FROM TB_ROOM), ?,?,?,?,?,?,?,?,?,?,?)" );
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			

			String SaveDir = Global.getInstance().getSaveDir()+"\\room\\";
			String filename = data.file_name;
			if (data.file_name.isEmpty() == false) {				
				filename =  util.Util.savetmpfile(data.buff,SaveDir );
			} 
			
			
			pstat.setInt(1, data.house_idx);
			pstat.setInt(2, data.room_no);
			pstat.setString(3, data.name);
			pstat.setString(4, data.wifi_select);
			pstat.setString(5, data.aircon_select);
			pstat.setString(6, data.meal_select);
			pstat.setString(7, data.vod_select);
			pstat.setString(8, data.floor_select);
			pstat.setString(9, data.spa_select);
			pstat.setString(10, filename);
			pstat.setInt(11, data.price);
			
			pstat.execute();
			res.result = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
		} finally {
			
			if (dao != null) {
				dao.closeAll();
			}
		}
		return res;
	}
	
	
	public ResponseRoom selectRoom(TB_ROOM data) {
		
		ResponseRoom res = new ResponseRoom();
		Vector<TB_ROOM> temp = new Vector<TB_ROOM>();
		MyJDBCDao dao = null;
		res.message = RoomConstant.DEF_IDX_LIST;
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			
			buffer.append(" select idx, house_idx, room_no, name, wifi_select, aircon_select, meal_select, vod_select, floor_select, spa_select, file_name ,price ");  
			buffer.append(" from TB_ROOM ");
			buffer.append(" where idx = ? ");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			pstat.setInt(1, data.idx);
			
			ResultSet result = pstat.executeQuery();
			
			while (result.next()) {
				
				TB_ROOM send_data = new TB_ROOM();
				
				send_data.idx = result.getInt(1);
				send_data.house_idx = result.getInt(2);
				send_data.room_no = result.getInt(3);
				send_data.name = result.getString(4);
				send_data.wifi_select = result.getString(5);
				send_data.aircon_select = result.getString(6);
				send_data.meal_select = result.getString(7);
				send_data.vod_select = result.getString(8);
				send_data.floor_select = result.getString(9);
				send_data.spa_select = result.getString(10);
				send_data.file_name = result.getString(11);
				send_data.price = result.getInt(12);
				
				String SaveDir = Global.getInstance().getSaveDir()+"\\room\\";
				
				send_data.buff = util.Util.loaddata(send_data.file_name, SaveDir);
				
				
				temp.add(send_data);
				res.result = true;
			}
			
			res.data = temp;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
			
		} finally {
			
			if (dao != null) {
				dao.closeAll();
			}
		}
		
		return res;
	}
	
	
	// 방 수정
	
	public ResponseRoom updateRoom(TB_ROOM data) {
		
		ResponseRoom res = new ResponseRoom();
		Vector<TB_ROOM> temp = new Vector<TB_ROOM>();
		
		res.message = RoomConstant.DEF_UPDATE;
		
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			
			buffer.append("UPDATE TB_ROOM SET house_idx =?, room_no =? , price =?, name =? , wifi_select = ?, aircon_select = ? , meal_select = ?, vod_select = ?, floor_select = ?, spa_select =? , file_name = ? where idx = ?  ");
		
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			
			String SaveDir = Global.getInstance().getSaveDir()+"\\room\\";

			String filename = data.file_name;

			if (data.file_name.isEmpty() == false) {				
				filename =  util.Util.savetmpfile(data.buff,SaveDir );
			} 
			
			
			pstat.setInt(1,data.house_idx);
			pstat.setInt(2, data.room_no);
			pstat.setInt(3, data.price);
			pstat.setString(4, data.name);
			pstat.setString(5, data.wifi_select);
			pstat.setString(6, data.aircon_select);
			pstat.setString(7, data.meal_select);
			pstat.setString(8, data.vod_select);
			pstat.setString(9, data.floor_select);
			pstat.setString(10, data.spa_select);
			pstat.setString(11, filename);
			pstat.setInt(12, data.idx);
			
			pstat.execute();
			
			res.result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
			
		} finally {
			
			if (dao != null) {
				dao.closeAll();
			}
		}
		
		return res;
	}
	
	// 방 삭제
	
	public ResponseRoom deleteRoom(TB_ROOM data) {
		
		ResponseRoom res = new ResponseRoom();
		Vector<TB_ROOM> temp = new Vector<TB_ROOM>();
		
		res.message = RoomConstant.DEF_DELETE;
		
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			buffer.append("delete FROM TB_ROOM where idx = ?");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setInt(1, data.idx);
			pstat.execute();
			res.result = true;
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
		} finally {
			
			if (dao != null) {
				dao.closeAll();
			}
		}
		
		return res;
	}
}
