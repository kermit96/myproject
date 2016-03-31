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

public class modifyBorder extends JPanel {

	JLabel mainL, titleL, wrL;
	JButton listB, modifyB, deleteB; 
	JTextField titleF;
	JTextArea area;
	CardLayout card;

	int no = 0;
	int idx;

	MainBorder main;
	MainPanel mainPP;
	JPanel ListP;
	ImageIcon icon1;

	//DB
	//JDBCDao	db;
	Connection con;

	PreparedStatement modifyS,selectS,showS,deleteS;
	ResultSet rs;

	Register register;


	ClientRead reader;
	ClientBase Serverconnect = Global.getInstance().serverConnect;

	public modifyBorder(MainBorder m ) {
		main = m;

		setLayout(new BorderLayout());



		mainL = new JLabel("공지사항 등록");
		titleL = new JLabel("제목", JLabel.LEFT);
		wrL = new JLabel("내용", JLabel.LEFT);

		titleF = new JTextField(" ", 39);
		area = new JTextArea(22,39);
		JScrollPane sp = new JScrollPane(area);

		listB = new JButton("목록");
		modifyB = new JButton("수정");
		deleteB = new JButton("삭제");


		//////////////////////// 이벤트 들어가는 부분
		ButtonEvent1 evt = new ButtonEvent1();

		listB.addActionListener(evt);
		modifyB.addActionListener(evt);
		deleteB.addActionListener(evt);


		JPanel ButtonP1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 160,0));

		ButtonP1.add(listB);

		JPanel ButtonP2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));

		ButtonP2.add(modifyB);
		ButtonP2.add(deleteB);


		JPanel ButtonP3 = new JPanel(new FlowLayout(90,0,0));
		ButtonP3.add("West",ButtonP1);
		ButtonP3.add("East",ButtonP2);

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



		this.add("Center",LastP);


		reader = new ClientRead() {

			@Override
			public void run(Object obj) {
				// TODO Auto-generated method stub

				try {

					if (obj instanceof ResponseBoard) {					
						ResponseBoard data = (ResponseBoard)obj;  	  
						Read(data);
					}
				} catch(Exception ex) {

					ex.printStackTrace();  
				}


			}
		};

		Serverconnect.AddClientRead(reader);

	}



	private void removeField(){

		titleF.setText("");
		area.setText("");
	}





	public void Read(ResponseBoard data ) {

		System.out.println("resboard=="+data.message);

		if (data.message == BoardConstant.DEF_DELETE)
		{
			realdeleteProc(data);            
		}

		if (data.message == BoardConstant.DEF_UPDATE)
		{
			ProcessModifyBoard(data);            
		}

		if(data.message == BoardConstant.DEF_HIT  ) 
		{
			
			
		}
		
	}

	public void ProcessModifyBoard(ResponseBoard res) {
		if (res.result == true) {
			JOptionPane.showMessageDialog(main.mainPP, "수정되었습니다");
			System.out.println(res.result);

			Close();
			modifyBorder.this.setVisible(false);
			removeField();		
			main.mainPP.showProc();

		} else {
			JOptionPane.showMessageDialog(main.mainPP, "오류가 발생했습니다");
		}


	}

	private void modifyProc(){
		// 텍스트 필드의 내용을 알아온다.

		String title = titleF.getText().trim();	
		String content = area.getText().trim();

		int idx3 = main.mainPP.g_idx;


		RequestBoard request = new RequestBoard();

		request.data = new TB_BOARD();

		request.message = BoardConstant.DEF_UPDATE;

		request.data.idx = main.mainPP.g_idx;


		request.data.title = title;
		request.data.content = content;

		Serverconnect.write(request);

		/*
		BoardSql sql = new BoardSql();

		ResponseBoard res = sql.ModifyBoard(request.data);



			JOptionPane.showConfirmDialog(main.mainPP, "수정 하시겠습니까?","수정",JOptionPane.YES_NO_OPTION);


			if (res.result == true) {
				JOptionPane.showMessageDialog(main.mainPP, "수정되었습니다");
				System.out.println(res.result);
				modifyBorder.this.setVisible(false);

			} else {
				JOptionPane.showMessageDialog(main.mainPP, "오류가 발생했습니다");
			}

			removeField();
			main.mainPP.clearTable();
			main.mainPP.showProc();
		 */

	}

	public void RealSelectProc(ResponseBoard res  ) 
	{
		RequestHit();		 
	}

	private void RequestHit() {
		// TODO Auto-generated method stub

		RequestBoard req = new RequestBoard();
		
		req.message = BoardConstant.DEF_HIT;
		
		TB_BOARD temp = new TB_BOARD();

		temp.idx = idx;
		req.data = temp;
		Write(req);

	}

	private void Close()
	{
		Serverconnect.RemoveClientRead(reader);

	}

	private void Write(Serializable obj)
	{
		Serverconnect.write(obj);	   
	}

	public void RealHitProc(ResponseBoard res  ) 
	{
		removeField();
		main.mainPP.clearTable();
		main.mainPP.showProc();	 
	}

	public void selectProc(int idx){

		RequestBoard res = new RequestBoard();

		TB_BOARD temp = new TB_BOARD();

		temp.idx = idx;        		
		res.data = temp;
		res.message = BoardConstant.DEF_LIST;
		Write(res);  

	}


	public void realdeleteProc(ResponseBoard res)
	{

		if (res.result == true) {
			JOptionPane.showMessageDialog(main.mainPP, "삭제되었습니다");
			modifyBorder.this.setVisible(false);
			Close();

		} else {
			JOptionPane.showMessageDialog(main.mainPP, "오류가 발생했습니다");
		}

		main.mainPP.showProc();
	}

	public void deleteProc(){

		int  kind =  JOptionPane.showConfirmDialog(main.mainPP, "삭제 하시겠습니까?","수정",JOptionPane.YES_NO_OPTION);

		if (kind != JOptionPane.YES_NO_OPTION ) {			
			return;
		}

		RequestBoard request = new RequestBoard();
		request.data = new TB_BOARD();

		request.message = BoardConstant.DEF_DELETE;

		request.data.idx = main.mainPP.g_idx;

		Write(request);

	}


	class ButtonEvent1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			if(target == listB){
				main.mainPP.card.show(main.mainPP.WriteP,"Table");
			}
			else if(target == modifyB){
				modifyProc();
			}
			else if(target == deleteB){
				
				deleteProc();
				
			}
		}
	}

}
