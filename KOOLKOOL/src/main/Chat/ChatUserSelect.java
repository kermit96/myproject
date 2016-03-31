package main.Chat;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.*;

import util.ClientRead;
import main.Global;
import main.Login;
import Dao.Constant;
import Dao.RequestAdmin;
import Dao.ResponseAdmin;
import Dao.TB_USER;
import Dao.TB_admin;
import Dao.Chat.RequestChatMsg;
import Dao.Chat.RequestSaveChatMsg;
import Dao.Chat.ResponseChatMsg;
import Dao.Chat.TB_Chatting;
import Dao.Chat.TB_SaveMsg;

@SuppressWarnings("serial")
public class ChatUserSelect extends JDialog {


	Global g_global = Global.getInstance();	

	ClientRead reader ;

	@Override
	public void dispose() {		
		g_global.serverConnect.RemoveClientRead(reader);		
		super.dispose();
	}

	public void Process(ResponseAdmin admin) {

		if (admin.result == false) {			
			JOptionPane.showMessageDialog(this, admin.reason, "상담원 명단 가져 오기 에러", JOptionPane.OK_OPTION);			
			return;
		}		

		list.setListData(admin.data);
	}

	public void Process(ResponseChatMsg data) {

		if(data.message != Constant.DEF_REQUEST_CHAT_MESSAGE )
			return;

		if (data.result == false) {			
			JOptionPane.showMessageDialog(this,  "현재 상담원은  로그인을 안 했습니다. ");			
			return;
		}

		Chatting chat = new  Chatting();	  
		chat.Set(data.data.toUserid,data.data.toUserName,-1);    
		chat.setVisible(true);

	}


	public void Write(Serializable obj  ) 
	{
		g_global.serverConnect.write(obj);

	}
	public String adminuserid = "";
	JList<TB_admin> list;
	public ChatUserSelect() {

		super();		

		reader = new ClientRead() {

			@Override
			public void run(Object obj) {
				// TODO Auto-generated method stub

				if (obj instanceof ResponseAdmin ) {

					ResponseAdmin admin = (ResponseAdmin)obj;
					Process(admin );					
				}

				if (obj instanceof ResponseChatMsg ) {

					ResponseChatMsg data = (ResponseChatMsg)obj;

					Process(data );					
				}						

			}
		};

		g_global.serverConnect.AddClientRead(reader);


		JLabel label = new JLabel("상담원 선택",JLabel.CENTER );

		list  = new JList<TB_admin>();

		JScrollPane p1 = new JScrollPane(list); 

		this.add(label,"North");
		this.add(p1,"Center");

		JButton button = new JButton("채팅하기");

		JButton Close = new JButton("닫기");

		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

		p2.add(button);
		p2.add(Close);


		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				dispose();
			}
		});

		Close.addActionListener(x->{

			//	this.setVisible(false);

			dispose();
		});
		button.addActionListener(x->Chat());

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				// 	super.mouseClicked(e);			
				JList list = (JList)evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					if (index >= 0)
						Chat();			        	
				}
			}	
		});

		this.add(p2,"South");		



		/*
		while(true) {
			boolean ret = g_global.serverConnect.CheckClientRead(reader);
			if (ret == true) 
				break;
			try {
				Thread.sleep(1);
			} catch (Exception ex) {
				
			}
		}
		*/
		
		try {
			Thread.sleep(100);
		} catch (Exception ex) {
			
		}

		RequestAdmin reqadmin = new RequestAdmin();

		reqadmin.message = Constant.DEF_LIST;

		Write(reqadmin);
		
		pack();

		setVisible(true);
		// TODO Auto-generated constructor stub
	}

	private void Chat()
	{

		adminuserid = list.getSelectedValue().adminid;

		boolean isadmin = g_global.isAdmin;

		RequestChatMsg msg = new RequestChatMsg();

		msg.message = Constant.DEF_REQUEST_CHAT_MESSAGE;
		msg.data = new TB_Chatting();

		msg.data.toUserid = adminuserid;
		msg.data.toUserName  = list.getSelectedValue().name;

		msg.data.fromuseridx =  g_global.getIdx();
		msg.data.fromuserid = g_global.getUserid();
		msg.data.fromusername = g_global.getUsername();

		Write(msg);

	}


	public static void main(String[] args) {

		// TODO Auto-generated method stub

		Login login = new Login(null);    	

		if (login.Select != Constant.DEF_LOGIN ) {
			login.dispose();
			return;
		}    	    	    		
		new ChatUserSelect();	
		login.dispose();		
	}

}
