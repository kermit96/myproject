package main.board;


import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.event.*;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import util.ClientBase;
import util.ClientConnect;
import util.ClientError;
import util.ClientRead;
import Dao.Constant;
import Dao.ResponseMember;
import Dao.board.BoardConstant;
import Dao.board.BoardSql;
import Dao.board.RequestBoard;
import Dao.board.ResponseBoard;
import Dao.board.TB_BOARD;
import main.Global;

import java.sql.*;
import java.util.ArrayList;

import main.*;

public class PWriteBorder extends JPanel {

	JLabel mainL, titleL, wrL;
	JButton listB, addB, cencelB;
	JTextField titleF;
	JTextArea area;
	CardLayout card;

	PMainMenu main;
	MainPanel mainPP;
	JPanel ListP;
	ImageIcon icon1;

	// DB
	//JDBCDao db;
	
	Connection con;

	PreparedStatement addS, allS, showS;
	ResultSet rs;
	Register register;
	
	
	ClientConnect connect;
	ClientRead  reader;
	ClientError generalerror;
	ClientError connecterror;
	
	
	
	ClientBase Serverconnect = Global.getInstance().serverConnect;

	public PWriteBorder(PMainMenu m) {
		main = m;

		
		setConnect();
		
		setLayout(new BorderLayout());

//		icon1 = new ImageIcon("src/P1123/white.png");

		mainL = new JLabel("공지사항 등록");
		titleL = new JLabel("제목", JLabel.LEFT);
		wrL = new JLabel("내용", JLabel.LEFT);

		titleF = new JTextField(" ", 39);
		area = new JTextArea(22,39);
		JScrollPane sp = new JScrollPane(area);
		
		listB = new JButton("목록");
		addB = new JButton("추가");
		cencelB = new JButton("취소");

		// ////////////////////// 이벤트 들어가는 부분
		ButtonEvent1 evt = new ButtonEvent1();

		listB.addActionListener(evt);
		addB.addActionListener(evt);
		cencelB.addActionListener(evt);

		JPanel ButtonP1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 160, 0));

		ButtonP1.add(listB);

		JPanel ButtonP2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));

		ButtonP2.add(addB);
		ButtonP2.add(cencelB);

		JPanel ButtonP3 = new JPanel(new FlowLayout(90, 0, 0));
		ButtonP3.add("West", ButtonP1);
		ButtonP3.add("East", ButtonP2);

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

		JPanel LastP = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		LastP.add(textP3);
		LastP.add(areaP3);
		LastP.add(ButtonP3);

		this.add("Center", LastP);

		//initDB();
		
		String g_userid = Global.getInstance().getUserid();
		
		System.out.println(g_userid);
		
		showProc();

	}



	private void removeField() {

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
	         
	         if (data.message == BoardConstant.DEF_INSERT)
	         {
	            ProcessInsertBoard(data);            
	         }
	         
	         if (data.message == BoardConstant.DEF_SHOW) 
	         {
	        	 realshowProc(data);
	        	 
	         }
	         
	      }
	   
	   }
	   
	   public void ProcessInsertBoard(ResponseBoard res) {
		      
		   
			if (res.result == true) {
				JOptionPane.showMessageDialog(main.pmainPP, "추가되었습니다");
				PWriteBorder.this.setVisible(false);
				// main.mainPP.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(main.pmainPP, "오류가 발생했습니다");
			}
			removeField();
//			main.pmainPP.clearTable();
			main.pmainPP.showProc();
		   
	   }


	private void addProc() {
		// 텍스트 필드의 내용을 알아온다.

		int kind = JOptionPane.showConfirmDialog(main.pmainPP, "추가 하시겠습니까?",
				"추가", JOptionPane.YES_NO_OPTION);
		if (kind != JOptionPane.YES_NO_OPTION) 
			return;
		
		String title = titleF.getText().trim();

		String content = area.getText().trim();

		
		RequestBoard request = new RequestBoard();
		
		request.data = new TB_BOARD();
		
		request.message = BoardConstant.DEF_INSERT;
		
		request.data.title = title;
		request.data.content = content;
		
		Serverconnect.write(request);
		/*
		BoardSql sql = new BoardSql();
		
		ResponseBoard res = sql.AddBoard(request.data);
		
			JOptionPane.showConfirmDialog(main.pmainPP, "추가 하시겠습니까?",
					"추가", JOptionPane.YES_NO_OPTION);

			if (res.result == true) {
				JOptionPane.showMessageDialog(main.pmainPP, "추가되었습니다");
				PWriteBorder.this.setVisible(false);
				// main.mainPP.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(main.pmainPP, "오류가 발생했습니다");
			}
			removeField();
			main.pmainPP.clearTable();
			main.pmainPP.showProc();
*/
	
		}

	

	private void realshowProc(ResponseBoard res ) 
	{
		
		for( int i=0;i<res.data.size();i++ ) {
			
			Object[] data = new Object[6];

			data[0] = res.data.get(i).idx;
			data[1] = res.data.get(i).title;
			data[2] = res.data.get(i).content;
			data[3] = res.data.get(i).admin_id;
			data[4] = res.data.get(i).reg_date;
			data[5] = res.data.get(i).hit;
			
			main.pmainPP.model.addRow(data);

		}
		
	}
	
	private void showProc() {
		
		RequestBoard req = new  RequestBoard();		
		req.message = BoardConstant.DEF_SHOW; 		
		
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
				
				main.pmainPP.model.addRow(data);

				
			}

			
		} 

			catch (Exception e) {
		}


     */
	}
	

	class ButtonEvent1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			if (target == listB) {
				main.pmainPP.card.show(main.pmainPP.WriteP, "Table");
			} else if (target == addB) {
				addProc();
			} else if (target == cencelB) {

				PWriteBorder.this.setVisible(false);

				main.pmainPP.setVisible(true);

			}
		}
	}

}
