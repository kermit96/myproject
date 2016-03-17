package iedu.dao;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import iedu.data.BoardComment;
import iedu.data.BoardData;
import iedu.sql.BaseDbSql;
import iedu.sql.BaseJDBCDao;

public class BoardDao   extends BaseDbSql{

	
	public int  MaxBoardCommentSeq()
	{
	
         BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	    	
	        pstat = dao.getPrepare("SELECT NEXT VALUE FOR commentseq ");	        		
            
	        
	        
	    	ResultSet result =pstat.executeQuery();
	    	result.next();
	    	return result.getInt(1);
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return -1;
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.getClass();	    	
	    }
	

	}

	
	
	
	public int  MaxBoardSeq()
	{
	
         BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	    	
	        pstat = dao.getPrepare("SELECT NEXT VALUE FOR boardseq ");	        		
            
	        
	        
	    	ResultSet result =pstat.executeQuery();
	    	result.next();
	    	return result.getInt(1);
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return -1;
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.getClass();	    	
	    }
	

	}
	
    public String InsertBoard(BoardData data )
    {   	
 
    	
    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("insert into board(boardtype,seq,title,writerseq,content, "
	        		+" refseq,step) values(?,?,?,?,?,?,(select isnull(max(step)+1,0) from board where refseq=?)) "	        		
	        		);
	        
	        pstat.setInt(1, data.boardtype);
	        pstat.setInt(2, MaxBoardSeq());
	        pstat.setNString(3,data.title);
	        pstat.setInt(4,data.writerseq);
	        
	        Connection con = dao.getcon();
	        
	        NClob lob = con.createNClob();
	        
	        lob.setString(1, data.content);
	        
	        pstat.setNClob(5, lob);;
	        
	        pstat.setInt(6,data.ref_seq);
	        
	        pstat.setInt(7,data.ref_seq);
	        
	    	pstat.execute();
	    	
	    		       
	    } catch (Exception ex) {
	    	
	    	ex.printStackTrace();
	    	return ex.toString();
	    	
	    } finally {
	    	
	    	if (dao!=null)
	    	  dao.closeAll();
	    	
	    }
	   	   
	   return "";
    	  
    	 
    }

    
    public void IncreseHit(int seq)
    {
    	

    	BaseJDBCDao dao = null;
    	
        BoardData data = new BoardData();
        data.seq = seq;
     	 /*
   	  * 	[boardtype] [tinyint] NULL,
   [seq] [int] IDENTITY(1,1) NOT NULL,
   [title] [nvarchar](100) NULL,
   [writerseq] [int] NULL,
   [content] [nvarchar](max) NULL,
   [createdate] [datetime] NULL,
   [updatedate] [datetime] NULL,
   [isdelete] [char](1) NOT NULL,
   [refseq] [int] NULL,
   [step] [int] NULL,
   [boardorder] [int] NULL,
   [hit] [int] NULL,

   	  */
       
       
   	    PreparedStatement pstat; 
   	    try {
   	    	dao = GetjdbcDao();
   	        pstat = dao.getPrepare("update board set hit=hit+1 where seq=? ");
   	        
   	        pstat.setInt(1, seq);
   	        
   	        pstat.execute();
   	       	
   	    	return ;
   	    		       
   	    } catch (Exception ex) {
   	    	ex.printStackTrace();
   	    	return ;
   	    	
   	    } finally {
   	    	if (dao!=null)
   	    	  dao.closeAll();	    	
   	    }
   	   	   

    	
    	
    }
    
    public BoardData  DetailBoard( int seq  )
    {
    BaseJDBCDao dao = null;
	
     BoardData data = new BoardData();
     data.seq = seq;
  	 /*
	  * 	[boardtype] [tinyint] NULL,
[seq] [int] IDENTITY(1,1) NOT NULL,
[title] [nvarchar](100) NULL,
[writerseq] [int] NULL,
[content] [nvarchar](max) NULL,
[createdate] [datetime] NULL,
[updatedate] [datetime] NULL,
[isdelete] [char](1) NOT NULL,
[refseq] [int] NULL,
[step] [int] NULL,
[boardorder] [int] NULL,
[hit] [int] NULL,

	  */
    
    
     IncreseHit(seq);
     
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("select board.*,nickname from board , member  where seq = ?  and board.writerseq = member.usernum  and  isdelete='N' ");
	        
	        pstat.setInt(1, seq);
	        
	    	ResultSet result = pstat.executeQuery();
	        while(result.next()) {
	        	 data.boardtype = result.getInt("boardtype");
	        	 data.hit = result.getInt("hit");
	        	 data.content = result.getNClob("content").toString();
	        	 data.title = result.getString("title");
	        	 data.createdate = result.getTimestamp("createdate");
	        	 data.updatedate = result.getTimestamp("updatedate");
	        	 data.writename = result.getString("nickname");
	        	 data.writerseq   = result.getInt("writerseq");
	        }
       
	        pstat = dao.getPrepare("select * from boardcomment a,member b where boardseq=?  and a.writerseq = b.usernum   order by seq desc");
	        	        
	        pstat.setInt(1, seq);
	        
	    	result = pstat.executeQuery();
	    	while(result.next())
	    	{	    		
	    		BoardComment comment = new BoardComment();
	    		comment.Content = result.getNClob("content").toString();
	    		comment.writername = result.getNString("nickname");
	    		comment.Writerseq = result.getInt("writerseq");
	    		comment.Createdate = result.getTimestamp("createdate");
	    		data.replys.addElement(comment);
	    	}
	    		        
	     return   data;
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.closeAll();	    	
	    }
    }
	
    public String DeleteBoard(int seq)
    {	
    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("update    board  set isDelete='Y'  where seq = ? ");
	        
	        pstat.setInt(1, seq);
	        
	    	pstat.execute();
	    	
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return ex.toString();
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.getClass();	    	
	    }	   	   
	   return "";
    }
    
    public String UpdateBoard( BoardData data   )
    {		
    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("update   board set  content=?,Updatedate=getdate(),tittle=?    where seq = ? ");
	        NClob clob = dao.getcon().createNClob();
	        clob.setString(1, data.content);
	        pstat.setNClob(1,clob);
	        pstat.setNString(2, data.title);
	        pstat.setInt(3, data.seq);
	        
	    	pstat.execute();
	    	
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return ex.toString();
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.getClass();	    	
	    }
	   	   
	   return "";
    }
	
    public Vector<BoardData> ListBoard(int boardtype,int pagenum,int pagesize)
    {		
    	    	
       BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("select * from "+  
 "(select b.*, nickname, row_number() over(order by refseq,step desc) as rownumber from board b, member m  where  b.isdelete!='Y'  and  b.writerseq = m.usernum  and boardtype=?)  a "+ 
"  where  rownumber>=? and  rownumber<=?");
	        
	       pstat.setInt(1, boardtype);
	       pstat.setInt(2, pagesize*(pagenum-1)+1);
	       pstat.setInt(3, pagesize*pagenum);
	       
	       ResultSet result = pstat.executeQuery();
	       Vector<BoardData> ret = new Vector<BoardData>(); 
	       while(result.next()){
	    	   BoardData board = new BoardData(); 
	    	    ret.addElement(board); 
	       }
	     return ret; 	
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.closeAll();	    	
	    }
	   	   
	   
    }    
    
    public String InsertCommnet(BoardComment comment  )
    {    	
    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare(" insert into boardcomment(seq,boardseq,content,writerseq) values(?,?,?,? ) ");
	        
	        
	        
	        pstat.setInt(1,  MaxBoardCommentSeq());
	        
	        pstat.setInt(2,  comment.boardseq);
	        
	        NClob clob = dao.getcon().createNClob();
	        clob.setString(1, comment.Content);

	        pstat.setNClob(3,  clob);
	        	        
	        pstat.setInt(4, comment.Writerseq);
	        
	    	pstat.execute();
	    	
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return ex.toString();
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.getClass();	    	
	    }
	   	   
	   return "";
    }
    
    public String UpdateCommnet(BoardComment comment )
    {
    	
    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("update    boardcomment  set content='?' ,updatedate=getdate()  where seq = ? ");
	        
	        NClob clob = dao.getcon().createNClob();
	        clob.setString(1, comment.Content);
	        
	        pstat.setNClob(2, clob);
	        pstat.setInt(2, comment.seq);
	        	        
	    	pstat.execute();
	    	
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return ex.toString();
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.getClass();	    	
	    }
	   	   
	   return "";
    }
    
    public String DeleteCommnet(int seq)
    {
    	
    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("update    boardcomment  set isDelete='Y'  where seq = ? ");	        
	        pstat.setInt(1, seq);	        
	    	pstat.execute();
	    		    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return ex.toString();
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.closeAll();;	    	
	    }
	   	   
	   return "";
    }
    
}
