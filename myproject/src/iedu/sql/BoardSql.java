package iedu.sql;

import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import iedu.dao.BoardDao;
import iedu.dao.BoardReply;
import iedu.util.PageInfo;

public class BoardSql  extends BaseDbSql {
    

	 public BoardSql( DBTYPE dbtype )
	 {        		 
         this.dbtype= dbtype;		 
	 }
	 
	 public BoardSql()
	 {
		 		  
	 }
	
	 public  BoardDao GetDetailBoard(int no)
	 {
		    BaseJDBCDao dao = null;
			PreparedStatement pstat;			
			BoardDao board = new BoardDao();						
			try {
				
				dao = GetjdbcDao();
				
				String sql = "update Reboard set rb_hit = rb_hit+1 where rb_no=?";
				pstat = dao.getPrepare(sql);
				pstat.setInt(1, no);
				pstat.execute();
				sql = "select * from reboard where rb_isShow='Y' and rb_No=?";		   
				pstat = dao.getPrepare(sql);
				pstat.setInt(1, no);
				ResultSet result = pstat.executeQuery();
				
				while(result.next()) {
									
					board.no = result.getInt("rb_No");
					board.hit = result.getInt("rb_hit");
					board.date = result.getTimestamp("rb_date");				
					NClob  cob=   result.getNClob("rb_Content");
					board.content = cob.toString();
					board.write  = result.getNString("rb_writer");
					board.title = result.getNString("rb_title");								
				}
				
				sql = "select * from reply where rr_isShow='Y' and rb_No=?";
				
				pstat = dao.getPrepare(sql);
				pstat.setInt(1, no);
				result = pstat.executeQuery();

				 while(result.next()) {
					 BoardReply reply = new BoardReply();  
					 
					 reply.rr_No = result.getInt("rr_no");
					 reply.rr_Writer = result.getNString("rr_writer");
					 reply.rr_Content = result.getNString("rr_content");
					 reply.rr_date = result.getTimestamp("rr_date");
					 board.replys.addElement(reply);
				 }
				
				
			} catch (Exception ex) {		   
				ex.printStackTrace();
			} finally {
				if (dao != null) 
					dao.closeAll();		    
			}

			return board;		  
	 }
	 

	 
	 public void updateBoard(int no, String title,String content)
	 {
			BaseJDBCDao dao = null;
			PreparedStatement pstat;			
			try {							
				dao = GetjdbcDao();			
				String sql = "update reboard set rb_title=?,rb_contet=?     where rb_no=? ";
				
				pstat = dao.getPrepare(sql);
				
				pstat.setNString(1, title);
				NClob clob = dao.getcon().createNClob();
				
				clob.setString(1, content);
				pstat.setNClob(2,clob );				
				pstat.setInt(3, no);
				pstat.execute();		   			
			} catch (Exception ex) {		   
				ex.printStackTrace();
			} finally {
				if (dao != null) 
					dao.closeAll();		    
			}
		 
	 }
	 
