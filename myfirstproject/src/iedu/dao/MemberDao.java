package iedu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import iedu.data.memberdata;
import iedu.sql.BaseDbSql;
import iedu.sql.BaseJDBCDao;

public class MemberDao extends BaseDbSql {

	
	public String  DeleteMember(int seq) 
	{
	    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("update    member  set isDelete='Y'  where seq = ? ");
	        
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
	
       public  String  UpdateMember(memberdata data)  throws Exception
       {
    
    	    BaseJDBCDao dao = null;
    	    
    	    PreparedStatement pstat; 
    	    try {
    	    	dao = GetjdbcDao();
    	        pstat = dao.getPrepare("update member set  password=?,name=?, tel=?,nickname=? ,updatedate=getdate()   where userid = ? ");
    	    	pstat.setString(1, data.password);
    	    	pstat.setNString(2, data.name);
    	    	pstat.setString(3, data.tel);
    	    	pstat.setNString(4, data.nickname);
    	    	pstat.setNString(5, data.userid);
    	    	
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
       
       
       public String GetMmeber(memberdata data) 
       {
    	
    	   BaseJDBCDao dao = null;
   	    
      	    PreparedStatement pstat; 
      	    try {
      	    	dao = GetjdbcDao();
      	        pstat = dao.getPrepare("select * from member where userid=?");
      	    	pstat.setNString(1, data.userid);
      	    	
      	    	ResultSet result = pstat.executeQuery();
      	    	
      	    	while(result.next()) {
      	    		 data.name =  result.getNString("name");
      	    		data.password =  result.getString("password");
      	    		data.seq =  result.getInt("usernum");
      	    		data.nickname =  result.getNString("nickname");
      	    		data.tel =  result.getString("tel");
      	    		
      	    	}
      	    	      	          	    	
      	    		       
      	    } catch (Exception ex) {
      	    	ex.printStackTrace();
      	    	return ex.toString();

      	    	
      	    } finally {
      	    	if (dao!=null)
      	    	  dao.getClass();
      	    	
      	    }
      	   
      	   
      	   return "";
      		
    	   
    	   
       }
       
       
       public String  InsertMember(memberdata data)
       {
    	   
   	    BaseJDBCDao dao = null;
	    
   	    PreparedStatement pstat; 
   	    try {
   	    	dao = GetjdbcDao();
   	        pstat = dao.getPrepare("insert into member(userid,password,name,tel,nickname) values(?,?,?,?,?");
   	    	pstat.setString(2, data.password);
   	    	pstat.setNString(3, data.name);
   	    	pstat.setString(4, data.tel);
   	    	pstat.setNString(5, data.nickname);
   	    	pstat.setNString(1, data.userid);
   	    	
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
	  
	  
}
