package main.board;



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.netty.client.ClientBase;
import com.netty.client.ClientConnect;
import com.netty.client.ClientError;
import com.netty.client.ClientRead;

import javax.swing.table.DefaultTableCellRenderer;

import Dao.Constant;
import Dao.board.BoardConstant;
import Dao.board.BoardSql;
import Dao.board.RequestBoard;
import Dao.board.ResponseBoard;
import util.ScreenLocation;
import util.StarRater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.sql.*;

import main.MenuBar;
import main.*;
import main.board.*;
import main.house.house_management;
import main.house.room_management;
import main.Chat.ChatAdmin;

public class MainBorder{
	
	JFrame f;
	JLabel mainL,titleL,wrL;
	JLabel nameL;
	JButton homeB, wrB, chB ,ccB, exB;
	TextField titleF;
	TextArea area;
	DefaultTableModel model;
	JTable table;
	CardLayout card;
	JPanel WriteP;
    MainPanel mainPP;
    AdminMenu AdminP;
    JPanel	cardPanel;
	MenuBar menubar;
	MyInfo infoP;
	
	ScreenLocation screen;
	int x = 0, y = 0;
	
	
  //	바구꼬 싶은 크래스 넣고
	public MainBorder() {
		
		f = new JFrame();
		card = new CardLayout();
		cardPanel = new JPanel(card); 
		
		menubar = new MenuBar();
		
		f.add("North", menubar);
		f.add(cardPanel);
		
		AdminP = new AdminMenu(this);
		mainPP = new MainPanel(this);
		infoP = new MyInfo(this);
		
		cardPanel.add("메인화면", AdminP);
		cardPanel.add("공지사항", mainPP);
		cardPanel.add("내정보", infoP);
		
		
		f.setSize(500, 620);
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		screen = new ScreenLocation();
		screen.setScreen();
		f.setLocation(screen.x, screen.y);

		
	}
		
	

	public static void main(String[] args) {
		
		new Login(null);
		new MainBorder();
		
	}
}


class AdminMenu extends JPanel{
	
	MainBorder main;
	JButton houseB, roomB, clientListB, submitListB;
	JLabel logoL;
	CardLayout card;
	JPanel AdminP;
	InfoBorder info;
	member_management member;
	ImageIcon bgcon;
	ImageIcon  bgcon1;
	ImageIcon	bgcon2;
	ImageIcon	bgcon3;
	ImageIcon bgimg;
	house_management houseMg;
	room_management roomMg;
	ChatAdmin	admin;
	
	
	public AdminMenu(MainBorder m) {
	
		main = m;
	
		this.setLayout(new BorderLayout());
	

	bgimg = new ImageIcon("resources/admin/bgimg.jpg");
	
	
	logoL = new JLabel(bgimg);
	
	logoL.addMouseListener(new ClickEvent());
	logoL.addMouseMotionListener(new ClickEvent());

	LabelEvent evt = new LabelEvent();
	
	main.menubar.tableL.addMouseListener(evt);
	main.menubar.homeL.addMouseListener(evt);
	main.menubar.myinfoL.addMouseListener(evt);
	main.menubar.chatL.addMouseListener(evt);
	
	
	card = new CardLayout();
	AdminP = new JPanel();
	AdminP.setLayout(card);
	
	AdminP.add("admin", logoL);
	
	
	card.first(AdminP);
	

//	this.add("North", menubar);
	this.add("Center", AdminP);
	
	this.setSize(500, 500);
	this.setVisible(true);
	
	
	}
	
	
	
	class ClickEvent extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int	x = e.getX();
			int	y = e.getY();
			if((x >= 88 && x <= 153) && (y > 100 && y <= 131)) {
				houseMg = new house_management();
			}
			else if((x >= 149 && x <= 240) && (y > 177 && y <= 229)){
				roomMg = new room_management();
			}
			else if((x >= 66 && x <= 126) && (y > 223 && y <= 269)){
				System.out.println("회원관리");
				member = new member_management();
			}
			else if((x >= 122 && x <= 214) && (y > 304 && y <= 372)){
				System.out.println("등록내용관리");
				info = new InfoBorder();
			}
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			int	x = e.getX();
			int	y = e.getY();
			if((x >= 88 && x <= 153) && (y > 100 && y <= 131)) {
				Cursor hourglassCursor = new Cursor(Cursor.HAND_CURSOR);
				logoL.setCursor(hourglassCursor);
			}
			else if((x >= 149 && x <= 240) && (y > 177 && y <= 229)){
				Cursor hourglassCursor = new Cursor(Cursor.HAND_CURSOR);
				logoL.setCursor(hourglassCursor);
			}
			else if((x >= 66 && x <= 126) && (y > 223 && y <= 269)){
				Cursor hourglassCursor = new Cursor(Cursor.HAND_CURSOR);
				logoL.setCursor(hourglassCursor);
			}
			else if((x >= 122 && x <= 214) && (y > 304 && y <= 372)){
				Cursor hourglassCursor = new Cursor(Cursor.HAND_CURSOR);
				logoL.setCursor(hourglassCursor);
			}
			else {
				Cursor hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				logoL.setCursor(hourglassCursor);
			}
			
		}
	}
	
	
	
	
	
	
	
