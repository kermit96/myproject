package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.house.*;


public class AdminMenu extends MyJFrame {

	JButton houseB, roomB, clientListB, submitListB;
	JLabel logoL;
	house_management house;
	room_management room;
	
	
	public AdminMenu() {

		logoL = new JLabel("�ΰ�");

		houseB = new JButton("���");
		roomB = new JButton("�� ���");
		clientListB = new JButton("ȸ�� ����");
		submitListB = new JButton("��ϳ��� ����");

		JPanel topPanel = new JPanel(new FlowLayout());

		JPanel menuPanel = new JPanel(new GridLayout(2, 2, 40, 40));

		menuPanel.add(houseB);
		menuPanel.add(roomB);
		menuPanel.add(clientListB);
		menuPanel.add(submitListB);

		ButtonEvent evt = new ButtonEvent();
		
		houseB.addActionListener(evt);
		roomB.addActionListener(evt);
		clientListB.addActionListener(evt);	
		submitListB.addActionListener(evt);
		
		JPanel menuPanel2 = new JPanel(new BorderLayout());

		menuPanel2.add("Center", menuPanel);

		menuPanel2.add("North", new JPanel() {
			public Dimension getPreferredSize() {
				return new Dimension(100, 30);
			}
		});

		menuPanel2.add("South", new JPanel() {
			public Dimension getPreferredSize() {
				return new Dimension(100, 30);
			}
		});

		menuPanel2.add("West", new JPanel() {
			public Dimension getPreferredSize() {
				return new Dimension(40, 10);
			}
		});
		menuPanel2.add("East", new JPanel() {
			public Dimension getPreferredSize() {
				return new Dimension(40, 10);
			}
		});

		
		JPanel finalPanel = new JPanel(new FlowLayout());
		
		topPanel.add("North", logoL);

		
		MenuBar MenuBar = new MenuBar();
		
		
		this.add("North",MenuBar);
		this.add("Center",menuPanel2);
		
		this.setTitle("Admin Login Menu");
		this.setSize(500,500);
		this.setResizable(false);
		this.setVisible(true);

	}

	public static void main(String[] args) {

		new AdminMenu();

	}
	
	
	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton target = (JButton)e.getSource();
			
			if( target == houseB) {
				house = new house_management();
			} else if(target == roomB) {
				room = new room_management();
			} else if(target == clientListB) {
				
			} else if (target == submitListB) {
				
			}
			
		}
		
		
	}

}
