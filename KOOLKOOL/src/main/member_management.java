package main;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import util.ClientRead;
import Dao.CommonSql;
import Dao.Constant;
import Dao.RequestMember;
import Dao.ResponseMember;
import Dao.TB_USER;

import java.awt.event.*;
import java.io.Serializable;

import main.board.*;

public class member_management{
   JFrame f;
   JTable memberTable;
   DefaultTableModel   model;
   JButton deleteB;
   JPanel Panel_one=new JPanel(new FlowLayout());
   JPanel Panel_two=new JPanel(new BorderLayout());
   public static int vidx = 0;
   int idx;

   Global g_global = Global.getInstance();
   
   ClientRead reader ;
   
   public member_management() {
      
      f=new JFrame();
      JScrollPane   setTable = setTable();
      JPanel setButton=setButton();

      Panel_two.add("Center",setTable);
      Panel_two.add("South", setButton);
      
      f.add(Panel_two);
      
      f.setSize(600,600);
      f.setVisible(true);
      
      reader = new ClientRead() {
    	@Override
    	public void run(Serializable obj) {
    		// TODO Auto-generated method stub
    		if (obj instanceof ResponseMember) {
    		  ResponseMember res = (ResponseMember)obj;
    		  ResponseProcess(res);
    		}
    	}  
      };
      
      g_global.serverConnect.AddClientRead(reader);
      f.addWindowListener(new WindowAdapter() {
    	  @Override
    	public void windowClosing(WindowEvent e) {
    		// TODO Auto-generated method stub
    		super.windowClosing(e);
    		Close();
    	}
	});
      
      showProc();
   }
   
   public void Close()
   {
	   g_global.serverConnect.RemoveClientRead(reader);	   
	   f.dispose();
   
   }
   
   public JScrollPane setTable(){
      String[] title={"회원번호","회원id","비밀번호","주소","이메일","전화번호"};
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
      memberTable=new JTable(model);
      JScrollPane sp=new JScrollPane(memberTable);
      return sp;
   }

   public void deleteProc(){
      int row = memberTable.getSelectedRow();
      
      int no2 = (int)memberTable.getValueAt(row,0);
      
      
      vidx = no2;
      
  //    CommonSql sql = new CommonSql();
      
   //   ResponseMember res = new ResponseMember();
      
      RequestMember request = new RequestMember();
      
      request.data = new TB_USER();
      
      request.message = Constant.DEF_DELETE;
      
      request.data.idx = vidx;
   
      
      System.out.println("idx : " + request.data.idx);
     //  res = sql.DeleteMember(request.data);
      
     int  ret =     JOptionPane.showConfirmDialog(f,"삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
      
      System.out.println(request.data.idx);
     
      if (ret == JOptionPane.NO_OPTION) {
    	  
    	  return;
      }
      
    Write(request);
      
   }

   
   public void Write(Serializable obj  ) {	   
	   g_global.serverConnect.write(obj);
   }
   
   public void ResponseProcess(ResponseMember res) 
   {
	   if (res.message == Constant.DEF_DELETE) { 
	      if(res.result == true){

	          JOptionPane.showMessageDialog(f,"삭제완료");
	       
		      showProc();
		      return;
	       }
	       else {
	          JOptionPane.showMessageDialog(f, "삭제에 실패했습니다."+ res.message );
	          return;
	       }
	      
	   }
	   
	   if (res.message == Constant.DEF_LIST) {	
		   
		   if(res.result == false){		      
		          JOptionPane.showMessageDialog(f, "List 를  가져 오는데 실패했습니다."+ res.message );
		          return;
		    }
		      
		 
		   		  clearTable();
		   	
		          for(int i=0; i<res.data.size(); i++){
		             
		             Object[] data = new Object[6];
		             
		             data[0] = res.data.get(i).idx;
		             data[1] = res.data.get(i).user_id;
		             data[2] = res.data.get(i).user_pwd;
		             data[3] = res.data.get(i).user_address;
		             data[4] = res.data.get(i).user_email;
		             data[5] = res.data.get(i).user_tel;		             
		             model.addRow(data);
		          }		          		   
	   }
	   	    	   
   }
   
   public void clearTable(){
      
      int rows = model.getRowCount();
      for(int i = 0; i < rows; i++){
         model.removeRow(0);
      }
      
   }
   
      
public void showProc() {
   
    RequestMember request = new RequestMember();
    
    request.data = new TB_USER();
    
    request.message = Constant.DEF_LIST;
   
    Write(request);
    
 }


   public JPanel setButton(){
      deleteB=new JButton("삭제");
      JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      p.add(deleteB);
      ButtonEvent evtdel =new ButtonEvent();
      deleteB.addActionListener(evtdel);
      return p;
   }
   
   public static void main(String[] args) {
      new member_management();
   }
   class TableEvent extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         int   row = memberTable.getSelectedRow();
         if(row == -1) {
            return;
         }
      }
   }
   class ButtonEvent implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
         JButton target=(JButton)e.getSource();
         if(target==deleteB){
            deleteProc();
         }
         
      }
      
   }
}