class ButtonEvent implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		
		if(target == houseB){
			
		}
		else if(target == roomB){
			
		}
		else if(target == clientListB){
			member = new member_management();
		}
		else if(target == submitListB){
			info = new InfoBorder();
		}
	}
}
	
	
 class LabelEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e){
			JLabel target = (JLabel)e.getSource();
			
			if(target == main.menubar.homeL){
				main.mainPP.showProc();
				main.card.show(main.cardPanel, "메인화면");
			}
			else if(target == main.menubar.myinfoL){
				main.card.show(main.cardPanel, "내정보");
			}
			else if(target == main.menubar.tableL){
				
				main.card.show(main.cardPanel, "공지사항");
				/*main.mainPP.WriteP.setVisible(false);
				main.mainPP.setVisible(true);*/
			}
			else if(target == main.menubar.chatL){
			
				System.out.println("말이안되지");
				admin = new ChatAdmin();
			}
			else if(target == main.menubar.logoutL){
				
			}
			else if(target == main.menubar.exitL){
				
			}
		}
	}

}


class MyInfo extends JPanel {

	MainBorder main;
	JButton modifyB, deleteB;
	
	JLabel titleL, idL, idCheckL, pwdL, pwdL2, nameL, telL, emailL, addrL;
	JTextField idF, nameF, telF, telF2, telF3, emailF, addrF;
	JPasswordField pwdF, pwdF2;
	Choice telCombo, emailCombo;
	MyJFrame f;
	
	Global g_global = Global.getInstance();
	
	
	public void Write (Serializable obj) 
	{
		
		g_global.serverConnect.write(obj);
	}
	
