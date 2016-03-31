package com.netty.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.net.*;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.*;

import Dao.*;
import Dao.Chat.RequestChatMsg;
import Dao.Chat.RequestSaveChatMsg;
import Dao.Chat.ResponseChatMsg;
import Dao.Chat.ResponseSaveChatMsg;
import Dao.board.BoardConstant;
import Dao.board.BoardSql;
import Dao.board.RequestBoard;
import Dao.board.ResponseBoard;
import Dao.house.HouseConstant;
import Dao.house.HouseSql;
import Dao.house.RequestHouse;
import Dao.house.ResponseHouse;
import Dao.reserve.RequestReserve;
import Dao.reserve.ReserveConstant;
import Dao.reserve.ReserveSql;
import Dao.reserve.ResponseReserve;
import Dao.review.R_Constant;
import Dao.review.RequestReview;
import Dao.review.ResponseReview;
import Dao.review.ReviewSql;
import Dao.room.RequestRoom;
import Dao.room.ResponseRoom;
import Dao.room.RoomConstant;
import Dao.room.RoomSql;

/**
 * Handler for a server-side channel.  This handler maintains stateful
 * information which is specific to a certain channel using member variables.
 * Therefore, an instance of this handler can cover only one channel.  You have
 * to create a new handler instance whenever you create a new channel and insert
 * this handler  to avoid a race condition.
 */

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	private static  final    HashSet<ServerHandler> AcceptHandle = new HashSet<ServerHandler>();  
	private ServerBase server;

	private boolean isAdmin = false;
	public String userid = "";	
	private String    host;
	public String username = "";

	private ChannelHandlerContext  ctx;
	

	
	public  ServerHandler(ServerBase server)
	{
		super();
		this.server =server;

	}

	

	private void ProcessLogin(LoginData data,ChannelHandlerContext ctx) 
	{
		LoginResponse res = new LoginResponse();
		res.data = new Dao.TB_USER();
		res.isAdmin = data.isAdmin;
		res.data.user_id = data.userid;
		res.data.user_pwd = data.password;
		try {
			CommonSql sql = new CommonSql();
			sql.CheckPassword(res);			
			if (res.result == true) {
				this.isAdmin = res.isAdmin;
				this.username = res.data.user_name;
				this.userid  = res.data.user_id;

				if (isAdmin == true)
					server.Log("관리자 Login Success.==>"+userid);
				else 		
					server.Log("일반 사용자 Login Success.==>"+userid);
				AcceptHandle.add(this);				
			}			
			ctx.writeAndFlush(res);

		} catch(Exception ex) {

			ex.printStackTrace();
		}		

	}



	// 현재 접속중인 client 를  찾는다.
	public ServerHandler GetServerHandler(String userid,boolean IsAdmin)  {

		for(ServerHandler temp:AcceptHandle ) {
			if (temp.isAdmin ==  IsAdmin && temp.userid.equals(userid) ) 
				return temp;    	      	   
		}

		return null;
	}



	// 쓴다 
	public void Write(Serializable obj) {

		try {
			ctx.writeAndFlush(obj);
		} catch (Exception ex) {			
			ex.printStackTrace();
			server.Log("에러:"+ex);
		}
	}

	private void InsertMember(TB_USER data,ChannelHandlerContext ctx) 
	{
		ResponseMember  res  = null;

		try {

			CommonSql sql = new CommonSql();

			res = sql.InsertMember(data);

			ctx.writeAndFlush(res);

		} catch(Exception ex) {
			ex.printStackTrace();			
		}

	}


	private void UpdateMember(TB_USER data,ChannelHandlerContext ctx) 
	{
		ResponseMember res  = null;

		try {

			CommonSql sql = new CommonSql();

			res = sql.UpdateMember(data);

			ctx.writeAndFlush(res);

		} catch(Exception ex) {

		}

	}


	private void DeleteMember(TB_USER data,ChannelHandlerContext ctx) 
	{
		ResponseMember res  = null;

		try {

			CommonSql sql = new CommonSql();

			res = sql.DeleteMember(data);

			ctx.writeAndFlush(res);

		} catch(Exception ex) {
           
		}

	}

	// data를 읽었을떄 나온다.
	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

		server.Read(ctx, msg);
		
		if (msg instanceof LoginData) {    		    		
			LoginData login = (LoginData)msg;
			ProcessLogin(login,ctx);    	
			return;
		}

		if (msg instanceof RequestMember) {
			RequestMember data = (RequestMember)msg; 
			ProcessMember(data,ctx);
			return;
		}

		if (msg instanceof RequestCheckUserId) {
			RequestCheckUserId data = (RequestCheckUserId)msg; 
			CheckUserid(data, ctx);
			return;
		}

		if (msg instanceof RequestAdmin) {

			RequestAdmin data = (RequestAdmin)msg; 
			ProcessAdminUser(data, ctx);
			return;
		}

		if (msg instanceof RequestSaveChatMsg ) {

			RequestSaveChatMsg data = (RequestSaveChatMsg)msg;
			ProcessSaveChatMsg(data,ctx);
			return;
		}

		if (msg instanceof RequestChatMsg) {
			RequestChatMsg data = (RequestChatMsg)msg;
			ProcessChatMsg(data,ctx);
			return;
		}
		
		if (msg instanceof RequestBoard) {
			
			RequestBoard data = (RequestBoard)msg;
			ProcessBoard(data,ctx);
			return;
		}
		
		if (msg instanceof RequestHouse) {
			RequestHouse data = (RequestHouse)msg;
			ProcessHouse(data,ctx);
			return;
		}
		
		
		if (msg instanceof RequestReview) {
			RequestReview data = (RequestReview)msg;
			ProcessReview(data,ctx);
			return;
		}

		if (msg instanceof RequestRoom) {
			RequestRoom data = (RequestRoom)msg;
			ProcessRoom(data,ctx);
			return;
		}
		
		if (msg instanceof RequestReserve) {
			RequestReserve data = (RequestReserve)msg;
			ProcessReserve(data,ctx);
			return;
		}
		
		if (msg instanceof RequestRegister) {
			RequestRegister data = (RequestRegister)msg;
			ProcessRegister(data,ctx);
		}

	}

	
	private void ProcessRegister(RequestRegister data,
			ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
		RegisterSql sql = new RegisterSql();
		
		
		if (data.message == Constant.DEF_LIST) {
			ShowReserveChcek(data.data,ctx);
			return;
		
		}
		
		if (data.message == Constant.DEF_UPDATE) {
			ModifyRegister(data.data, ctx);
		}
	}

	private void ShowReserveChcek(TB_REGISTER data, ChannelHandlerContext ctx) {
		
		ResponseRegister res = null;
		
		try {
			
			RegisterSql sql = new RegisterSql();
			
			res = sql.ShowRegister(data);
			ctx.writeAndFlush(res);
		} catch (Exception ex) {
		}
	}
	
	
	private void ModifyRegister(TB_REGISTER data, ChannelHandlerContext ctx) {
		
		ResponseRegister res = null;
		
		try {
			
			RegisterSql sql = new RegisterSql();
			
			res = sql.ModifyRegister(data);
			ctx.writeAndFlush(res);
		} catch (Exception ex) {
			
		}
	}
	
	
	private void ProcessReserve(RequestReserve data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
		ResponseReserve res  = null;
		ReserveSql  sql = new ReserveSql();
		
		if(data.message == ReserveConstant.DEF_LIST )
		{
		   res = sql.allSearch(data.data);	
		}
		
		if(data.message == ReserveConstant.DEF_LIST_CONDITION )
		{
			res = sql.conditionSearch(data.data);			
		}
		
		if(data.message == ReserveConstant.DEF_INSERT )
		{
			res = sql.reservation(data.data);			
		}
		
		if(data.message == ReserveConstant.DEF_DELETE)
		{
			
			res = sql.deleteReserve(data.data);
		}
		
		if(data.message == ReserveConstant.DEF_IDX_LIST)
		{			
			Dao.reserve.ResponseReserve roomres  = null;			
			roomres = sql.reserveShow(data.data);			
			ctx.writeAndFlush(roomres);
			return;
		}
		
		if(data.message == ReserveConstant.DEF_UPDATE)
		{
			Dao.reserve.ResponseReserve roomres  = null;
			roomres = sql.ModifyReserve(data.data);			
			ctx.writeAndFlush(roomres);			
			return;
		}
				
		ctx.writeAndFlush(res);


	}


	private void ProcessRoom(RequestRoom data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		ResponseRoom res  = null;
		RoomSql  sql = new RoomSql();
		
		if(data.message == RoomConstant.DEF_LIST )
		{
		   res = sql.selectRoomList(data.data);	
		}
		
		if(data.message == RoomConstant.DEF_UPDATE )
		{
			res = sql.updateRoom(data.data);
			
		}
		
		if(data.message == RoomConstant.DEF_IDX_LIST )
		{
			res = sql.selectRoom(data.data);			
		}
		
		if(data.message == RoomConstant.DEF_DELETE)
		{
			res = sql.deleteRoom(data.data);			
		}
				
		ctx.writeAndFlush(res);
		
	}


	private void ProcessReview(RequestReview data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
		ResponseReview res  = null;
		ReviewSql  sql = new ReviewSql();
		
		if(data.message == R_Constant.DEF_READ_BASIC_LIST )
		{
		   res = sql.ReadReview(data.data);	
		}
		
		if(data.message == R_Constant.DEF_READ_CONTENT_LIST )
		{
			res = sql.ReadReviewContent(data.data);
			
		}
		
		if(data.message == R_Constant.DEF_INSERT_REVIEW )
		{
			res = sql.InsertReview(data.data);
			
		}
				
				
		if(data.message == R_Constant.DEF_DELETE_REVIEW)
		{
			res = sql.DeleteReview(data.data);			
		}

		if(data.message == R_Constant.DEF_UPDATE_REVIEW)
		{
			res = sql.UpdateReview(data.data);			
		}
				
		ctx.writeAndFlush(res);
		
	}

	private void ProcessHouse(RequestHouse data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		ResponseHouse res  = null;
		HouseSql  sql = new HouseSql();
		
		if(data.message == HouseConstant.DEF_INSERT )
		{
		   res = sql.insertHouse(data.data);	
		}
		
		if(data.message == HouseConstant.DEF_UPDATE )
		{
			res = sql.updateHouse(data.data);
			
		}
		
		if(data.message == HouseConstant.DEF_LIST)
		{
			res = sql.selectHouseList(data.data);			
		}
		
		if(data.message == HouseConstant.DEF_DELETE)
		{
			res = sql.deleteHouse(data.data);			
		}

		if(data.message == HouseConstant.DEF_ROOMAS_LIST)
		{
			res = sql.selectHouseIdxList(data.data);			
		}
		
		if(data.message == HouseConstant.DEF_IDX_LIST)
		{
			res = sql.selectHouse(data.data);			
		}

		if(data.message == HouseConstant.DEF_REVIEWAS_LIST)
		{
			res = sql.selectHouseIdxListreview(data.data);			
		}

		ctx.writeAndFlush(res);
		
	}


	private void ProcessBoard(RequestBoard data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		ResponseBoard res  = null;
		BoardSql  sql = new BoardSql();
		
		if(data.message == BoardConstant.DEF_INSERT )
		{
		   res = sql.AddBoard(data.data);	
		}
		
		if(data.message == BoardConstant.DEF_UPDATE )
		{
			res = sql.ModifyBoard(data.data);			
		}
		
		if(data.message == BoardConstant.DEF_LIST)
		{
			res = sql.SelectBoard(data.data);			
		}
		
		if(data.message == BoardConstant.DEF_DELETE)
		{
			res = sql.deleteBoard(data.data);			
		}

		if(data.message == BoardConstant.DEF_HIT)
		{
			res = sql.hitBoard(data.data);			
		}
		
		if(data.message == BoardConstant.DEF_SHOW)
		{
			res = sql.ShowBoard(data.data);			
		}
		
	//	ctx.writeAndFlush(res);
		
		ctx.write(res);
        ctx.flush();
	}


	// 관리자에게 상담을 요청한다.
	//  접속한 상담원이 없으면  result 에 false 있으면 true 
	private void ProcessRequestChatMsg(RequestChatMsg data, ChannelHandlerContext ctx  ) {

		ServerHandler handler = this.GetServerHandler(data.data.toUserid, true);

		ResponseChatMsg msg  =  new ResponseChatMsg();

		if (handler == null) {    	    	
			msg.result = false;    		 
		} else {
			msg.result = true;   		    		 
		}			
		
		msg.message = Constant.DEF_REQUEST_CHAT_MESSAGE;
		msg.reason = "";
		msg.data = data.data;

		this.Write(msg);

	}

	
	
	private void ProcessChatMsg(RequestChatMsg data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub

		server.Log("process chat message="+data.message);
		server.Log("process chat message="+data.data.fromisAdmin);
		if (data.message == Constant.DEF_REQUEST_CHAT_MESSAGE )
			ProcessRequestChatMsg( data,  ctx  );

		if (data.message == Constant.DEF_RQUEST_FIRST_CHAT_MESSAGE)
			ProcessFirstChatMsg(data,ctx);
		
		if (data.message == Constant.DEF_CHATTING_MESSAGE)
			ProcessChattingMsg(data,ctx);
		
		if (data.message == Constant.DEF_CHAT_LEAVE)
			ProcessLeaveChat(data,ctx);
		
	}


	// chatting 을 전달한다.
	private void ProcessChattingMsg(RequestChatMsg data,
			ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub

		ServerHandler handler = this.GetServerHandler(data.data.toUserid, !data.data.fromisAdmin);
		
		if (handler == null)
			return;

		ResponseChatMsg msg  =  new ResponseChatMsg();
				
		msg.message = Constant.DEF_CHATTING_MESSAGE;
		msg.reason = "";
		msg.data = data.data;
        msg.result = true;		
        handler.Write(msg);
        
	}


	// 처음  chatting을 보낸다.
	private void ProcessFirstChatMsg(RequestChatMsg data,
			ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
		
		System.out.println("from is admin=="+data.data.fromisAdmin);
		System.out.println("from is name=="+data.data.fromusername);
		System.out.println("from is index=="+data.data.fromuseridx);
		ServerHandler handler = this.GetServerHandler(data.data.toUserid, !data.data.fromisAdmin);
		
		if (handler == null)
			return;

		ResponseChatMsg msg  =  new ResponseChatMsg();
		
		msg.message = Constant.DEF_RQUEST_FIRST_CHAT_MESSAGE;
		msg.reason = "";
		msg.data = data.data;
        msg.result = true;		
        handler.Write(msg);
       

        
	}
	
	
	private void ProcessLeaveChat(RequestChatMsg data,ChannelHandlerContext ctx){

		ServerHandler handler = this.GetServerHandler(data.data.toUserid, !data.data.fromisAdmin);
		
		if (handler == null)
			return;

		ResponseChatMsg msg  =  new ResponseChatMsg();
		
		msg.message = Constant.DEF_CHAT_LEAVE;
		msg.reason = "";
		msg.data = data.data;
        msg.result = true;		
        handler.Write(msg);		
		
 }

	// chatting 내용을  저장한다.
	private void ProcessSaveChatMsg(RequestSaveChatMsg data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		ResponseSaveChatMsg res ;
		try {

			Dao.Chat.ChatSql sql = new Dao.Chat.ChatSql();


			res = sql.ProcessMsg(data);

			ctx.writeAndFlush(res);

		} catch(Exception ex) {
			ex.printStackTrace();			
		}    	
	}


	// 중복된 user 를 check 한다.
	private void CheckUserid(RequestCheckUserId data , ChannelHandlerContext ctx )
	{
		ResponseCheckUserId res ;
		try {

			CommonSql sql = new CommonSql();

			res = sql.CheckUserid(data);

			ctx.writeAndFlush(res);

		} catch(Exception ex) {

		}    	
	}

	
	// 접속한 관리자 명단을 가져온다.
	private void ProcessAdminUser(RequestAdmin data ,ChannelHandlerContext ctx ) 
	{

		ResponseAdmin  senddata = new ResponseAdmin();
		senddata.message = Constant.DEF_LIST;
		senddata.data = new Vector<TB_admin>();
		senddata.result = true;
		for( ServerHandler temp :AcceptHandle) {

			if (temp.isAdmin == false) 
				continue;    		

			TB_admin adminuser = new TB_admin();    		
			adminuser.adminid = temp.userid;
			adminuser.name = temp.username;

			System.out.println("user name="+adminuser.name);


			senddata.data.add(adminuser);    		
		}    	
		ctx.writeAndFlush(senddata);

	}

	
	//  user 관리 
	private void ProcessMember(RequestMember data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub

		if (data.message == Constant.DEF_INSERT) {
			InsertMember(data.data,ctx);
			return;
		}

		if (data.message == Constant.DEF_UPDATE) {
			UpdateMember(data.data,ctx);
			return;
		}

		if (data.message == Constant.DEF_DELETE) {
			DeleteMember(data.data,ctx);
			return;
		}
		
		if (data.message == Constant.DEF_LIST) {
			ListMember(data.data,ctx);
			return;
		}
		
		if (data.message == Constant.DEF_SINGLE_LIST) {
			SingleListMember(data.data,ctx);
			return;
		}
		
		
		

	}


	private void SingleListMember(TB_USER data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		ResponseMember res  = null;

		try {

			CommonSql sql = new CommonSql();

			res = sql.selectUser(data);

			ctx.writeAndFlush(res);

		} catch(Exception ex) {

		}
	}


	private void ListMember(TB_USER data, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
		ResponseMember res  = null;

		try {

			CommonSql sql = new CommonSql();

			res = sql.ShowMember(data);

			ctx.writeAndFlush(res);

		} catch(Exception ex) {

		}
		
	}


	// 접속이 끊어졌을떄 
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		AcceptHandle.remove(this);  

		if (userid.isEmpty() ) {
			server.Log("접속이 끊어졌습니다.==>"+host);
		} else {

			server.Log("접속이 끊어졌습니다.==>"+userid);	
		}
	}


	// 접속이 되었을때 
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		host = ((InetSocketAddress)ctx.channel().remoteAddress()).getAddress().getHostAddress();
		this.ctx =  ctx;

		
		server.Active(ctx);
		
		server.Log("host==>"+host);

		//	System.out.println("host=>"+host);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		server.exceptionCaught(ctx, cause);
		ctx.close();
	}
}
