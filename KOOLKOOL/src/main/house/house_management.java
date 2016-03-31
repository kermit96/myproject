package main.house;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.netty.client.ClientRead;

import main.Global;

import java.awt.event.*;

import Dao.Constant;
import Dao.ResponseMember;
import Dao.house.HouseConstant;
import Dao.house.HouseSql;
import Dao.house.RequestHouse;
import Dao.house.ResponseHouse;
import Dao.house.TB_HOUSE;

import java.awt.event.*;
import java.io.Serializable;

public class house_management{
	JFrame f;
	static JTable mainT;
	DefaultTableModel	model;
	JButton addB;
	JPanel WriteP;
	CardLayout card;
	mainP houseP;
	
	public house_management() {
		f=new JFrame();
		
		card=new CardLayout();
		f.setLayout(card);
		
		houseP= new mainP(this);
		
		f.add("mainP", houseP);
		f.setTitle("KOOLKOOL");
		f.setSize(600,500);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new house_management();
	}
}

class mainP extends JPanel{
	
	house_management main;
	house_adjust modify;
	JTable mainT;
	DefaultTableModel	model;
	CardLayout card,mcard;
	JPanel WriteP;
	JButton addB;
	String no;
	JPanel titlePanel;
	JLabel titleL;
	HouseSql sql;
	ResponseHouse res;
	String file_name;
	Global g_global = Global.getInstance();
	ClientRead reader ;
	
	public static int houseIdx = 0;
	
	public mainP(house_management m){
		
		main=m;
		 
		
		this.setLayout(new BorderLayout());
		titleL = new JLabel("숙박 시설 관리",JLabel.CENTER);
		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(titleL);
		
		mainT=new JTable();
		String[] title={"번호","이름","대표자이름","이메일","주소","등록일"};
		
		model=new DefaultTableModel(title,0){
		public boolean isCellEditable(int row,int column){
			if(column>=0){
				return false;
			}
			else{
				return true;
			}
		}
		};
		
		TableEvent evtT=new TableEvent();
		mainT=new JTable(model);
		mainT.addMouseListener(evtT);
		JScrollPane sp=new JScrollPane(mainT);
		
		addB=new JButton("등록");
		addB.addActionListener(new ButtonEvent());
		
		JPanel buttonP = new JPanel(new FlowLayout());
		
		buttonP.add(addB);
		
		JPanel homeP=new JPanel(new BorderLayout());
		homeP.add(sp);
		homeP.add("South",buttonP);
		
		house_add WriteE = new house_add(main);
		card = new CardLayout();
		
		modify=new house_adjust(main);
		mcard = new CardLayout();
		
		WriteP = new JPanel();
		WriteP.setLayout(card);
		
		WriteP.add("Table" , homeP);
		WriteP.add("Write", WriteE);
		WriteP.add("Modify", modify);
		card.first(WriteP);
		
		this.add("North",titlePanel);
		this.add("Center",WriteP);
		//this.add("Center",modifyP);
		
		this.setSize(600,500);
		this.setVisible(true);
		
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
		  	// 뿌려줘야 될 함수 
		      
		      main.f.addWindowListener(new WindowAdapter() {
		    	  @Override
		    	public void windowClosing(WindowEvent e) {
		    		// TODO Auto-generated method stub
		    		super.windowClosing(e);
		    		Close();
		    		
		    	}
			});		

		g_global.serverConnect.AddClientRead(reader);
		showProc();
		
	}	
	
	public void Close()
	{
		   g_global.serverConnect.RemoveClientRead(reader);	   
		   main.f.dispose();

	}
	
	
	public void clearTable() {
		int	rows = mainT.getRowCount();
		for(int i = 0; i < rows; i++) {
			model.removeRow(0);
		}
	}	
	
	public void Write (Serializable obj) 
	{
		
		g_global.serverConnect.write(obj);
	}
	
	public void showProc() {
		
		// 현재 등록된 숙박시설을 보여준다
		
		RequestHouse request = new RequestHouse();
		
		request.data = new TB_HOUSE();
		request.message = HouseConstant.DEF_LIST;
		
		Write(request);
	}
	
	public void ProcessHouse(ResponseHouse res) {
		 
		if (res.message == HouseConstant.DEF_LIST) { 
		
			if (res.result == true) {
			
				clearTable();
				for (int i = 0; i < res.data.size(); i++) {
					
					Object[] data = new Object[6];
					
					data[0] = res.data.get(i).idx;
					data[1] = res.data.get(i).name;
					data[2] = res.data.get(i).host_name;
					data[3] = res.data.get(i).host_email;
					data[4] = res.data.get(i).address;
					data[5] = res.data.get(i).reg_date;
					
					model.addRow(data);
				}
			} else {
				System.out.println("error message :" + res.reason);
			}
		}
	
	}
	
	class ButtonEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target=(JButton)e.getSource();
			
			CardLayout card=(CardLayout)(WriteP.getLayout());
			if(target==addB){
				//main.houseP.HM.houseP.HM.card.show(WrieP,"Edit");
				main.houseP.titleL.setText("숙박 시설 등록");
				card.show(WriteP, "Write");
			}
		}
}
	class TableEvent extends MouseAdapter{
		public void mouseClicked(MouseEvent eM){
			int row=mainT.getSelectedRow();
			if(row==-1){
				return;
			}
			if(eM.getClickCount()==2){
				
				int no = (int)mainT.getValueAt(row, 0);
				houseIdx = no;
				
				String name = (String)mainT.getValueAt(row, 1);
				String host_name = (String)mainT.getValueAt(row, 2);
				
				// 값을 넘겨서 house를 select한다
				modify.selectHouse(houseIdx);
				
				main.houseP.titleL.setText("숙박 시설 수정");
				main.houseP.card.show(WriteP, "Modify");
				
				}
			}
		}
	
	private void removeField() {

		modify.nameT.setText("");
		modify.exponentT.setText("");
		modify.calT.setText("");
		modify.emailT.setText("");
		modify.addressT.setText("");
		//remarkT.setText("");
		modify.remark.setText("");
		modify.emailCombo.select("");
	}
	
	}