	public MyInfo(MainBorder m) {
		main = m;
		

		
		// 등록
//		g_global.serverConnect.AddClientRead(reader);
		
		// 삭제 
// 		g_global.serverConnect.RemoveClientRead(reader);
		
		
		f = new MyJFrame();
		ScreenLocation screen = new ScreenLocation();
		screen.setScreen();
		f.setLocation(screen.x,screen.y);
		
		modifyB = new JButton("수정");
		deleteB = new JButton("삭제");
		
		titleL = new JLabel(" * 회 원 정 보 * ");
		idL = new JLabel("ID");
		idCheckL = new JLabel();
		pwdL = new JLabel("PASSWORD");
		pwdL2 = new JLabel("PASSWORD");
		nameL = new JLabel("이름");
		telL = new JLabel("전화번호");
		emailL = new JLabel("이메일");
		addrL = new JLabel("주소");

		idF = new JTextField(10);
		pwdF = new JPasswordField(10);
		pwdF2 = new JPasswordField(10);
		nameF = new JTextField(10);

		telF = new JTextField(3);
		telCombo = new Choice();
		telF2 = new JTextField(3);
		telF3 = new JTextField(3);

		emailCombo = new Choice();
		emailF = new JTextField(5);
		addrF = new JTextField(20);
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(titleL);
		
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idPanel.add(idF);
		
		JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passPanel.add(pwdF);
		
		JPanel passPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passPanel2.add(pwdF2);
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(nameF);

		JPanel labelPanel = new JPanel(new GridLayout(7, 1, 10, 15));
		labelPanel.add(idL);
		labelPanel.add(pwdL);
		labelPanel.add(pwdL2);
		labelPanel.add(nameL);
		labelPanel.add(telL);
		labelPanel.add(emailL);
		labelPanel.add(addrL);

		
		
		
		StarRater starRater = new StarRater(5, 1, 1);
        starRater.addStarListener(
            new StarRater.StarListener()   {

                public void handleSelection(int selection) {
                    System.out.println(selection);
                }
            });
        
    	titlePanel.add(starRater);
	
		JPanel telPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		telCombo.addItem("010");
		telCombo.addItem("011");
		telCombo.addItem("017");
		telCombo.addItem("018");
		telPanel.add(telCombo);
		telPanel.add(new Label("-"));
		telPanel.add(telF2);
		telPanel.add(new Label("-"));
		telPanel.add(telF3);

		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		emailCombo.addItem("naver.com");
		emailCombo.addItem("hanmail.net");
		emailCombo.addItem("nate.com");
		emailPanel.add(emailF);
		emailPanel.add(new Label("@"));
		emailPanel.add(emailCombo);
		
		JPanel addrPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addrPanel.add("West",addrF);
		
		JPanel fieldPanel = new JPanel(new GridLayout(7, 1, 5, 0));

		fieldPanel.add(idPanel);
		fieldPanel.add(passPanel);
		fieldPanel.add(passPanel2);
		fieldPanel.add(namePanel);
		fieldPanel.add(telPanel);
	
		fieldPanel.add(emailPanel);
		fieldPanel.add(addrPanel);

		JPanel idCheckPanel = new JPanel(new GridLayout(7, 1, 20, 15));

		idCheckPanel.add(idCheckL);

		JPanel labelFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

		labelFieldPanel.add("West", labelPanel);
		labelFieldPanel.add("Center", fieldPanel);
		labelFieldPanel.add("East", idCheckPanel);

		ButtonEvent event = new ButtonEvent();
		modifyB.addActionListener(event);
		deleteB.addActionListener(event);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

		buttonPanel.add("West", modifyB);
		buttonPanel.add("East", deleteB);
		
		this.setLayout(new BorderLayout());
		this.add("North", titlePanel);
		this.add("Center", labelFieldPanel);
		this.add("South", buttonPanel);
		
		
		idF.setText("hong");
		pwdF.setText("1234");
		pwdF2.setText("1234");
		nameF.setText("홍길동");
		telCombo.select(0);
		telF2.setText("1234");
		telF3.setText("5678");
		emailF.setText("abc");
		emailCombo.select(0);
		addrF.setText("서울시 구로구 구로동");
		
/*		f.setTitle("회원정보 확인");
		f.add(this);
		f.setSize(500, 500);
		f.setVisible(true);*/
		
	}
	
	public void modifyProc() {
		
		int kind = JOptionPane.showConfirmDialog(f,"수정하시겠습니까","Modify",JOptionPane.YES_NO_OPTION);
		
		if (kind == 0) {
			JOptionPane.showMessageDialog(f, "수정되었습니다");
			
		}
	}
	
	public void deleteProc() {
	
		int kind = JOptionPane.showConfirmDialog(f,"삭제하시겠습니까","Delete",JOptionPane.YES_NO_OPTION);
		
		if (kind == 0) {
			JOptionPane.showMessageDialog(f, "삭제되었습니다");
		}
	}


	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton target = (JButton)e.getSource();
			
