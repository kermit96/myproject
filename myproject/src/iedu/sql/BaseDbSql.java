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

		if (getdbtype ==0) 
		{
			dbtype=DBTYPE.ORACLE_TYPE;			
			if (port ==0) 
				port = 1521;
		}

		if (getdbtype ==1) 
		{
			dbtype=DBTYPE.MSSQL_TYPE;
			if (port ==0) 
				port = 1433;


		}

		if (getdbtype ==2) 
		{
			dbtype=DBTYPE.MYSQL_TYPE;
			if (port ==0) 
				port = 3306;

		}


		BaseJDBCDao dao = null;                   

		if (dbtype==DBTYPE.ORACLE_TYPE)
			dao = new OracleJDBCDao(userid,password,host,port,dbname  ) ;			 
		else if(dbtype==DBTYPE.MSSQL_TYPE) 
			dao = new MssqlSqlJDBCDao(userid,password,host,port,dbname );
		else if (dbtype==DBTYPE.MYSQL_TYPE)
			dao = new MySqlJDBCDao(userid,password,host,port,dbname );

		return dao;
	}

}
