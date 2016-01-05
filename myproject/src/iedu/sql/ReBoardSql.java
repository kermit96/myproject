package iedu.sql;

import java.sql.*;

import iedu.data.ReBoardData;

public class ReBoardSql extends BaseDbSql {

	     public int MaxNo() 
	     {
	    	 String sql="";
	    	 
	    	 sql="select max() from ";
	    	 
	    	 BaseJDBCDao dao= null;
	    	 try {
	    		 dao = this.GetjdbcDao();
	    		 PreparedStatement prepare= dao.getPrepare(sql);
	    		 ResultSet result = prepare.executeQuery();
	    		 result.next();
	    		 return result.getInt(0);
	    		 
	    	 } catch(Exception ex) {
	    		
	    		 
	    	 }
	    	 
	    	 return 0;
	    	 
	    	 	    	 
	     }
	     
	     public void InserBoard(ReBoardData data  )
	     {
	    	   // 질의 명령을 실행하기 위해서 필요한
	    	 BaseJDBCDao dao= null;
	    	 
	    	 int maxno = MaxNo();
	    	 try {
	    		 dao = this.GetjdbcDao();
	    	 
	    		 String sql ="";
	    		 
	    		 PreparedStatement prepare= dao.getPrepare(sql);
	             
	    		 prepare.setInt(1,maxno);
	    		 prepare.setString(2,data.title);
	    		 prepare.setString(3,data.Writer);
	    		 prepare.setInt(4,maxno);
	    		 prepare.setInt(1,maxno);
	    		 
	    		 prepare.execute();
	    		 
	    	 } catch(Exception ex) {
	    		 
	    		 
	    	 }
	    	 
	     }
	
}
