package iedu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import iedu.data.memberdata;
import iedu.sql.BaseDbSql;
import iedu.sql.BaseJDBCDao;

public class MemberDao extends BaseDbSql {

	
	public int  MaxMemberSeq()
	{
	
         BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	    	
	        pstat = dao.getPrepare("SELECT NEXT VALUE FOR memberseq ");	        		
            
	        
	        
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

	
	
	public String  DeleteMember(int seq) 
	{
	    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("update    member  set isDelete='Y'  where usernum = ? ");
	        
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
      	        pstat = dao.getPrepare("select * from member where userid=? and isdelete='N' ");
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
      	    	  dao.closeAll();
      	    	
      	    }
      	         	   
      	   return "";
      		    	       	   
       }
       
       
       public boolean  ChangePassWord(String userid,String oldpassword,String newpassword)
       {
    	
    	   BaseJDBCDao dao = null;
     	    
    	    PreparedStatement pstat; 
    	    try {
    	    	dao = GetjdbcDao();
    	        pstat = dao.getPrepare("update member set password=?  where userid=?  and password=? ");
    	    	pstat.setString(1, newpassword);
    	    	pstat.setNString(2, userid);
    	    	pstat.setString(3, oldpassword);
    	    	
    	        int updatenum = pstat.executeUpdate();
    	        
    	        if (updatenum == 0   )
    	        	return false;
    	        else 
    	        	return true;
    	    	    	    	       	    	    	    		       
    	    } catch (Exception ex) {
    	    	ex.printStackTrace();
    	    	return false;

    	    	
    	    } finally {
    	    	if (dao!=null)
    	    	  dao.closeAll();
    	    	
    	    }
    	         	   
    	   
       }
       
       public boolean IsExistMember(String userid)
       {

    	   BaseJDBCDao dao = null;
      	    
     	    PreparedStatement pstat; 
     	    try {
     	    	dao = GetjdbcDao();
     	        pstat = dao.getPrepare("select count(*) from member where userid=? ");
     	    	pstat.setNString(1, userid);
     	    	
     	    	ResultSet result = pstat.executeQuery();
     	    	
     	    	while(result.next()) {
     	    		 int count = result.getInt(1);
     	    		 if (count == 0) return false;
     	    		 return true;
     	    	}
     	    	      	          	    	
     	    		       
     	    } catch (Exception ex) {
     	    	ex.printStackTrace();
     	    	return false;

     	    	
     	    } finally {
     	    	if (dao!=null)
     	    	  dao.closeAll();
     	    	
     	    }
     	         	   

     	    return false;
    	   
    	   
       }
       
       public String  InsertMember(memberdata data)
       {
    	   
   	    BaseJDBCDao dao = null;
	    
   	    PreparedStatement pstat; 
   	    try {
   	    	dao = GetjdbcDao();
   	        pstat = dao.getPrepare("insert into member(userid,password,name,tel,nickname,usernum) values(?,?,?,?,?,?)");
   	    	pstat.setString(2, data.password);
   	    	pstat.setNString(3, data.name);
   	    	pstat.setString(4, data.tel);
   	    	pstat.setNString(5, data.nickname);
   	    	pstat.setNString(1, data.userid);
   	    	pstat.setInt(6, MaxMemberSeq());
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
	  
	  
}
