package util;
/* �씠 �겢�옒�뒪�뒗 JDBC �옉�뾽�쓣 ?����?�� �렪�븯寃� �빐 二쇨린�?���븳 ��?�떥?��?�떚 �겢�옒�뒪?���? 留뚮�? �삁�젙�씠�떎.
 * 
 */
import java.sql.*;
public class JDBCDao {
	//�굹�뒗 �늻?��곌�媛� �씠 �겢�옒�뒪?���? new �떆�궗�븣 �옄�룞�쟻�쑝濡� Driver?���? 濡쒕뵫��??�� �떢�떎.
	public JDBCDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e){
			System.out.println("�뱶�씪�씠踰� 濡쒕�? �뿉�윭");
		}		
	}
	//�굹�뒗 �삤�씪�겢�뿉 �젒�냽�쓣 �슂?��?�븯硫� ���떊 �젒�냽�쓣 �빐二쇰?�� �븿�닔?���? 留뚮뱾�?�� �떢�떎.
	public Connection getCON(){
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"scott","tiger");
		}
		catch(Exception e){
			System.out.println("�젒�냽 �뿉�윭"+e);
		}
		return con;
		
	}
	//�굹�뒗 �뒪��?��?���듃?��?��?��?���? 留뚮뱾��? �떖�씪?�� �븯硫� ���떊 留뚮뱾��? 二쇰?�� �븿�닔?���? 留뚮뱾�?�� �떢�떎.
	public Statement getSTMT(Connection con){
		//�뒪��?��?���듃?��?��?��?���? 留뚮뱾�?��硫� Connection �씠 �엳�뼱�빞 �븯誘�濡�..
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
		}
		catch(Exception e){
			System.out.println("�뒪��?��?���듃?��?��?�� �깮�꽦 �뿉�윭"+e);
		}
		return stmt;
	}
	//�굹�뒗 �뼇諛⑺�? �뒪��?��?���듃?��?��?��媛� �븘�슂�븯硫� ���떊 留뚮뱾�뼱二?��?�� �븿�닔?���? 留뚮뱾�?�� �떢�떎.
	public Statement getSTMT(Connection con,boolean isKind){
			//�뒪��?��?���듃?��?��?��?���? 留뚮뱾�?��硫� Connection �씠 �엳�뼱�빞 �븯誘�濡�..
			Statement stmt = null;
			try{
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
			}
			catch(Exception e){
				System.out.println("�뒪��?��?���듃?��?��?�� �깮�꽦 �뿉�윭"+e);
			}
			return stmt;
		}
	//�씠寃� 留먭?���? �븘�슂�븳 �븿�닔(�옄二� �궗�슜�븯�뒗 �븿�닔)媛� �엳�쑝硫� 留뚮뱾�뼱��? �벐硫� �맂�떎.
	//PreparedStatement?���? 留뚮뱾��? 二쇰?�� �븿�닔
	public PreparedStatement getPSTMT(Connection con,String sql){
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e){
			System.out.println("�뒪��?��?���듃 ?��?��?�� �깮�꽦 �뿉�윭"+ e);
		}
		return pstmt;
	}
	//�떕�븘 �떖�씪?�� �븯硫� ���떊 �떕�븘 �떖�씪?�� �븯�뒗 �븿�닔?���? 留뚮뱾�?�� �떢�떎. 洹쇰?�� �떕�뒗 ?��?��쪟媛�? �뿬�윭媛�吏� �씠�떎.
	public void close(Object obj){
		//obj�븞�뿉�뒗 �떕?�� �떢�� 寃껋?�� �뱾�뼱�삤寃� �븳�떎.
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

