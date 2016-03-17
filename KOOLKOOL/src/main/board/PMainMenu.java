package main.board;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import	javax.swing.border.*;

import Dao.CommonSql;
import Dao.Constant;
import Dao.RequestMember;
import Dao.ResponseMember;
import Dao.TB_USER;
import Dao.board.BoardConstant;
import Dao.board.BoardSql;
import Dao.board.RequestBoard;
import Dao.board.ResponseBoard;
import util.ClientBase;
import util.ClientConnect;
import util.ClientError;
import util.ClientRead;
import util.ScreenLocation;
import util.StarRater;
import main.Chat.ChatUserSelect;
import main.Global;
import main.reserve.*;
import main.board.*;

public class PMainMenu {

	JFrame f;
	JButton reserB, reserokB, myB, reviewB;
	TextField titleF;
	TextArea area;
	PMenuBar pmenubar;
	ReserveWrite reserve;
	ReserveInfo	 info;
	CardLayout card;
	JPanel cardPanel;
	UserMenu UserP;
	PMainPanel pmainPP;
	PMyInfo pinfoP;


	public PMainMenu() {
		f = new JFrame();

		card = new CardLayout();
		cardPanel = new JPanel(card);

		pmenubar = new PMenuBar();
		f.add("North", pmenubar);
		f.add(cardPanel);

		UserP = new UserMenu(this);
		pmainPP = new PMainPanel(this);
		pinfoP = new PMyInfo(this);

		cardPanel.add("메인화면", UserP);
		cardPanel.add("공지사항", pmainPP);
		cardPanel.add("내정보", pinfoP);

		f.setSize(500,620);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new PMainMenu();
	}		
}

class UserMenu extends JPanel{

	PMainMenu main;
	JButton reserB, reserokB, myB, reviewB;
	TextField titleF;
	TextArea area;
	JPanel UserP;
	ReserveWrite reserve;
	ReserveInfo    info;
	CardLayout card;
	JLabel logoL;
	ImageIcon	pbgimg;
	ChatUserSelect UserChat;
	public Global g_global = Global.getInstance();



	public UserMenu(PMainMenu m) {

		main = m;


		this.setLayout(new BorderLayout());


		pbgimg = new ImageIcon("resources/admin/Pbgimg.jpg");


		logoL = new JLabel(pbgimg);

		logoL.addMouseListener(new ClickEvent());
		logoL.addMouseMotionListener(new ClickEvent());


		LabelEvent evt1 = new LabelEvent();

		main.pmenubar.tableL.addMouseListener(evt1);
		main.pmenubar.homeL.addMouseListener(evt1);
		main.pmenubar.myinfoL.addMouseListener(evt1);
		main.pmenubar.chatL.addMouseListener(evt1);

		card = new CardLayout();
		UserP = new JPanel();
		UserP.setLayout(card);

		UserP.add("user", logoL);

		card.first(UserP);


		this.add("Center", UserP);

		this.setSize(500,500);
		this.setVisible(true);

	}


	class ClickEvent extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int	x = e.getX();
			int	y = e.getY();

			if((x >= 76 && x <= 213) && (y > 240 && y <= 350)){
				System.out.println("예약");
				reserve = new ReserveWrite();
			}

			else if((x >= 251 && x <= 378) && (y > 165 && y <= 271)){
				System.out.println("REVIEW");
			}
			else if((x >= 432 && x <= 514) && (y > 247 && y <= 354)){
				System.out.println("예약하기");
				info = new ReserveInfo();
			}

		}
		@Override
		public void mouseMoved(MouseEvent e) {
			int	x = e.getX();
			int	y = e.getY();

			if((x >= 76 && x <= 213) && (y > 240 && y <= 350)){
				Cursor hourglassCursor = new Cursor(Cursor.HAND_CURSOR);
				logoL.setCursor(hourglassCursor);

			}
			else if((x >= 251 && x <= 378) && (y > 165 && y <= 271)){
				Cursor hourglassCursor = new Cursor(Cursor.HAND_CURSOR);
				logoL.setCursor(hourglassCursor);

			}
			else if((x >= 432 && x <= 514) && (y > 247 && y <= 354)){
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

			if(target == reserB){
				reserve = new ReserveWrite();

			}
			else if(target == reserokB){
				info = new ReserveInfo();
			}
			else if(target == myB){

			}
			else if(target == reviewB){

			}
		}
	}

	class LabelEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e){
			JLabel target = (JLabel)e.getSource();

			if(target == main.pmenubar.homeL){

				main.card.show(main.cardPanel, "메인화면");
				main.pinfoP.removeField();
			}
			else if(target == main.pmenubar.myinfoL){
				main.pinfoP.showUserProc();
				main.card.show(main.cardPanel, "내정보");

			}
			else if(target == main.pmenubar.tableL){
				main.card.show(main.cardPanel, "공지사항");

			}
			else if(target == main.pmenubar.chatL){
				UserChat = new ChatUserSelect();
			}
			else if(target == main.pmenubar.logoutL){

			}
			else if(target == main.pmenubar.exitL){

			}
		}
	}
}


