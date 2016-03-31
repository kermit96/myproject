package main;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import util.*;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.netty.client.ClientBase;
import com.netty.client.ClientConnect;
import com.netty.client.ClientError;
import com.netty.client.ClientRead;

import Dao.*;


/**
 * <pre>
 * main
 * Register.java
 * </pre>
 * @author �豹��
 * @Date 2015. 11. 30
 * 
 * @category ȸ������
 */


@SuppressWarnings("serial")
public class Register extends JDialog {

	JButton okB, clearB, exitB;
	JLabel titleL, idL, idCheckL, pwdL, pwdL2, nameL, telL, emailL, addrL;
	JTextField idF, nameF, telF, telF2, telF3, emailF, addrF;
	JPasswordField pwdF, pwdF2;
	Choice telCombo, emailCombo;

	ScreenLocation screen;
	int x = 0, y = 0;


	// �̺�Ʈ  ����   
	ClientConnect  connect ;
	ClientError  generalerror ;
	ClientError  connecterror;
	ClientRead  reader;
	
	
	ClientBase Serverconnect;
	
	public void  ConnectError(Throwable ex) 
	{		
//		Serverconnect.stop();		
		JOptionPane.showMessageDialog(this, ex.toString());
		
	}

	
	public void ConnectRelease()
	{
		
		if(Serverconnect == null)
			return;
		
		Serverconnect.RemoveClientConnect(connect);
		Serverconnect.RemoveClientError(generalerror );
		Serverconnect.RemoveClientRead(reader);
	
		Serverconnect.stop();
		
	}
	
	// ó���� �Լ��� ����Ѵ�.
	public void setConnect()
	{

		if(Serverconnect == null)
			return;
				
		connect = new ClientConnect() {
			@Override
			public void run() {
				Connnect();
			}
		};

		generalerror = new ClientError() {

			@Override
			public void run(Throwable cause) {
				// TODO Auto-generated method stub
				GeneralError(cause);
			}

		};


		connecterror = new ClientError() {

			@Override
			public void run(Throwable cause) {
				// TODO Auto-generated method stub
				ConnectError(cause);
			}

		};

		ClientRead  reader = new ClientRead() {

			@Override
			public void run(Object obj) {
				// TODO Auto-generated method stub

				Read(obj);
			}

		};
		
		Serverconnect.AddClientConnect(connect);
		
		Serverconnect.AddClientError(generalerror );

		Serverconnect.AddClientRead(reader);
		
	}
	
	
	public Register(JDialog frame,String host,int port)
	{
		super(frame);
				
		SettingConnect(host,port);
						
	}
	
	public void SettingConnect(String host,int port)
	{
		Serverconnect = new ClientBase(host,port);		
		this.setConnect();
		this.showRegister();		
	}
	
	public Register(Frame frame,String host,int port)
	{
		super(frame);					
		SettingConnect(host,port);						
	}
	

	public void  GeneralError(Throwable ex) 
	{	
		JOptionPane.showMessageDialog(this, ex.toString());       
	}

	boolean isRegistr = false; 
		
	// user insert  
	private void ConnectRegister()
	{
				 		
		RequestMember request = new RequestMember();
		request.data =  new TB_USER();
		request.message = Constant.DEF_INSERT ;
	//    data.isadmin = false;
		request.data.user_id = idF.getText().trim();
		request.data.user_pwd =  new String(pwdF.getPassword()).trim();
		request.data.user_name = this.nameF.getText().trim();
		request.data.user_email = this.emailF.getText().trim()+"@"+ this.emailCombo.getSelectedItem().trim() ;
		request.data.user_tel = telCombo.getSelectedItem() + "-" + telF2.getText().trim() + "-" + telF3.getText();
		request.data.user_address = this.addrF.getText().trim();
	    
	    Serverconnect.write(request);
	}
	
	
	// ���� id �� �ִ��� check ��
	private void  ConnectCheckid()
	{

		
		RequestCheckUserId check = new RequestCheckUserId();
		check.userid = idF.getText().trim();
		Serverconnect.write(check);
		
	}
	
