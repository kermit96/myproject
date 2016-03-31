package main.house;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

import com.netty.client.ClientRead;

import javax.swing.border.*;

import main.Global;
import Dao.Constant;

import java.io.*;

import Dao.house.HouseConstant;
import Dao.house.HouseSql;
import Dao.house.RequestHouse;
import Dao.house.ResponseHouse;
import Dao.house.TB_HOUSE;

import java.io.Serializable;
import java.sql.*;


public class house_add extends JPanel{
	
	JTextField	nameT,exponentT,calT, calT2, calT3, emailT ,addressT ,title;
	JTextArea remark;
	JLabel logo;
	JButton roomaddB,addB,cancelB;
	Choice telCombo, emailCombo;
	
 	ImageIcon 	icon, icon2;
	Image		img = null;
	public String      fname;
	public String      fdir;
	FileDialog   fd;
	CardLayout card;
	house_management main;
	mainP houseP;
	Global g_global = Global.getInstance();
	ClientRead reader ;
	
	// ó���� �Լ��� ����Ѵ�.
	
	public house_add(house_management m) {
		
		main= m;
		
		this.setLayout(new BorderLayout());
		
		JPanel FieldP=setField();
		JPanel ButtonP=setButton();
		
		//�������г� �̹�����
		icon = new ImageIcon("resources/main/default.gif");
		logo = new JLabel(icon);
		logo.setPreferredSize(new java.awt.Dimension(250, 250));
		logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		// �̹���
		ImgChange evt = new ImgChange();
		JPanel p16 = new JPanel();
		p16.setBorder(new TitledBorder(new LineBorder(Color.black,2),"��������"));
		logo.addMouseListener(evt);
		p16.add(logo);

		JPanel p00 = new JPanel();
		p00.add("Center", p16);
		
		this.add("West",FieldP);
		this.add("South",ButtonP);
		this.add("Center",p00);
		
		setSize(600,500);
		setVisible(true);
		
		
		reader = new ClientRead() {
	    	@Override
	    	public void run(Object obj) {
	    		// TODO Auto-generated method stub
	    		
	    		if (obj instanceof ResponseHouse) {
	    			ResponseHouse res = (ResponseHouse)obj;
	    			ProcessHouse(res);
	    		}
	    	}  
	      };
	  	g_global.serverConnect.AddClientRead(reader);
	}

	public JPanel setField(){
		
		JLabel nameL=new JLabel("�̸�",JLabel.LEFT);
		JLabel exponentL=new JLabel("��ǥ��",JLabel.LEFT);
		JLabel calL=new JLabel("��ȭ��ȣ",JLabel.LEFT);
		JLabel emailL=new JLabel("�̸���",JLabel.LEFT);
		JLabel addressL=new JLabel("�ּ�",JLabel.LEFT);
		JLabel remarkL=new JLabel("���",JLabel.LEFT);
		JLabel title=new JLabel("���� �ü�");
		title.setHorizontalAlignment(JLabel.CENTER);
		add(title,BorderLayout.NORTH);
		//JLabel hL=new JLabel("���ڽü�",JLabel.TOP);
		
		nameT=new JTextField(15);
		exponentT=new JTextField(15);
		calT= new JTextField();
		calT2 = new JTextField(3);
		calT3 = new JTextField(3);
		emailT=new JTextField(5);
		
		addressT=new JTextField(15);
		remark=new JTextArea(5,4);
		telCombo = new Choice();
		emailCombo = new Choice();
		calT2.setDocument(new JTextFieldLimit(4));
		calT3.setDocument(new JTextFieldLimit(4));
		
		//remark.setText(" ");
		//remark.select(49,53);
	
		JScrollPane pane=new JScrollPane(remark);
		
		JPanel p1=new JPanel(new GridLayout(5,1,15,15));
		p1.add(nameL);
		p1.add(exponentL);
		p1.add(calL);
		p1.add(emailL);
		p1.add(addressL);
		//p1.add(remarkL);
		
		JPanel telPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		telCombo.addItem("010");
		telCombo.addItem("011");
		telCombo.addItem("017");
		telCombo.addItem("018");
		telPanel.add(telCombo);
		telPanel.add(new Label("-"));
		telPanel.add(calT2);
		telPanel.add(new Label("-"));
		telPanel.add(calT3);
		
		
		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		emailCombo.addItem("naver.com");
		emailCombo.addItem("hanmail.net");
		emailCombo.addItem("nate.com");		
		emailPanel.add(emailT);
		emailPanel.add(new Label("@"));
		emailPanel.add(emailCombo);
		
		// ������ �ʵ� �гο� ���� �� ��
		
		JPanel p2=new JPanel(new GridLayout(5,1,15,15));
		p2.add(nameT);
		p2.add(exponentT);
		p2.add(telPanel);
		p2.add(emailPanel);
		p2.add(addressT);
		
		JPanel p00=new JPanel(new BorderLayout());
		p00.add("West",remarkL);
		p00.add("Center",pane);
		
		JPanel p3=new JPanel(new BorderLayout(10,10));
		p3.add("West",p1);
		p3.add("Center",p2);
		p3.add("South",p00);
		
		
		JPanel	p4 = new JPanel(new BorderLayout());
		p4.add("North", p3);
		
		return p4;
	}


