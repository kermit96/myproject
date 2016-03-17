package main;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

import Dao.CommonSql;
import Dao.RequestMember;
import Dao.ResponseMember;
import Dao.TB_USER;
import util.*;

public class MyInfo extends JPanel {

	JButton modifyB, deleteB;
	
	JLabel titleL, idL, idCheckL, pwdL, pwdL2, nameL, telL, emailL, addrL;
	JTextField idF, nameF, telF, telF2, telF3, emailF, addrF;
	JPasswordField pwdF, pwdF2;
	Choice telCombo, emailCombo;
	MyJFrame f;
	
	Global g_global = Global.getInstance();
	RequestMember request;
	
	
	
	public void Write (Serializable obj) 
	{
		
		g_global.serverConnect.write(obj);
	}
	
	public MyInfo() {
		
		
		util.ClientRead reader = new util.ClientRead() {

			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		// ���
		//g_global.serverConnect.AddClientRead(reader);
		
		// ���� 
// 		g_global.serverConnect.RemoveClientRead(reader);
		
		
		f = new MyJFrame();
		ScreenLocation screen = new ScreenLocation();
		screen.setScreen();
		f.setLocation(screen.x,screen.y);
		
		modifyB = new JButton("����");
		deleteB = new JButton("����");
		
		titleL = new JLabel(" * ȸ �� �� �� * ");
		idL = new JLabel("ID");
		idCheckL = new JLabel();
		pwdL = new JLabel("PASSWORD");
		pwdL2 = new JLabel("PASSWORD");
		nameL = new JLabel("�̸�");
		telL = new JLabel("��ȭ��ȣ");
		emailL = new JLabel("�̸���");
		addrL = new JLabel("�ּ�");

		idF = new JTextField(10);
		pwdF = new JPasswordField(10);
		pwdF2 = new JPasswordField(10);
		nameF = new JTextField(10);

		telF = new JTextField(3);
		telCombo = new Choice();
		telF2 = new JTextField(3);
		telF3 = new JTextField(3);

		emailCombo = new Choice();
		emailF = new JTextField(5);
		addrF = new JTextField(20);
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(titleL);
		
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idPanel.add(idF);
		
		JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passPanel.add(pwdF);
		
		JPanel passPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passPanel2.add(pwdF2);
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(nameF);

		JPanel labelPanel = new JPanel(new GridLayout(7, 1, 10, 15));
		labelPanel.add(idL);
		labelPanel.add(pwdL);
		labelPanel.add(pwdL2);
		labelPanel.add(nameL);
		labelPanel.add(telL);
		labelPanel.add(emailL);
		labelPanel.add(addrL);
		
		/*StarRater starRater = new StarRater(5, 1, 1);
        starRater.addStarListener(
            new StarRater.StarListener()   {

                public void handleSelection(int selection) {
                    System.out.println(selection);
                }
            });
        setselection(���� ����)
        starRater.setSelection(4);
        
		titlePanel.add(starRater);
		 */	
		JPanel telPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		telCombo.addItem("010");
		telCombo.addItem("011");
		telCombo.addItem("017");
		telCombo.addItem("018");
		telPanel.add(telCombo);
		telPanel.add(new Label("-"));
		telPanel.add(telF2);
		telPanel.add(new Label("-"));
		telPanel.add(telF3);

		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		emailCombo.addItem("naver.com");
		emailCombo.addItem("hanmail.net");
		emailCombo.addItem("nate.com");
		emailPanel.add(emailF);
		emailPanel.add(new Label("@"));
		emailPanel.add(emailCombo);
		
		JPanel addrPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addrPanel.add("West",addrF);
		
		JPanel fieldPanel = new JPanel(new GridLayout(7, 1, 5, 0));

		fieldPanel.add(idPanel);
		fieldPanel.add(passPanel);
		fieldPanel.add(passPanel2);
		fieldPanel.add(namePanel);
		fieldPanel.add(telPanel);
	
		fieldPanel.add(emailPanel);
		fieldPanel.add(addrPanel);

		JPanel idCheckPanel = new JPanel(new GridLayout(7, 1, 20, 15));

		idCheckPanel.add(idCheckL);

		JPanel labelFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

		labelFieldPanel.add("West", labelPanel);
		labelFieldPanel.add("Center", fieldPanel);
		labelFieldPanel.add("East", idCheckPanel);

		ButtonEvent event = new ButtonEvent();
		modifyB.addActionListener(event);
		deleteB.addActionListener(event);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

		buttonPanel.add("West", modifyB);
		buttonPanel.add("East", deleteB);
		
		this.setLayout(new BorderLayout());
		this.add("North", titlePanel);
		this.add("Center", labelFieldPanel);
		this.add("South", buttonPanel);
		
		
/*		idF.setText("hongf");
		pwdF.setText("1234");
		pwdF2.setText("1234");
		nameF.setText("ȫ�浿");
		telCombo.select(0);
		telF2.setText("1234");
		telF3.setText("5678");
		emailF.setText("abc");
		emailCombo.select(0);
		addrF.setText("����� ���α� ���ε�");*/
		
		f.setTitle("ȸ������ Ȯ��");
		f.add(this);
		f.setSize(500, 500);
		f.setVisible(true);
		
	}
	
	
	public void modifyUser() {
		
		
		request = new RequestMember();
		
		request.data = new TB_USER();
		
		request.data.user_id = idF.getText().trim();
		request.data.user_pwd = new String(pwdF.getPassword());
		request.data.user_name = nameF.getText().trim();
		request.data.user_tel = telCombo.getSelectedItem() + "-" + telF2.getText().trim() + "-" + telF3.getText();
		request.data.user_email = emailF.getText().trim() + "@" + emailCombo.getSelectedItem();
		request.data.user_address = addrF.getText().trim();
		
		CommonSql sql = new CommonSql();
		ResponseMember res = sql.UpdateMember(request.data);
		
		if (res.result == true) {
			JOptionPane.showMessageDialog(f, "�����Ǿ����ϴ�");
		} else {
			System.out.println("error:"+ res.reason);
		}
	}
	
	
	
	public void modifyProc() {
		
		int kind = JOptionPane.showConfirmDialog(f,"�����Ͻðڽ��ϱ�","Modify",JOptionPane.YES_NO_OPTION);
		
		if (kind == 0) {
			modifyUser();
		}
	}
	
	public void deleteProc() {
	
		int kind = JOptionPane.showConfirmDialog(f,"�����Ͻðڽ��ϱ�","Delete",JOptionPane.YES_NO_OPTION);
		
		if (kind == 0) {
			
			//deleteUser();
			JOptionPane.showMessageDialog(f, "�����Ǿ����ϴ�");
		}
	}

	public static void main(String[] args) {

		new MyInfo();
	}
	
	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton target = (JButton)e.getSource();
			
			if (target == modifyB) {
				modifyProc();
			} else if(target == deleteB) {
				deleteProc();
			}
					
			
		}
		
	}

}
