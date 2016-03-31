package main.review;
import	java.awt.*;
import	java.awt.event.*;

import	javax.swing.*;
import	javax.swing.table.*;

import com.netty.client.ClientRead;

import main.Global;
import main.Login;
import Dao.review.*;

import java.io.Serializable;
import	java.sql.*;
import java.util.Date;
import java.util.Vector;
import java.text.SimpleDateFormat;

import  util.*;
import Dao.*;



/**
 * <pre>
 * main
 * ReviewList.java
 * </pre>
 * @author ����ȣ
 * @Date 2015. 11. 30
 * 
 * @category Review ����Ʈ 
 */

public class ReviewList extends JFrame  {
	
	JTable	table;
	DefaultTableModel	model;
	
	JDBCDao				db;
	Connection			con;
	//	Statement�� �ʿ��� ��ŭ ���� ����ص� ����� ����.
	PreparedStatement	allS, nameS, titleS, dateS,avgS, modifyS,deleteS,addS;
	ResultSet			rs;
	
	ReviewPanel	firstP;
	ReviewWrite	secondP;
	ReviewModify thirdP;
	MemModify	fourthP;
	JPanel		mainP;	//	ī�� ���̾ƿ��� ��ġ�� �г�
	CardLayout	card;
    
	//2015.12.06 sql test
	ReviewSql sql;
	ResponseReview res;
	ResponseTbHouse house_res;
	//ReviewWrite rWrite;	
	JPanel	southP,centerP,centerP1,centerP2,topP,topP1,topP2,botP ;
	JButton regB;
	JTextArea textA;
	JLabel noL,houseL,nameL,titleL,dateL,revL,avgL,contL;
	JTextField titleF,avgF,noF,nameF,dateF;	
	JScrollPane sp,spp ;
	
	static int Review_itx = 1;
	static int House_itx = 1;
	static int House_value = 1;//���� ��Ϸ� ���̺��� �� ��ȣ
	
	StarRater starRater; 
	static Date date;
	boolean flag_con = false;// DB���� ���� ���� ����� �б� ��� 1�� �����ϴٷ� ���.
	int selName = 1;
	int selTableRow = 0;
	
	Global g_global = Global.getInstance();
	ClientRead reader;
	//TableCellRenderer renderer = new MyTableCellRenderer();
	