			if (target == modifyB) {
				modifyProc();
			} else if(target == deleteB) {
				deleteProc();
			}
					
			
		}
		
	}

}







	
 class MainPanel extends JPanel{
	
	MainBorder main;
	JButton  wrB;
	TextArea area;
	DefaultTableModel model;
	JTable table;
	CardLayout card;
	JPanel WriteP;
	modifyBorder modify;
	String	no;
	public static int g_idx = 0;
	 ResultSet rs;
	 
	 
	 
		ClientConnect connect;
		ClientRead  reader;
		ClientError generalerror;
		ClientError connecterror;
	ClientBase Serverconnect = Global.getInstance().serverConnect;
	public MainPanel(MainBorder m) {
		main = m;
		
		setConnect();
		this.setLayout(new BorderLayout());

		wrB = new JButton("글쓰기");
		
		wrB.addActionListener(new ButtonEvent());
		
		
		
		String[] title = {"No.", "제 목","내용","작성자", "작성일","조회수"};
		
		model = new DefaultTableModel(title, 0){
			
			public boolean isCellEditable(int row, int column){
				if(column >= 0){
					return false;
					
				}
				else{
					return true;
				}
			}
		};
		
		
		TableEvent evt = new TableEvent();
		
		table = new JTable(model);
		table.addMouseListener(evt);
		
		
		table.getColumn("No.").setPreferredWidth(4);
		table.getColumn("작성일").setPreferredWidth(60);
		JScrollPane sp = new JScrollPane(table);
		
		

		
		JPanel CenterP = new JPanel(new FlowLayout());
	
	
		CenterP.add(sp);
		CenterP.add("South",wrB);
		

		
		WriteBorder t4 = new WriteBorder(main);
		card = new CardLayout();
		
		WriteP = new JPanel();
		WriteP.setLayout(card);
		
		WriteP.add("Table" , CenterP);
		WriteP.add("Edit", t4);
	
		
		
		this.add("Center", WriteP);
		
		showProc();
		
	}
	
	
	 public void  ConnectError(Throwable ex)  {      
//	      Serverconnect.stop();      
	      JOptionPane.showMessageDialog(this, ex.toString());
	      
	   }
	   
	   public void  GeneralError(Throwable ex) 
	   {   
	      JOptionPane.showMessageDialog(this, ex.toString());       
	   }   
	   
	   public void ConnectRelease()
	   {
	      
	      if(Serverconnect == null)
	         return;

	      Serverconnect.RemoveClientRead(reader);

	   }
	   
	   
	   // 처리할 함수를 등록한다.
	   public void setConnect()
	   {

	      ClientRead  reader = new ClientRead() {

	         @Override
	         public void run(Object obj) {
	            // TODO Auto-generated method stub
        	  try {
	            Read(obj);
        	  } catch(Exception ex) {
        		  
        		ex.printStackTrace();  
        	  }
        	  
	         }

	      };

	      Serverconnect.AddClientRead(reader);
	      
	   }
	   public void Read(Object obj ) {

    	   System.out.println("list:"+obj);

	      if (obj instanceof Dao.board.ResponseBoard) {
	    	   System.out.println("list:"+obj);
	         ResponseBoard data = (ResponseBoard)obj;
	         ProcessListBoard(data);      

	      }
	   
	   }
	   
	   public void ProcessListBoard(ResponseBoard res) {
		 
		    
	         if (res.message == BoardConstant.DEF_SHOW)
	         {
	        	 RealshowProc(res);
	         }
	   }
		
	
	   public void RealshowProc(ResponseBoard res  )
	   {
		  ;
			clearTable();
			try {
				
		//		res = sql.ShowBoard(null);

				for( int i=0;i<res.data.size();i++ ) {
					
					Object[] data = new Object[6];

					data[0] = res.data.get(i).idx;
					data[1] = res.data.get(i).title;
					data[2] = res.data.get(i).content;
					data[3] = res.data.get(i).admin_id;
					data[4] = res.data.get(i).reg_date;
					data[5] = res.data.get(i).hit;					
					model.addRow(data);
					
				}
			}
				catch(Exception e){}
	   }
	   
	/////////////////////////////////////////////////// 테이블에 보여준다.
		public void showProc(){
			  System.out.println("requsets==> ");
			RequestBoard req=  new  RequestBoard();
			req.message = BoardConstant.DEF_SHOW;
			Write(req);				
			
		}
		
		
		public void clearTable(){
			
			int rows = table.getRowCount();
			for(int i = 0; i < rows; i++){
				model.removeRow(0);
			}
			
		}
		
		public void Write(Serializable obj) {			
			Serverconnect.write(obj);
		}

	
	class TableEvent extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			
	
			int row = table.getSelectedRow();
			
			if(row == -1){
				return;
			}
			
			if(e.getClickCount() == 2){

				modify = new modifyBorder(main);
				
				
				WriteP.add("Modify", modify);
				
				int no2 = (int)table.getValueAt(row,0);
				
				g_idx = no2;
				
				String title = (String)table.getValueAt(row, 1);
				String content = (String)table.getValueAt(row, 2);
				String admin_id = (String)table.getValueAt(row, 3);
				String reg_date = (String)table.getValueAt(row, 4);
				String hit = String.valueOf(table.getValueAt(row, 5));
													
				modify.selectProc(g_idx);
									
				modify.titleF.setText(title);
				modify.area.setText(content);
				
				main.mainPP.card.show(WriteP, "Modify");
						
			}
			
		
			
		}
	}
	
	
	

	

	class ButtonEvent implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			CardLayout card = (CardLayout)(WriteP.getLayout());
			if(target == wrB){
				
				card.show(WriteP , "Edit");
			}
		
				
			
		}
		
	}
	
	
}
