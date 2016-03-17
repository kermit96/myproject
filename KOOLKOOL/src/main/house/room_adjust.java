package main.house;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import main.Global;
import util.ClientRead;
import Dao.Constant;
import Dao.house.HouseConstant;
import Dao.house.HouseSql;
import Dao.house.RequestHouse;
import Dao.house.ResponseHouse;




import Dao.room.RequestRoom;
import Dao.room.ResponseRoom;
import Dao.room.RoomConstant;
import Dao.room.RoomSql;
import Dao.room.TB_ROOM;













//import main.room.house_add.ButtonEvent;
import java.io.*;

public class room_adjust extends JPanel {
	JLabel 	accommodationL, roomsnumberL, priceL, wifiL, airL, foodL, badL,vodL,sparL,duplexL,twinL,
	wifionL, aironL,foodonL, badonL,vodonL,sparonL,duplexonL,twinonL,wifioffL,airoffL,foodoffL,vodoffL,sparoffL,duplexoffL,twinoffL,
	logo, nameL;
	JRadioButton wifion, wifioff, airon, airoff, foodon, foodoff,badon,badoff,vodon,vodoff,sparon,sparoff,duplexon,duplexoff,
	twinon,twinoff;
	JTextField  roomsnumberText, priceText,nameText;
	JButton menuButton, addButton,cancelButton;
	JComboBox	accommodation;

	Choice houseList;
	
	ImageIcon 	icon, icon2;
	Image		img = null;
	String      fname;
	String      fdir;
	String currFile ="";
	
	FileDialog   fd;
	ButtonGroup  wifigroup,airgroup,foodgroup,vodgroup,badgroup,spargroup,duplexgroup;
	
	HouseSql sql_house;
	ResponseHouse res_house;
	ResponseRoom res;
	
	RequestRoom request;
	room_management main;
	mainP roomP;
	
	Global g_global = Global.getInstance();
	ClientRead reader;
	