	static String user_name ="";//�α��� ����� �̸��� �ӽ÷� ����
	static String user_id ="abcd";//�α��� ����� �̸��� �ӽ÷� ����
	
	
	private  void Write(Serializable obj) {			
		g_global.serverConnect.write(obj);
	}
	public ReviewList() {
			
		reader = new ClientRead( ) {
			
			@Override
			public void run(Object obj) {
				
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
							card.show(mainP, "SUB2");	
							//flag_con = true;							
							break;
						case Dao.review.R_Constant.DEF_INSERT_REVIEW: 
							System.out.println("insert test");					
							break;
						case Dao.review.R_Constant.DEF_UPDATE_REVIEW: 
							System.out.println("update test00");													
							//��� �ؽ�Ʈ  ��Ȱ��ȭ��Ŵ,�޺� �ڽ� ���� ���ϰ� ��
							thirdP.titleF.setEditable(false);
							thirdP.nameF.setEditable(false);
							thirdP.dateF.setEditable(false);
							//avgF.setEditable(false);
							thirdP.starRater.setEnabled(false);
							thirdP.textA.setEditable(false);
							thirdP.combo.setEditable(false);						
								
							thirdP.combo.removeAllItems();
							System.out.println("modify �Ϸ�");
							allProc();	
							card.show(mainP, "MAIN");
							
							break;
						case Dao.review.R_Constant.DEF_DELETE_REVIEW: 
							System.out.println("delete test00");
							allProc();	
							card.show(mainP, "MAIN");
							break;	
						default:
					}
				}
				
				 if(obj instanceof Dao.review.ResponseTbHouse   ){
					Dao.review.ResponseTbHouse data = (Dao.review.ResponseTbHouse)obj;
					//System.out.println("readNameProc~~11");
					//System.out.println("selName= " +selName);
					if(data.message ==Dao.review.R_Constant.DEF_READ_HOUSENAME_LIST){
												
						if(selName == 1){ // REG ��ư ������ �� �� house name,���� �ð�,��,�̸��� ReviewWrite card�� ����
							if(readNameProc(data.data) == true){
								//System.out.println("readNameProc(data.data)");
							}
						}
						//���콺 ���� Ŭ������ �� ��ϵ� House_itx �� �ش��ϴ� house name,���� �ð�,��,�̸��� ReviewWrite card�� ����
						//content ������  ReviewWrite card�� ����
						else if(selName == 2) { 
												
							readNameProc(data.data,House_itx);
							selName = 1;
							//System.out.println("readNameProc(data.data,House_itx) list �Ϸ�");
										
							//������ ����Ʈ ���� ���� ȭ������ ���� ����
							int	row = table.getSelectedRow();
							if(row == -1) {
								return;
							}
							int no = (int)table.getValueAt(row, 0);
							Review_itx = no;//���̺��� ��� ��ȣ�� ������
							int house_itx = (int)table.getValueAt(row, 1);
							//db���� tb_house ���̺��� idx,name ���� ��û��
							House_itx = house_itx;
							System.out.println("house_itx ^^="+ house_itx);
							String  name = (String) table.getValueAt(row, 2);//���̺��� name ������
							String	title= (String) table.getValueAt(row, 3);//���̺��� title ������
							
							//System.out.println("thirdP.nameF ^^");
							thirdP.nameF.setText(name);
							thirdP.titleF.setText(title);
							//thirdP.textA.setText(content);
							thirdP.dateF.setText(date.toString());
							//thirdP.avgF.setText(String.valueOf(star));
							thirdP.starRater.setSelection(ReviewWrite.Review_star);
							//System.out.println("ReviewWrite.Review_star=" + ReviewWrite.Review_star);
							System.out.println("readNameProc12");
							card.show(mainP, "SUB2");	
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
				
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				g_global.serverConnect.RemoveClientRead(reader);
			}
		});
		
		
		
		mainP = new JPanel();
		this.add("Center", mainP);	
		
		setField();//
		/*
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
		//clP4.add(starRater);
		  
		 */
		
		firstP  =  new ReviewPanel(this); //review ����Ʈ ������ ����
		secondP  =  new ReviewWrite(this);//review ��� ������ ����(�ű� ���� ���)
		thirdP =  new ReviewModify(this); //review ����(����,����) ������
		fourthP  =  new MemModify(this);  //review���� ȸ�� ���� ���� ������
		
		mainP.setLayout(card = new CardLayout());
		mainP.add("MAIN", firstP);
		mainP.add("SUB1", secondP);			
		mainP.add("SUB2", thirdP);
		mainP.add("SUB3", fourthP);	
		
		card.show(mainP, "MAIN");//2015.12.04

		this.setSize(600, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		
	}
	//inner class
	class  ReviewPanel extends JPanel {
			ReviewList	main;
			
		public  ReviewPanel(ReviewList m) {			
			main = m;
			
			ShowReview();//���� ����Ʈ ȭ�� UI ������
			setEvent();	 //���� ����Ʈ �̺�Ʈ ����			
			allProc();					
			
		}		
		
		//Main Page ������
		private void ShowReview(){
			
			this.setLayout(new BorderLayout());
			setField();//
			JPanel topP = new JPanel(new GridLayout(2,1));//dummy
			topP1 = new JPanel();
			topP1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			JLabel topLabel = new JLabel("REVIEW �� ���");		
			topP1.add(topLabel);
			topP.add(topP1);
			//topP.setBackground(Color.CYAN);
			//topP1.setBackground(Color.CYAN);
						
			
			botP = new JPanel(new BorderLayout(10,10));
			JPanel botP1 = new JPanel(new FlowLayout());
			regB = new JButton("���");	
			botP1.add(regB);
			botP.add(botP1);
			
			
			sp = setTable();		
			sp.setBackground(Color.BLUE);
			this.add("North",topP);
			this.add("Center",sp);		
			this.add("South",botP);
						
			this.setSize(600,600);
			this.setVisible(true);
		}
		private void setEvent(){
			ButtonEvent evt= new ButtonEvent();
			
			regB.addActionListener(evt);
			
			TableEvent evt2 = new TableEvent();			
			table.addMouseListener(evt2);
		}
		
		class TableEvent extends MouseAdapter {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) {
					user_name = g_global.getUsername();
										
					//System.out.println("�α��� �̸�=" +user_name);
					//���� 
					//1.���̺��� ������ �о� Review ���� ȭ��� Review ���� ȭ������ ���縦 ��.
					//2.db���� tb_house ���̺��� idx,name ���� ������ ���̺��� idx���� ��ġ�ϴ� tb_house�� �̸����� �޺� �ڽ��� ���縦 ��
					// �̶� �޺��ڽ� ���� ���� ��Ŵ(���� ȭ�鿡���� ���� ���ϰ� ��)
					//3 db���� �� ���븸 �о���� textA ������ ������
					//���콺�� ������ ���� �˾Ƴ���.	
					int	row = table.getSelectedRow();					
					selTableRow = row;
					if(row == -1) {
						return;
					}
					int no = (int)table.getValueAt(row, 0);
					Review_itx = no;//���̺��� ��� ��ȣ�� ������
					int house_itx = (int)table.getValueAt(row, 1);
					House_value = house_itx;//���̺��� ���ȣ ����Ŵ
					String  name = (String) table.getValueAt(row, 2);//���̺��� name ������
					String	title= (String) table.getValueAt(row, 3);//���̺��� title ������
					Date  date =  (Date)table.getValueAt(row, 4);    //���̺��� reg_date ������
					int	star = (int) table.getValueAt(row, 5);		 //���̺��� star ������
					ReviewWrite.Review_star = star;					
					//������ ����Ʈ ���� ���� ȭ������ ���� ����
					thirdP.nameF.setText(name);
					thirdP.titleF.setText(title);
					thirdP.dateF.setText(date.toString());
					thirdP.starRater.setSelection(ReviewWrite.Review_star);
					System.out.println(ReviewList.user_name);
					System.out.println(nameF.getText());
					//����� üũ
					
					if(!ReviewList.user_name.equals(name)){
						//JOptionPane.showMessageDialog(main.mainP, "Ÿ���� ���� ������  �����ϴ�.");
						thirdP.modifyB.setEnabled(false);
						thirdP.removeB.setEnabled(false);
						//starRater.setEnabled(false);
						//return;
						
					}
					else{
						//JOptionPane.showMessageDialog(main.mainP, "���� �Ͻðڽ��ϱ�?");
						thirdP.modifyB.setEnabled(true);
						thirdP.removeB.setEnabled(true);
						//starRater.setEnabled(true);
					}
					
					//DB�� name�� ��û��
					selName =2;
					thirdP.starRater.setSelection(0);
					getNameProc();				
					
					
				}
				
			}
		}		
		
		
		class ButtonEvent implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					JButton	target = (JButton)e.getSource();
					if(target == regB) {
						user_name = g_global.getUsername();
						//System.out.println("�α��� �̸�=" +user_name);
						selName=1;
						//thirdP.combo.removeAllItems();
						//starRater.setSelection(0);
				//		ReviewWrite.Review_star = 0;
						secondP.SetstarRater(0);
						System.out.println("ReviewWrite.Review_star="+ReviewWrite.Review_star);
				//		ReviewWrite.SetstarRater(0);
						getNameProc();
						//readNameProc();
									
					}
					
				}
		}
		
	}
	public JScrollPane setTable() {
		//private JTable	table;
		String[] title = {"��ȣ","���ȣ", "�ۼ���", "����", "�ۼ���", "����"};//�Խñ��� �Ⱥ���		
				
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
		
		table = new JTable(model);//���̺� �� ��ü ����	
		JScrollPane sp1 = new JScrollPane(table);//��ũ�� �߰�		
		return sp1;
		
	}