	public void Connnect()
	{
		
		if (isRegistr ) {
			
			 okB.setEnabled(false);
			 clearB.setEnabled(false);
			 
			
			 ConnectRegister();
			 return;
		} 
		
		ConnectCheckid();
		
	}

	public void DisConnnect()
	{

	}

	
	public void ProcessCheckUserid( ResponseCheckUserId data )
	{

		if (data.result == true) {
			idCheckL.setText("�ߺ�");
			idCheckL.setForeground(Color.red);
			return;
		}

		 idCheckL.setText("��� ����");
		 idCheckL.setForeground(Color.blue);
		
	}
	
	public void ProcessInsertMember(ResponseMember data)
	{
		if (data.result == true) {			
			JOptionPane.showMessageDialog(this, "��� �Ǿ����ϴ�");  
			Close();			
			return;
		}		
		 okB.setEnabled(true);
		 clearB.setEnabled(true);			
 		 JOptionPane.showMessageDialog(this,data.reason);
	}
	
	
	public void Read(Object obj ) {

		this.Serverconnect.stop();
			

		if (obj instanceof Dao.ResponseMember ) {
			
			ResponseMember data = (ResponseMember)obj;
			
		
			if (data.message == Constant.DEF_INSERT)
			{
				ProcessInsertMember(data );				
			}
			
		}
		
		if (obj instanceof Dao.ResponseCheckUserId) {
			
			ResponseCheckUserId data = (ResponseCheckUserId)obj;
			
			ProcessCheckUserid(data);
		}
	}

	Dialog dlg;
	
	public void showRegister() {

		dlg = new Dialog(this, "Registration");

		okB = new JButton("ȸ������");
		clearB = new JButton("reset");
		exitB = new JButton("���");

		titleL = new JLabel(" * ȸ   ��   ��  �� * ");
		idL = new JLabel("ID");
		idCheckL = new JLabel();
		pwdL = new JLabel("��й�ȣ");
		pwdL2 = new JLabel("��й�ȣ Ȯ��");
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

		FoucusEvent focusEvent = new FoucusEvent();
		idF.addFocusListener(focusEvent);

		telF2.setDocument(new JTextFieldLimit(4));
		telF3.setDocument(new JTextFieldLimit(4));

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

		// JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		fieldPanel.add(idPanel);
		fieldPanel.add(passPanel);
		fieldPanel.add(passPanel2);
		fieldPanel.add(namePanel);
		fieldPanel.add(telPanel);
		// fieldPanel.add(telF);
		// fieldPanel.add(emailF);
		fieldPanel.add(emailPanel);
		fieldPanel.add(addrPanel);

		JPanel idCheckPanel = new JPanel(new GridLayout(7, 1, 20, 15));

		idCheckPanel.add(idCheckL);

		JPanel labelFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

		labelFieldPanel.add("West", labelPanel);
		labelFieldPanel.add("Center", fieldPanel);
		labelFieldPanel.add("East", idCheckPanel);

		// labelFieldPanel.setBorder(BorderFactory.createEmptyBorder(10 , 10 ,
		// 10 , 10));

		// ȸ������, ����, ���� ��ư �̺�Ʈ ���

		ButtonEvent event = new ButtonEvent();
		okB.addActionListener(event);
		clearB.addActionListener(event);
		exitB.addActionListener(event);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

		buttonPanel.add("West", okB);
		buttonPanel.add("Center", clearB);
		buttonPanel.add("East", exitB);

		dlg.add("North", titlePanel);
		dlg.add("Center", labelFieldPanel);
		dlg.add("South", buttonPanel);

		dlg.setSize(500, 500);
		

		// dlg.setResizable(false);

		/*
		 * Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		 * 
		 * int x = (scr.width - 500) / 2; int y = (scr.height - 500) / 2;
		 * dlg.setLocation(x, y);
		 */
		
		screen = new ScreenLocation();
		screen.setScreen();
		dlg.setLocation(screen.x, screen.y);
		
		dlg.setModal(true);;
		this.setModal(true);
		
		dlg.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			  dlg.dispose();
			  Close();			  
			}			
		} );
		
	}

	
	public void  ShowWinodw()
	{
         dlg.setVisible(true);	
		 
	}
	

	// ��й�ȣ ���� ǥ����
	
	
	public static boolean valCheck(String pwd) {
		
		String pattern = "[a-z0-9]{4,10}";
		boolean isValCheck = pwd.matches(pattern);
		System.out.println(isValCheck);
		return isValCheck;
	}
	
	// ȸ�������� �����ϴ� �Լ�

	
	public void registerProc( ) {

		String id = idF.getText().trim();
		String pwd = new String(pwdF.getPassword());
		String pwd2 = new String(pwdF2.getPassword());
		String name = nameF.getText().trim();
		String tel = telCombo.getSelectedItem() + "-" + telF2.getText().trim() + "-" + telF3.getText();
		String email = emailF.getText().trim() + "@" + emailCombo.getSelectedItem();
		String addr = addrF.getText().trim();

		if (id == null || id.length() == 0) {
			JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
			idF.grabFocus();
			return;
		} else if (pwd == null || pwd.length() == 0) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
			pwdF.grabFocus();
			return;
		} else if (pwd2 == null || pwd2.length() == 0) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �� �� �� �Է��ϼ���");
			pwdF2.grabFocus();
			return;
		} else if (name == null || name.length() == 0) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���");
			nameF.grabFocus();
			return;
		} else if (tel == null || tel.length() == 0) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �Է��ϼ���");
			telF.grabFocus();
			return;
		} else if (email == null || email.length() == 0) {
			JOptionPane.showMessageDialog(this, "�̸����� �Է��ϼ���");
			emailF.grabFocus();
			return;
		} else if (addr == null || addr.length() == 0) {
			JOptionPane.showMessageDialog(this, "�ּҸ� �Է��ϼ���");
			addrF.grabFocus();
			return;
		}

		// �ߺ��Ǿ��� �� �ٽ� �Է�
		// ��й�ȣ1�� ��й�ȣ2�� ���� ���� �� �ٽ� �Է�

		if (!pwd.equals(pwd2)) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ����  ���� �ʽ��ϴ�");
			return;
		}
		
		// ��й�ȣ ���� �˻� 
		
		
		if (valCheck(pwd) == false) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� 4~10���� ������ + ���ڷ� ������ �մϴ�");
		}
		

		/*System.out.println("id :" + id);
		System.out.println("pwd :" + pwd.toString());
		System.out.println("pwd2 :" + pwd2.toString());
		System.out.println("name :" + name);
		System.out.println("tel : " + tel);
		System.out.println("email :" + email);
		System.out.println("addr : " + addr);
		System.out.println("��� �Ǿ����ϴ�");*/
		
		
		ConnectRequest(true);		
							
	}
	
