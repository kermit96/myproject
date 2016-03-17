package main.review;
import	java.awt.*;
import	java.awt.event.*;
import java.io.Serializable;

import	javax.swing.*;
import	javax.swing.table.*;

import main.Global;
import main.review.ReviewList.ReviewPanel;
import main.review.ReviewWrite.ComboEvent;
import Dao.review.RequestReview;
import Dao.review.RequestTbHouse;
import Dao.review.ResponseReview;
import Dao.review.ResponseTbHouse;
import Dao.review.ReviewSql;
import Dao.review.TB_HOUSE;
import Dao.review.TB_REVIEW;
import util.ClientRead;
import util.JDBCDao;
import util.StarRater;

import	java.sql.*;
import java.util.Date;
import java.util.Vector;

/**
 * <pre>
 * main
 * ReviewModify.java
 * </pre>
 * @author ����ȣ
 * @Date 2015. 11. 30
 * 
 * @category Review ���� 
 */

	public class ReviewModify extends JPanel  {
	private JTable	table;
	private DefaultTableModel	model;
	
	JDBCDao				db;
	Connection			con;
	//	Statement
	PreparedStatement	allS, nameS, titleS, dateS,avgS, modifyS, deleteS,addS;
	ResultSet			rs;
	
	JPanel		mainP;
	CardLayout	card;
	
	//
	//2015.12.06 sql test
	//ReviewSql sql;
	//ResponseReview res;
	//ResponseTbHouse house_res;
	//private DefaultTableModel	model;
	JPanel	southP,centerP,centerP1,centerP2,topP,topP1,topP2,botP ;
	JButton logoB,homeB,infoB,exitB,listB,modifyB,removeB;
	JTextArea textA;
	JComboBox combo;//2015.12.08 �߰�
	JLabel noL,revL,titleL,nameL,dateL,avgL,contL,Jhouse,topLabel;
	JTextField titleF,avgF,noF,nameF,dateF;	
	JScrollPane sp ; 
	ReviewList	main;	
	
	Global g_global = Global.getInstance();
	ClientRead reader;
	
	StarRater starRater;
	int Review_star = 0;
	
	ReviewPanel	firstP;
	ReviewWrite	secondP;
	ReviewModify thirdP;
	MemModify	fourthP;
	//2015.12.06 sql test
	ReviewSql sql;
	ResponseReview res;
	
	private  void Write(Serializable obj) {			
		g_global.serverConnect.write(obj);
	}
	public ReviewModify(ReviewList m) {
reader = new ClientRead( ) {
			
			@Override
			public void run(Serializable obj) {
				
				System.out.println("obj="+obj);
				
				if(obj instanceof Dao.review.ResponseReview  ){
					Dao.review.ResponseReview data = (Dao.review.ResponseReview)obj;
					System.out.println("readListProc~~");
					switch(data.message){
						case Dao.review.R_Constant.DEF_READ_BASIC_LIST: 
							//TB_REVIEW ���̺��� idx,house_idx,title,star �� ������
							readListProc(data);
							System.out.println("readListProc~1");
							break;
						case Dao.review.R_Constant.DEF_READ_CONTENT_LIST: 
							//TB_REVIEW ���̺��� content ���� �Ͼ��
							readConProc(data.data);
							System.out.println("readConProc~2");
							//flag_con = true;							
							break;
						case Dao.review.R_Constant.DEF_INSERT_REVIEW: 
							System.out.println("insert test");					
							break;
						case Dao.review.R_Constant.DEF_UPDATE_REVIEW: 
							System.out.println("update test11");
							main.allProc();
							JOptionPane.showMessageDialog(main.mainP, "���� �Ϸ� �Ǿ����ϴ�");
							topLabel.setText("REVIEW �� ����");
							modifyB.setText("����");
							
							//��� �ؽ�Ʈ  ��Ȱ��ȭ��Ŵ,�޺� �ڽ� ���� ���ϰ� ��
							titleF.setEditable(false);
							nameF.setEditable(false);
							dateF.setEditable(false);
							//avgF.setEditable(false);
							starRater.setEnabled(false);
							textA.setEditable(false);
							combo.setEditable(false);
							
							
							combo.removeAllItems();
							System.out.println("modify �Ϸ�");
							
							main.card.show(main.mainP, "MAIN");
							break;
						case Dao.review.R_Constant.DEF_DELETE_REVIEW: 
							System.out.println("delete test");
							break;		
							
						default:
					}
				}
				
				 if(obj instanceof Dao.review.ResponseTbHouse   ){
					Dao.review.ResponseTbHouse data = (Dao.review.ResponseTbHouse)obj;
					//System.out.println("readNameProc~~11");
					//System.out.println("selName= " +selName);
					if(data.message ==Dao.review.R_Constant.DEF_READ_HOUSENAME_LIST){
												
						//if(selName == 1){ // REG ��ư ������ �� �� house name,���� �ð�,��,�̸��� ReviewWrite card�� ����
						//	if(readNameProc(data.data) == true){
						//		System.out.println("readNameProc(data.data)");
						//	}
						//}
						//���콺 ���� Ŭ������ �� ��ϵ� House_itx �� �ش��ϴ� house name,���� �ð�,��,�̸��� ReviewWrite card�� ����
						//content ������  ReviewWrite card�� ����
						   { 
												
							//readNameProc(data.data,House_itx);
							//selName = 1;
							System.out.println("readNameProc(data.data,House_itx)00 �Ϸ�");
														
							//������ ����Ʈ ���� ���� ȭ������ ���� ����
							int	row = table.getSelectedRow();
							if(row == -1) {
								return;
							}
							int no = (int)table.getValueAt(row, 0);
							ReviewList.Review_itx = no;//���̺��� ��� ��ȣ�� ������
							int house_itx = (int)table.getValueAt(row, 1);
							//db���� tb_house ���̺��� idx,name ���� ��û��
							ReviewList.House_itx = house_itx;
							//System.out.println("house_itx="+ house_itx);
							String  name = (String) table.getValueAt(row, 2);//���̺��� name ������
							String	title= (String) table.getValueAt(row, 3);//���̺��� title ������
							
							//System.out.println("thirdP.nameF ^^");
							//thirdP.nameF.setText(name);
							//thirdP.titleF.setText(title);
							//thirdP.textA.setText(content);
							//thirdP.dateF.setText(date.toString());
							//thirdP.avgF.setText(String.valueOf(star));
							//thirdP.starRater.setSelection(ReviewWrite.Review_star);
							//System.out.println("ReviewWrite.Review_star=" + ReviewWrite.Review_star);
							//System.out.println("readNameProc12");
							//card.show(mainP, "SUB2");	
							//rflag = false;
						}						
							
					}
					else {
						System.out.println("DEF_READ_HOUSENAME_LIST ������ �����ϴ�");
					}
						
					
				}
				
			}
		};
			
		g_global.serverConnect.AddClientRead(reader);	
		/*		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				g_global.serverConnect.RemoveClientRead(reader);
			}
		});
		*/
		
		main = m;
		setField();
		ReadReview();
		//System.out.println("test 01-1");
		sp = setTable();
		setEvent();
		//System.out.println("test 01-2");
		allProc();
		//System.out.println("test 01-3");
	}	
	
	private void ReadReview(){
		
		this.setLayout(new BorderLayout());
		
		/*
		JPanel topP = new JPanel(new GridLayout(2,1));//dummy
		topP1 = new JPanel();
		topP1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		topLabel = new JLabel("REVIEW �� ����");		
		topP1.add(topLabel);
		topP.add(topP1);
		*/
		topP = new JPanel();
		topP.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		topLabel = new JLabel("REVIEW �� ����");		
		topP.add(topLabel);
		//topP.add(topP1);
		
		//topP.setBackground(Color.CYAN);
		//topP1.setBackground(Color.CYAN);
				
		botP = new JPanel(new BorderLayout(10,10));
		JPanel botP1 = new JPanel(new FlowLayout());
		
		listB = new JButton("��� ");	
		modifyB = new JButton("���� ");
		//modifyB.setEnabled(false);//��ư ��Ȱ��ȭ
		removeB = new JButton("���� ");	
		//removeB.setEnabled(false);//��ư ��Ȱ��ȭ
		botP1.add(listB);			
		botP1.add(modifyB);			
		botP1.add(removeB);		
		botP.add("South",botP1);
	
		textA = new JTextArea("�׽�Ʈ",70,0);
		textA.setEditable(false);
		sp = new JScrollPane(textA);
		
		centerP = new JPanel(new GridLayout(3,1));
	
		//revL = new JLabel("Review ����");
		titleL = new JLabel(" ����");
		nameL = new JLabel(" �ۼ���");  //2015.12.01 �߰�
		dateL = new JLabel(" �ۼ����� ");//2015.12.01 �߰�
		avgL = new JLabel(" ����");
		contL = new JLabel("����");
									
		titleF = new JTextField(17);
		titleF.setEditable(false);
		nameF = new JTextField(8);
		nameF.setEditable(false);
		//dateF = new JTextField(6);
		
		
		dateF.setEditable(false);
		avgF = new JTextField(5);
		avgF.setEditable(false);
		
		//JPanel cP1 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,10));
		JPanel clP1 = new JPanel(new FlowLayout(FlowLayout.LEFT,31,15));//�󺧰� �ʵ� ���� ���� ���� ����
		JPanel clP11 = new JPanel(new FlowLayout(FlowLayout.LEFT,26,2));//�󺧰� �ʵ� ���� ���� ���� ����
		JPanel clP2 = new JPanel(new FlowLayout(FlowLayout.LEFT,25,12));//�󺧰� �ʵ� ���� ���� ���� ����
		JPanel clP3 = new JPanel(new FlowLayout(FlowLayout.LEFT,17,12));//�󺧰� �ʵ� ���� ���� ���� ����
		JPanel clP4 = new JPanel(new FlowLayout(FlowLayout.LEFT,31,12));//�󺧰� �ʵ� ���� ���� ���� ����				
			
		clP1.add(titleL);
		clP1.add(titleF);
		
		combo = new JComboBox();
		JLabel Jhouse= new JLabel("�漱��",JLabel.CENTER);
		
		clP11.add(Jhouse);
		clP11.add(combo);
		
		clP2.add(nameL);
		clP2.add(nameF);
		
		clP3.add(dateL);
		clP3.add(dateF);
		
		clP4.add(avgL);
		//�� ������ ���� ǥ��
		
		starRater = new StarRater(5, 1, 1);
	    starRater.addStarListener(
			   new StarRater.StarListener(){
			          public void handleSelection(int selection) {
		                  //System.out.println(selection);
		                  ReviewWrite.Review_star = selection;
		                  
			              //System.out.println("�� ����"+ ReviewWrite.Review_star);
			              }
			           });
			    			  
	    starRater.setSelection(ReviewWrite.Review_star);
		clP4.add(starRater);
		
		//clP4.add(avgF);		
		
		JPanel cPP1 = new JPanel(new GridLayout(5,1));
		cPP1.add(clP1);
		cPP1.add(clP11);
		cPP1.add(clP2);
		cPP1.add(clP3);
		cPP1.add(clP4);		
	
		JPanel jp1 = new JPanel(new GridLayout(8,1));
		
		
		
		JPanel cP = new JPanel(new BorderLayout());
		JPanel jpp1 = new JPanel(new BorderLayout());
		JPanel jpp2 = new JPanel(new FlowLayout());
		
		cP.add("North",jpp1);
		cP.add("West",cPP1);//dummy
		cP.add("East",jpp2);//dummy
		
		
		JPanel cPPP = new JPanel(new BorderLayout(20,10));
		cPPP.add("Center",cP);
		
		
		JPanel cb1P = new JPanel(new BorderLayout(36,30));//���� �󺧰��� ���� ����
		JPanel conP = new JPanel(new BorderLayout(29,10));
		conP.add("East",contL);
		cb1P.add("West",conP);
		cb1P.add("Center",sp);
		
		JPanel cPP = new JPanel(new BorderLayout(160,1));
		JPanel cPP_dummy = new JPanel(new FlowLayout());
		cPP.add("North",cPPP); //�󺧰� �ʵ� ���� ��ġ
		cPP.add("Center",cb1P);//�ؽ�Ʈ ���� ��ġ
		cPP.add("East",cPP_dummy);
		
				
		JPanel dummyP = new JPanel(new GridLayout(1,1));
		
		this.add("North",topP);
		this.add("Center",cPP);
		this.add("East",dummyP);	
		this.add("South",botP);		
		
		this.setSize(600, 600);
		this.setVisible(true);
	}
		
	public void setField() {
		noL = new JLabel("��ȣ", JLabel.RIGHT);
		nameL = new JLabel("�ۼ���", JLabel.RIGHT);
		titleL = new JLabel("����", JLabel.RIGHT);
		dateL = new JLabel("�ۼ� ��¥", JLabel.RIGHT);
		avgL = new JLabel("����", JLabel.RIGHT);
				
		noF = new JTextField(20);
		noF.setEditable(false);//���� ���ϰ� ����
		nameF = new JTextField(10);
		titleF = new JTextField(10);
		dateF = new JTextField(10);
		avgF = new JTextField(10);
			
		
		JPanel	p1 = new JPanel(new GridLayout(5, 1));
		p1.add(noL);
		p1.add(nameL);
		p1.add(titleL);
		p1.add(dateL);
		p1.add(avgL);
		
		JPanel	p2 = new JPanel(new GridLayout(6, 1));
		JPanel	p21 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(noF);
		p2.add(nameF);
		p2.add(titleF);
		p2.add(dateF);
		p2.add(avgF);
		p2.add(p21);
		
		JPanel	p3 = new JPanel(new GridLayout(2,1));
		p3.add("West", p1);
		p3.add("Center", p2);
		//p3.add("East",jp1);
		JPanel	p4 = new JPanel(new BorderLayout());
		p4.add("North", p3);		
		
		//return p4;
	}
	
	public void clearField() {
		noF.setText("");//
		nameF.setText("");//
		titleF.setText("");//
		dateF.setText("");//
		avgF.setText("");//
		
	}
//	
	public void clearTable() {
		int	rows = table.getRowCount();
		for(int i = 0; i < rows; i++) {
			model.removeRow(0);
		}
	}
	
	private void addProc() {
		
			//����
			//�Է»����� ���� �˾Ƽ�
			String	name = nameF.getText().trim();
			String	title = titleF.getText().trim();
			String	date = dateF.getText().trim();
			String	avg = avgF.getText().trim();
			String	commend = textA.getText().trim();
			
			int temp = ReviewList.Review_itx;
			//System.out.println("addProc() temp " + temp);		
			//	�����ͺ��̽��� �Է�����.
			//	�Է��� �� ���� addS�� �̿��ϸ� �ȴ�.
			try {
				addS.setString(1, name);
				addS.setString(2, title);
				addS.setString(3, date);
				addS.setString(4, avg);
				addS.setString(5, commend);
				
				addS.execute();
			}
			catch(Exception e) {
				System.out.println(e);
			}
			//System.out.println("test01");
			//clearField();
			//allProc();
			//System.out.println("test02");
	}
	public void modifyProc() {
		//	����
		TB_REVIEW temp = new TB_REVIEW();		
		RequestReview req = new RequestReview();
		//temp.house_idx = ReviewList.House_itx;
		temp.house_idx = ReviewWrite.House_ittx;
		//System.out.println("House_itx ="+ReviewList.House_itx);
		temp.title = titleF.getText().trim();		
		temp.content = textA.getText().trim();
		//temp.date =  dateF.getText().trim();
		//temp.star = Integer.parseInt(avgF.getText().trim());
		temp.star = ReviewWrite.Review_star;
		temp.idx = ReviewList.Review_itx;
		//System.out.println("itx"+ReviewList.House_itx);
		req.data = temp;
		req.message = Dao.review.R_Constant.DEF_UPDATE_REVIEW;
		Write(req);
		System.out.println("������Ʈ ��û ��");
		
		//TB_REVIEW ���� �ű� ���� �� ���		
		//allProc();	
	}
	/*
	public void allProc() {
		
		
		TB_REVIEW temp = new TB_REVIEW();
		ResponseReview req = new ResponseReview();	
		req.message = Dao.review.R_Constant.DEF_READ_BASIC_LIST;
		
			
		
		sql = new ReviewSql();
		res = new ResponseReview();
		
		res = sql.ReadReview(temp);
	
		
		clearTable();//
	
		//1.TB_REVIEW�� �⺻��(idx,house_idx,title,user_name,star) �б�
		//System.out.println("vector size" + res.data.size());
		for(int i=0;i<res.data.size();i++) {
		
			Object[] test = new Object[6];
		    test[0] = res.data.get(i).idx;	//��ȣ		//	
		    test[1] = res.data.get(i).house_idx;//.house_idx;	//	
		    test[2] = res.data.get(i).user_name;//.title;  		//  
		    test[3] = res.data.get(i).title;//.user_name;  		//  
		    test[4] = res.data.get(i).reg_date;//.star;  		// 
		    test[5] = res.data.get(i).star;//.star;  		//  
		    ReviewList.date = (Date)res.data.get(i).reg_date;//static �� ����
		    model.addRow(test);	
			    
		}
		//System.out.println("test 01");
		//clearField();
	}*/
	public void allProc() {
		//TB_REVIEW temp = new TB_REVIEW();
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_BASIC_LIST;
		Write(req);
		System.out.println("ReviewModify allProc()");
		//secondP.combo.removeAllItems();
		//thirdP.combo.removeAllItems();
	}
	
	//JTable�� DB�� ���� �о� ������
	private void readListProc(ResponseReview r){
		sql = new ReviewSql();
		res = new ResponseReview();		
		res = r;
		
		clearTable();//
		
		try {
			//1.TB_REVIEW�� �⺻��(idx,house_idx,title,user_name,star) �б�
			//System.out.println("vector size" + res.data.size());
			for(int i=0;i<res.data.size();i++) {
			
				Object[] test = new Object[6];
			    test[0] = res.data.get(i).idx;	//��ȣ		//	
			    test[1] = res.data.get(i).house_idx;//.house_idx;	//	
			    test[2] = res.data.get(i).user_name;//.title;  		//  
			    test[3] = res.data.get(i).title;//.user_name;  		//  
			    test[4] = res.data.get(i).reg_date;//.star;  		// 
			    test[5] = res.data.get(i).star;//.star;  		//  
			    Date date = (Date)res.data.get(i).reg_date;
			    model.addRow(test);	
		
			}
		}
		catch(Exception ex){		    
			System.out.println("readListProc() error");
			ex.printStackTrace();	
		}		
		
	}
	//contentProc �Լ��� ��û �� ������ ���� ���� content ���� �о� ó����.
	private void readConProc(Vector<TB_REVIEW> r){
		Vector <TB_REVIEW> res = new Vector<TB_REVIEW>();
		res = r;
		try{
			String str = (String)res.get(0).content;	//��ȣ		
			textA.setText(str);// ���䳻�� ���� ȭ������ ������.
			//System.out.println("content ���� ó����0");
		}			
		catch(Exception ex){
			System.out.println("readConProc error");
			ex.printStackTrace();	
		}
		//System.out.println("content ���� ó����1");
				
		//clearTable();	
	}
	//content ���� �޶�� ������ ��û��.
	private void contentProc(int idx) {
			
		TB_REVIEW temp = new TB_REVIEW();
		//sql = new ReviewSql();
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_CONTENT_LIST;
		req.data.idx = idx;			
		Write(req);
		//System.out.println("content ��û��");
	}
	//���� Ŭ�� ���� �� ,���̺��� ������ ���� 
	private void readNameProc(Vector<TB_HOUSE> r,int idx) {
		int hidx = idx;
		//System.out.println("readNameProc11");
		//���콺�� ������ ���� �˾Ƴ���.	
		int	row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		int no = (int)table.getValueAt(row, 0);
		ReviewList.Review_itx = no;//���̺��� ��� ��ȣ�� ������
		int house_itx = (int)table.getValueAt(row, 1);
		//db���� tb_house ���̺��� idx,name ���� ��û��
		ReviewList.House_itx = house_itx;
		//System.out.println("house_itx="+ house_itx);
		String  name = (String) table.getValueAt(row, 2);//���̺��� name ������
		String	title= (String) table.getValueAt(row, 3);//���̺��� title ������
		Date  date =  (Date)table.getValueAt(row, 4);    //���̺��� reg_date ������
		int	star = (int) table.getValueAt(row, 5);		 //���̺��� star ������
		ReviewWrite.Review_star = star;
		
		//���̺��� idx���� ��ġ�ϴ� tb_house�� �̸����� �޺� �ڽ��� ���縦 ��
		//Vector <String> name = new Vector<String>();	
		boolean house_flag = false;
		for(int i=0;i<r.size();i++){
			int iddx =r.get(i).idx;
			if(iddx == ReviewList.House_itx)
		    {
				String str = r.get(i).name;
				combo.addItem(str);
				//System.out.println("DB_itx OK="+ ReviewList.House_itx);
				house_flag = true;
				break;
		    }				
			
		}		
		if(house_flag==false){
			System.out.println("��ġ�ϴ� house_name�� �����ϴ�.");
		}
		//DB���� �� ������ ��û��				
		contentProc(ReviewList.Review_itx);	
		
	}
			
	public void getNameProc() {	
		
		clearTable();		
		RequestTbHouse req = new RequestTbHouse();	
		req.message = Dao.review.R_Constant.DEF_READ_HOUSENAME_LIST;		
		Write(req);
		//System.out.println("getNameProc()1");
		
	}
	
	//���� ���� ���� ���� �Լ�
	public void deleteProc() {
			//		����
			TB_REVIEW temp = new TB_REVIEW();		
			RequestReview req = new RequestReview();				
			temp.idx = ReviewList.Review_itx;//Integer.parseInt("10");
			//System.out.println("itx"+ReviewList.Review_itx);
			req.message = Dao.review.R_Constant.DEF_DELETE_REVIEW;
			req.data = temp;
			Write(req);
			
	}
	//
	public JScrollPane setTable() {
		//private JTable	table;
		String[] title = {"��ȣ","���ȣ", "�ۼ���", "����", "����","�ۼ���" };		
		model = new DefaultTableModel(title, 0) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};		
				
		table = new JTable(model);		
		JScrollPane sp1 = new JScrollPane(table);		
		return sp1;
	}
	private void setEvent(){
		ButtonEvent evt = new ButtonEvent();		
		listB.addActionListener(evt);
		modifyB.addActionListener(evt);
		removeB.addActionListener(evt);
		ComboEvent evt2 = new ComboEvent();
		combo.addActionListener(evt2);
	}
	class ComboEvent implements ActionListener{
		public void actionPerformed(ActionEvent e) 
	    {
	        if(e.getSource() == combo)
	        {
	            //�׸��� ��ȯ���� ������Ʈ�� ���������� ��ȯ�Ǵ� String���� ����ȯ ����� �մϴ�
	            String str = (String)combo.getSelectedItem();
	            System.out.println("TT �� �̸�" + str);
	            int index = combo.getSelectedIndex();
	            ReviewWrite.House_ittx = index + 1;//house_itx�� 1������ �����ϴϱ� 1�� ������
	            System.out.println("TT index =" + ReviewWrite.House_ittx );
	           
	        }
	    }
	}
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton	target = (JButton) e.getSource();
			
			if(target == listB) {
				//System.out.println("listB");
				topLabel.setText("REVIEW �� ����");
				modifyB.setText("����");
				//���� ��ư���� ������� ���������ٰ� ��� ������ �ʵ� �� ��Ȱ��ȭ��
				titleF.setEditable(false);
				nameF.setEditable(false);
				dateF.setEditable(false);
				avgF.setEditable(false);
				//starRater.setSelection(ReviewWrite.Review_star);
				starRater.setSelection(0);//����Ʈ 0���� ǥ��
				//System.out.println("Star ="+ReviewWrite.Review_star);
				textA.setEditable(false);				
				main.clearField();
				main.allProc();//Review list�� ��ȯ�� ���̺� �ٽ� ���� ��
				combo.removeAllItems();///Review list�� ��ȯ�� �޺� �ڽ� �׸� ����
				//secondP.combo.removeAllItems();
				//Review_star = 0;//��� ȭ�� ���� ��� ��ư ���� ��� �ʱⰪ���� ���� 
				main.card.show(main.mainP, "MAIN");				
			}	
			else if(target == modifyB) {
				//�ۼ��� �ڽ��� ���� ������ ���� ��ư�� ���� ��ư�� Ȱ��ȭ�ؾ� ��
				//�ٸ� ����� ���� ����� ���� ��ư�� ���� ��ư�� ��Ȱ��ȭ�ؾ� ��
				//System.out.println("modifyB");				
				//Reviewwrite ī�忡 ���� "REVIEW �� ����"�� ������ ��ϵǾ� �ֱ� ������ �̸� �̿���.
				//System.out.println(ReviewList.user_name);
				//System.out.println(nameF.getText());
										
				if(topLabel.getText() =="REVIEW �� ����"){
					//ó������ �̸���,��� ��¥�� ��Ȱ��ȭ��Ŵ
					titleF.setEditable(true);
					nameF.setEditable(false);
					dateF.setEditable(false);
					//avgF.setEditable(true);
					starRater.setEnabled(true);
					textA.setEditable(true);
					combo.setEditable(false);//�޺� �ڽ� ���� ���ϰ� ��
					
					//����ȭ�鿡�� ��ư�� �������� ���� ȭ������ ��ȯ
					topLabel.setText("Review �� ����");
					modifyB.setText("����");
					
					//���� ��ư�� �������� db���� tb_house�� name ���� �ҷ��ͼ� �޺� �ڽ��� �������
					//combo.removeAllItems();
					/*
					ResponseTbHouse ret = main.getNameProc();						
					//tb_house���� name ���� ���� ��
					for(int i=0;i<ret.data.size();i++){
						//int idx =ret.data.get(i).idx;
						//System.out.println("no="+ idx);
						//ReviewWrite.House_ittx = idx;
						//System.out.println("no1="+ ReviewWrite.House_ittx);
						String ssname =ret.data.get(i).name;
						combo.addItem(ssname);
						//System.out.println("name ="+ ssname);
					}
					*/	
					
				}
				//���� ȭ�鿡�� ��ư�� �������� ���� ���� db�� ���� �� ���̺� ����Ʈ ������Ʈ
				else if(topLabel.getText()=="Review �� ����"||modifyB.getText() =="����"){
					//�Էµ� ���� Ȯ��
					String	title = titleF.getText().trim();
					//String	avg = avgF.getText().trim();
					String	avg = String.valueOf(Review_star) ;
//					String	house_idx = String.valueOf(ReviewList.House_itx);
					//System.out.println("ReviewList.House_itx="+ ReviewList.House_itx);
					String	text = textA.getText().trim();
				
		            int index = combo.getSelectedIndex();
		            ReviewWrite.House_ittx = index + 1;//house_itx�� 1������ �����ϴϱ� 1�� ������

		        //    String	house_idx = String.valueOf(ReviewList.House_itx);
					
					//�Է� �׸� �Է� ������ ���� �� Ȯ�� ȭ��ó��
					if(title == null || title.length() == 0) {
						JOptionPane.showMessageDialog(main.mainP, "���� �Է��ϼ���");
						return;
					}
					else if(avg == null || avg.length() == 0) {
						JOptionPane.showMessageDialog(main.mainP, "���� �����ϼ���");
						return;
					}
					else if(text  == null || text .length() == 0) {
						JOptionPane.showMessageDialog(main.mainP, "���� �Է��ϼ���");
						return;
					}					
					//���� �׸� DB�� �����.
					//main.modifyProc();
					modifyProc();
				
				}
				
			}
			else if(target == removeB) {
				//System.out.println("removeB");
				main.deleteProc();
				main.allProc();
				Review_star = 0;
				//JOptionPane.showMessageDialog(main.mainP, "��ϵǾ����ϴ�");
				main.card.show(main.mainP, "MAIN");
			}	
		}
	}
	
}

