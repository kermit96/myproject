package iedu.sql;

public class FileBoardSQL {
	
	public   static final  int GETTOTAL =1;
	
	
  public static String GetSql(int num)
  {
	      StringBuffer buff = new StringBuffer();
	
	      switch(num){
	      case GETTOTAL:
	    	  buff.append("select  count(*)  as total  from fileboard");
	  
	      }
	      return buff.toString();
  }
  	
}
