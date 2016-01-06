package iedu.config;

import iedu.sql.DBTYPE;

public class Dbconfig {
    private String dbname;
    private int    dbtype;
    
    private String  userid;
    private String  password;
    private String  host;
    private int    port;
    
    
    ConfigFileHandler handler;
    /**
	 * @return the dbname
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * @param dbname the dbname to set
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
		handler.setValue("dbname",dbname );
	}

	/**
	 * @return the dbtype
	 */
	public int getDbtype() {
		return dbtype;
	}

	/**
	 * @param dbtype the dbtype to set
	 */
	public void setDbtype(int dbtype) {
		this.dbtype = dbtype;
		handler.setValue("dbtype",dbtype );
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
		handler.setValue("userid",userid );
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
		handler.setValue("password",password );
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
		handler.setValue("host",host );
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
		
		handler.setValue("port",port );
	}


	public void Save()
	{
		handler.Save();
		
	}
	
    public Dbconfig()
    {
    	
    	handler = ConfigFileHandler.getConfigFileHandler();
    	
    	host = handler.getValue("host");
		port = 0;
		
		try {
		  port = 	Integer.parseInt( handler.getValue("port"));

		} catch (Exception ex ) {}
		
		dbname =  handler.getValue("dbname");
		userid =  handler.getValue("userid");
		 password =  handler.getValue("password");
		 
		dbtype = Integer.parseInt( handler.getValue("dbtype"));

	    	
    }
    
}
