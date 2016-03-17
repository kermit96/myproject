package main.board;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import main.board.*;
public class PMenuBar extends JPanel{
	
	JLabel logoL,homeL, exitL, tableL, myinfoL,logoutL,chatL;
	JButton  wrB;
	TextArea area;
	DefaultTableModel model;
	JTable table;
	CardLayout card;
	JPanel WriteP;
	
	ImageIcon logocon;
	ImageIcon bgcon;
	ImageIcon homecon;
	ImageIcon exitcon;
	ImageIcon bordercon;
	ImageIcon myinfocon;
	ImageIcon logoutcon;
	ImageIcon chatcon;
	
	public PMenuBar(){
		

		this.setLayout(new BorderLayout());
		logocon = new ImageIcon("resources/admin/koolkool4.png");
		bgcon = new ImageIcon("resources/user/p_gray.png");
		homecon = new ImageIcon("resources/user/p_home.png");
		exitcon = new ImageIcon("resources/user/p_exit.png");
		bordercon = new ImageIcon("resources/user/p_borad.png");
		myinfocon = new ImageIcon("resources/user/p_myinfo.png");
		logoutcon = new ImageIcon("resources/user/p_logout.png");
		chatcon = new ImageIcon("resources/user/p_chat.png");
		
		logoL = new JLabel(logocon, JLabel.LEFT);
		logoL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		homeL = new JLabel(homecon, JLabel.RIGHT);
		homeL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		myinfoL = new JLabel(myinfocon, JLabel.RIGHT);
		myinfoL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		tableL = new JLabel(bordercon, JLabel.RIGHT);
		tableL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		chatL = new JLabel(chatcon, JLabel.RIGHT);
		chatL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		logoutL = new JLabel(logoutcon, JLabel.RIGHT);
		logoutL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		exitL = new JLabel(exitcon, JLabel.RIGHT);
		exitL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		

		JPanel LogoP1 = new JPanel(new FlowLayout())
		{
			public void paintComponent(Graphics g){
				 Dimension d = getSize();
	                g.drawImage(bgcon.getImage(), 0, 0, d.width, d.height, null);
			}
			
		};
		LogoP1.add("North",logoL);
		
		
		JPanel Label2 = new JPanel(new FlowLayout(FlowLayout.RIGHT,17,0))
		{
			public void paintComponent(Graphics g){
				 Dimension d = getSize();
	                g.drawImage(bgcon.getImage(), 0, 0, d.width, d.height, null);
			}
			
		};
		Label2.add(homeL);
		Label2.add(myinfoL);
		Label2.add(tableL);
		Label2.add(chatL);
		Label2.add(logoutL);
		Label2.add(exitL);
		
		
		JPanel LogoP2 = new JPanel(new BorderLayout());
		LogoP2.add("West",LogoP1);
		
		
		
		JPanel hapP = new JPanel(new FlowLayout(20,0,0))
		{
			public void paintComponent(Graphics g){
				 Dimension d = getSize();
	                g.drawImage(bgcon.getImage(), 0, 0, d.width, d.height, null);
			}
			
		};
		hapP.add(LogoP2);
		hapP.add(Label2);
		
		this.add("North", hapP);

	}
	
/*	public static void main(String[] agrs){
		new PMenuBar();
	}*/
}
