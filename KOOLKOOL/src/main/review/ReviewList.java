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
 * @author 차준호
 * @Date 2015. 11. 30
 * 
 * @category Review 리스트 
 */

public class ReviewList extends JFrame  {
	
	JTable	table;
	DefaultTableModel	model;
	
	JDBCDao				db;
	Connection			con;
	//	Statement는 필요한 만큼 만들어서 사용해도 상관이 없다.
	PreparedStatement	allS, nameS, titleS, dateS,avgS, modifyS,deleteS,addS;
	ResultSet			rs;
	
	ReviewPanel	firstP;
	ReviewWrite	secondP;
	ReviewModify thirdP;
	MemModify	fourthP;
	JPanel		mainP;	//	카드 레이아웃을 설치할 패널
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
	static int House_value = 1;//기존 등록뢴 테이블의 방 번호
	
	StarRater starRater; 
	static Date date;
	boolean flag_con = false;// DB에서 리뷰 글을 제대로 읽글 경우 1로 변경하다록 사용.
	int selName = 1;
	int selTableRow = 0;
	
	Global g_global = Global.getInstance();
	ClientRead reader;
	//TableCellRenderer renderer = new MyTableCellRenderer();
	
	static String user_name ="";//로그인 사용자 이름을 임시로 지정
	static String user_id ="abcd";//로그인 사용자 이름을 임시로 지정
	
	
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
							//TB_REVIEW 테이블에서 idx,house_idx,title,star 값 가져옮
							readListProc(data);
							System.out.println("readListProc~1");
							break;
						case Dao.review.R_Constant.DEF_READ_CONTENT_LIST: 
							//TB_REVIEW 테이블에서 content 값만 일어옮
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
							//모든 텍스트  비활성화시킴,콤보 박스 선택 못하게 함
							thirdP.titleF.setEditable(false);
							thirdP.nameF.setEditable(false);
							thirdP.dateF.setEditable(false);
							//avgF.setEditable(false);
							thirdP.starRater.setEnabled(false);
							thirdP.textA.setEditable(false);
							thirdP.combo.setEditable(false);						
								
							thirdP.combo.removeAllItems();
							System.out.println("modify 완료");
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
												
						if(selName == 1){ // REG 버튼 눌렀을 때 때 house name,현재 시간,별,이름을 ReviewWrite card로 복사
							if(readNameProc(data.data) == true){
								//System.out.println("readNameProc(data.data)");
							}
						}
						//마우스 더블 클력했을 때 등록된 House_itx 에 해당하는 house name,현재 시간,별,이름을 ReviewWrite card로 복사
						//content 내용을  ReviewWrite card로 복사
						else if(selName == 2) { 
												
							readNameProc(data.data,House_itx);
							selName = 1;
							//System.out.println("readNameProc(data.data,House_itx) list 완료");
										
							//선택한 리스트 리뷰 보기 화면으로 값을 복사
							int	row = table.getSelectedRow();
							if(row == -1) {
								return;
							}
							int no = (int)table.getValueAt(row, 0);
							Review_itx = no;//테이블의 등록 번호를 저장함
							int house_itx = (int)table.getValueAt(row, 1);
							//db에서 tb_house 테이블에서 idx,name 값을 요청함
							House_itx = house_itx;
							System.out.println("house_itx ^^="+ house_itx);
							String  name = (String) table.getValueAt(row, 2);//테이블에서 name 가져옮
							String	title= (String) table.getValueAt(row, 3);//테이블에서 title 가져옮
							
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
						System.out.println("DEF_READ_HOUSENAME_LIST 응답이 없습니다");
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
		                  
			              //System.out.println("별 수량"+ ReviewWrite.Review_star);
			              }
			           });
			    			  
	    starRater.setSelection(ReviewWrite.Review_star);
		//clP4.add(starRater);
		  
		 */
		
		firstP  =  new ReviewPanel(this); //review 리스트 페이지 생성
		secondP  =  new ReviewWrite(this);//review 등록 페이지 생성(신규 리뷰 등록)
		thirdP =  new ReviewModify(this); //review 보기(수정,제거) 페이지
		fourthP  =  new MemModify(this);  //review에서 회원 정보 수정 페이지
		
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
			
			ShowReview();//리뷰 리스트 화면 UI 보여줌
			setEvent();	 //리뷰 리스트 이벤트 설정			
			allProc();					
			
		}		
		
		//Main Page 보여줌
		private void ShowReview(){
			
			this.setLayout(new BorderLayout());
			setField();//
			JPanel topP = new JPanel(new GridLayout(2,1));//dummy
			topP1 = new JPanel();
			topP1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			JLabel topLabel = new JLabel("REVIEW 글 목록");		
			topP1.add(topLabel);
			topP.add(topP1);
			//topP.setBackground(Color.CYAN);
			//topP1.setBackground(Color.CYAN);
						
			
			botP = new JPanel(new BorderLayout(10,10));
			JPanel botP1 = new JPanel(new FlowLayout());
			regB = new JButton("등록");	
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
										
					//System.out.println("로그인 이름=" +user_name);
					//할일 
					//1.테이블의 내용을 읽어 Review 보기 화면과 Review 수정 화면으로 복사를 함.
					//2.db에서 tb_house 테이블에서 idx,name 값을 가져와 테이블의 idx값과 일치하는 tb_house의 이름값만 콤보 박스에 복사를 함
					// 이때 콤보박스 쓰기 금지 시킴(보기 화면에서는 수정 못하게 함)
					//3 db에서 글 내용만 읽어오서 textA 영역에 보여줌
					//마우스로 선택한 줄을 알아낸다.	
					int	row = table.getSelectedRow();					
					selTableRow = row;
					if(row == -1) {
						return;
					}
					int no = (int)table.getValueAt(row, 0);
					Review_itx = no;//테이블의 등록 번호를 저장함
					int house_itx = (int)table.getValueAt(row, 1);
					House_value = house_itx;//테이블의 방번호 기억시킴
					String  name = (String) table.getValueAt(row, 2);//테이블에서 name 가져옮
					String	title= (String) table.getValueAt(row, 3);//테이블에서 title 가져옮
					Date  date =  (Date)table.getValueAt(row, 4);    //테이블에서 reg_date 가져옮
					int	star = (int) table.getValueAt(row, 5);		 //테이블에서 star 가져옮
					ReviewWrite.Review_star = star;					
					//선택한 리스트 리뷰 보기 화면으로 값을 복사
					thirdP.nameF.setText(name);
					thirdP.titleF.setText(title);
					thirdP.dateF.setText(date.toString());
					thirdP.starRater.setSelection(ReviewWrite.Review_star);
					System.out.println(ReviewList.user_name);
					System.out.println(nameF.getText());
					//사용자 체크
					
					if(!ReviewList.user_name.equals(name)){
						//JOptionPane.showMessageDialog(main.mainP, "타인의 글은 읽을수  없습니다.");
						thirdP.modifyB.setEnabled(false);
						thirdP.removeB.setEnabled(false);
						//starRater.setEnabled(false);
						//return;
						
					}
					else{
						//JOptionPane.showMessageDialog(main.mainP, "수정 하시겠습니까?");
						thirdP.modifyB.setEnabled(true);
						thirdP.removeB.setEnabled(true);
						//starRater.setEnabled(true);
					}
					
					//DB에 name을 요청함
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
						//System.out.println("로그인 이름=" +user_name);
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
		String[] title = {"번호","방번호", "작성자", "제목", "작성일", "평점"};//게시글은 안보여		
				
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
		
		table = new JTable(model);//테이블에 모델 객체 삽입	
		JScrollPane sp1 = new JScrollPane(table);//스크롤 추가		
		return sp1;
		
	}
