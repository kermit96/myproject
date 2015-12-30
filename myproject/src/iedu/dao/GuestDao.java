package iedu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import iedu.data.GeustInfo;



public class GuestDao {

	public void insertGuest( String Write,String content) {

		OracleJDBCDao dao = null;
		PreparedStatement pstat; 
		try {
			dao = new OracleJDBCDao();
			String sql = "insert into guest(g_No,g_Writer,g_Content,g_Date) values( (select nvl(max(g_no),0)+1  from guest ), ?,?, sysdate)";		   
			pstat = dao.getPrepare(sql);

			pstat.setString(1, Write);
			pstat.setString(2, content);

			pstat.execute();		   
		} catch (Exception ex) {		   
			ex.printStackTrace();
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}

	}


	public ArrayList<GeustInfo>  getGuest() {

		ArrayList<GeustInfo> array = new ArrayList<GeustInfo>();

		OracleJDBCDao dao = null;

		PreparedStatement pstat;

		try {

			dao = new OracleJDBCDao();
			String sql = "select * from Guest Order  by g_no desc";		   
			pstat = dao.getPrepare(sql);
			ResultSet result = pstat.executeQuery();
			while(result.next()) {
				GeustInfo data = new GeustInfo();
				data.no = result.getInt("g_no");
				data.writer = result.getString("g_Writer");
				data.content = result.getString("g_Content");
				data.date = result.getDate("g_Date");
				array.add(data);
			}		   
			pstat.execute();

		} catch (Exception ex) {		   
			ex.printStackTrace();
		} finally {
			if (dao != null) 
				dao.closeAll();		    
		}

		return array;
	}
}


