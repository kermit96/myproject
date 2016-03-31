package main.Chat;

import main.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import org.jdatepicker.JDatePicker;

import com.netty.client.ClientRead;

import Dao.Constant;
import Dao.Chat.RequestSaveChatMsg;
import Dao.Chat.ResponseSaveChatMsg;
import Dao.Chat.TB_Chatting;
import Dao.Chat.TB_SaveMsg;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.Date;


// 상담원 데이타 관리
public class ChatAdmin extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel model ;
	Global g_global =Global.getInstance();

	ClientRead reader;


	JTextArea area;
	private JDatePicker startpicker;
	private JDatePicker endpicker;
	private JTextField userfield;
	private JTextField adminfield;

	public void DeleteRow()
	{
		while(model.getRowCount() >0) {
			try {
				model.removeRow(0);
			} catch (Exception ex) {

			}
		}

	}

	public void Process(ResponseSaveChatMsg response) {

		if (response.result == false) {
			JOptionPane.showMessageDialog(this, response.reason, "chatting 데이타 가져 오기 에러", JOptionPane.OK_OPTION);
			return;
		}

		DeleteRow();
		for( TB_SaveMsg chatdata :response.data) {
			// Object[] columnNames = { "일자", "상담원", "상담자","seq","msg"};
			Object []data = new Object[5];
			data[0] = chatdata.reqdate;
			data[1] = chatdata.adminuserid;
			data[2] = chatdata.username;
			data[3] = chatdata.seq;
			data[4] = chatdata.Msg;
			model.addRow(data);			
		}

		if ( model.getRowCount() <1  ) {
			this.area.setText("");
			return;			
		}


		table.setRowSelectionInterval(0, 0);
		if (table.getSelectedRow() <0 ) {
			this.area.setText("");
			return;
		}

		this.area.setText(table.getValueAt(0, 4).toString() );

	}

	public void Close() {

		g_global.serverConnect.RemoveClientRead(reader);
		dispose();

	}

	public ChatAdmin() {

		reader = new ClientRead() {
			@Override
			public void run(Object obj) {
				if (obj instanceof ResponseSaveChatMsg) {
					ResponseSaveChatMsg response = (ResponseSaveChatMsg)obj;  
					Process(response);
				}				
			}

		};

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);		

				InitData();
				System.out.println("init data");	
			}
		});


		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				Close();
			}
		} );

		JButton Deletebutton = new JButton("삭제");

		Deletebutton.addActionListener(x->Delete());

		Object[] columnNames = { "일자", "상담원", "상담자","seq","msg"};

		model = new DefaultTableModel() {			
			public boolean isCellEditable(int row, int column) {				
				return false;
			};						
		};

		for(Object column :columnNames)
			model.addColumn(column);

		table = new JTable(model) ;

		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setWidth(0);


		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setWidth(0);



		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent event) {
				// do some actions here, for example
				// print first column value from selected row
				//      System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
				try {
					int selerow = table.getSelectedRow();
					if (selerow <0)
						return;
					String str =  (String)model.getValueAt(selerow, 4) ;
					area.setText(str);

				} catch (Exception ex) {

				}
			}
		});


		this.setTitle("채팅 메시지 관리");



		JButton FindButton = new JButton("검색");


		FindButton.addActionListener(x->{
			this.WriteData();	
		});

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane,"Center");

		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		startpicker = new JDatePicker(util.Util.StringToDate("2015-01-01"));

		startpicker.SetTextFieldSize(75);

		endpicker = new JDatePicker(new Date());

		endpicker.SetTextFieldSize(75);

		JLabel lable1= new JLabel ("상담원");
		JLabel lable2= new JLabel ("상담자");

		adminfield = new JTextField(10);
		userfield = new JTextField(10);

		p2.add(startpicker);
		p2.add(endpicker);

		p2.add(lable1);
		p2.add(adminfield);
		p2.add(lable2);
		p2.add(userfield);


		p2.add(FindButton);
		p2.add(Deletebutton);

		getContentPane().add(p2,"North");

		area = new JTextArea(5,40);
		JScrollPane  textsp = new  JScrollPane(area);

		area.setEditable(false);

		getContentPane().add(textsp,"South");

		this.setSize(700,700);

		this.setResizable(false);

	}


	private void WriteData()
	{
		RequestSaveChatMsg chatdata = new RequestSaveChatMsg();
		chatdata.message = Constant.DEF_LIST;
		chatdata.data  = new TB_SaveMsg();		
		chatdata.data.adminusername = this.adminfield.getText();
		chatdata.data.username = this.userfield.getText();
		
		String  startstr = this.startpicker.getTextFieldString();
		String endstr = this.endpicker.getTextFieldString();
		
		startstr = startstr.replace('.', '-');
		endstr = endstr.replace('.' , '-');

		chatdata.data.reqdate = util.Util.StringToDate( startstr );
		chatdata.data.enddate = util.Util.StringToDate(endstr);		
		Write(chatdata);				
		
	}

	private void InitData()
	{

		g_global.serverConnect.AddClientRead(reader);



		//	System.out.println("init");
	}

	private void Delete()
	{    	
		int i = table.getSelectedRow();

		if (i <0)
			return;

		int seq = (int)model.getValueAt(i, 3);

		RequestSaveChatMsg chatdata = new RequestSaveChatMsg();

		chatdata.message = Constant.DEF_DELETE;
		chatdata.data  = new TB_SaveMsg();

		chatdata.data.seq = seq;

		Write(chatdata);

	}


	public void Write(Serializable obj) {

		//	System.out.println("write obj==>"+obj);
		g_global.serverConnect.write(obj);

	}

	public static void main(String[] args) {

		Login login = new Login(null);    	

		if (login.Select != Constant.DEF_LOGIN ) {    		
			return;
		}

//		login.dispose();

		ChatAdmin frame = new ChatAdmin();
		//	 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