//  ������ �����ϰ�  ��û�Ѵ�.
	// true �̸�  user ��� 
	// false �̸�  ���� id ���� check 
	public void ConnectRequest(boolean isRegister )
	{		
		if (idF.getText().trim().isEmpty() ) 
			return;
		
		this.isRegistr = isRegister;
		this.Serverconnect.stop();
		this.Serverconnect.start(this.connecterror);
		
	}
		
	public void Close()
	{				
		ConnectRelease();
		
		setVisible(false);
		dispose();
	}

	public void textReset() {

		idCheckL.setText("");
		idF.setText("");
		pwdF.setText("");
		pwdF2.setText("");
		nameF.setText("");
		telF.setText("");
		emailF.setText("");
		addrF.setText("");

	}

	public static void main(String[] args) {

		JFrame frame = null;
		new Register(frame,"localhost",2222);
	}

	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton target = (JButton) e.getSource();

			if (target == okB) {
				registerProc();
			} else if (target == clearB) {
				textReset();
			} else if (target == exitB) {
				setVisible(false);
			}
		}
	}

	class FoucusEvent implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
	
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			
			ConnectRequest(false);

	
			 /*���̵� db�� �̹� ������ label�� �ߺ��̶�� ���������� ǥ���ϰ� 
			 idCheckL.setText("�ߺ�");
			 idCheckL.setForeground(Color.red); 
			 
			 ���̵� db�� �ߺ��Ǵ°� ������ ��� �����̶��ǥ���Ѵ� 
			 idCheckL.setText("��� ����");
			 idCheckL.setForeground(Color.blue);
			 */

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
