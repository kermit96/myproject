package main;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.event.*;
import	javax.swing.border.*;

import util.ScreenLocation;
import main.reserve.*;

public class MainMenu extends MyJFrame{
	
	JButton reserB, reserokB, myB, reviewB;
	
	PMenuBar menubar;
	ReserveWrite reserve;
	ReserveInfo	 info;
	MyInfo myInfo;
	
	Global g_global = Global.getInstance();
	ScreenLocation screen;
	int x = 0, y = 0;
	
	public void Write (Serializable obj) 
	{
		
		g_global.serverConnect.write(obj);
	}
	
	
	public MainMenu() {
		
		util.ClientRead reader = new util.ClientRead() {

			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub
			}
		};
		
		// 등록
		//g_global.serverConnect.AddClientRead(reader);
		
		
		reserB = new JButton("예약하기");
		reserokB = new JButton("예약확인");
		myB = new JButton(" 공 백 ");
		reviewB = new JButton("REVIEW");

		
		ButtonEvent evt = new ButtonEvent();
		
		reserB.addActionListener(evt);
		reserokB.addActionListener(evt);

		JPanel MenuB1 = new JPanel(new GridLayout(2,2 ,40, 40));
		
		MenuB1.add(reserB);
		MenuB1.add(reserokB);
		MenuB1.add(myB);
		MenuB1.add(reviewB);
		
		JPanel MenuB2 = new JPanel(new BorderLayout());

		MenuB2.add("Center",MenuB1);
		
		
		MenuB2.add("North", new JPanel(){
			public Dimension getPreferredSize() {
				return new Dimension(100, 30);
			}
		});

		MenuB2.add("South", new JPanel(){
			public Dimension getPreferredSize() {
				return new Dimension(100, 30);
			}
		});
		
		MenuB2.add("West", new JPanel(){
			public Dimension getPreferredSize() {
				return new Dimension(40, 10);
			}
		});
		MenuB2.add("East", new JPanel(){
			public Dimension getPreferredSize() {
				return new Dimension(40, 10);
			}
		});
		
		JPanel MenuB3 = new JPanel(new BorderLayout());
		MenuB3.add("Center", MenuB2);
		
		this.add("Center",MenuB3);
		
		MenuSet();
		this.setSize(500,500);
		this.setVisible(true);
		
		screen = new ScreenLocation();
		screen.setScreen();
		this.setLocation(screen.x, screen.y);
		
		
		
	}
	
	public void MenuSet(){
		
		menubar = new PMenuBar();
		
		this.add("North",menubar);
	}

	public static void main(String[] args) {
		new MainMenu();
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
				myInfo = new MyInfo();
			}
			else if(target == reviewB){
				
			}
		}
	}

}