//	단추를 만들어주는 전담 함수
	public void setButton() {
	
		regB  = new JButton("  등록  ");			
		
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
		noF.setText("");//번호
		nameF.setText("");//작성자
		titleF.setText("");//제목
		dateF.setText("");//작성일자
		avgF.setText("");//평점
		textA.setText("");//평점
		
	}
//	테이블 값 제거
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
			    date = (Date)res.data.get(i).reg_date;
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
			thirdP.textA.setText(str);// 리뷰내용 보기 화면으로 전달함.
			//System.out.println("content 정상 처리함0");
		}			
		catch(Exception ex){
			//System.out.println("readConProc error");
			ex.printStackTrace();	
		}
		//System.out.println("content 정상 처리함1");
				
		//clearTable();	
	}
	//content 글을 달라고 서버에 요청함.
	private void contentProc(int idx) {
		//System.out.println("content 요청함00");	
		TB_REVIEW temp = new TB_REVIEW();
		RequestReview req = new RequestReview();	
		req.message = Dao.review.R_Constant.DEF_READ_CONTENT_LIST;
	    temp.idx = idx;
		req.data =  temp;	
		//System.out.println("idx= "+idx);	
		Write(req);
		
		//System.out.println("content 요청함11");
	}
	
	//등록 버튼 누르면  
	private boolean readNameProc(Vector<TB_HOUSE> r) {
		//System.out.println("readNameProc1");
		//테이블의 idx값과 일치하는 tb_house의 이름값만 콤보 박스에 복사를 함
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
		//현재 시간 가져 오기
		
		String str = DateToString(new Date());
		//String str = DateToStringTime(new Date());
		//System.out.println("현재 시간="+str);	
		secondP.dateF.setText(str.toString());
		card.show(mainP, "SUB1");//리뷰 등록 화면 전환
		//System.out.println("readNameProc3");
		return true;
		
	}
	//더블 클릭 했을 때 ,테이블의 내용을 읽음 
	private void readNameProc(Vector<TB_HOUSE> r,int itx) {
		//System.out.println("readNameProc11");
		
		System.out.println("House_itx ="+House_itx);
		//테이블의 idx값과 일치하는 tb_house의 이름값만 콤보 박스에 복사를 함
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
			System.out.println("일치하는 house_name이 없습니다.");
		}
		//DB에서 글 내용을 요청함				
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
		//할일
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
		//System.out.println("업데이트 요청 함");		
		
	}
	
	//리뷰 정보 제거 전담 함수
	public void deleteProc() {
		//		할일
		TB_REVIEW temp = new TB_REVIEW();		
		RequestReview req = new RequestReview();				
		temp.idx = ReviewList.Review_itx;//Integer.parseInt("10");
		System.out.println("itx"+ReviewList.Review_itx+"삭제");
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