	public room_adjust(room_management m) {
		main=m;
	
		this.setLayout(new BorderLayout());
		
		accommodationL = new JLabel("숙박시설");
		roomsnumberL = new JLabel("방번호");
		priceL = new JLabel("가격");
		nameL = new JLabel("이름");
		wifiL = new JLabel("Wifi");
		airL = new JLabel("에어컨");
		foodL = new JLabel("식사");
		vodL = new JLabel("VOD");
		sparL = new JLabel("스파");
		duplexL = new JLabel("복층");
		
		wifionL = new JLabel("ON");
		aironL = new JLabel("ON");
		foodonL = new JLabel("ON");
		vodonL = new JLabel("ON");
		sparonL = new JLabel("ON");
		duplexonL = new JLabel("ON");
		wifioffL = new JLabel("OFF");
		airoffL= new JLabel("OFF");
		foodoffL= new JLabel("OFF");
		vodoffL = new JLabel("OFF");
		sparoffL= new JLabel("OFF");
		duplexoffL= new JLabel("OFF");
		
		
		houseList = new Choice();
		
//		sql_house = new HouseSql();
//		res_house = new ResponseHouse();
		
		try {
		
//		res_house = sql_house.selectHouseIdxList(null);
//		res_house.message = Constant.DEF_LIST;
		
		   RequestHouse req = new RequestHouse();
		   
		   req.message = HouseConstant.DEF_ROOMAS_LIST;
			
		   Write(req);
		
		/*
		for (int i = 0; i < res_house.data.size(); i++) {
			
			Object[] data = new Object[2];
			
			data[0] = res_house.data.get(i).idx;
			data[1] = res_house.data.get(i).name;
		
			String houseName = (String)data[1];
			
			houseList.addItem(houseName);
			
		}
				*/
		} catch (Exception e) {
			
		}
		

		
		roomsnumberText= new JTextField(10);
		priceText = new JTextField(10);
		nameText = new JTextField(10);
		
		menuButton = new JButton("목록");
		menuButton.setSize(15,15);
		addButton = new JButton("수정");
		addButton.setSize(15,15);
		cancelButton = new JButton("삭제");
		cancelButton.setSize(15,15);
		ButtonEvent evtM = new ButtonEvent();
		menuButton.addActionListener(evtM);
		addButton.addActionListener(evtM);
		cancelButton.addActionListener(evtM);
		
		wifion = new JRadioButton();
		wifioff = new JRadioButton();
		airon = new JRadioButton();
		airoff = new JRadioButton();
		foodon = new JRadioButton();
		foodoff = new JRadioButton();
		vodon= new JRadioButton();
		vodoff= new JRadioButton();
		sparon= new JRadioButton();
		sparoff= new JRadioButton();
		duplexon= new JRadioButton();
		duplexoff= new JRadioButton();
		wifigroup = new ButtonGroup();
		airgroup = new ButtonGroup();
		foodgroup = new ButtonGroup();
		vodgroup = new ButtonGroup();
		spargroup = new ButtonGroup();
		duplexgroup = new ButtonGroup();
		
		wifigroup.add(wifion);wifigroup.add(wifioff);
		airgroup.add(airon);airgroup.add(airoff);
		foodgroup.add(foodon);foodgroup.add(foodoff);
		vodgroup.add(vodon);vodgroup.add(vodoff);
		spargroup.add(sparon);spargroup.add(sparoff);
		duplexgroup.add(duplexon);duplexgroup.add(duplexoff);
		
		//오른쪽패널 이미지라벨
		icon = new ImageIcon("src\\pack\\b.png");
		logo = new JLabel(icon);
		logo.setPreferredSize(new java.awt.Dimension( 250, 250));
		logo.setCursor(new Cursor(Cursor.HAND_CURSOR));

		//목록 3개
		JPanel p = new JPanel(new GridLayout(4, 1, 15, 15));
		p.add(accommodationL);
		p.add(roomsnumberL);
		p.add(priceL);
		p.add(nameL);
		
		JPanel p1 = new JPanel(new GridLayout(4, 1, 10, 10));
		p1.add(houseList);
		p1.add(roomsnumberText);
		p1.add(priceText);
		p1.add(nameText);
		
		//위치
		JPanel p2 = new JPanel(new FlowLayout());
		p2.add("West", p);
		p2.add("Center", p1);
		
		JPanel p31 = new JPanel(new FlowLayout());
		p31.add(menuButton);
		
		JPanel p32 = new JPanel(new FlowLayout(FlowLayout.LEFT ,10, 0));
		p32.add(addButton);
		p32.add(cancelButton);
		
		//목록,추가,삭제
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT , 120, 0));
		p3.add("East",p31);
		p3.add("West",p32);
		
		// 이미지
		ImgChange evt = new ImgChange();
		JPanel p16 = new JPanel();
		p16.setBorder(new TitledBorder(new LineBorder(Color.black,2),"사진보기"));
		logo.addMouseListener(evt);
		p16.add(logo);

		JPanel p00 = new JPanel();
		p00.add("Center", p16);
	
		//선택지4개
		JPanel p5 = new JPanel(new GridLayout(8, 1,3,3));
		p5.add(wifiL);
		p5.add(airL);
		p5.add(foodL);
		p5.add(vodL);
		p5.add(duplexL);
		p5.add(sparL);
		//p5.add(twinL);

		//on
		JPanel p6 = new JPanel(new GridLayout(8, 1));
		p6.add(wifionL);
		p6.add(aironL);
		p6.add(foodonL);
		p6.add(vodonL);
		p6.add(duplexonL);
		p6.add(sparonL);
		
		JPanel p7 = new JPanel(new GridLayout(8, 1));
		p7.add(wifion);
		p7.add(airon);
		p7.add(foodon);
		p7.add(vodon);
		p7.add(duplexon);
		p7.add(sparon);
		
		//p7.add(twinon);

		//위치
		JPanel p8 = new JPanel(new BorderLayout());
		p8.add("West", p6);
		p8.add("Center", p7);

		//off
		JPanel p9 = new JPanel(new GridLayout(8, 1));
		p9.add(wifioffL);
		p9.add(airoffL);
		p9.add(foodoffL);
		p9.add(vodoffL);
		p9.add(duplexoffL);
		p9.add(sparoffL);
		
		//p9.add(twinoffL);
		JPanel p10 = new JPanel(new GridLayout(8, 1));
		p10.add(wifioff);
		p10.add(airoff);
		p10.add(foodoff);
		p10.add(vodoff);
		p10.add(duplexoff);
		p10.add(sparoff);
		
		//p10.add(twinoff);
		//위치
		JPanel p11 = new JPanel(new BorderLayout());
		p11.add("West", p9);
		p11.add("Center", p10);

		//전체위치
		JPanel p12 = new JPanel(new FlowLayout());
		p12.add("West", p5);
		p12.add("Center", p8);
		p12.add("East", p11);

		JPanel p13 = new JPanel(new BorderLayout());
		p13.add("North", p2);
		p13.add("Center", p12);
		
		
		this.add("West", p13);
		this.add("Center",p00);
		this.add("South", p3);

		//setSize(500,500);
		//setVisible(true);
		
		reader = new ClientRead() {
			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub

				if (obj instanceof ResponseRoom) {
					ResponseRoom res = (ResponseRoom) obj;
					ProcessRoom(res);
				}
				
				if (obj instanceof ResponseHouse) {
					ResponseHouse res = (ResponseHouse) obj;
					if (res.message ==HouseConstant.DEF_ROOMAS_LIST  )
					   responseHoseList(res);
				}
				
				
			}
		};
		g_global.serverConnect.AddClientRead(reader);
		
	}

	public void openProc() {
		this.fd = new FileDialog(this.fd, "사진 불러오기", FileDialog.LOAD);
		fd.setVisible(true);
		fname = fd.getFile();
		fdir= fd.getDirectory(); 
		showimg();
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
	

	public void Write(Serializable obj) {

		g_global.serverConnect.write(obj);
	}
	
	

	// 수정
	public void modifyProc() {
		
		request = new RequestRoom();
		request.data = new TB_ROOM();
		request.message = RoomConstant.DEF_UPDATE;
		request.data.house_idx = houseList.getSelectedIndex() + 1;
		request.data.room_no = Integer.parseInt(roomsnumberText.getText().trim());
		request.data.price = Integer.parseInt(priceText.getText().trim());
		request.data.name = nameText.getText().trim();
		
		if (fname == null) {
			request.data.file_name =main.roomP.file_name;
		} else {
			fname = fd.getFile();
			request.data.file_name = fname;
		}
		
		if (wifion.isSelected()) {
			request.data.wifi_select = "Y";
		} else {
			request.data.wifi_select = "N";
		}
		
		if (airon.isSelected()) {
			request.data.aircon_select = "Y";
		} else {
			request.data.aircon_select = "N";
		}
		
		if (foodon.isSelected()) {
			request.data.meal_select = "Y";
		} else {
			request.data.meal_select = "N";
		}
		
		if (vodon.isSelected()) {
			request.data.vod_select = "Y";
		} else {
			request.data.vod_select = "N";
		}
		
		if (sparon.isSelected()) {
			request.data.spa_select = "Y";
		} else {
			request.data.spa_select = "N";
		}
		
		if (duplexon.isSelected()) {
			request.data.floor_select = "Y";
		} else {
			request.data.floor_select = "N";
		}
		
		request.data.idx = main.roomP.roomIdx;
		
		// 이미지 업로드 (나중에 경로 바꿔야 함)
		
		uploadProc();
		
		Write(request);
		
		
	}
	
	
	public void ProcessRoom(ResponseRoom res) {
		
		if (res.message == RoomConstant.DEF_UPDATE) {
		
			if (res.result == true) {
				
				JOptionPane.showMessageDialog(main.roomP, "수정되었습니다");
				main.roomP.titleL.setText("숙박 시설 목록");
				main.roomP.card.show(main.roomP.WriteP, "Table");
				main.roomP.showProc();
				room_adjust.this.setVisible(false);
				
			} else {
				
			}
			
		} else if (res.message == RoomConstant.DEF_DELETE) {
			
			if (res.result == true) {
				JOptionPane.showMessageDialog(main.roomP, "삭제되었습니다");
				main.roomP.titleL.setText("숙박 시설 목록");
				main.roomP.card.show(main.roomP.WriteP, "Table");
				main.roomP.showProc();
				room_adjust.this.setVisible(false);
				
			} else {
				
			}
		}
		
	}
	
	
	
	public void uploadProc() {
		
		String currFile = fd.getFile();
		
		if (currFile == main.roomP.file_name || currFile.length() == 0) {
			currFile = roomP.file_name;
			System.out.println("현재 파일 :" + main.roomP.file_name);
		} else {
			currFile = fd.getFile();
		}
		
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
		
		// 파일이 업로드 될 경로
		
		File upFile = new File("resources/room/", fname);
		
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
	}
	
	public void deleteProc() {
		
		request = new RequestRoom();
		request.data = new TB_ROOM();
		request.message = RoomConstant.DEF_DELETE;
		
		request.data.idx = main.roomP.roomIdx;
		
		Write(request);
		
	}
	
	private void responseHoseList ( ResponseHouse res )  
	{
		res_house = res;

		try {
		for (int i = 0; i < res_house.data.size(); i++) {
		
			Object[] data = new Object[2];
		
			data[0] = res_house.data.get(i).idx;
			data[1] = res_house.data.get(i).name;
	
			String houseName = (String)data[1];
		
			houseList.addItem(houseName);
		
	}
	
	} catch (Exception e) {
		
	}
	
	}
	private void removeField() {
		
		roomsnumberText.setText("");
		priceText.setText("");
		wifigroup.clearSelection();
		airgroup.clearSelection();
		foodgroup.clearSelection();
		vodgroup.clearSelection();
		spargroup .clearSelection();
		duplexgroup.clearSelection();
		
		
	}
	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			if (target == menuButton) {
				main.roomP.card.show(main.roomP.WriteP, "Table");
				removeField();
			} 
			else if (target ==addButton) {
				int kind = JOptionPane.showConfirmDialog(main.roomP, "수정 하시겠습니까?","수정",JOptionPane.YES_NO_OPTION);
		
				if (kind == 0) {
					
					//room_adjust.this.setVisible(false);
					modifyProc();
				} 
				else {
					JOptionPane.showMessageDialog(main.roomP, "취소");
				}
				removeField();
			} 
			
			else if (target == cancelButton) {
				int kind = JOptionPane.showConfirmDialog(main.roomP, "삭제 하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
				
				if (kind == 0) {
					
					deleteProc();
					
				}
				else {
					JOptionPane.showMessageDialog(main.roomP, "취소");
				}
				
			}
		}
	}
}