package Dao.review;
import Dao.*;

import java.io.Serializable;
import java.sql.*;
import java.util.Vector;
/*
 * 	ResponseReview 함수와  RequestReview 함수 구현
 * 
 */
public class ReviewSql   {

	public ReviewSql()  {
		// TODO Auto-generated constructor stub													
	}

	public ResultSet GetSql(String sql) throws Exception {

		MyJDBCDao dao = new MyJDBCDao();
		return dao.GetResultStat(sql);
 
	}
	//ResponseReview 함수 구현
	//1.TB_REVIEW의 기본값(idx,house_idx,title,user_name,star) 읽기
	public ResponseReview ReadReview(TB_REVIEW data)  {
		ResponseReview res = new ResponseReview();
		res.message =  R_Constant.DEF_READ_BASIC_LIST ;
		Vector<TB_REVIEW> temp= new Vector<TB_REVIEW>();
		MyJDBCDao dao = null;
		try {

			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(1024);
			
			buffer.append("select idx,house_idx,user_name,title,star,reg_date from TB_REVIEW ORDER BY idx");
			PreparedStatement pstat = dao.getPrepare(buffer.toString());		
			ResultSet result = pstat.executeQuery();
			while(result.next()) {
				TB_REVIEW send = new TB_REVIEW();
				send.idx = result.getInt(1);	
				send.house_idx = result.getInt(2);
				send.user_name = result.getString(3);
				send.title = result.getString(4);				
				send.star = result.getInt(5);
				send.reg_date =  result.getDate(6);
				
				//send.reg_date = result.getString(5);	
				temp.add(send);//Vector에 추가함	
				//res.data = temp;	
				//break;				
			}   
						//temp.add(data);
			res.data = temp;			
			res.result = true;

		} catch(Exception ex) {
			res.result = false;
			ex.printStackTrace();			

		} finally {
			
			if (dao != null)
				dao.closeAll();	
		}

		return res;
	}	
	
	//2.TB_REVIEW 에서 content 읽기
	public ResponseReview ReadReviewContent(TB_REVIEW data)  {
		ResponseReview res = new ResponseReview();
		Vector<TB_REVIEW> temp= new Vector<TB_REVIEW>();
		res.message =  R_Constant.DEF_READ_CONTENT_LIST ;
		MyJDBCDao dao = null;
		try {

			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(1024);

			buffer.append("select content from TB_REVIEW where idx = ?");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setInt(1, data.idx);	
			
			ResultSet result = pstat.executeQuery();
			while(result.next()) {				
				data.content = result.getString(1);			 						 
				break;
			}
			temp.add(data);
			res.data = temp;
			res.result = true;

		} catch(Exception ex) {
			res.result = false;
			ex.printStackTrace();			

		} finally {
			
			if (dao != null)
				dao.closeAll();	
		}

		return res;
	}
	
		
	//3.TB_HOUSE 에서 name 읽기	
	public ResponseTbHouse ReadTbHouse(TB_HOUSE data)  {
		ResponseTbHouse res = new ResponseTbHouse();
		Vector<TB_HOUSE> temp= new Vector<TB_HOUSE>();
		res.message =  R_Constant.DEF_READ_HOUSENAME_LIST;
		MyJDBCDao dao = null;
		try {

			dao = new MyJDBCDao();

			StringBuilder buffer = new StringBuilder(1024);

			buffer.append("select idx,name from TB_HOUSE ORDER BY idx");
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());		
				
			ResultSet result = pstat.executeQuery();
			while(result.next()) {
				TB_HOUSE house = new TB_HOUSE();
				house.idx = result.getInt(1);
				house.name = result.getString(2);
				temp.add(house);
			}			
			
			res.data = temp;
			res.result = true;

		} catch(Exception ex) {
			res.result = false;
			ex.printStackTrace();			

		} finally {
			
			if (dao != null)
				dao.closeAll();	
		}

