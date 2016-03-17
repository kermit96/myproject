package main.review;
import	java.awt.*;
import	java.awt.event.*;
import  java.io.*;

import	javax.swing.*;
import	javax.swing.table.*;

import main.Global;
import  util.*;//util������ JDBCDao ���
import  Dao.*;
import Dao.review.RequestReview;
import Dao.review.RequestTbHouse;
import Dao.review.ResponseReview;
import Dao.review.ReviewSql;
import Dao.review.TB_HOUSE;
import Dao.review.TB_REVIEW;

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


public class ReviewWrite extends JPanel  {
	
	private JTable	table;
	private DefaultTableModel	model;
	
	JDBCDao				db;
	Connection			con;
	PreparedStatement	allS, nameS, titleS, dateS,avgS, modifyS, deleteS,addS;
	ResultSet			rs;
		
	JPanel	southP,centerP,centerP1,centerP2,topP,topP1,topP2,botP ;
	JButton logoB,homeB,infoB,exitB,listB,writeB;
	JTextArea textA;
	JComboBox combo;//2015.12.08 �߰�
	JLabel noL,revL,titleL,nameL,dateL,avgL,contL;
	JTextField titleF,avgF,noF,nameF,dateF;	
	JScrollPane sp,spp; 
	
	static int House_ittx = 0;//2015.122.08
	StarRater starRater; 
	static int Review_star = 0;
	boolean flag_combo = false;
	//
	Global g_global = Global.getInstance();
	ClientRead reader;
	
	//2015.12.06 sql test
	ReviewSql sql;
	ResponseReview res;
	
	ReviewList main;	
	String house_name;
	
	private  void Write(Serializable obj) {			
		g_global.serverConnect.write(obj);
	}	
	public ReviewWrite(ReviewList m) {
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
						case Dao.review.R_Constant.DEF_INSERT_REVIEW: 
							System.out.println("DEF_INSERT_REVIEW~~");
							main.allProc();									
							break;
						default:
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
		WriteReview();
		sp = setTable();
		setEvent();
		allProc();
	
	}
	
