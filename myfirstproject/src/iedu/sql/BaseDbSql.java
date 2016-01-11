package iedu.sql;

import iedu.config.ConfigFileHandler;
import iedu.config.Dbconfig;

public class BaseDbSql {

	protected DBTYPE dbtype = DBTYPE.ORACLE_TYPE;

	public BaseDbSql()
	{

		
	}

	
	public DBTYPE getDbtype()
	{		
		return dbtype;
	}
 
	protected BaseJDBCDao GetjdbcDao() throws Exception
	{

		Dbconfig  config = new Dbconfig();
		
		String host = config.getHost();
		int port = 0;
		
		try {
			config.getPort();

		} catch (Exception ex ) {}
		
		String dbname =  config.getDbname();
		String userid =  config.getUserid();
		String password =  config.getPassword();
		int  getdbtype = config.getDbtype();

        dbtype = DBTYPE.fromInt(getdbtype); 

		BaseJDBCDao dao = null;                   

		dao = BaseJDBCDao.GetjdbcDao(dbtype, host, port, dbname, userid, password);
		
		return dao;
	}

}
