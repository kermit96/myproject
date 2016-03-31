package main.board;


import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.event.*;

import main.Global;
import main.Register;
import util.ClientBase;
import util.ClientConnect;
import util.ClientError;
import util.ClientRead;
import Dao.Constant;
import Dao.board.BoardConstant;
import Dao.board.BoardSql;
import Dao.board.RequestBoard;
import Dao.board.ResponseBoard;
import Dao.board.TB_BOARD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import main.*;

public class PmodifyBorder extends JPanel {

	
	PMainMenu main;
	JLabel mainL, titleL, wrL;
	JButton listB, modifyB, deleteB; 
	JTextField titleF;
	JTextArea area;
	CardLayout card;
	
	int no = 0;
	int idx;
	
	
	MainPanel mainPP;
	JPanel ListP;
	ImageIcon icon1;
	
	//DB
	//JDBCDao	db;
	Connection con;
	
	PreparedStatement modifyS,selectS,showS,deleteS;
	ResultSet rs;
	
	Register register;
	
	ClientConnect connect;
	ClientRead  reader;
	ClientError generalerror;
	ClientError connecterror;
	
	
	ClientBase Serverconnect = Global.getInstance().serverConnect;
	
	public PmodifyBorder(PMainMenu m ) {
		main = m;
		
		setLayout(new BorderLayout());

//		icon1 = new ImageIcon("src/P1123/white.png");

		mainL = new JLabel("공지사항 등록");
		titleL = new JLabel("제목", JLabel.LEFT);
		wrL = new JLabel("내용", JLabel.LEFT);

		titleF = new JTextField(" ", 39);
		area = new JTextArea(22,39);
		
		
		JScrollPane sp = new JScrollPane(area);
		
		listB = new JButton("목록");
	
		
		//////////////////////// 이벤트 들어가는 부분
		ButtonEvent1 evt = new ButtonEvent1();
		
		listB.addActionListener(evt);	
		
		JPanel ButtonP1 = new JPanel(new BorderLayout());

		ButtonP1.add(listB);
		

		JPanel ButtonP3 = new JPanel(new FlowLayout(0,0,0));
		ButtonP3.add("Center",ButtonP1);
		
		JPanel textP1 = new JPanel(new GridLayout(1, 1));
		textP1.add(titleL);
		JPanel textP2 = new JPanel(new GridLayout(1, 1));
		textP2.add(titleF);

		JPanel areaP1 = new JPanel(new GridLayout(1, 1));
		areaP1.add(wrL);

		JPanel areaP2 = new JPanel(new GridLayout(1, 1));
		areaP2.add(sp);

		JPanel textP3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		textP3.add(textP1);
		textP3.add(textP2);

		JPanel areaP3 = new JPanel(new FlowLayout());

		areaP3.add(areaP1);
		areaP3.add(areaP2);


		JPanel LastP = new JPanel(new FlowLayout());
	

		LastP.add(textP3);
		LastP.add(areaP3);
		LastP.add(ButtonP3);
		
		this.add("Center",LastP);


	}
	
	
	