	public JPanel setButton(){
			roomaddB=new JButton("���");
			addB=new JButton("�߰�");
			cancelB=new JButton("���");
			ButtonEvent evt = new ButtonEvent();
			roomaddB.addActionListener(evt);
			addB.addActionListener(evt);
			cancelB.addActionListener(evt);
			JPanel p = new JPanel(new FlowLayout());
			p.add("Wast",roomaddB);
			p.add("East",addB);
			p.add("Center",cancelB);
			return p;
	}
	
	public void openProc() {
		this.fd = new FileDialog(this.fd, "���� �ҷ�����", FileDialog.LOAD);
		fd.setVisible(true);
		fname = fd.getFile();
		fdir= fd.getDirectory(); 
		
		if (fname == null || fname.length() == 0) {
			logo.setIcon(icon);
			return;
		}
		
		showimg();
	}

	// ���� �ü� ��� ���μ��� 
	
	public void ProcessHouse(ResponseHouse res) {
		
		// ��ϵ��� �� ó�� 
		
		if (res.message == HouseConstant.DEF_INSERT) {
			
			
			if (res.result == true) {
				
				JOptionPane.showMessageDialog(main.houseP, "�߰��Ǿ����ϴ�");
				
				main.houseP.titleL.setText("���� �ü� ���");
				main.houseP.showProc();
				house_add.this.setVisible(false);
				removeField();
				
			} else {
				
				
				JOptionPane.showMessageDialog(this, res.reason.toString());   
			}
		}
	}
	
	public void Write (Serializable obj) 
	{
		g_global.serverConnect.write(obj);
	}
	
	public void addProc() {
		
		String name = nameT.getText().trim();
		String host_name = exponentT.getText().trim();
		String tel = telCombo.getSelectedItem() + "-" + calT2.getText().trim() + "-" + calT3.getText();
		String email =emailT.getText().trim() + "@" + emailCombo.getSelectedItem();
		String address = addressT.getText().trim();
		String home_intro = remark.getText().trim();
		
		fname = fd.getFile();
		
		RequestHouse request = new RequestHouse();
		
		request.data =  new TB_HOUSE();
		
		request.message = HouseConstant.DEF_INSERT;
		
		request.data.name = name;
		request.data.host_name = host_name;
		request.data.host_tel = tel;
		request.data.host_email = email;
		request.data.address = address;
		request.data.home_intro = home_intro;
		request.data.file_name = fname;
		
		// �̹��� ���ε�
		
		String currFile = fd.getFile();
		File file = new File(fd.getDirectory(),currFile);
		String filePath = file.getPath();
		
		long len = file.length();
		
		byte[] buff2 = new byte[(int) len];
		FileInputStream fin = null;
 
		try {

			fin = new FileInputStream(file);
			fin.read(buff2);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
			fin.close();

			} catch (Exception e2) {
				
			}
		}
		
		request.data.buff = buff2;
		
		File upFile = new File("resources/house/", fname);
		
		FileOutputStream fout = null;
		
		try {
			fout = new FileOutputStream(upFile);
			fout.write(request.data.buff);
			fout.flush();
			
		} catch (Exception e) {
			
		} finally {
			
		
			try {
				fout.close();
			} catch (Exception e2) {
				
			}
		
		}
		
		Write(request);
	}
	
	public void showimg() {
		ImageIcon newIcon = new ImageIcon(fdir + fname);
		logo.setIcon(newIcon);

	}
	class ImgChange extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel target = (JLabel) e.getSource();	
			target.removeAll();
			openProc();
		}
	}
	private void removeField() {

		nameT.setText("");
		exponentT.setText("");
		calT.setText("");
		emailT.setText("");
		addressT.setText("");
		remark.setText(" ");
		// �̹��� �ø��Ŀ� �ٽ� �̹��� �⺻ �̹����� �ʱ�ȭ
		logo.setIcon(icon);
		
	}
	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			if (target == roomaddB) {
				
				main.houseP.card.show(main.houseP.WriteP, "Table");
				main.houseP.titleL.setText("���� �ü� ���");
				removeField();
				
			} else if (target == addB) {
				int kind = JOptionPane.showConfirmDialog(main.houseP, "�߰� �Ͻðڽ��ϱ�?","�߰�",JOptionPane.YES_NO_OPTION);
				
				if (kind == 0) {
					
					// ��� ���μ��� ����
					addProc();
				}
				else {
					JOptionPane.showMessageDialog(main.houseP, "���");
				}
				
			} else if (target == cancelB) {

				//house_add.this.setVisible(false);
				//main.houseP.setVisible(true);
				removeField();
			}
		}
	}
	
	@SuppressWarnings("serial")
	class JTextFieldLimit extends PlainDocument {

		int limit;

		public JTextFieldLimit(int limit) {

			super();
			this.limit = limit;

		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {

			if (str == null) {
				return;
			}

			if (getLength() + str.length() <= limit)
				super.insertString(offset, str, attr);
		}
	}
}


