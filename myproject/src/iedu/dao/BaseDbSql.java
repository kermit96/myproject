package iedu.dao;

import iedu.config.ConfigFileHandler;

public class BaseDbSql {

	protected DBTYPE dbtype = DBTYPE.ORACLE_TYPE;

	public BaseDbSql()
	{

     int k=0;
	}

 
	protected BaseJDBCDao GetjdbcDao() throws Exception
	{

		ConfigFileHandler handler = ConfigFileHandler.getConfigFileHandler();

		String host = handler.getValue("host");
		int port = 0;
		try {
			Integer.parseInt( handler.getValue("port"));

		} catch (Exception ex ) {}
		String dbname =  handler.getValue("dbname");
		String userid =  handler.getValue("userid");
		String password =  handler.getValue("password");
		int  getdbtype = Integer.parseInt( handler.getValue("dbtype"));


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