	private void WriteReview(){
		this.setLayout(new BorderLayout());		
		JPanel topP = new JPanel(new GridLayout(2,1));//dummy
		
		/*
		topP1 = new JPanel();
		topP1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		JLabel topLabel = new JLabel("REVIEW �� ���");		
		topP1.add(topLabel);
		topP.add(topP1);
		*/
		
		topP = new JPanel();
		topP.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		JLabel topLabel = new JLabel("REVIEW �� ���");		
		topP.add(topLabel);
		//topP.add(topP1);
		
		//topP.setBackground(Color.CYAN);
		//topP1.setBackground(Color.CYAN);
				
		botP = new JPanel(new BorderLayout(10,10));
		JPanel botP1 = new JPanel(new FlowLayout());
		listB = new JButton("���");					
		botP1.add(listB);
		writeB = new JButton("���");			
		botP1.add(writeB);		
		botP.add("South",botP1);
		
		textA = new JTextArea("�׽�Ʈ",60,0);
		//textA.setEditable(false);
		sp = new JScrollPane(textA);
		
		centerP = new JPanel(new GridLayout(3,1));
	
		revL = new JLabel("Review ����");
		titleL = new JLabel(" ����");
		nameL = new JLabel(" �ۼ���");  //2015.12.01 �߰�
		dateL = new JLabel(" �ۼ����� ");//2015.12.01 �߰�
		avgL = new JLabel(" ����");
		contL = new JLabel("����");
									
		titleF = new JTextField(17);
		//titleF.setEditable(false);
		nameF = new JTextField(8);
		//nameF.setEditable(false);
		dateF = new JTextField(6);
		//dateF.setEditable(false);
		avgF = new JTextField(5);
		//avgF.setEditable(false);
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
	                   System.out.println(selection);
	                   Review_star = selection;
	                   System.out.println("�� ����"+ Review_star);
	               }
	           });
	    	   
	    starRater.setSelection(0);
	   
	    clP4.add(starRater);

		//clP4.add(avgF); �ؽ�Ʈ �ʵ� ��� star �̿�		
		
		JPanel cPP1 = new JPanel(new GridLayout(5,1));
		cPP1.add(clP1);
		cPP1.add(clP11);
		cPP1.add(clP2);
		cPP1.add(clP3);
		cPP1.add(clP4);						
		
		JPanel cP = new JPanel(new BorderLayout());
		JPanel jpp1 = new JPanel(new BorderLayout());
		JPanel jpp2 = new JPanel(new FlowLayout());
		
		cP.add("North",jpp1);
		cP.add("West",cPP1);
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
		
	private void setEvent(){
		ButtonEvent evt = new ButtonEvent();
		ComboEvent evt1 = new ComboEvent();
		//ComboChangedEvent evt2 = new ComboChangedEvent();
		listB.addActionListener(evt);
		writeB.addActionListener(evt);
		combo.addActionListener(evt1);
		//combo.addItemListener(evt2);
	}
		
	public void exitProc() {
		
		db.close(rs);
		db.close(allS);
		db.close(nameS);
		db.close(titleS);
		db.close(dateS);
		db.close(avgS);
		db.close(addS);
		db.close(modifyS);
		db.close(deleteS);
		db.close(con);
		System.exit(0);
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
	private void contentProc(int idx) {
		
		TB_REVIEW temp = new TB_REVIEW();
		//sql = new ReviewSql();
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_CONTENT_LIST;
		req.data.idx = idx;			
		Write(req);					
	}
	
	public void SetstarRater(int star) 
	{
		Review_star = star;
		starRater.setSelection(Review_star);
	}
	
	public void addProc() {
		
		TB_REVIEW temp = new TB_REVIEW();
				
		// �Է� ȭ���� ������ ����
		temp.house_idx = ReviewList.House_itx;
		temp.user_name = nameF.getText().trim();
		temp.title = titleF.getText().trim();		
		temp.content = textA.getText().trim();
		temp.star = Review_star;
		starRater.setSelection(Review_star);
		RequestReview req = new RequestReview();
		req.message = Dao.review.R_Constant.DEF_INSERT_REVIEW;
		req.data = temp;
		System.out.println("ReviewList.House_itx="+ReviewList.House_itx);
		Write(req);		
	}
	/*
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
	*/
	
	/*
	public void allProc() {
		
		clearTable();	
		TB_REVIEW temp = new TB_REVIEW();
		sql = new ReviewSql();
		res = new ResponseReview();
		res = sql.ReadReview(temp);
		//1.TB_REVIEW�� �⺻��(idx,house_idx,title,user_name,star) �б�
		for(int i=0;i<res.data.size();i++) {
			
			Object[] test = new Object[6];
		    test[0] = res.data.get(i).idx;	//��ȣ		//	
		    test[1] = res.data.get(i).house_idx;//.house_idx;	//	
		    test[2] = res.data.get(i).user_name;//.title;  		//  
		    test[3] = res.data.get(i).title;//.user_name;  		//  
		    test[4] = res.data.get(i).star;//.star;  		//  
		    test[5] = res.data.get(i).reg_date;//.star;  		// 
		    //date = (Date)res.data.get(i).reg_date;
		    model.addRow(test);  
		    
		}		
		//System.out.println("test 03");
		//clearField();
	}
	*/
	public void allProc() {
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_BASIC_LIST;
		Write(req);
		System.out.println("allProc() 11");
		//combo.removeAllItems();
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
			    ReviewList.date = (Date)res.data.get(i).reg_date;
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
		//boolean con_flag = false;
		Vector <TB_REVIEW> res = new Vector<TB_REVIEW>();
		res = r;
		try{
			String str = (String)res.get(0).content;	//��ȣ		
			textA.setText(str);// ���䳻�� ���� ȭ������ ������.
		}			
		catch(Exception ex){
			System.out.println("readConProc error");
			ex.printStackTrace();	
		}
		System.out.println("content ���� ó����");
		main.card.show(main.mainP, "SUB2");	
		
		//clearTable();	
	}
	
	public void modifyProc() {
		//	����
		//	����� ������ �ؽ�Ʈ ������ �˾Ƴ���
				
		int	no = Integer.parseInt(noF.getText().trim());
		int house_idx = ReviewList.House_itx;
		String	name = nameF.getText().trim();
		String	title = titleF.getText().trim();
		String	date = dateF.getText().trim();
		String	avg = avgF.getText().trim();
		String	commend = textA.getText().trim();
		
				
		//		���Ǹ� �����Ѵ�.
		try {
			modifyS.setInt(1,house_idx);
			modifyS.setString(2, name);
			modifyS.setString(3, title);
			modifyS.setString(4, date);
			modifyS.setString(5, avg);
			modifyS.setString(6, commend);
			modifyS.setInt(7, no);
		
			modifyS.execute();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//clearField();
		allProc();
	}
	
	public JScrollPane setTable() {
		//private JTable	table;
		String[] title = {"��ȣ","���ȣ", "�ۼ���", "����","����", "�ۼ���" };//�Խñ��� �Ⱥ���		
				
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
	class ComboEvent implements ActionListener{
		public void actionPerformed(ActionEvent e) 
	    {
	        if(e.getSource() == combo)
	        {
	            //�׸��� ��ȯ���� ������Ʈ�� ���������� ��ȯ�Ǵ� String���� ����ȯ ����� �մϴ�
	            String str = (String)combo.getSelectedItem();
	            //indexOf(1);
	            System.out.println("�� �̸�" + str);
	            int index = combo.getSelectedIndex();
	            ReviewList.House_itx = index + 1;//house_itx�� 1������ �����ϴϱ� 1�� ������
	            System.out.println("House_itx =" +  ReviewList.House_itx);
	           
	        }
	    }
	}
	/*
	class ComboChangedEvent implements ItemListener {
		public void itemStateChanged(ItemEvent e){
			System.out.println("��fdfdf.");
			  //flag_combo = false;
			  if(e.getStateChange()==ItemEvent.SELECTED){
				  flag_combo = true;
	                //Object data = colors.getSelectedItem();
	                // System.out.println(data.toString());
	        	  System.out.println("���� �����ϼ���.");
	          }
	          //else{
	          //	  System.out.println("��fdfdf.");
	          //}
			 
	    }
	}
	*/
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton	target = (JButton) e.getSource();
			if(target == listB) { //��� ��ư
				//System.out.println("listB");
				
				main.allProc();//Review ȭ�� ���̺� ������
				main.card.show(main.mainP, "MAIN");
			}	
			else if(target == writeB) {//��� ��ư
							 			
				//�� �ʵ忡 �ʿ��� �����Ͱ� �ִ��� Ȯ���Ѵ�.
				String	title = titleF.getText().trim();
				int	avg =  Review_star ;
				System.out.println("Review_star="+Review_star);
				String	text = textA.getText().trim();
				if(title == null || title.length() == 0) {
					JOptionPane.showMessageDialog(main.mainP, "���� �Է��ϼ���");
					return;
				}
				//else if(avg == null || avg.length() == 0) {
				else if(avg == 0) {
					JOptionPane.showMessageDialog(main.mainP, "���� ���� �ϼ���");
					return;
				}
				//else if( == false) {
				//	JOptionPane.showMessageDialog(main.mainP, "�����Ͻ� ���� ���� �ϼ���");
				//	return;
				//}
				else if(text  == null || text .length() == 0) {
					JOptionPane.showMessageDialog(main.mainP, "���� �Է��ϼ���");
					return;
				}
				//�ؽ�Ʈ �ʵ�� �ؽ�Ʈ ������ ������ �о� DB�� �����Ѵ�.
				addProc();
				main.allProc();
				Review_star = 0;
				JOptionPane.showMessageDialog(main.mainP, "��ϵǾ����ϴ�");
				main.card.show(main.mainP, "MAIN");
			}
		
		}
	}
	
}

