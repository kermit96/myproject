package main.board;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.Serializable;

import javax.swing.event.*;
import javax.swing.table.*;

import util.ClientBase;
import util.ClientConnect;
import util.ClientError;
import util.ClientRead;
import Dao.Constant;
import Dao.RegisterSql;
import Dao.RequestRegister;
import Dao.ResponseRegister;
import Dao.TB_REGISTER;
import Dao.board.BoardConstant;
import Dao.board.BoardSql;
import Dao.board.RequestBoard;
import Dao.board.ResponseBoard;
import Dao.board.TB_BOARD;
import Dao.reserve.ResponseReserve;
import main.Global;
import main.MenuBar;
public class InfoBorder{

	JFrame f;
	DefaultTableModel model;
	JButton deleteB;
	JTable table;
	
	 
	ClientConnect connect;
	ClientRead  reader;
	ClientError generalerror;
	ClientError connecterror;
	ClientBase Serverconnect = Global.getInstance().serverConnect;
	ResponseRegister res;
	Global g_global = Global.getInstance();
	
	public static int vidx = 0;
	
	public InfoBorder() {
		
		f = new JFrame();

		String[] title = {"번호", "이름", "ID", "회원이름","이메일","등록일","예약상태"};
		
		model = new DefaultTableModel(title, 0){
			
			public boolean isCellEditable(int row, int column){
				if(column >= 0){
					return false;
					
				}
				else{
					return true;
				}
			}
		};
		
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		
		deleteB = new JButton("상태변경");
		
		ButtonEvent evt = new ButtonEvent();
		
		deleteB.addActionListener(evt);
		
		
		table.getColumn("번호").setPreferredWidth(40);
		table.getColumn("등록일").setPreferredWidth(90);
	
	
		JPanel hapP = new JPanel(new FlowLayout());
	
		hapP.add("Center", sp);
		hapP.add("South", deleteB);
		
		f.add("Center",hapP);
		
		f.setSize(500,530);
		f.setVisible(true);
		
	
		
		reader = new ClientRead() {
			@Override
			public void run(Object obj) {
				// TODO Auto-generated method stub

				if (obj instanceof ResponseRegister) {
					ResponseRegister res = (ResponseRegister) obj;
					realshowProc(res);
				}
			}
		};
		
		 g_global.serverConnect.AddClientRead(reader);
		
		
		
		// server 데이터를 읽을 것을 가져온다. 
	//	setConnect(); 
		showProc();

	}
	

	public static void main(String[] args) {
		new InfoBorder();
	}
	

	/*   public void ConnectRelease()
	   {
	      
	      if(Serverconnect == null)
	         return;

	      Serverconnect.RemoveClientRead(reader);

	   }*/
	   

	/*   // 처리할 함수를 등록한다.
	   public void setConnect()
	   {

	        reader = new ClientRead() {

	         @Override
	         public void run(Serializable obj) {
	            // TODO Auto-generated method stub

	        	 try {
	            Read(obj);
	        	 } catch(Exception ex) {
	        		 
	        		 ex.printStackTrace();
	        	 }
	         }

	      };
	      Serverconnect.AddClientRead(reader);
	   }*/
	   
	  /* public void Read(Serializable obj ) {	

	      if (obj instanceof Dao.ResponseRegister) {
	         
	    	  ResponseRegister data = (ResponseRegister)obj;
	         
	         if (data.message == Constant.DEF_LIST)
	         {
	            realshowProc(data);            
	         }
	         
	         if (data.message == Constant.DEF_UPDATE)
	         {
	        	 RealModifyProc(data);            
	         }
	      }
   }*/
	
	  public void Close()
	   {
		   g_global.serverConnect.RemoveClientRead(reader);	   
		   f.dispose();
	   
	   }
	  
	  
private void realshowProc(ResponseRegister res)
{
	
	System.out.println("현재 res message :" + res.message);
	if (res.message == Constant.DEF_LIST) {
			
	//	res = sql.ShowRegister(null);

		clearTable();
		
		for( int i=0;i<res.data.size();i++ ) {
			

			System.out.println("adfdffadfadf df : " + res.data);
			
			Object[] data = new Object[7];

			data[0] = res.data.get(i).idx;
			data[1] = res.data.get(i).name;
			data[2] = res.data.get(i).user_id;
			data[3] = res.data.get(i).user_name;
			data[4] = res.data.get(i).user_email;
			data[5] = res.data.get(i).reg_date;
			data[6] = res.data.get(i).reserve_state;
			
			
			if (data[6].equals("N")) {
				data[6] = "대기중";
			}
			else if(data[6].equals("Y")){
				data[6] = "예약완료";
			}

			model.addRow(data);
		}
	} else if (res.message == Constant.DEF_UPDATE) {
		
		System.out.println("dafssfddasfsdfdsfdsfafafaf");
		
		if(res.result == true) {
			JOptionPane.showMessageDialog(f,"변경완료");
		}
		else {
			JOptionPane.showMessageDialog(f, "변경이 실패했습니다.==>"+res.reason );
		}
		showProc();
	}
	
	}

	
public void showProc() {		
	
	RequestRegister req = new RequestRegister();	
	req.data = new TB_REGISTER();
	req.message = Constant.DEF_LIST;
	Write(req);
}

public void Write(Serializable obj  ) {	   
	   g_global.serverConnect.write(obj);
}


public void ModifyProc(){
	
	int sel = JOptionPane.showConfirmDialog(f,"변경하시겠습니까?","변경",JOptionPane.YES_NO_OPTION);
 
	if (sel != JOptionPane.YES_OPTION ) {    	
    	return;
    }
	
	int row = table.getSelectedRow();
	int no = (int)table.getValueAt(row, 0);
	
	vidx = no;

	RequestRegister req = new RequestRegister();
	req.data = new TB_REGISTER();
	req.data.idx = vidx;
	req.message = Constant.DEF_UPDATE;
    Write(req);
		
}


public void clearTable(){
	
	int rows = model.getRowCount();
	for(int i = 0; i < rows; i++){
		model.removeRow(0);
	}	
	
}

class ButtonEvent implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		
		if(target == deleteB){
			ModifyProc();
		}
		
	}
}
}

