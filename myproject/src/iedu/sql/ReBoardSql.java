package iedu.sql;

import java.sql.*;
import java.util.Vector;

import iedu.data.ReBoardData;

public class ReBoardSql extends BaseDbSql {

	     public int MaxNo() 
	     {
	    	 String sql="";
	    	 
	    	 sql="select nvl(max(rb_NO ) ,0)+1 from RepleBoard";
	    	 
	    	 BaseJDBCDao dao= null;
	    	 try {
	    		 dao = this.GetjdbcDao();
	    		 PreparedStatement prepare= dao.getPrepare(sql);
	    		 ResultSet result = prepare.executeQuery();
	    		 result.next();
	    		 return result.getInt(1);
	    		 
	    	 } catch(Exception ex) {
	    			    		 
	    	 }	
	    	 
	    	 return 0;
	    	 
	     }
	     
	     
	     public ReBoardData   DetailBoard(int seq)
	     {
	    	 ReBoardData obj = new ReBoardData(); 

	    	  BaseJDBCDao dao= null;
		    	 
		    	 
		    	 try {
		    		 dao = this.GetjdbcDao();
		    	 
		    		 String sql =" select * from  repleBoard where rb_no=?";
		    		 
		    		 PreparedStatement prepare= dao.getPrepare(sql);
		             prepare.setInt(1,seq)  ;

		    		 
		    		 ResultSet result = prepare.executeQuery();
		    		 while(result.next()){
		    			 
		    			 obj.no = result.getInt("rb_no");
		    			 obj.title = result.getString("rb_title");
		    			 obj.body = result.getString("rb_body");
		    			 obj.date = result.getTimestamp("rb_date");
		    			 obj.Writer = result.getString("rb_writer");
		    			 obj.hit = result.getInt("rb_hit");
		    			 obj.group = result.getInt("rb_group");
		    			 obj.step = result.getInt("rb_step");
		    			 obj.order = result.getInt("rb_order");
		    			 obj.ok = result.getInt("rb_ok");
		    			 obj.bad = result.getInt("rb_bad");
		    		 }
		    		 
		    	 } catch(Exception ex) {
		    		 		    		 
		    	 }
		    	
	    	  	    	  
	    	 return  obj;

	    	 
	     }
	     
	     public Vector<ReBoardData> ListBoard( int pagenum,int pagesize )
	     {
	    	 
	    	 Vector<ReBoardData> vec = new Vector<ReBoardData>(); 
	    	  if (pagenum<=0) {	    		  
	    		   pagenum=1;
	    	  }
	    	
	    	  BaseJDBCDao dao= null;
		    	 
		    	 int maxno = MaxNo();
		    	 try {
		    		 dao = this.GetjdbcDao();
		    	 
		    		 String sql =" select * from   ( select rb_no,rb_title,rb_writer,rb_date,rb_group,rb_step,rb_order,rb_hit,  " 
		    		 + "       row_number() over(order by rb_no desc) as rowcount  from RepleBoard  )  a "+
                    " where rowcount>=? and rowcount<=?";
		    		 
		    		 PreparedStatement prepare= dao.getPrepare(sql);
		             prepare.setInt(1, (pagenum-1 ) *pagesize +1)  ;
		             prepare.setInt(2, (pagenum ) *pagesize )  ;
		    		 
		    		 ResultSet result = prepare.executeQuery();
		    		 while(result.next()){
		    			 ReBoardData obj = new ReBoardData();
		    			 obj.no = result.getInt("rb_no");
		    			 obj.title = result.getString("rb_title");
		    			 obj.body = result.getString("rb_body");
		    			 obj.date = result.getTimestamp("rb_date");
		    			 obj.Writer = result.getString("rb_writer");
		    			 obj.hit = result.getInt("rb_hit");
		    			 obj.group = result.getInt("rb_group");
		    			 obj.step = result.getInt("rb_step");
		    			 obj.order = result.getInt("rb_order");
		    			 vec.addElement(obj);		    			 
		    		 }
		    		 
		    	 } catch(Exception ex) {
		    		 		    		 
		    	 }
		    	
	    	  	    	  
	    	 return  vec;
	     }
	     
	     public void InserBoard(ReBoardData data  )
	     {
	    	   // 질의 명령을 실행하기 위해서 필요한
	    	 BaseJDBCDao dao= null;
	    	 
	    	 int maxno = MaxNo();
	    	 try {
	    		 dao = this.GetjdbcDao();
	    	 
	    		 String sql ="insert into RepleBoard(rb_no,rb_title,rb_writer,rb_body) values(?,?,?,?)";
	    		 
	    		 PreparedStatement prepare= dao.getPrepare(sql);
	    		 
	             Connection con= dao.getcon();
	    		 Clob clob = con.createClob();
	    		 
	    		 clob.setString(1, data.body);
	    		 
	    		 prepare.setInt(1,maxno);
	    		 prepare.setString(2,data.title);
	    		 prepare.setString(3,data.Writer);
	    		 prepare.setClob(4,clob);
	    		 
	    		 prepare.execute();
	    		 
	    		 
	    	 } catch(Exception ex) {
	    		 
	    		 
	    	 }
	    	 
	     }
	
}
