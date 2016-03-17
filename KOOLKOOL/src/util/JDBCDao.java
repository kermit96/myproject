package util;
/* ï¿½ì”  ï¿½ê²¢ï¿½ì˜’ï¿½ë’ªï¿½ë’— JDBC ï¿½ì˜‰ï¿½ë¾½ï¿½ì“£ ?†«ï¿½ï¿½?œ‘ ï¿½ë ªï¿½ë¸¯å¯ƒï¿½ ï¿½ë¹ äºŒì‡¨ë¦°ï¿½?ï¿½ë¸³ ï¿½ì?ï¿½ë–¥?”±?‹ë–š ï¿½ê²¢ï¿½ì˜’ï¿½ë’ª?‘œï¿? ï§ëš®ë±? ï¿½ì‚ï¿½ì ™ï¿½ì” ï¿½ë–.
 * 
 */
import java.sql.*;
public class JDBCDao {
	//ï¿½êµ¹ï¿½ë’— ï¿½ëŠ»?´ê³Œï¿½åª›ï¿½ ï¿½ì”  ï¿½ê²¢ï¿½ì˜’ï¿½ë’ª?‘œï¿? new ï¿½ë–†ï¿½ê¶—ï¿½ë¸£ ï¿½ì˜„ï¿½ë£ï¿½ìŸ»ï¿½ì‘æ¿¡ï¿½ Driver?‘œï¿? æ¿¡ì’•ëµ«ï¿½ë¸??¨ï¿½ ï¿½ë–¢ï¿½ë–.
	public JDBCDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e){
			System.out.println("ï¿½ë±¶ï¿½ì”ªï¿½ì” è¸°ï¿½ æ¿¡ì’•ëµ? ï¿½ë¿‰ï¿½ìœ­");
		}		
	}
	//ï¿½êµ¹ï¿½ë’— ï¿½ì‚¤ï¿½ì”ªï¿½ê²¢ï¿½ë¿‰ ï¿½ì ’ï¿½ëƒ½ï¿½ì“£ ï¿½ìŠ‚?´?‹ë¸¯ï§ï¿½ ï¿½ï¿½ï¿½ë–Š ï¿½ì ’ï¿½ëƒ½ï¿½ì“£ ï¿½ë¹äºŒì‡°?’— ï¿½ë¸¿ï¿½ë‹”?‘œï¿? ï§ëš®ë±¾æ?¨ï¿½ ï¿½ë–¢ï¿½ë–.
	public Connection getCON(){
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"scott","tiger");
		}
		catch(Exception e){
			System.out.println("ï¿½ì ’ï¿½ëƒ½ ï¿½ë¿‰ï¿½ìœ­"+e);
		}
		return con;
		
	}
	//ï¿½êµ¹ï¿½ë’— ï¿½ë’ªï¿½ë?’ï¿½?” ï¿½ë“ƒ?™’?‡³?“ƒ?‘œï¿? ï§ëš®ë±¾ï¿½ë¼? ï¿½ë––ï¿½ì”ª?¨ï¿½ ï¿½ë¸¯ï§ï¿½ ï¿½ï¿½ï¿½ë–Š ï§ëš®ë±¾ï¿½ë¼? äºŒì‡°?’— ï¿½ë¸¿ï¿½ë‹”?‘œï¿? ï§ëš®ë±¾æ?¨ï¿½ ï¿½ë–¢ï¿½ë–.
	public Statement getSTMT(Connection con){
		//ï¿½ë’ªï¿½ë?’ï¿½?” ï¿½ë“ƒ?™’?‡³?“ƒ?‘œï¿? ï§ëš®ë±¾ï¿½? ®ï§ï¿½ Connection ï¿½ì”  ï¿½ì—³ï¿½ë¼±ï¿½ë¹ ï¿½ë¸¯èª˜ï¿½æ¿¡ï¿½..
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
		}
		catch(Exception e){
			System.out.println("ï¿½ë’ªï¿½ë?’ï¿½?” ï¿½ë“ƒ?™’?‡³?“ƒ ï¿½ê¹®ï¿½ê½¦ ï¿½ë¿‰ï¿½ìœ­"+e);
		}
		return stmt;
	}
	//ï¿½êµ¹ï¿½ë’— ï¿½ë¼‡è«›â‘ºë¼? ï¿½ë’ªï¿½ë?’ï¿½?” ï¿½ë“ƒ?™’?‡³?“ƒåª›ï¿½ ï¿½ë¸˜ï¿½ìŠ‚ï¿½ë¸¯ï§ï¿½ ï¿½ï¿½ï¿½ë–Š ï§ëš®ë±¾ï¿½ë¼±äºŒ?‡°?’— ï¿½ë¸¿ï¿½ë‹”?‘œï¿? ï§ëš®ë±¾æ?¨ï¿½ ï¿½ë–¢ï¿½ë–.
	public Statement getSTMT(Connection con,boolean isKind){
			//ï¿½ë’ªï¿½ë?’ï¿½?” ï¿½ë“ƒ?™’?‡³?“ƒ?‘œï¿? ï§ëš®ë±¾ï¿½? ®ï§ï¿½ Connection ï¿½ì”  ï¿½ì—³ï¿½ë¼±ï¿½ë¹ ï¿½ë¸¯èª˜ï¿½æ¿¡ï¿½..
			Statement stmt = null;
			try{
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
			}
			catch(Exception e){
				System.out.println("ï¿½ë’ªï¿½ë?’ï¿½?” ï¿½ë“ƒ?™’?‡³?“ƒ ï¿½ê¹®ï¿½ê½¦ ï¿½ë¿‰ï¿½ìœ­"+e);
			}
			return stmt;
		}
	//ï¿½ì” å¯ƒï¿½ ï§ë¨­?¬ï¿½ë£? ï¿½ë¸˜ï¿½ìŠ‚ï¿½ë¸³ ï¿½ë¸¿ï¿½ë‹”(ï¿½ì˜„äºŒï¿½ ï¿½ê¶—ï¿½ìŠœï¿½ë¸¯ï¿½ë’— ï¿½ë¸¿ï¿½ë‹”)åª›ï¿½ ï¿½ì—³ï¿½ì‘ï§ï¿½ ï§ëš®ë±¾ï¿½ë¼±ï¿½ê½? ï¿½ë²ï§ï¿½ ï¿½ë§‚ï¿½ë–.
	//PreparedStatement?‘œï¿? ï§ëš®ë±¾ï¿½ë¼? äºŒì‡°?’— ï¿½ë¸¿ï¿½ë‹”
	public PreparedStatement getPSTMT(Connection con,String sql){
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e){
			System.out.println("ï¿½ë’ªï¿½ë?’ï¿½?” ï¿½ë“ƒ ?™’?‡³?“ƒ ï¿½ê¹®ï¿½ê½¦ ï¿½ë¿‰ï¿½ìœ­"+ e);
		}
		return pstmt;
	}
	//ï¿½ë–•ï¿½ë¸˜ ï¿½ë––ï¿½ì”ª?¨ï¿½ ï¿½ë¸¯ï§ï¿½ ï¿½ï¿½ï¿½ë–Š ï¿½ë–•ï¿½ë¸˜ ï¿½ë––ï¿½ì”ª?¨ï¿½ ï¿½ë¸¯ï¿½ë’— ï¿½ë¸¿ï¿½ë‹”?‘œï¿? ï§ëš®ë±¾æ?¨ï¿½ ï¿½ë–¢ï¿½ë–. æ´¹ì‡°?œ² ï¿½ë–•ï¿½ë’— ?†«?‚…ìªŸåª›ï¿? ï¿½ë¿¬ï¿½ìœ­åª›ï¿½ï§ï¿½ ï¿½ì” ï¿½ë–.
	public void close(Object obj){
		//objï¿½ë¸ï¿½ë¿‰ï¿½ë’— ï¿½ë–•?¨ï¿½ ï¿½ë–¢ï¿½ï¿½ å¯ƒê»‹?”  ï¿½ë±¾ï¿½ë¼±ï¿½ì‚¤å¯ƒï¿½ ï¿½ë¸³ï¿½ë–.
		try{
			if(obj instanceof ResultSet){
				((ResultSet)obj).close();
			}
			else if(obj instanceof Statement){
				((ResultSet)obj).close();
			}
			else if(obj instanceof Connection){
				((ResultSet)obj).close();
			}
			else if(obj instanceof PreparedStatement){
				((PreparedStatement)obj).close();
			}			
			
		}
		catch(Exception e){
		}
	}
}

