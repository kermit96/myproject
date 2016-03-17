package Dao;

import java.sql.*;
import java.util.*;

import Dao.Chat.ResponseSaveChatMsg;

public class CommonSql   {

	public CommonSql()  {
		// TODO Auto-generated constructor stub													
	}

	public ResultSet GetSql(String sql) throws Exception {
		MyJDBCDao dao = new MyJDBCDao();
		return dao.GetResultStat(sql);
	}

	public void CheckPassword(LoginResponse data)  {

		MyJDBCDao dao = null;
		try {

			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(1024);

			if (data.isAdmin == false)  {		
				buffer.append("select user_id,user_password, user_name, user_tel,user_address,user_email,reg_date,idx  from TB_USER where  user_id=? ");
			} else {
				buffer.append("select admin_id,admin_password,admin_name as user_name,'' as user_tel ,'' as user_address  ,'' as user_email ,'' as reg_date ,-1 as idx from TB_ADMIN where  admin_id=? ");			
			}

			PreparedStatement pstat = dao.getPrepare(buffer.toString());		

			pstat.setString(1, data.data.user_id);

			data.result = false;

			ResultSet result = pstat.executeQuery();
			while(result.next()) {

				String password = result.getString(2);
				if (data.data.user_pwd.equals(password) == false) {
					continue;
				}		

				System.out.println("user_name="+result.getString(3));
				System.out.println("isAdmin="+data.isAdmin);
				
				data.data.user_pwd = password;			 						 
				data.data.user_id = result.getString(1);			 			 
				data.data.user_name = result.getString(3);
				data.data.user_tel = result.getString(4);
				data.data.user_address = result.getString(5);
				data.data.user_email = result.getString(6);
				data.data.reg_date = result.getDate(7);
				data.data.idx = result.getInt(8);
				System.out.println("user_idx="+data.data.idx );
				data.result = true;
				break;
				
			}

		} catch(Exception ex) {

			ex.printStackTrace();
			data.result = false;
			data.reason = ex.toString();

		} finally {

			if (dao != null)
				dao.closeAll();	
		}
	}

	public Dao.ResponseMember UpdateMember(TB_USER  data) 
	{		
		Dao.ResponseMember res = new Dao.ResponseMember();

		res.message = Constant.DEF_UPDATE;

		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
			buffer.append("update tb_user set user_pwd=?,user_name=?,user_tel=?,user_address=?, user_ email=? where user_id=?");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setString(1, data.user_pwd);

			pstat.setString(2, data.user_name);

			pstat.setString(3, data.user_tel);

			pstat.setString(4, data.user_address);

			pstat.setString(5, data.user_email);
			pstat.setString(6, data.user_id);
			pstat.execute();
			res.result = true;

		} catch(Exception ex) {
			ex.printStackTrace();
			res.result = false;
			res.reason =ex.toString();

		} finally {

			if (dao != null ) 
				dao.closeAll();
		}

