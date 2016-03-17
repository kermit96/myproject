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
 * @author 차준호
 * @Date 2015. 11. 30
 * 
 * @category Review 수정 
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
	JComboBox combo;//2015.12.08 추가
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
							//TB_REVIEW 테이블에서 idx,house_idx,title,star 값 가져옮
							readListProc(data);
							System.out.println("readListProc~1");
							break;
						case Dao.review.R_Constant.DEF_READ_CONTENT_LIST: 
							//TB_REVIEW 테이블에서 content 값만 일어옮
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
							JOptionPane.showMessageDialog(main.mainP, "수정 완료 되었습니다");
							topLabel.setText("REVIEW 글 보기");
							modifyB.setText("수정");
							
							//모든 텍스트  비활성화시킴,콤보 박스 선택 못하게 함
							titleF.setEditable(false);
							nameF.setEditable(false);
							dateF.setEditable(false);
							//avgF.setEditable(false);
							starRater.setEnabled(false);
							textA.setEditable(false);
							combo.setEditable(false);
							
							
							combo.removeAllItems();
							System.out.println("modify 완료");
							
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
												
						//if(selName == 1){ // REG 버튼 눌렀을 때 때 house name,현재 시간,별,이름을 ReviewWrite card로 복사
						//	if(readNameProc(data.data) == true){
						//		System.out.println("readNameProc(data.data)");
						//	}
						//}
						//마우스 더블 클력했을 때 등록된 House_itx 에 해당하는 house name,현재 시간,별,이름을 ReviewWrite card로 복사
						//content 내용을  ReviewWrite card로 복사
						   { 
												
							//readNameProc(data.data,House_itx);
							//selName = 1;
							System.out.println("readNameProc(data.data,House_itx)00 완료");
														
							//선택한 리스트 리뷰 보기 화면으로 값을 복사
							int	row = table.getSelectedRow();
							if(row == -1) {
								return;
							}
							int no = (int)table.getValueAt(row, 0);
							ReviewList.Review_itx = no;//테이블의 등록 번호를 저장함
							int house_itx = (int)table.getValueAt(row, 1);
							//db에서 tb_house 테이블에서 idx,name 값을 요청함
							ReviewList.House_itx = house_itx;
							//System.out.println("house_itx="+ house_itx);
							String  name = (String) table.getValueAt(row, 2);//테이블에서 name 가져옮
							String	title= (String) table.getValueAt(row, 3);//테이블에서 title 가져옮
							
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
						System.out.println("DEF_READ_HOUSENAME_LIST 응답이 없습니다");
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
		topLabel = new JLabel("REVIEW 글 보기");		
		topP1.add(topLabel);
		topP.add(topP1);
		*/
		topP = new JPanel();
		topP.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		topLabel = new JLabel("REVIEW 글 보기");		
		topP.add(topLabel);
		//topP.add(topP1);
		
		//topP.setBackground(Color.CYAN);
		//topP1.setBackground(Color.CYAN);
				
		botP = new JPanel(new BorderLayout(10,10));
		JPanel botP1 = new JPanel(new FlowLayout());
		
		listB = new JButton("목록 ");	
		modifyB = new JButton("수정 ");
		//modifyB.setEnabled(false);//버튼 비활성화
		removeB = new JButton("삭제 ");	
		//removeB.setEnabled(false);//버튼 비활성화
		botP1.add(listB);			
		botP1.add(modifyB);			
		botP1.add(removeB);		
		botP.add("South",botP1);
	
		textA = new JTextArea("테스트",70,0);
		textA.setEditable(false);
		sp = new JScrollPane(textA);
		
		centerP = new JPanel(new GridLayout(3,1));
	
		//revL = new JLabel("Review 보기");
		titleL = new JLabel(" 제목");
		nameL = new JLabel(" 작성자");  //2015.12.01 추가
		dateL = new JLabel(" 작성일자 ");//2015.12.01 추가
		avgL = new JLabel(" 평점");
		contL = new JLabel("내용");
									
		titleF = new JTextField(17);
		titleF.setEditable(false);
		nameF = new JTextField(8);
		nameF.setEditable(false);
		//dateF = new JTextField(6);
		
		
		dateF.setEditable(false);
		avgF = new JTextField(5);
		avgF.setEditable(false);
		
		//JPanel cP1 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,10));
		JPanel clP1 = new JPanel(new FlowLayout(FlowLayout.LEFT,31,15));//라벨과 필드 가로 세로 조정 가능
		JPanel clP11 = new JPanel(new FlowLayout(FlowLayout.LEFT,26,2));//라벨과 필드 가로 세로 조정 가능
		JPanel clP2 = new JPanel(new FlowLayout(FlowLayout.LEFT,25,12));//라벨과 필드 가로 세로 조정 가능
		JPanel clP3 = new JPanel(new FlowLayout(FlowLayout.LEFT,17,12));//라벨과 필드 가로 세로 조정 가능
		JPanel clP4 = new JPanel(new FlowLayout(FlowLayout.LEFT,31,12));//라벨과 필드 가로 세로 조정 가능				
			
		clP1.add(titleL);
		clP1.add(titleF);
		
		combo = new JComboBox();
		JLabel Jhouse= new JLabel("방선택",JLabel.CENTER);
		
		clP11.add(Jhouse);
		clP11.add(combo);
		
		clP2.add(nameL);
		clP2.add(nameF);
		
		clP3.add(dateL);
		clP3.add(dateF);
		
		clP4.add(avgL);
		//평가 점수를 별로 표시
		
		starRater = new StarRater(5, 1, 1);
	    starRater.addStarListener(
			   new StarRater.StarListener(){
			          public void handleSelection(int selection) {
		                  //System.out.println(selection);
		                  ReviewWrite.Review_star = selection;
		                  
			              //System.out.println("별 수량"+ ReviewWrite.Review_star);
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
		
		
		JPanel cb1P = new JPanel(new BorderLayout(36,30));//내용 라벨과의 간격 조절
		JPanel conP = new JPanel(new BorderLayout(29,10));
		conP.add("East",contL);
		cb1P.add("West",conP);
		cb1P.add("Center",sp);
		
		JPanel cPP = new JPanel(new BorderLayout(160,1));
		JPanel cPP_dummy = new JPanel(new FlowLayout());
		cPP.add("North",cPPP); //라벨과 필드 영역 배치
		cPP.add("Center",cb1P);//텍스트 영역 배치
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
		noL = new JLabel("번호", JLabel.RIGHT);
		nameL = new JLabel("작성자", JLabel.RIGHT);
		titleL = new JLabel("제목", JLabel.RIGHT);
		dateL = new JLabel("작성 날짜", JLabel.RIGHT);
		avgL = new JLabel("평점", JLabel.RIGHT);
				
		noF = new JTextField(20);
		noF.setEditable(false);//편집 못하게 막음
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
		
			//할일
			//입력상자의 내용 알아서
			String	name = nameF.getText().trim();
			String	title = titleF.getText().trim();
			String	date = dateF.getText().trim();
			String	avg = avgF.getText().trim();
			String	commend = textA.getText().trim();
			
			int temp = ReviewList.Review_itx;
			//System.out.println("addProc() temp " + temp);		
			//	데이터베이스에 입력하자.
			//	입력을 할 때는 addS를 이용하면 된다.
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
		//	할일
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
		System.out.println("업데이트 요청 함");
		
		//TB_REVIEW 에서 신규 리뷰 값 등록		
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
	
		//1.TB_REVIEW의 기본값(idx,house_idx,title,user_name,star) 읽기
		//System.out.println("vector size" + res.data.size());
		for(int i=0;i<res.data.size();i++) {
		
			Object[] test = new Object[6];
		    test[0] = res.data.get(i).idx;	//번호		//	
		    test[1] = res.data.get(i).house_idx;//.house_idx;	//	
		    test[2] = res.data.get(i).user_name;//.title;  		//  
		    test[3] = res.data.get(i).title;//.user_name;  		//  
		    test[4] = res.data.get(i).reg_date;//.star;  		// 
		    test[5] = res.data.get(i).star;//.star;  		//  
		    ReviewList.date = (Date)res.data.get(i).reg_date;//static 에 저장
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
	
	//JTable로 DB의 값을 읽어 저장함
	private void readListProc(ResponseReview r){
		sql = new ReviewSql();
		res = new ResponseReview();		
		res = r;
		
		clearTable();//
		
		try {
			//1.TB_REVIEW의 기본값(idx,house_idx,title,user_name,star) 읽기
			//System.out.println("vector size" + res.data.size());
			for(int i=0;i<res.data.size();i++) {
			
				Object[] test = new Object[6];
			    test[0] = res.data.get(i).idx;	//번호		//	
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
	//contentProc 함수의 요청 후 서버로 부터 받은 content 글을 읽어 처리함.
	private void readConProc(Vector<TB_REVIEW> r){
		Vector <TB_REVIEW> res = new Vector<TB_REVIEW>();
		res = r;
		try{
			String str = (String)res.get(0).content;	//번호		
			textA.setText(str);// 리뷰내용 보기 화면으로 전달함.
			//System.out.println("content 정상 처리함0");
		}			
		catch(Exception ex){
			System.out.println("readConProc error");
			ex.printStackTrace();	
		}
		//System.out.println("content 정상 처리함1");
				
		//clearTable();	
	}
	//content 글을 달라고 서버에 요청함.
	private void contentProc(int idx) {
			
		TB_REVIEW temp = new TB_REVIEW();
		//sql = new ReviewSql();
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_CONTENT_LIST;
		req.data.idx = idx;			
		Write(req);
		//System.out.println("content 요청함");
	}
	//더블 클릭 했을 때 ,테이블의 내용을 읽음 
	private void readNameProc(Vector<TB_HOUSE> r,int idx) {
		int hidx = idx;
		//System.out.println("readNameProc11");
		//마우스로 선택한 줄을 알아낸다.	
		int	row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		int no = (int)table.getValueAt(row, 0);
		ReviewList.Review_itx = no;//테이블의 등록 번호를 저장함
		int house_itx = (int)table.getValueAt(row, 1);
		//db에서 tb_house 테이블에서 idx,name 값을 요청함
		ReviewList.House_itx = house_itx;
		//System.out.println("house_itx="+ house_itx);
		String  name = (String) table.getValueAt(row, 2);//테이블에서 name 가져옮
		String	title= (String) table.getValueAt(row, 3);//테이블에서 title 가져옮
		Date  date =  (Date)table.getValueAt(row, 4);    //테이블에서 reg_date 가져옮
		int	star = (int) table.getValueAt(row, 5);		 //테이블에서 star 가져옮
		ReviewWrite.Review_star = star;
		
		//테이블의 idx값과 일치하는 tb_house의 이름값만 콤보 박스에 복사를 함
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
			System.out.println("일치하는 house_name이 없습니다.");
		}
		//DB에서 글 내용을 요청함				
		contentProc(ReviewList.Review_itx);	
		
	}
			
	public void getNameProc() {	
		
		clearTable();		
		RequestTbHouse req = new RequestTbHouse();	
		req.message = Dao.review.R_Constant.DEF_READ_HOUSENAME_LIST;		
		Write(req);
		//System.out.println("getNameProc()1");
		
	}
	
	//리뷰 정보 제거 전담 함수
	public void deleteProc() {
			//		할일
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
		String[] title = {"번호","방번호", "작성자", "제목", "평점","작성일" };		
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
	            //항목의 반환형이 오브젝트의 참조값으로 반환되니 String으로 형변환 해줘야 합니다
	            String str = (String)combo.getSelectedItem();
	            System.out.println("TT 방 이름" + str);
	            int index = combo.getSelectedIndex();
	            ReviewWrite.House_ittx = index + 1;//house_itx는 1번부터 시작하니까 1을 더해줌
	            System.out.println("TT index =" + ReviewWrite.House_ittx );
	           
	        }
	    }
	}
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton	target = (JButton) e.getSource();
			
			if(target == listB) {
				//System.out.println("listB");
				topLabel.setText("REVIEW 글 보기");
				modifyB.setText("수정");
				//재등록 버튼에서 목록으로 빠져나갔다가 목록 누르면 필드 값 비활성화함
				titleF.setEditable(false);
				nameF.setEditable(false);
				dateF.setEditable(false);
				avgF.setEditable(false);
				//starRater.setSelection(ReviewWrite.Review_star);
				starRater.setSelection(0);//디폴트 0으로 표시
				//System.out.println("Star ="+ReviewWrite.Review_star);
				textA.setEditable(false);				
				main.clearField();
				main.allProc();//Review list로 전환시 테이블 다시 보여 줌
				combo.removeAllItems();///Review list로 전환시 콤보 박스 항목 지움
				//secondP.combo.removeAllItems();
				//Review_star = 0;//등록 화면 가서 목록 버튼 누를 경우 초기값으로 돌림 
				main.card.show(main.mainP, "MAIN");				
			}	
			else if(target == modifyB) {
				//작성자 자신의 글을 볼때만 수정 버튼과 삭제 버튼은 활성화해야 함
				//다른 사람의 글을 볼경우 수정 버튼과 삭제 버튼은 비활성화해야 함
				//System.out.println("modifyB");				
				//Reviewwrite 카드에 최초 "REVIEW 글 보기"로 이전에 등록되어 있기 때문에 이를 이용함.
				//System.out.println(ReviewList.user_name);
				//System.out.println(nameF.getText());
										
				if(topLabel.getText() =="REVIEW 글 보기"){
					//처음에는 이름과,등록 날짜는 비활성화시킴
					titleF.setEditable(true);
					nameF.setEditable(false);
					dateF.setEditable(false);
					//avgF.setEditable(true);
					starRater.setEnabled(true);
					textA.setEditable(true);
					combo.setEditable(false);//콤보 박스 선택 못하게 함
					
					//보기화면에서 버튼이 눌러지면 수정 화면으로 전환
					topLabel.setText("Review 글 수정");
					modifyB.setText("재등록");
					
					//수정 버튼이 눌러지면 db에서 tb_house의 name 값을 불러와서 콤보 박스에 등록해줌
					//combo.removeAllItems();
					/*
					ResponseTbHouse ret = main.getNameProc();						
					//tb_house에서 name 값을 가져 옮
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
				//수정 화면에서 버튼이 눌러지면 수정 사항 db에 저장 후 테이블 리스트 업데이트
				else if(topLabel.getText()=="Review 글 수정"||modifyB.getText() =="재등록"){
					//입력된 내용 확인
					String	title = titleF.getText().trim();
					//String	avg = avgF.getText().trim();
					String	avg = String.valueOf(Review_star) ;
//					String	house_idx = String.valueOf(ReviewList.House_itx);
					//System.out.println("ReviewList.House_itx="+ ReviewList.House_itx);
					String	text = textA.getText().trim();
				
		            int index = combo.getSelectedIndex();
		            ReviewWrite.House_ittx = index + 1;//house_itx는 1번부터 시작하니까 1을 더해줌

		        //    String	house_idx = String.valueOf(ReviewList.House_itx);
					
					//입력 항목에 입력 내용이 없을 때 확인 화면처리
					if(title == null || title.length() == 0) {
						JOptionPane.showMessageDialog(main.mainP, "제목 입력하세요");
						return;
					}
					else if(avg == null || avg.length() == 0) {
						JOptionPane.showMessageDialog(main.mainP, "별점 선택하세요");
						return;
					}
					else if(text  == null || text .length() == 0) {
						JOptionPane.showMessageDialog(main.mainP, "글을 입력하세요");
						return;
					}					
					//수정 항목 DB에 등록함.
					//main.modifyProc();
					modifyProc();
				
				}
				
			}
			else if(target == removeB) {
				//System.out.println("removeB");
				main.deleteProc();
				main.allProc();
				Review_star = 0;
				//JOptionPane.showMessageDialog(main.mainP, "등록되었습니다");
				main.card.show(main.mainP, "MAIN");
			}	
		}
	}
	
}

