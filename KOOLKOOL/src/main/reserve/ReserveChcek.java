package main.reserve;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import main.MenuBar;


public class ReserveChcek extends JFrame {

	DefaultTableModel model;
	JButton deleteB;
	JTable table;
	MenuBar menubar;
	
	public ReserveChcek() {

		String[] title = {"��ȣ", "�̸�", "ID", "ȸ���̸�","�̸���","�����","�������"};
		
		model = new DefaultTableModel(title, 0);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		deleteB = new JButton("���º���");
	
	
		JPanel hapP = new JPanel(new FlowLayout());
	
		hapP.add("Center", sp);
		hapP.add("South", deleteB);
		
	
		
		this.add("Center",hapP);
		
		menuSet();
		this.setSize(500,600);
		this.setVisible(true);
		
		
	}
	
	public void menuSet(){
		menubar = new MenuBar();
		
		this.add("North",menubar);
	}

	public static void main(String[] args) {
		new ReserveChcek();
	}

}