	public void insertBoard( String Write,String title,String content) {

		BaseJDBCDao dao = null;
		PreparedStatement pstat;
		
		try {			
			
			dao = GetjdbcDao();			
			String sql = "insert into reboard(rb_No,rb_title,rb_Writer,rb_Content,rb_date,rb_hit,rb_isSHow) values( (select nvl(max(rb_No),0)+1  from reboard ), ?,?,?, sysdate,0,'Y')";
			if (dbtype != DBTYPE.ORACLE_TYPE) {				
				sql = "insert into reboard(rb_title,rb_Writer,rb_Content,rb_hit,rb_isSHow) values( ?,?,?, 0,'Y')";
			}
			pstat = dao.getPrepare(sql);
			pstat.setNString(1, title);
			pstat.setNString(2, Write);
			NClob clob = dao.getcon().createNClob();
			
			clob.setString(1, content);
			pstat.setNClob(3,clob );
			pstat.execute();		   			
		} catch (Exception ex) {		   
			ex.printStackTrace();
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}

	}
	
	
	public void SaveReply(String userid,String Content,int boardno) 
	{		
		
		BaseJDBCDao dao = null;
		PreparedStatement pstat;	
		try {			
						
			dao = GetjdbcDao();			
			String sql = "insert into reply(rr_no, rb_no,rr_Writer,rr_Content,rr_date,rr_isShow) values( (select nvl(max(rb_No),0)+1  from reply ), ?,?,?)";
			if (dbtype != DBTYPE.ORACLE_TYPE) {				
				sql = "insert into reply(rb_no,rr_Writer,rr_Content) values( ?,?,?)";
			}
			pstat = dao.getPrepare(sql);
			pstat.setInt(1, boardno);
			pstat.setNString(2, userid);
			NClob clob = dao.getcon().createNClob();			
			clob.setString(1, Content);
			pstat.setNClob(3,clob );
			pstat.execute();		   			
			
		} catch (Exception ex) {		   
			ex.printStackTrace();
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}
		
		
	}

	
	public boolean UpdateReply(int no,String Content )
	{
		BaseJDBCDao dao = null;

		
		try {										
			dao = GetjdbcDao();
			String sql = "update  reply set rr_content = ? where rr_no=? ";
			PreparedStatement prepare = dao.getPrepare(sql);
			prepare.setNString(1, Content);
			prepare.setInt(2, no);			
			prepare.execute();
			
		   			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			return false;
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}
		
		return true;
		
	}
	
	public boolean DeleteReply(int no)
	{
		BaseJDBCDao dao = null;

		
		try {										
			dao = GetjdbcDao();
			String sql = "update  reply set rb_isShow = 'N' where rr_no=? ";
			PreparedStatement prepare = dao.getPrepare(sql);
			prepare.setInt(1, no);			
			prepare.execute();
		   			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			return false;
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}
		
		return true;
	}

	
	
	public boolean DeleteBoard(int no)
	{
		BaseJDBCDao dao = null;
		PreparedStatement pstat;
		
		try {										
			dao = GetjdbcDao();
			String sql = "update  reboard set rb_isShow = 'N' where rb_no=? ";
			PreparedStatement prepare = dao.getPrepare(sql);
			prepare.setInt(1, no);			
			prepare.execute();
		   			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			return false;
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}
		
		return true;
	}
	
	public ArrayList<BoardDao>  ListBoard( int nowpage) {

		BaseJDBCDao dao = null;
		PreparedStatement pstat;
		
		
		
		
		ArrayList<BoardDao> array = new ArrayList<BoardDao>();
		
		try {
		
			PageInfo pInfo = null;
		// 	pInfo = new PageInfo();
								
			dao = GetjdbcDao();
			
			String sql="";
			
			sql = "select count(*) as CNT from reBoard where rbn_isShow='Y'";
			
			pstat = dao.getPrepare(sql);
			ResultSet result = pstat.executeQuery();
			result.next();
			int totalCount = result.getInt("CNT");
			
			pInfo = new PageInfo(nowpage,totalCount,10,5 );
			
			sql = "select * from  (select * ,row_number() over(order by rb_NO desc) as rank   from reboard where rb_isShow='Y' ))  ";		   
			pstat = dao.getPrepare(sql);
			 result = pstat.executeQuery();
			
			while(result.next()) {
				
				BoardDao board = new BoardDao();
				board.no = result.getInt("rb_No");
				board.hit = result.getInt("rb_hit");
				board.date = result.getTimestamp("rb_date");				
				NClob  cob=   result.getNClob("rb_Content");
				board.content = cob.toString();
				board.write  = result.getNString("rb_writer");
				board.title = result.getNString("rb_title");				
				array.add(board);
			}
			
		} catch (Exception ex) {		   
			ex.printStackTrace();
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}

		return array;
	}

	
}
