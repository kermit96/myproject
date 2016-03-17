package main.review;
import	java.awt.*;
import	java.awt.event.*;

import	javax.swing.*;
import	javax.swing.table.*;
import  main.review.*;
//import	JDBC.*;


import	java.sql.*;

public class MemModify extends JPanel  {
	
	JPanel		mainP;
	CardLayout	card;		
	JPanel	southP,centerP,centerP1,centerP2,topP,topP1,topP2,botP ;
	JButton logoB,homeB,infoB,exitB,regB,modifyB,removeB,cancelB ;
	JTextArea textA;
	JLabel test1L,test2L,test3L,test4L,testL,test5L,test6L,test7L;
	JTextField field1F,field2F,field3F,field4F,field5F,field6F,field7F;	
	JScrollPane sp ; 
	ReviewList	main;	
	
	public MemModify(ReviewList m) {
		main = m;
		this.setLayout(new BorderLayout());		
		JScrollPane sp ;//=  setTable(); 
		JPanel topP11 = new JPanel();//dummy
		topP = new JPanel();//new GridLayout(1,4));
		topP.setLayout(new BorderLayout(50,50));
						
		topP1 = new JPanel();//new GridLayout(1,4));
		topP1.setLayout(new FlowLayout(FlowLayout.RIGHT));
				
		topP2 = new JPanel();//new GridLayout(1,4));
		topP2.setLayout(new FlowLayout());		
				
		logoB = new JButton("로고");
		topP1.add(logoB);
		
		homeB = new JButton(" 홈 ");
		infoB = new JButton("정보");
		exitB = new JButton("종료");
	
		topP2.add(homeB);
		topP2.add(infoB);
		topP2.add(exitB);
		
		topP.add("West",topP1);
		topP.add("Center",topP2);
		topP.add("South",topP11);		
		
		botP = new JPanel(new FlowLayout());
		
		modifyB = new JButton("수정");
		removeB = new JButton("회원탈퇴");		
		cancelB = new JButton("취소");
		
		ButtonEvent evt = new ButtonEvent();
		
		logoB.addActionListener(evt);
		homeB.addActionListener(evt);
		infoB.addActionListener(evt);
		exitB.addActionListener(evt);
		modifyB.addActionListener(evt);
		removeB.addActionListener(evt);
		cancelB.addActionListener(evt);
		
		botP.add(modifyB);
		botP.add(removeB);		
		botP.add(cancelB);	
		
		textA = new JTextArea("test",70,0);
		sp = new JScrollPane(textA);
		
		centerP = new JPanel(new BorderLayout());
	
		testL = new JLabel("회원 정보");
		
		test1L = new JLabel("ID",10);
		test2L = new JLabel("PASSWORD",10);
		test3L = new JLabel("PASSWORD",10);
		test4L = new JLabel("NAME",10);
		test5L = new JLabel("PNONE NUM.",10);
		test6L = new JLabel("EMAIL",10);
		test7L = new JLabel("ADDRESS",10);		
	
		field1F = new JTextField("ID",5);
		field2F = new JTextField("PASSWORD",5);
		field3F = new JTextField("PASSWORD",5);
		field4F = new JTextField("NAME",5);
		field5F = new JTextField("PNONE NUM.",5);
		field6F = new JTextField("EMAIL",5);
		field7F = new JTextField("ADDRESS",5);	
		
		centerP1 = new JPanel(new GridLayout(7, 1));	
		
		JPanel centerP111 = new JPanel(new FlowLayout());
		
		JPanel centerP222 = new JPanel(new GridLayout(10,1));
		JPanel centerP333 = new JPanel(new GridLayout(10,1));	
		JPanel centerP444 = new JPanel(new BorderLayout(120,0));	
		JPanel centerP555 = new JPanel(new GridLayout(1,1));	
		
		centerP111.add(testL);
			
		centerP222.add(test1L);
		centerP222.add(test2L);	
		centerP222.add(test3L);
		centerP222.add(test4L);	
		centerP222.add(test5L);	
		centerP222.add(test6L);		
		
		centerP333.add(field1F);		
		centerP333.add(field2F);	
		centerP333.add(field3F);		
		centerP333.add(field4F);
		centerP333.add(field5F);	
		centerP333.add(field6F);		
		
		centerP444.add("West",centerP222);		
		centerP444.add("Center",centerP333);
		centerP444.add("North",centerP111);	
		centerP444.add("East",centerP555);			
		
		
		add("North",topP);
		add("Center",centerP444);
		add("South",botP);		
		
		this.setSize(500, 500);
		this.setVisible(true);	
	}
	class ButtonEvent implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JButton	target = (JButton)e.getSource();
				if(target == logoB) {
					System.out.println("logoB");						
				}
				else if(target == homeB) {
					System.out.println("homeB");	
					main.card.show(main.mainP, "MAIN");
				}
				else if(target == infoB) {
					System.out.println("infoB");	
					main.card.show(main.mainP, "SUB3");
				}
				else if(target == exitB) {
					System.out.println("exitB");
					
				}							
				else if(target == modifyB) {
					System.out.println("modifyB");	
					//main.card.show(main.mainP, "SUB2");
				}
				else if(target == removeB) {
					System.out.println("removeB");							
				}	
				if(target == cancelB) {
					System.out.println("cancelB");
					
				}					
				
			}
	}

}

