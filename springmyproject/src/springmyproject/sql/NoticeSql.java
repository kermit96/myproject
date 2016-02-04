package springmyproject.sql;

public class NoticeSql {
 
	  public static final int INSERTNOTICE = 1;
	  public static final int GETTOTAL = 2;
	  public static final int GETNOTICELIST = 3;
	
	  public static String getSQL(int code) {
		  StringBuffer buff = new StringBuffer();
		  switch(code) {
		  case INSERTNOTICE:
			  buff.append("insert ");
			  buff.append("into ");
			  buff.append("  notice");
			  buff.append(" values");
			  buff.append(" ( (select nvl(max(n_no),0) +1 from notice),?,?,?,?,SYSDATE ,'Y')");
			  buff.append("");
			  buff.append("");
			  						 			  
			  break;
			  
		  case GETTOTAL:
			  buff.append(" select ");
			  buff.append(" count(*) as TOT  ");
			  buff.append(" from notice ");
			  buff.append(" where n_isShow= 'Y' ");
			  break;
			  
			  
		  case GETNOTICELIST:
			  buff.append("SELECT ");
			  		
			  buff.append(" RNO, "); 
			  buff.append("n_NO AS NO, ");	
			  buff.append("n_Kind AS KIND, ");
			  buff.append(" n_Title AS TITLE, ");
			  buff.append(" 	n_Writer AS WRITER, ");
			  buff.append(" n_Body AS BODY, ");
			  buff.append(" n_Date AS WDATE ");
			  buff.append("  FROM "); 
			  buff.append("  (SELECT rownum AS RNO, t1.* "); 
			  buff.append("  FROM ");
			  buff.append("  (SELECT * FROM Notice WHERE n_isShow='Y' ORDER BY n_Kind ASC, n_Date DESC) t1)  ");
			  buff.append("   WHERE	 RNO BETWEEN ? AND ? "); 

			  break;
		  }
		  
		  return buff.toString();
	  }
	
}