		return res;
	}


	public ResponseMember DeleteMember(TB_USER  data) 
	{		
		ResponseMember res = new ResponseMember();

		res.message = Constant.DEF_DELETE;

		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
			buffer.append("delete from tb_user where idx = ?");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setInt(1, data.idx);

			pstat.execute();

			res.result = true;

		} catch(Exception ex) {
			ex.printStackTrace();
			res.result = false;
			res.reason =ex.toString();

		} finally {

			if (dao != null ) 
				dao.closeAll();
		}
		return res;
	}

	public ResponseCheckUserId CheckUserid(RequestCheckUserId  data) 
	{		
		ResponseCheckUserId res = new ResponseCheckUserId();				

		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
			buffer.append("select count(*) from tb_user where user_id = ?");			

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setString(1, data.userid);

			ResultSet set = pstat.executeQuery();

			while(set.next()) {

				int num  = set.getInt(1);

				if (num == 0)
					res.result = false;
				else 
					res.result = true;				
			}

		} catch(Exception ex) {
			ex.printStackTrace();
			res.result = false;
			res.reason =ex.toString();			
		} finally {

			if (dao != null ) 
				dao.closeAll();
		}

		return res;

	}

	// 멤버를 넣는다
	public ResponseMember InsertMember(TB_USER  data  ) 
	{

		ResponseMember res = new ResponseMember();

		res.message = Constant.DEF_INSERT;

		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();

			buffer.append("Insert into TB_USER(idx,user_id,USER_PASSWORD,user_name,user_tel,user_address,USER_EMAIL ) ");

			buffer.append(" values(tb_user_seq.nextval,?,?,?,?,?,?)");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setString(1, data.user_id);

			pstat.setString(2, data.user_pwd);

			pstat.setString(3, data.user_name);

			pstat.setString(4, data.user_tel);

			pstat.setString(5, data.user_address);

			pstat.setString(6, data.user_email);

			pstat.execute();
			res.result = true;

		} catch(Exception ex) {
			ex.printStackTrace();
			res.result = false;
			res.reason =ex.toString();
		} finally {

			if (dao != null ) 
				dao.closeAll();
		}
		return res;
	}

	// user 리스트 출력
	
	public ResponseMember selectUser(TB_USER data){
        
        Dao.ResponseMember res = new Dao.ResponseMember();
        
        res.message = Constant.DEF_SINGLE_LIST;
              
        MyJDBCDao dao = null;
        try{
           dao = new MyJDBCDao();
           
           StringBuilder buffer = new StringBuilder(2048);
           
           buffer.append("SELECT idx, user_id, user_name , user_password, user_address, user_email, user_tel FROM TB_USER where idx = ?");
           
           
           PreparedStatement pstat = dao.getPrepare(buffer.toString());
     
           pstat.setInt(1, data.idx);
           
           ResultSet result = pstat.executeQuery();
           
           res.data = new Vector<TB_USER>(); 
           
           while(result.next()) {
              TB_USER tbdata= new TB_USER();
              tbdata.idx = result.getInt(1);
              tbdata.user_id = result.getString(2);
              tbdata.user_name = result.getString(3);
              tbdata.user_pwd = result.getString(4);
              tbdata.user_address = result.getString(5);
              tbdata.user_email = result.getString(6);
              tbdata.user_tel = result.getString(7);                            
              res.data.add(tbdata);
              
           }
           res.result = true;
           
        }catch(Exception e){
           e.printStackTrace();
           res.result = false;
           res.reason = e.toString();
        }
        finally{
           if(dao != null)
              dao.closeAll();
        }
        return res;
     }
	
	
	public ResponseMember ShowMember(TB_USER data){
	      
	      Dao.ResponseMember res = new Dao.ResponseMember();
	      
	      res.message = Constant.DEF_LIST;
	            
	      MyJDBCDao dao = null;
	      try{
	         dao = new MyJDBCDao();
	         
	         StringBuilder buffer = new StringBuilder(2048);
	         
	         buffer.append("SELECT idx, user_id, user_password, user_address, user_email, user_tel FROM TB_USER ORDER BY idx");
	         
	         
	         PreparedStatement pstat = dao.getPrepare(buffer.toString());
	         
	   
	         
	         ResultSet result = pstat.executeQuery();
	         
	         res.data = new Vector<TB_USER>(); 
	         
	         while(result.next()) {
	            TB_USER tbdata= new TB_USER();
	            tbdata.idx = result.getInt(1);
	            tbdata.user_id = result.getString(2);
	            tbdata.user_pwd = result.getString(3);
	            tbdata.user_address = result.getString(4);
	            tbdata.user_email = result.getString(5);
	            tbdata.user_tel = result.getString(6);
	            
	            
	            res.data.add(tbdata);
	            
	         }
	         res.result = true;
	         
	      }catch(Exception e){
	         e.printStackTrace();
	         res.result = false;
	         res.reason = e.toString();
	      }
	      finally{
	         if(dao != null)
	            dao.closeAll();
	      }
	      return res;
	   }
	
	
	
}
