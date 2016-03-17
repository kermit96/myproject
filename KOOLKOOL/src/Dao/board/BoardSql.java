package Dao.board;

import Dao.Constant;
import Dao.MyJDBCDao;
import io.netty.handler.codec.http.HttpContentEncoder.Result;

import java.sql.*;
import java.util.Vector;
public class BoardSql {
	
	public BoardSql() {
		
	}
	public ResultSet GetSql(String sql) throws Exception{
		
		MyJDBCDao dao = new MyJDBCDao();
		return dao.GetResultStat(sql);
	}

	
	// 전체 리스트를 가져온다.
	public ResponseBoard ShowBoard(TB_BOARD data){
		
		Dao.board.ResponseBoard res = new Dao.board.ResponseBoard();
		
		res.message = BoardConstant.DEF_SHOW;
				
		MyJDBCDao dao = null;
		try{
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("SELECT idx, title, content,admin_id, TO_CHAR(reg_date,'YYYY-MM-DD'),hit FROM TB_BOARD ORDER BY idx");
			
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
	
			
			ResultSet result = pstat.executeQuery();
			
			res.data = new Vector<TB_BOARD>(); 
			
			while(result.next()) {
				TB_BOARD tbdata= new TB_BOARD();
				tbdata.idx = result.getInt(1);
				tbdata.title = result.getString(2);
				tbdata.content = result.getString(3);
				tbdata.admin_id = result.getString(4);
				tbdata.reg_date = result.getString(5);
				tbdata.hit = result.getInt(6);

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
	
	
	public ResponseBoard AddBoard(TB_BOARD data){
		
		Dao.board.ResponseBoard res = new Dao.board.ResponseBoard();
		
		res.message = BoardConstant.DEF_INSERT;
		
		MyJDBCDao dao = null;
		try{
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("INSERT INTO TB_BOARD VALUES((SELECT NVL(MAX(idx), 0) + 1 FROM TB_BOARD),'admin',?,?,1,SYSDATE)");
			
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setString(1, data.title);
			pstat.setString(2, data.content);
			
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
	
	
	public ResponseBoard ModifyBoard(TB_BOARD data){
		
		Dao.board.ResponseBoard res = new Dao.board.ResponseBoard();
		
		res.message = BoardConstant.DEF_UPDATE;
		
		
		MyJDBCDao dao = null;
		try{
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
					
			buffer.append("UPDATE TB_BOARD SET title = ?, content = ? WHERE idx = ?");
						
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
						
			pstat.setString(1, data.title);
			pstat.setString(2, data.content);
			pstat.setInt(3, data.idx);
			
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
	
	
	// 선택한 열을 가져온다.
	public ResponseBoard SelectBoard(TB_BOARD data){
		
		Dao.board.ResponseBoard res = new Dao.board.ResponseBoard();
		
		res.message = BoardConstant.DEF_LIST;
		
		Vector<TB_BOARD> temp = new Vector<TB_BOARD>(); 
		
		MyJDBCDao dao = null;
		try{
			dao = new MyJDBCDao();
			
			
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("SELECT idx, title, content, admin_id, TO_CHAR(reg_date,'YYYY-MM-DD'), hit FROM TB_BOARD WHERE idx = ?");
			
			
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setInt(1,data.idx);
		
			
			
			ResultSet result = pstat.executeQuery();
			
			
			while(result.next()) {
				TB_BOARD tbdata= new TB_BOARD();
				
				tbdata.idx = result.getInt(1);
				tbdata.title = result.getString(2);
				tbdata.content = result.getString(3);
				tbdata.admin_id = result.getString(4);
				tbdata.reg_date = result.getString(5);
				tbdata.hit = result.getInt(6);
				
				
				
				temp.add(tbdata);
				res.result = true;
			}
			res.data = temp;
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
	
	
	
public ResponseBoard deleteBoard(TB_BOARD data){
		
		Dao.board.ResponseBoard res = new Dao.board.ResponseBoard();
		
		res.message = BoardConstant.DEF_DELETE;
		
		MyJDBCDao dao = null;
		try{
			dao = new MyJDBCDao();
			
			StringBuilder buffer = new StringBuilder(2048);
			
			buffer.append("DELETE FROM TB_BOARD WHERE idx = ?");
			
			
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

public ResponseBoard hitBoard(TB_BOARD data){
	
	Dao.board.ResponseBoard res = new Dao.board.ResponseBoard();
	
	res.message = BoardConstant.DEF_HIT;
	
	MyJDBCDao dao = null;
	
	try{
		dao = new MyJDBCDao();
		
		StringBuilder buffer = new StringBuilder(2048);
		
		buffer.append("UPDATE TB_BOARD SET hit = hit + 1 WHERE idx = ?");
		
		
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
