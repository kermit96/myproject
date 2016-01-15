package iedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import iedu.data.FileBoardData;
import iedu.sql.BaseJDBCDao;


public class FileBoardDAO {
	// 생성하는 순간 컨넥션 폴에 있는 컨넥션을 받아 오도록 하자
	public 		 BaseJDBCDao jdbcdao;

	public Connection con;

	public FileBoardDAO()
	{
		
		try {
			
			jdbcdao = BaseJDBCDao.GetjdbcDao();
			  
			
		} catch(Exception ex) {

		}
       
      
	}
	
	public int GetMaxNo()
	{
		
		
		return 0;
	}
	
	public void WriteFileBoard( FileBoardData data  )
	{
		String sql ="" ;
		PreparedStatement prepare = null;
		  try {

			  prepare =   jdbcdao.getPrepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		
	}
	
	
	public void Close()
	{
		   jdbcdao.closeAll();
		
	}
}
