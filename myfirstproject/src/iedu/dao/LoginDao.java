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
	        pstat = dao.getPrepare("select * from member where userid = ? and password=? and isdelete='N'");
	    	pstat.setString(1, id);
	    	pstat.setString(2, pw);
	    	
	    	ResultSet result = pstat.executeQuery();
	    	while(result.next()) {
	    		map.put("id", result.getString("userid"));
	    		map.put("name", result.getString("name"));
	    		map.put("nick", result.getString("nickname"));
	    		map.put("seq", result.getInt("usernum"));
	    	}
	    	
	    	/*
	    	 * 
	    	 * 	[usernum] [int] IDENTITY(1,1) NOT NULL,
	[userid] [nvarchar](50) NOT NULL,
	[password] [varchar](20) NULL,
	[name] [nvarchar](50) NULL,
	[tel] [varchar](40) NULL,
	[isDelete] [char](1) NULL,
	[CreateDate] [datetime] NULL,
	[UpdateDate] [datetime] NULL,
	    	 */
	    	
	    	
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	
	    } finally {
   	    	if (dao!=null)
   	    		dao.closeAll();   	    
	    	
	    }
	    	   
	    return map;
   }
   
}
