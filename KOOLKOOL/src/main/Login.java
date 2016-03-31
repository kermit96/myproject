package main;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.swing.*;

import Dao.LoginData;
import Dao.LoginResponse;
import util.*;

/**
 * <pre>
 * main
 * Login.java
 * </pre>
 * @author 김국희
 * @Date 2015. 11. 30
 * 
 * @category 로그인 
 */


public class Login extends JDialog {


	ClientBase Serverconnect;

	JLabel logoL, idL, pwL;

	JTextField idF;
	JPasswordField pwF;
	JButton loginB, memberB;
	// 설정 버튼
	JButton  SettingB;
	JRadioButton adminB, userB;
	Register register;

	ScreenLocation screen;
	int x = 0, y = 0;
	
	
	Global g_global = Global.getInstance();

	public static final short  DEF_NO_LOGIN = 0 ;
	public static final short  DEF_LOGIN = 1 ;
	
	// 로그인 했는지 안 했는지 선택 
	
	public short Select = DEF_NO_LOGIN;

	public void  ConnectError(Throwable ex) 
	{
		Serverconnect.stop();
		loginB.setEnabled(true);
		memberB.setEnabled(true);

		JOptionPane.showMessageDialog(this, ex.toString());   

	}


	public void  GeneralError(Throwable ex) 
	{	
	// 	JOptionPane.showMessageDialog(this, ex.toString());				
	}

	
	// 서버에 접속한다.
	public void Connnect()
	{
		String userid = idF.getText();
		String password = new String(pwF.getPassword());	   
		LoginData data = new LoginData();

		data.userid = userid;
		data.password = password;

		data.isAdmin =  !userB.isSelected();
		Serverconnect.write(data);
	}





// 응답 처리한다.
	private void ProcessLogin(LoginResponse data) {
		// TODO Auto-generated method stub

		if (data.result == false) {
			
			JOptionPane.showMessageDialog(this, "id가 없거나 password 가 일치하지 않습니다.");
			this.loginB.setEnabled(true);
			this.SettingB.setEnabled(true);
			this.memberB.setEnabled(true);			
			return;
		}
	    this.setVisible(false);
	    
	    g_global.isAdmin = data.isAdmin;
	    g_global.setIdx(data.data.idx );
	    g_global.setUserid(data.data.user_id );
	    g_global.setUsername(data.data.user_name);
	    g_global.setPassword(new String(this.pwF.getPassword()).trim() );
	    this.Select = DEF_LOGIN;
	    	    	    	 
	    
		Close();				
	}
    

	public void Read(Serializable obj ) {
		
		Dao.LoginResponse data = null ;	   
		if (obj instanceof Dao.LoginResponse ) {
			data = (LoginResponse)obj;
			ProcessLogin(data);		   
		}

	}
	
  private void Close()
  {
		Serverconnect.RemoveClientConnect(connect);
		Serverconnect.RemoveClientError(generalerror );
		Serverconnect.RemoveClientRead(reader);
         this.dispose();	  
  }
	

	// 이벤트  변수   
	ClientConnect  connect ;
	ClientError  generalerror ;
	ClientError  connecterror;
	ClientRead  reader;	

	
	
	public int getport()
	{		
		return g_global.getPort();				
	}
	
	public String gethost()
	{		
		return g_global.getServer();				
	}
	
	
	public void Setting()
	{		
		Setting(false);		
	}
	
	
	public void Setting(boolean isForce  )
	{
		
	   if (getport() == 0  ||  gethost().isEmpty()   || isForce== true) {
		   
			Setting set = new  Setting(this,"환경 설정");
			
			set.setPort(getport());
			set.setServer(gethost()); 
			
			set.setVisible(true);
			// 	System.out.println(set.getPort());

			int port = set.getPort();
			
			String host = set.getServer();
			
			g_global.setPort(port);
			g_global.setServer(host);							        		   
	   }
	   	   		
	}
	