	private void removeField(){
		
		titleF.setText("");
		area.setText("");
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
               } catch (Exception ex) {
            	   
            	   ex.printStackTrace();
               }
	         }

	      };

	      Serverconnect.AddClientRead(reader);
	      
	   }
	   public void Read(Object obj ) {

	  

	      if (obj instanceof Dao.board.ResponseBoard) {
	         
	         ResponseBoard data = (ResponseBoard)obj;
	         
	         Process(data);
	      }
	   
	   }
	   
	   public void Process(ResponseBoard res) {
		   
		   System.out.println("상황");
		   
		   if (res.message == BoardConstant.DEF_DELETE) {
			   realdeleteProc(res);
			   return;
		   }
		   
		   if (res.message == BoardConstant.DEF_UPDATE) {
			   realmodifyProc(res);
			   return;
		   }
		   
		   if (res.message == BoardConstant.DEF_HIT) {
			   realselectProc(res);
			   return;
		   }

	   }
	   
		private void Close()
		{
			Serverconnect.RemoveClientRead(reader);
			
		}
	
	
	private void realmodifyProc(ResponseBoard res)
	{
		
		if (res.result == true) {
			JOptionPane.showMessageDialog(main.pmainPP, "수정되었습니다");
			
			PmodifyBorder.this.setVisible(false);
			Close();
			removeField();
			main.pmainPP.showProc();
						
		} else {
			JOptionPane.showMessageDialog(main.pmainPP, "오류가 발생했습니다");
		}
		
		

		
				
	}
	
	
	private void modifyProc(){
		// 텍스트 필드의 내용을 알아온다.
		
		
		int kind = JOptionPane.showConfirmDialog(main.pmainPP, "수정 하시겠습니까?","수정",JOptionPane.YES_NO_OPTION);
		

		if (kind != JOptionPane.YES_NO_OPTION) {
			
			return;
		}
		
		
		String title = titleF.getText().trim();	
		String content = area.getText().trim();
			
		
		RequestBoard request = new RequestBoard();
		
		request.data = new TB_BOARD();
		
		request.message = BoardConstant.DEF_UPDATE;
		
		request.data.idx = main.pmainPP.g_idx;
		
		
		request.data.title = title;
		request.data.content = content;
		
		Serverconnect.write(request);

	}
		
	
	

	public void realselectProc(ResponseBoard res )
	{
		
		removeField();
	//	main.pmainPP.clearTable();
		main.pmainPP.showProc();
	}
	
	public void Write(Serializable obj)
	{
		Serverconnect.write(obj);
		
	}
	
	public void selectProc(int idx){
	
		
	
		RequestBoard req = new RequestBoard();


		TB_BOARD temp = new TB_BOARD();
		
		temp.idx = idx;
		req.message = BoardConstant.DEF_HIT;
		
		Write(req);
		
		/*
		  		 
		 	BoardSql sql = new BoardSql();
		
			res = sql.SelectBoard(temp);
			
			int sidx = res.data.get(0).idx;
			String name = res.data.get(0).title;
			
		
			try{
			res = sql.hitBoard(temp);
			
			for(int i = 0; i<res.data.size(); i++){
				
				Object[] data = new Object[6];
				
				data[0] = res.data.get(i).idx;
				data[1] = res.data.get(i).title;
				data[2] = res.data.get(i).content;
				data[3] = res.data.get(i).admin_id;
				data[4] = res.data.get(i).reg_date;
				data[5] = res.data.get(i).hit;
				
			}
			
			
			
		}catch(Exception e){}
	
			
			removeField();
			main.pmainPP.clearTable();
			main.pmainPP.showProc();

			*/

	}
	
  public void realdeleteProc(ResponseBoard res  )
  {
	
		if (res.result == true) {
			JOptionPane.showMessageDialog(main.pmainPP, "삭제되었습니다");
			PmodifyBorder.this.setVisible(false);
			
		} else {
			JOptionPane.showMessageDialog(main.pmainPP, "오류가 발생했습니다");
		}
		
		removeField();
//		main.pmainPP.clearTable();
		main.pmainPP.showProc();
		
		

     	  
  }
						
	public void deleteProc(){
	
		
		int kind = JOptionPane.showConfirmDialog(main.pmainPP, "삭제 하시겠습니까?","수정",JOptionPane.YES_NO_OPTION);
		
		if (kind != JOptionPane.YES_NO_OPTION) {			
			return;			
		}
		
		
		TB_BOARD temp = new TB_BOARD();
		
		temp.idx = idx;
		
		RequestBoard request = new RequestBoard();
		
		request.data = new TB_BOARD();
		
		request.message = BoardConstant.DEF_DELETE;
		
		request.data.idx = main.pmainPP.g_idx;
		Write(request);
		
		/*
		 * 
		
		BoardSql sql = new BoardSql();
		
		ResponseBoard res = new ResponseBoard();
		
		res = sql.deleteBoard(request.data);
	
			
			if (res.result == true) {
				JOptionPane.showMessageDialog(main.pmainPP, "삭제되었습니다");
				PmodifyBorder.this.setVisible(false);
				
			} else {
				JOptionPane.showMessageDialog(main.pmainPP, "오류가 발생했습니다");
			}
			
			removeField();
			main.pmainPP.clearTable();
			main.pmainPP.showProc();
			
			*/
		
	
		
	}

	
	class ButtonEvent1 implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
				JButton target = (JButton) e.getSource();
				if(target == listB){
					main.pmainPP.card.show(main.pmainPP.WriteP,"Table");
				}

		}
	}

}
