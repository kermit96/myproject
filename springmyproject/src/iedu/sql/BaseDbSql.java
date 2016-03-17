package iedu.sql;

import iedu.config.ConfigFileHandler;
import iedu.config.Dbconfig;

public class BaseDbSql {

	
	BaseJDBCDao dao;
	
	public BaseDbSql() 
	{
		
		try {
			dao = BaseJDBCDao.GetjdbcDao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public DBTYPE getDbtype()
	{		
	
		
		return dao.dbtype;
	
	}
 
	
	
	public  BaseJDBCDao GetjdbcDao() throws Exception
	{	
		
		return dao;
	}

}
