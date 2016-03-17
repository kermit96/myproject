package Dao;


import Dao.Constant;
import Dao.MyJDBCDao;
import Dao.board.ResponseBoard;
import Dao.board.TB_BOARD;
import io.netty.handler.codec.http.HttpContentEncoder.Result;

import java.sql.*;
import java.util.Vector;
public class RegisterSql {
	
	public RegisterSql() {
		
	}
	public ResultSet GetSql(String sql) throws Exception{
		
		MyJDBCDao dao = new MyJDBCDao();
		return dao.GetResultStat(sql);
	}
	
	public ResponseRegister ShowRegister(TB_REGISTER data){
		
		ResponseRegister res = new ResponseRegister();
		
		res.message = Constant.DEF_LIST;
				
		MyJDBCDao dao = null;
		try{
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("select R.idx,M.name,U.user_id,U.user_name,U.user_email,TO_CHAR(R.reg_date,'YYYY-MM-DD'),R.reserve_state ");
			buffer.append(" from tb_reserve R, tb_user U, tb_house H, tb_room M  ");
			buffer.append(" where R.house_idx = h.idx AND R.user_idx = U.idx AND R.room_idx = M.idx ");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			ResultSet result = pstat.executeQuery();
			
			res.data = new Vector<TB_REGISTER>();
			
		
			
			while(result.next()){
				TB_REGISTER tbdata = new TB_REGISTER();
				tbdata.idx = result.getInt(1);
				tbdata.name = result.getString(2);
				tbdata.user_id = result.getString(3);
				tbdata.user_name = result.getString(4);
				tbdata.user_email = result.getString(5);
				tbdata.reg_date = result.getString(6);
				tbdata.reserve_state = result.getString(7);
				
				res.data.add(tbdata);
			}
			res.result = true;
	
		}catch(Exception e){
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
			
		}
		finally{
			if(dao != null)
				dao.closeAll();
		}
		return res;
		
	}

	public ResponseRegister ModifyRegister(TB_REGISTER data){
		
		
		ResponseRegister res = new ResponseRegister();
		
		res.message = Constant.DEF_UPDATE;
				
		MyJDBCDao dao = null;
		try{
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
					
			buffer.append("UPDATE TB_RESERVE SET reserve_state = 'Y' WHERE idx = ?");
			
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setInt(1, data.idx);
			pstat.execute();
			res.result = true;
			
		}catch(Exception e){
			e.printStackTrace();
			res.result = false;
			res.reason = e.toString();
		}
		finally{
			if(dao != null)
				dao.closeAll();
		}
		return res;
	}
	
}