//	���߸� ������ִ� ���� �Լ�
	public void setButton() {
	
		regB  = new JButton("  ���  ");			
		
	}
	public void	setField() {
		noL = new JLabel("NO");
		houseL = new JLabel("HOUSE Num.");
		nameL = new JLabel("NAME");
		titleL = new JLabel("TITLE");
		dateL = new JLabel("DATE");
		avgL = new JLabel("STAR");
				
		noF = new JTextField(10);
		nameF = new JTextField(10);
		titleF = new JTextField(10);
		dateF = new JTextField(10);
		avgF = new JTextField(10);
		textA = new JTextArea(20,30);///
	}
	
	public void clearField() {
		noF.setText("");//��ȣ
		nameF.setText("");//�ۼ���
		titleF.setText("");//����
		dateF.setText("");//�ۼ�����
		avgF.setText("");//����
		textA.setText("");//����
		
	}
//	���̺� �� ����
	public void clearTable() {
		int	rows = table.getRowCount();
		for(int i = 0; i < rows; i++) {
			model.removeRow(0);
		}
	}	
	
	public void allProc() {
		//TB_REVIEW temp = new TB_REVIEW();
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_BASIC_LIST;
		Write(req);
		System.out.println("allProc()");
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
			    date = (Date)res.data.get(i).reg_date;
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
			thirdP.textA.setText(str);// ���䳻�� ���� ȭ������ ������.
			//System.out.println("content ���� ó����0");
		}			
		catch(Exception ex){
			//System.out.println("readConProc error");
			ex.printStackTrace();	
		}
		//System.out.println("content ���� ó����1");
				
		//clearTable();	
	}
	//content ���� �޶�� ������ ��û��.
	private void contentProc(int idx) {
		//System.out.println("content ��û��00");	
		TB_REVIEW temp = new TB_REVIEW();
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_CONTENT_LIST;
	    temp.idx = idx;
		req.data =  temp;	
		//System.out.println("idx= "+idx);	
		Write(req);
		
		//System.out.println("content ��û��11");
	}
	
	//��� ��ư ������  
	private boolean readNameProc(Vector<TB_HOUSE> r) {
		//System.out.println("readNameProc1");
		//���̺��� idx���� ��ġ�ϴ� tb_house�� �̸����� �޺� �ڽ��� ���縦 ��
		Vector <String> name = new Vector<String>();
		secondP.combo.removeAllItems();
		for(int i=0;i<r.size();i++){
				//int idx =r.get(i).idx;
				String str =r.get(i).name;
				secondP.combo.addItem(str);

				//break;
		}
		secondP.nameF.setText(user_name);//user_name
		secondP.nameF.setEditable(false);
		secondP.dateF.setEditable(false);
		secondP.titleF.setText("");//user_name
		secondP.textA.setText("");//user_name
		//secondP.avgF.setText("");
		//���� �ð� ���� ����
		
		String str = DateToString(new Date());
		//String str = DateToStringTime(new Date());
		//System.out.println("���� �ð�="+str);	
		secondP.dateF.setText(str.toString());
		card.show(mainP, "SUB1");//���� ��� ȭ�� ��ȯ
		//System.out.println("readNameProc3");
		return true;
		
	}
	//���� Ŭ�� ���� �� ,���̺��� ������ ���� 
	private void readNameProc(Vector<TB_HOUSE> r,int itx) {
		//System.out.println("readNameProc11");
		
		System.out.println("House_itx ="+House_itx);
		//���̺��� idx���� ��ġ�ϴ� tb_house�� �̸����� �޺� �ڽ��� ���縦 ��
		//Vector <String> name = new Vector<String>();	
		boolean house_flag = false;
		/*
		for(int i=0;i<r.size();i++){
			int iddx =r.get(i).idx;
			//if(iddx == House_itx)
			if(iddx == House_value)
		    {
				String str = r.get(i).name;
				thirdP.combo.addItem(str);
				System.out.println("DB_itx OK="+ House_value);
				house_flag = true;
				break;
		    }				
			
		} */
		
		thirdP.combo.removeAllItems();
		int select = 0;
		System.out.println("update size:"+r.size());
		for(int i=0;i<r.size();i++){
				//int idx =r.get(i).idx;
			int iddx =r.get(i).idx;
			
			if(iddx == House_value)
			{
				select  = i;
				
			}
				String str =r.get(i).name;
				thirdP.combo.addItem(str);
				System.out.println("update :"+str);
				//break;
		}
		
		
		thirdP.combo.setSelectedIndex(select);
		
		
		if(house_flag==false){
			System.out.println("��ġ�ϴ� house_name�� �����ϴ�.");
		}
		//DB���� �� ������ ��û��				
		contentProc(Review_itx);
		System.out.println("Review_itx ="+Review_itx);
	}
			
	public void getNameProc() {	
		
		//clearTable();		
		RequestTbHouse req = new RequestTbHouse();	
		req.message = Dao.review.R_Constant.DEF_READ_HOUSENAME_LIST;		
		Write(req);
		//System.out.println("getNameProc()1");
		
	}
	
	public void modifyProc() {
		//����
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
		//System.out.println("������Ʈ ��û ��");		
		
	}
	
	//���� ���� ���� ���� �Լ�
	public void deleteProc() {
		//		����
		TB_REVIEW temp = new TB_REVIEW();		
		RequestReview req = new RequestReview();				
		temp.idx = ReviewList.Review_itx;//Integer.parseInt("10");
		System.out.println("itx"+ReviewList.Review_itx+"����");
		req.message = Dao.review.R_Constant.DEF_DELETE_REVIEW;
		req.data = temp;
		Write(req);
		//
		
	}	
	
		//
	public  static String DateToString(Date date) {      
	     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	       String ret = format.format(date);      
	     return ret;      
	}
	   
	public static Date StringToDate(String str) {
         
	     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	     Date date = null;
	     try {
	        date= format.parse(str);
	     } catch (Exception ex) {
	                 
	     }
	     return date;
	}
	public  static String DateToStringTime(Date date) {		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String ret = format.format(date);		
		return ret;		
	}
	
	
	public static void main(String[] args) {
		new Login(null);
		new ReviewList();
	}
	

}




