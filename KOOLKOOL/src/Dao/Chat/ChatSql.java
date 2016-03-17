package Dao.Chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import Dao.Constant;
import Dao.MyJDBCDao;

public class ChatSql {

	public ChatSql() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	
	public ResponseSaveChatMsg SelectChatMsg(TB_SaveMsg data )
	{
		Dao.Chat.ResponseSaveChatMsg res = new Dao.Chat.ResponseSaveChatMsg();

		res.message = Constant.DEF_LIST;
		
		res.data = new Vector<TB_SaveMsg>();
		MyJDBCDao dao = null;

		try {
			dao = new MyJDBCDao();
			StringBuilder buffer = new StringBuilder(1024);

			buffer.append("select  a.seq, a.adminuserid, c.admin_name, a.useridx,b.user_id, b.user_name,a.msg,a.regdate from tb_chatmsg a left outer join  tb_user b  on a.useridx = b.idx "); 
			buffer.append(" left outer join tb_admin  c  on a.adminuserid = c.admin_id  "); 
			buffer.append("where a.regdate between to_date(?||' 00:01','YYYY/MM/DD HH24:MI') and to_date(? || ' 23:59','YYYY/MM/DD HH24:MI') ");
			buffer.append("and   c.admin_name like '%'||?||'%' ");
			buffer.append(" and b.user_name like '%'||?||'%' ");
			  
			buffer.append(" order by a.seq desc");			

			PreparedStatement pstat = dao.getPrepare(buffer.toString());		

			pstat.setString(1,util.Util.DateToString(data.reqdate));
			pstat.setString(2,util.Util.DateToString(data.enddate));
			pstat.setString(3,data.adminusername);
			pstat.setString(4,data.username);
			
			res.result = true;

			ResultSet result = pstat.executeQuery();

			while(result.next()) {			
				TB_SaveMsg chat = new TB_SaveMsg();
				chat.seq  = result.getInt(1);
				chat.adminuserid = result.getString(2);
				chat.adminusername= result.getString(3);
				chat.useridx =result.getInt(4);
				chat.userid  = result.getString(5);
				chat.username  = result.getString(6);
				chat.Msg  = result.getString(7);
				chat.reqdate  = result.getDate(8);
				res.data.add(chat);			
			}

		} catch(Exception ex) {

			ex.printStackTrace();
			res.result = false;
			res.reason = ex.toString();

		} finally {

			if (dao != null)
				dao.closeAll();	
		}

		return res ;
	}


	public ResponseSaveChatMsg  DeleteChatMsg(TB_SaveMsg data)
	{
		
		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
			buffer.append("delete from  tb_chatmsg ");
			buffer.append("where seq = ?");

			PreparedStatement pstat = dao.getPrepare(buffer.toString());

			pstat.setInt(1, data.seq);

			pstat.executeUpdate();
								
			 return SelectChatMsg(data);
			
		} catch(Exception ex) {
			
			Dao.Chat.ResponseSaveChatMsg res = new Dao.Chat.ResponseSaveChatMsg();
			res.message = Constant.DEF_DELETE;			
			ex.printStackTrace();
			res.result = false;
			res.reason =ex.toString();
			return res;

		} finally {

			if (dao != null ) 
				dao.closeAll();
		}

	//	return null;

	}
	
	public ResponseSaveChatMsg InsertChatMsg(TB_SaveMsg data ) 
	{
		
		Dao.Chat.ResponseSaveChatMsg res = new Dao.Chat.ResponseSaveChatMsg();
		res.message = Constant.DEF_INSERT;
		MyJDBCDao dao =null;
		try {

			StringBuilder buffer = new StringBuilder(2048);
			dao = new MyJDBCDao();
			buffer.append("insert into TB_chatmsg( seq, AdminUserId,useridx,Msg) values ( ");
			buffer.append("tb_chat_seq.nextval,?,?,?");
			buffer.append(")");
	
			Clob clob = dao.getcon().createClob();
			
			clob.setString(1, data.Msg);
			
			PreparedStatement pstat = dao.getPrepare(buffer.toString());
			
			pstat.setString(1, data.adminuserid);
			pstat.setInt(2, data.useridx);
			pstat.setClob(3, clob);
			pstat.execute();
			
			clob.free();
			
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

	public ResponseSaveChatMsg ProcessMsg(RequestSaveChatMsg data)  {
		
		// TODO Auto-generated method stub
		

		if (data.message == Constant.DEF_LIST )
		{
			return SelectChatMsg(data.data );			
		}
		
		if (data.message == Constant.DEF_DELETE )
		{
			return this.DeleteChatMsg(data.data );			
		}
		
		if (data.message == Constant.DEF_INSERT )
		{
			return this.InsertChatMsg(data.data );			
		}
		
		System.out.println("올라른  메시지가 아닙니다.");
		
		return null;
	}
}
