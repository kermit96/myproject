package Dao.reserve;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import main.Global;
import Dao.Constant;
import Dao.MyJDBCDao;

public class ReserveSql {

	public ReserveSql() {
		// TODO Auto-generated constructor stub
	}

	// 전체 숙박 데이터 찾기
	public ResponseReserve allSearch(TB_ReserverAll data) {
		MyJDBCDao dao = null;
		ResponseReserve res = new ResponseReserve();

		res.message = ReserveConstant.DEF_LIST;
		try {
			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(2048);

			buffer.append("SELECT r.idx,h.name, h.host_name, r.room_no, r.wifi_select, r.aircon_select, r.meal_select, r.vod_select,r.floor_select, r.spa_select, r.price, r.file_name, h.idx FROM TB_ROOM r, TB_HOUSE h where r.house_idx = h.idx ORDER BY r.idx" );
			PreparedStatement pstat = dao.getPrepare(buffer.toString());

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
				temp.room.house_idx = result.getInt(13);
				
				String SaveDir = Global.getInstance().getSaveDir()+"\\room\\";
				temp.room.buff = util.Util.loaddata(temp.room.file_name, SaveDir);
				
		
				
				res.data.add(temp);
			}

			res.result = true;

		} catch (Exception e) {
			res.result = false;
			res.reason = e.toString();
		} finally {

			if (dao != null) {
				dao.closeAll();
			}
		}
		return res;
	}

	// 조건검색하기
	public ResponseReserve conditionSearch(TB_ReserverAll data) {
		MyJDBCDao dao = null;
		ResponseReserve res = new ResponseReserve();

		res.message = ReserveConstant.DEF_LIST_CONDITION;
		
		try {
			dao = new MyJDBCDao();
			StringBuilder buffer = new StringBuilder(2048);

			buffer.append("SELECT r.idx, h.name, h.host_name, r.room_no, r.wifi_select, r.aircon_select, r.meal_select, r.vod_select,r.floor_select, r.spa_select, r.price, r.file_name, h.idx FROM TB_ROOM r, TB_HOUSE h WHERE h.idx = r.house_idx and r.reserve_state='N' AND r.wifi_select = ? AND r.aircon_select = ? AND r.meal_select = ? AND r.vod_select = ? AND r.floor_select = ? AND r.spa_select = ? AND r.price between ? AND ? order by r.idx");
			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setString(1, data.room.wifi_select);
			pstat.setString(2, data.room.aircon_select);
			pstat.setString(3, data.room.meal_select);
			pstat.setString(4, data.room.vod_select);
			pstat.setString(5, data.room.floor_select);
			pstat.setString(6, data.room.spa_select);
			pstat.setInt(7, data.room.price);
			pstat.setInt(8, data.room.max_price);
			ResultSet result = pstat.executeQuery();

			res.data = new Vector<TB_ReserverAll>();

			while (result.next()) {
				System.out.println();
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

	// 예약하기 인설트
	public ResponseReserve reservation(TB_ReserverAll data) {
		Dao.reserve.ResponseReserve res = new Dao.reserve.ResponseReserve();

		res.message = ReserveConstant.DEF_INSERT;

		MyJDBCDao dao = null;
		try {
			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(2048);

			buffer.append("INSERT INTO TB_RESERVE (idx, user_idx, house_idx, room_idx, start_date, end_date) VALUES ((SELECT NVL(MAX(idx), 0) + 1 FROM TB_RESERVE), ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'), TO_DATE(?,'YYYY/MM/DD') )");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			DateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sFormat.format(data.reserve.start_date);
			String eDate = sFormat.format(data.reserve.end_date);

			pstat.setInt(1, data.reserve.user_idx);
			pstat.setInt(2, data.reserve.house_idx);
			pstat.setInt(3, data.reserve.room_idx);
			pstat.setString(4, sDate);
			pstat.setString(5, eDate);

	

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

	// 예약된 데이터 불러오기
	public ResponseReserve reserveShow(TB_ReserverAll data) {
		MyJDBCDao dao = null;
		ResponseReserve res = new ResponseReserve();

		res.message = ReserveConstant.DEF_IDX_LIST;
		try {
			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(2048);

			buffer.append("SELECT r.idx, h.name, h.host_name, r.room_no, r.wifi_select, r.aircon_select, r.meal_select, r.vod_select,r.floor_select, r.spa_select,r.price, r.file_name, r2.start_date, r2.end_date  FROM TB_ROOM r, TB_USER u, TB_HOUSE h, TB_RESERVE r2 WHERE r2.user_idx = u.idx  and r2.house_idx = h.idx  and r2.room_idx = r.idx and r2.user_idx = ?");
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			

			pstat.setInt(1, data.reserve.user_idx);
			

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
				temp.reserve.start_date = result.getDate(13);
				temp.reserve.end_date = result.getDate(14);
				
				String SaveDir = Global.getInstance().getSaveDir()+"\\room\\";
				temp.room.buff = util.Util.loaddata(temp.room.file_name, SaveDir);
				
				
				res.data.add(temp);
			}
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

	// 삭제하기
	public ResponseReserve deleteReserve(TB_ReserverAll data) {
		Dao.reserve.ResponseReserve res = new Dao.reserve.ResponseReserve();
		res.message = Constant.DEF_DELETE;

		MyJDBCDao dao = null;
		try {
			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(2048);
			buffer.append("DELETE FROM TB_RESERVE WHERE idx = ?");
			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setInt(1, data.reserve.idx);
			pstat.execute();
			res.result = true;
			System.out.println(res.result);
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

	// 예약 데이터 수정하기
	public ResponseReserve ModifyReserve(TB_ReserverAll data) {

		Dao.reserve.ResponseReserve res = new Dao.reserve.ResponseReserve();
		res.message = ReserveConstant.DEF_UPDATE;

		MyJDBCDao dao = null;

		try {
			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(2048);

			buffer.append("UPDATE TB_RESERVE SET start_date = TO_DATE(?,'YYYY/MM/DD'), end_date = TO_DATE(?,'YYYY/MM/DD') WHERE idx = ?");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			DateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sFormat.format(data.reserve.start_date);
			String eDate = sFormat.format(data.reserve.end_date);

			pstat.setString(1, sDate);
			pstat.setString(2, eDate);
			pstat.setInt(3, data.reserve.idx);
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
