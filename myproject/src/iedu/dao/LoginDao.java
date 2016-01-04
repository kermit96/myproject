package iedu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import iedu.sql.BaseDbSql;
import iedu.sql.BaseJDBCDao;


public class LoginDao extends BaseDbSql {
    
   
	public LoginDao()
	{		
	}
		
	
	
   
   public HashMap isMember(String id,String pw )
   {
	   
	   HashMap map = new HashMap();
	    BaseJDBCDao dao = null;
	    
	    PreparedStatement pstat; 
	    
	    try {
	    	dao = GetjdbcDao();
	        pstat = dao.getPrepare("select * from member where m_id = ? and m_password=?");
	    	pstat.setString(1, id);
	    	pstat.setString(2, pw);
	    	
	    	ResultSet result = pstat.executeQuery();
	    	while(result.next()) {
	    		map.put("id", result.getString("m_id"));
	    		map.put("name", result.getString("m_Name"));
	    		map.put("nick", result.getString("m_Nick"));	    		
	    	}
	    		       
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	
	    } finally {
	    	if (dao!=null)
	    	  dao.getClass();
	    	
	    }
	    	   
	    return map;
   }
   
}
