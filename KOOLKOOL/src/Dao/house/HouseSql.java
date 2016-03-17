package Dao.house;

import java.sql.PreparedStatement;
import java.util.Vector;

import Dao.Constant;
import Dao.MyJDBCDao;

import main.Global;
import java.sql.*;

public class HouseSql {

	public HouseSql() {
		// TODO Auto-generated constructor stub
	}
	
	// room에서 사용할 houseList 
	// room  
	public ResponseHouse selectHouseIdxList(TB_HOUSE data) {
		
		ResponseHouse res = new ResponseHouse();
		res.message = HouseConstant.DEF_ROOMAS_LIST;
		
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			
			buffer.append("select idx, name ");
			buffer.append(" from TB_HOUSE ");
			buffer.append("	order by idx	");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			ResultSet result = pstat.executeQuery();
			
			res.data = new Vector<TB_HOUSE>();
			
			while (result.next()) {
				
				TB_HOUSE test = new TB_HOUSE();
				
				test.idx = result.getInt(1);
				test.name = result.getString(2);
			
				//System.out.println(test.name);
				
				res.data.add(test);
				
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

	
	// room에서 사용할 houseList 
	// room  
	public ResponseHouse selectHouseIdxListreview(TB_HOUSE data) {
		
		ResponseHouse res = new ResponseHouse();
		res.message = HouseConstant.DEF_REVIEWAS_LIST;
		
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			
			buffer.append("select idx, name ");
			buffer.append(" from TB_HOUSE ");
			buffer.append("	order by idx	");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			ResultSet result = pstat.executeQuery();
			
			res.data = new Vector<TB_HOUSE>();
			
			while (result.next()) {
				
				TB_HOUSE test = new TB_HOUSE();
				
				test.idx = result.getInt(1);
				test.name = result.getString(2);
			
				//System.out.println(test.name);
				
				res.data.add(test);
				
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

	
	
	// 숙박시설 리스트 출력

	public ResponseHouse selectHouseList(TB_HOUSE data) {
		
		ResponseHouse res = new ResponseHouse();
		res.message = HouseConstant.DEF_LIST;
		
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("select idx, name, host_name, host_email, reg_date, address	");
			buffer.append("	FROM TB_HOUSE	");
			buffer.append("	order by idx	");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			ResultSet result = pstat.executeQuery();
			
			res.data = new Vector<TB_HOUSE>();
			
			
			while (result.next()) {
				
				TB_HOUSE test = new TB_HOUSE();
				
				test.idx = result.getInt(1);
				test.name = result.getString(2);
				test.host_name = result.getString(3);
				test.host_email = result.getString(4);
				test.reg_date = result.getDate(5);
				test.address = result.getString(6);
	
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
	
	
	
	// 숙박시설 등록하는 쿼리
	
	public ResponseHouse insertHouse(TB_HOUSE data) {
		
		ResponseHouse res = new ResponseHouse();
		res.message = HouseConstant.DEF_INSERT;
		MyJDBCDao dao = null;
	
		try {
			
			StringBuilder buffer = new StringBuilder(1024);
			
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
			dao = new MyJDBCDao();
			
			buffer.append("insert into TB_HOUSE(idx,name, host_name, host_email, home_intro, file_name,address,host_tel)" );
			buffer.append("values ( (SELECT NVL(MAX(idx), 0) + 1 FROM TB_HOUSE),?,?,?,?,?,?,?)");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());			
			
			String SaveDir = Global.getInstance().getSaveDir()+"\\house\\";

			String filename = data.file_name;
			if (data.file_name.isEmpty() == false) {
				filename =  util.Util.savetmpfile(data.buff,SaveDir );
			} 

			System.out.println(data.file_name);
			pstat.setString(1, data.name);
			pstat.setString(2, data.host_name);
			pstat.setString(3, data.host_email);
			pstat.setString(4, data.home_intro);
			pstat.setString(5, filename);
			pstat.setString(6, data.address);
			pstat.setString(7, data.host_tel);
			
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
	
	
	// 숙박시설 확인 
	// idx 값을 가지고  가져온다. 
	public ResponseHouse selectHouse(TB_HOUSE data) {
		
		
		ResponseHouse res = new ResponseHouse();
		Vector<TB_HOUSE> temp = new Vector<TB_HOUSE>();
		
		res.message = HouseConstant.DEF_IDX_LIST;
		MyJDBCDao dao = null;
		
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			
			buffer.append("select idx, name, host_name, host_email,host_tel, file_name, address, home_intro	");
			buffer.append("	FROM TB_HOUSE	");
			buffer.append("	where idx = ?	");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setInt(1, data.idx);
			
			ResultSet result = pstat.executeQuery();
			
			while (result.next()) {
				
				TB_HOUSE send_data = new TB_HOUSE();
				
				send_data.idx = result.getInt(1);
				send_data.name = result.getString(2);
				send_data.host_name = result.getString(3);
				send_data.host_email = result.getString(4);
				send_data.host_tel = result.getString(5);
				send_data.file_name = result.getString(6);
				send_data.address = result.getString(7);
				send_data.home_intro = result.getString(8);
				
				String SaveDir = Global.getInstance().getSaveDir()+"\\house\\";
				send_data.buff = util.Util.loaddata(send_data.file_name, SaveDir);
				
				temp.add(send_data);
				System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbb: " + send_data.file_name);
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaa:" + send_data.buff);
				res.result = true;
			}
			
			res.data = temp;
		} catch (Exception e) {
			res.result = false;
			e.printStackTrace();
		} finally {
			
			if (dao != null) {
				dao.closeAll();
			}
		}
		return res;
	}
	
	// 숙박시설 수정
	
	public ResponseHouse updateHouse(TB_HOUSE data) {
		
		ResponseHouse res = new ResponseHouse();
		Vector<TB_HOUSE> temp = new Vector<TB_HOUSE>();
		
		res.message = HouseConstant.DEF_UPDATE;
		
		MyJDBCDao dao = null;
				
		try {
			
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			
			
			buffer.append("UPDATE TB_HOUSE SET name = ? , host_name = ?, host_email = ?, host_tel =? , file_name = ?, address = ? , home_intro = ? where idx = ?");
		
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
		

			String SaveDir = Global.getInstance().getSaveDir()+"\\house\\";

			String filename = data.file_name;

			if (data.file_name.isEmpty() == false) {				
				filename =  util.Util.savetmpfile(data.buff,SaveDir );
			} 
			
			
			pstat.setString(1, data.name);
			pstat.setString(2, data.host_name);
			pstat.setString(3, data.host_email);
			pstat.setString(4, data.host_tel);
			pstat.setString(5, filename);
			pstat.setString(6, data.address);
			pstat.setString(7, data.home_intro);
			pstat.setInt(8, data.idx);
			
			pstat.execute();
			
			res.result = true;
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
			
		}  finally {
			
			if (dao != null) {
				dao.closeAll();
			}
		}
		
		return res;
	}
	
	// 숙박시설 삭제 
	
	
	public ResponseHouse deleteHouse(TB_HOUSE data) {
		
		ResponseHouse res = new ResponseHouse();
		Vector<TB_HOUSE> temp = new Vector<TB_HOUSE>();
		
		res.message = HouseConstant.DEF_DELETE;
		
		MyJDBCDao dao = null;
		
		
		try {

			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(1024);
			buffer.append("delete FROM TB_HOUSE where idx = ?");
			
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