		return res;
	}
	//TB_REVIEW 에서sysdate 읽기
			public ResponseReview ReadReviewTime(TB_REVIEW data)  {
				ResponseReview res = new ResponseReview();
				Vector<TB_REVIEW> temp= new Vector<TB_REVIEW>();
			
				MyJDBCDao dao = null;
				try {

					dao = new MyJDBCDao();

					StringBuilder buffer = new StringBuilder(1024);

					buffer.append("select sysdate from dual");
					
					PreparedStatement pstat = dao.getPrepare(buffer.toString());
					
					//pstat.setInt(1, data.idx);	
					
					ResultSet result = pstat.executeQuery();
					while(result.next()) {	
						TB_REVIEW send = new TB_REVIEW();
						send.reg_date = result.getDate(1);
						temp.add(send);//Vector에 추가함	
									
					}
					res.data = temp;
					res.result = true;

				} catch(Exception ex) {
					res.result = false;
					ex.printStackTrace();			

				} finally {
					
					if (dao != null)
						dao.closeAll();	
				}

				return res;
			}
	//4.TB_REVIEW 에서 신규 리뷰 값 등록
	public ResponseReview InsertReview(TB_REVIEW  data  ) 
	{
	
		ResponseReview res = new ResponseReview();

		
		res.message = R_Constant.DEF_INSERT_REVIEW;

		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
		
			buffer.append("Insert into TB_REVIEW(idx,house_idx,user_name,title,content,star) ");
			
			buffer.append(" values((SELECT NVL(MAX(idx), 0) + 1 FROM TB_REVIEW),?,?,?,?,?)");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setInt(1, data.house_idx);
			pstat.setString(2, data.user_name);
			pstat.setString(3, data.title);
			pstat.setString(4, data.content);			
			pstat.setInt(5, data.star);	
					
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
	//5.TB_REVIEW 에서 title,content,star 값 업데이트
	public ResponseReview UpdateReview(TB_REVIEW  data) 
	{		
		ResponseReview res = new ResponseReview();
		
		res.message = R_Constant.DEF_UPDATE_REVIEW;
		
		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
			buffer.append("update tb_review set house_idx=?,title=?,content=?,star=?,reg_date = sysdate  where idx=?");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setInt(1, data.house_idx);
			pstat.setString(2, data.title);
			pstat.setString(3, data.content);
			pstat.setInt(4, data.star);
			pstat.setInt(5, data.idx);

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
	
	//6.TB_REVIEW 에서 idx 값에 대항하는 row 값 제거
	public ResponseReview DeleteReview(TB_REVIEW  data) 
	{		
		ResponseReview res = new ResponseReview();

		res.message = R_Constant.DEF_DELETE_REVIEW ;
		
		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
			buffer.append("delete from tb_review where idx = ?");

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
	//RequestReview 기본 값을 요청하는 함수 구현
	public RequestReview ReqReadReview(TB_REVIEW  data)  {
		//Serializable obj;
		RequestReview req = new RequestReview();
		//TB_REVIEW temp= new TB_REVIEW();
		req.message = Dao.review.R_Constant.DEF_READ_BASIC_LIST;
		//req.message = Constant.DEF_LIST;
		req.data = data;
		//g_global.serverConnect.write(req);
		
		return req;
	}
	//RequestReview content 값을 요청하는 함수 구현
	public RequestReview ReqReadReviewContent(TB_REVIEW  data)  {
		//Serializable obj;
		RequestReview req = new RequestReview();
		TB_REVIEW temp= new TB_REVIEW();
		req.message = Dao.review.R_Constant.DEF_READ_CONTENT_LIST;
		//req.message = Constant.DEF_LIST;
		req.data = temp;
		
		return req;
		
		// g_global.serverConnect.write(req);		
	}	
	//3.TB_HOUSE 에서 name 읽기	
	public void ReqReadTbHouse(TB_HOUSE data)  {
		//Serializable obj;
		RequestTbHouse req = new RequestTbHouse();
		//TB_REVIEW temp= new TB_REVIEW();
		req.message = Dao.review.R_Constant.DEF_READ_HOUSENAME_LIST;
		//req.message = Constant.DEF_LIST;
		req.data = data;
		//g_global.serverConnect.write(req);	
	}		
	//4.TB_REVIEW 에서 신규 리뷰 값 등록
	public void ReqInsertReview(TB_REVIEW  data  ) 
	{
		//Serializable obj;
		RequestReview req = new RequestReview();
		req.message = Dao.review.R_Constant.DEF_INSERT_REVIEW;
		//req.message = Constant.DEF_INSERT;
		//TB_REVIEW temp= new TB_REVIEW();
		req.data = data;
		//g_global.serverConnect.write(obj);	
		
	}	
	//5.TB_REVIEW 에서 title,content,star 값 업데이트
	public void ReqUpdateReview(TB_REVIEW  data) 
	{		
		//Serializable obj;
		RequestReview req = new RequestReview();
		req.message = Dao.review.R_Constant.DEF_UPDATE_REVIEW;
		//req.message = Constant.DEF_UPDATE;
		req.data = data;
		//g_global.serverConnect.write(req);	
	}
	
	//6.TB_REVIEW 에서 idx 값에 대항하는 row 값 제거
	public void ReqDeleteReview(TB_REVIEW  data) 
	{		
		RequestReview req = new RequestReview();
		req.message = Dao.review.R_Constant.DEF_DELETE_REVIEW;
		//req.message = Constant.DEF_DELETE;
		req.data = data;
		//g_global.serverConnect.write(req);	
		
	}	
}