	public Login (JFrame frame ) {	   	   
		super(frame,"ReverveSystem");

		screen = new ScreenLocation();
		screen.setScreen();
		this.setLocation(screen.x, screen.y);
		
		//  setting 값을 처리한다.
		Setting();
		
		
		// close 버튼을 눌러을때 처리 
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				Close();					
			}
		});
		
		if (g_global.serverConnect == null) {
			
			g_global.serverConnect = new ClientBase(gethost(),getport());
						
		}
		
		Serverconnect = g_global.serverConnect;



		connect = new ClientConnect() {
			@Override
			public void run() {
				Connnect();
			}
		};

		generalerror = new ClientError() {

			@Override
			public void run(Throwable cause) {
				// TODO Auto-generated method stub
				GeneralError(cause);
			}

		};


		connecterror = new ClientError() {

			@Override
			public void run(Throwable cause) {
				// TODO Auto-generated method stub
				ConnectError(cause);
			}

		};

		ClientRead  reader = new ClientRead() {

			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub

				Read(obj);
			}

		};

		Serverconnect.AddClientConnect(connect);
		Serverconnect.AddClientError(generalerror );
		Serverconnect.AddClientRead(reader);

		idF = new JTextField(10);
		pwF = new JPasswordField();

		loginB = new JButton("Login");
		memberB = new JButton("회원가입");

		ButtonEvent evt = new ButtonEvent();

		loginB.addActionListener(evt);
		memberB.addActionListener(evt);

		
		SettingB = new JButton("설정");
		
		SettingB.addActionListener(evt);
		
		adminB = new JRadioButton("Admin");
		userB = new JRadioButton("User");

		ButtonGroup g = new ButtonGroup();
		Font font = new Font("San-Serif",Font.BOLD,20);

		adminB.setFont(font);
		userB.setFont(font);
		// 사용자 버튼을 select 한다.
		userB.setSelected(true); 
		g.add(adminB);
		g.add(userB);

		ImageIcon icon = new ImageIcon("resources/main/home.png");

		logoL = new JLabel(icon);

		//logoL.setPreferredSize(new Dimension(300,300));

		idL = new JLabel("ID");
		pwL = new JLabel("PW");
		idL.setHorizontalAlignment(JLabel.LEFT);
		pwL.setHorizontalAlignment(JLabel.LEFT);


		JPanel p1 = new JPanel(new FlowLayout(1,10,10));

		p1.add(logoL);

		JPanel p3 = new JPanel(new GridLayout(2,1,10,10));

		p3.add(idL);
		p3.add(pwL);

		JPanel p2 = new JPanel(new GridLayout(2,1,10,10));

		p2.add(idF);
		p2.add(pwF);


		JPanel p4 = new JPanel(new FlowLayout());

		p4.add("West", p3);
		p4.add("Center",p2);

		JPanel pp = new JPanel(new GridLayout(2,1,0,-126));

		pp.add(p1);
		pp.add(p4);

		JPanel p5 = new JPanel(new FlowLayout());

		p5.add(loginB);
		p5.add(memberB);
		p5.add(SettingB);
         

		JPanel p6 = new JPanel(new BorderLayout());

		p6.add("North",pp);
		p6.add("Center",p4);

		JPanel p7 = new JPanel(new FlowLayout());

		p7.add(adminB);
		p7.add(userB);


		JPanel p8 = new JPanel(new BorderLayout());

		p8.add(p7,"North");
		p8.add(p5,"South");

		this.add("North", pp);
		this.add("South", p8);
		this.add("Center",p4);

		this.setFont(font);
		this.setSize(400,300);
		
		//this.setLocation(600, 600);
		
		
		this.setModal(true);
		this.setVisible(true);
		
		

		
		
	}

	public static void main(String[] args) {   

		new Login(null);
	}


	public void Startlogin()
	{	   	   
		// 로그인 접속후에는   버튼을 쓰지 목하게 한다. 

		loginB.setEnabled(false);
		memberB.setEnabled(false);
		SettingB.setEnabled(false);
		Serverconnect.start(this.connecterror);

	}
	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton target = (JButton) e.getSource();

			if(target == loginB){
				
//				System.out.println("Login");
				Startlogin();
				
			} else if(target == memberB){
				
				System.out.println("회원가입");
				// 회원가입 호출
				register = new Register( Login.this,Login.this.gethost(),Login.this.getport());
				register.ShowWinodw();

			} 	else if(target == SettingB){
				System.out.println("설정 ");

				// 회원가입 호출
				Setting(true);
				
				Serverconnect.SethostAndPort(gethost(), getport());

			}
		}
	}

}