class PMyInfo extends JPanel {

	JFrame f;
	PMainMenu main;
	JButton modifyB, deleteB;

	JLabel titleL, idL, idCheckL, pwdL, pwdL2, nameL, telL, emailL, addrL;
	JTextField idF, nameF, telF, telF2, telF3, emailF, addrF;
	JPasswordField pwdF, pwdF2;
	Choice telCombo, emailCombo;
	CommonSql sql;
	ResponseMember res;
	RequestMember request;
	Global g_global = Global.getInstance();

	public int g_userIdx = 0;
	public String g_userName = "";

	ClientRead reader ;

	public void Write (Serializable obj) 
	{

		g_global.serverConnect.write(obj);
	}

	public PMyInfo(PMainMenu m) {
		main = m;

		reader = new ClientRead() {
			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub
				if (obj instanceof ResponseMember) {
					ResponseMember res = (ResponseMember)obj;
					ResponseProcess(res);
				}
			}  
		};

		g_global.serverConnect.AddClientRead(reader);

		f = new JFrame();
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

		g_userIdx = g_global.getIdx();
		g_userName = g_global.getUsername();
	}



	public void showUserProc() {

		sql = new CommonSql();
		res = new ResponseMember();

		RequestMember request = new RequestMember();

		request.data = new Dao.TB_USER();		
		request.message = Constant.DEF_SINGLE_LIST;
		request.data.idx = g_userIdx;

		Write(request);
	}
	/*TB_USER temp = new TB_USER();

		temp.idx = g_userIdx;

		res = sql.selectUser(temp);

		String id = res.data.get(0).user_id;
		String name = res.data.get(0).user_name;
		String password = res.data.get(0).user_pwd;
		String tel = res.data.get(0).user_tel;
		String email = res.data.get(0).user_email;
		String address = res.data.get(0).user_address;

		String[] telArr = tel.split("-");
		String[] emailArr = email.split("@");

		idF.setText(id);
		idF.setEditable(false);
		nameF.setText(name);
		telCombo.select(telArr[0]);
		telF2.setText(telArr[1]);
		telF3.setText(telArr[2]);
		emailF.setText(emailArr[0]);
		emailCombo.select(emailArr[1]);
		addrF.setText(address);*/

	public void ResponseProcess(ResponseMember res) 
	{
		System.out.println(res.message);
		if (res.message == Constant.DEF_UPDATE ) {

			RealModifyUser(res);
		}

		if (res.message == Constant.DEF_SINGLE_LIST) { 

			if (res.result == true) {

				System.out.println("afafaeaaefaea");

				String id = res.data.get(0).user_id;
				String name = res.data.get(0).user_name;
				String password = res.data.get(0).user_pwd;
				String tel = res.data.get(0).user_tel;
				String email = res.data.get(0).user_email;
				String address = res.data.get(0).user_address;

				String[] telArr = tel.split("-");
				String[] emailArr = email.split("@");

				System.out.println(res.data.get(0).user_address);

				idF.setText(id);
				idF.setEditable(false);
				nameF.setText(name);
				telCombo.select(telArr[0]);
				telF2.setText(telArr[1]);
				telF3.setText(telArr[2]);
				emailF.setText(emailArr[0]);
				emailCombo.select(emailArr[1]);
				addrF.setText(address);

			} else {
				System.out.println(res.message);
				System.out.println(res.reason);
			}
		}
	}

	public void removeField() {

		idF.setText("");
		pwdF.setText("");
		pwdF2.setText("");
		nameF.setText("");
		telCombo.select("");
		telF2.setText("");
		telF3.setText("");
		emailF.setText("");
		emailCombo.select("");
		addrF.setText("");
	}


	public void RealModifyUser(ResponseMember res )
	{

		if (res.result == true) {
			JOptionPane.showMessageDialog(f, "수정되었습니다");
		} else {
			System.out.println("error:"+ res.reason);
		}

	}

	public void modifyUser() {

		request = new RequestMember();

		request.data = new TB_USER();

		request.data.user_pwd = new String(pwdF.getPassword());
		request.data.user_name = nameF.getText().trim();
		request.data.user_tel = telCombo.getSelectedItem() + "-" + telF2.getText().trim() + "-" + telF3.getText();
		request.data.user_email = emailF.getText().trim() + "@" + emailCombo.getSelectedItem();
		request.data.user_address = addrF.getText().trim();
		request.data.idx = g_userIdx;

		Write(request);
		//		CommonSql sql = new CommonSql();
		//		ResponseMember res = sql.UpdateMember(request.data);

	}

	public void modifyProc() {

		int kind = JOptionPane.showConfirmDialog(f,"수정하시겠습니까","Modify",JOptionPane.YES_NO_OPTION);

		if (kind == 0) {

			modifyUser();

		}
	}

	public void deleteProc() {

		int kind = JOptionPane.showConfirmDialog(f,"삭제하시겠습니까","Delete",JOptionPane.YES_NO_OPTION);

		if (kind == 0) {
			//	JOptionPane.showMessageDialog(f, "삭제되었습니다");
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


class PMainPanel extends JPanel{

	PMainMenu main;
	JButton  wrB;
	TextArea area;
	DefaultTableModel model;
	JTable table;
	CardLayout card;
	JPanel WriteP;
	PmodifyBorder pmodify;
	String	no;
	public static int g_idx = 0;
	ResultSet rs;



	ClientConnect connect;
	ClientRead  reader;
	ClientError generalerror;
	ClientError connecterror;
	ClientBase Serverconnect = Global.getInstance().serverConnect;
	public void Write (Serializable obj) 
	{

		Serverconnect.write(obj);
	}


	public PMainPanel(PMainMenu m) {
		main = m;


		setConnect();
		this.setLayout(new BorderLayout());


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



		PWriteBorder t4 = new PWriteBorder(main);
		card = new CardLayout();

		WriteP = new JPanel();
		WriteP.setLayout(card);

		WriteP.add("Table" , CenterP);
		WriteP.add("Edit", t4);



		this.add("Center", WriteP);

		showProc();

	}


	public void  ConnectError(Throwable ex)  {      
		//      Serverconnect.stop();      
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

		  reader = new ClientRead() {

			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub
               try {
				Read(obj);
               } catch (Exception ex) {
            	   
            	   ex.printStackTrace();
            	   
               }
			}

		};

		Serverconnect.AddClientRead(reader);

	}
	public void Read(Serializable obj ) {

	

		if (obj instanceof Dao.board.ResponseBoard) {

			ResponseBoard data = (ResponseBoard)obj;


				Process(data);            


		}

	}

	public void Process(ResponseBoard res) {
		if (res.message == BoardConstant.DEF_SHOW)
		{
			RealShowProc(res);            
		}

	}


	public void RealShowProc(ResponseBoard res)
	{
		
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


	/////////////////////////////////////////////////// 테이블에 보여준다.
	public void showProc(){

		RequestBoard req = new  RequestBoard();
		
		req.message = BoardConstant.DEF_SHOW;
		Write(req);
		/*
		BoardSql sql = new BoardSql();

		ResponseBoard res = new ResponseBoard();


		try {
			res = sql.ShowBoard(null);


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
       */
	}


	public void clearTable(){

		int rows = table.getRowCount();
		for(int i = 0; i < rows; i++){
			model.removeRow(0);
		}

	}



	class TableEvent extends MouseAdapter{

		public void mouseClicked(MouseEvent e){


			int row = table.getSelectedRow();

			if(row == -1){
				return;
			}

			if(e.getClickCount() == 2){

				pmodify = new PmodifyBorder(main);


				WriteP.add("Modify", pmodify);

				int no2 = (int)table.getValueAt(row,0);

				g_idx = no2;

				String title = (String)table.getValueAt(row, 1);
				String content = (String)table.getValueAt(row, 2);
				String admin_id = (String)table.getValueAt(row, 3);
				String reg_date = (String)table.getValueAt(row, 4);
				String hit = String.valueOf(table.getValueAt(row, 5));




				pmodify.selectProc(g_idx);


				pmodify.titleF.setText(title);
				pmodify.area.setText(content);

				main.pmainPP.card.show(WriteP, "Modify");



			}

		}

	}

}





