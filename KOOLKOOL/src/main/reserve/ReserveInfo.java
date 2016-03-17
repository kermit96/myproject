package main.reserve;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import util.ClientRead;
import Dao.Constant;
import Dao.reserve.RequestReserve;
import Dao.reserve.RequestRoom;
import Dao.reserve.ReserveConstant;
import Dao.reserve.ReserveSql;
import Dao.reserve.ResponseReserve;
import Dao.reserve.ResponseRoom;
import Dao.reserve.TB_ReserverAll;
import main.Global;
import main.board.PMainMenu;
import main.reserve.ReserveWrite.ImgChange;
import main.reserve.ReserveWrite.TableEvent;

public class ReserveInfo extends JDialog {
	PMainMenu main;
	JLabel sleepL, noL, priceeL, logo, cal, wifiL2, airL2, bobL2, wifionL2,
			aironL2, bobonL2, wifioffL2, airoffL2, boboffL2, vodL, vodL2,
			flowL, flowL2, spaL, spaL2, vodonL2, vodoffL2, flowonL2, flowoffL2,
			spaonL2, spaoffL2, startL2, stopL2, cal3, cal4, target;
	JRadioButton wifiOn2, wifiOff2, airOn2, airOff2, mealOn2, bobOff2, vodOn2,
			floorON2, spaOn2, vodOff2, floorOff2, spaOff2;
	JTextField sleepT, noT, priceT, startT2, stopT2;
	JButton deleteB, modifyB;
	JTable table;
	ImageIcon icon, icon4, icon5, Background;
	Image img = null;
	String fname;
	String fdir;
	String file_name;
	FileDialog fd;
	TitledBorder tborder;
	JPanel rightCompP;
	CalenderInfo calenderinfo;
	DefaultTableModel model;
	ReserveSql sql;
	//ResponseRoom res;
	ResponseReserve res;
	public static int vidx = 0;
	Global g_global = Global.getInstance();
	ClientRead reader;
	
	
	public ReserveInfo() {
		//sleepT.setEditable(false);
		//Background = new ImageIcon("resources\\admin\\sky.png");
		
		// 오른쪽라벨
		sleepL = new JLabel("브랜드명");
		noL = new JLabel("방번호");
		priceeL = new JLabel("가격");
		wifiL2 = new JLabel("Wifi");
		airL2 = new JLabel("에어컨");
		bobL2 = new JLabel("아침식사");
		vodL2 = new JLabel("VOD");
		flowL2 = new JLabel("복층");
		spaL2 = new JLabel("스파");
		startL2 = new JLabel("시작일");
		stopL2 = new JLabel("시작일");
		wifionL2 = new JLabel("ON");
		aironL2 = new JLabel("ON");
		bobonL2 = new JLabel("ON");
		vodonL2 = new JLabel("ON");
		flowonL2 = new JLabel("ON");
		spaonL2 = new JLabel("ON");
		wifioffL2 = new JLabel("OFF");
		airoffL2 = new JLabel("OFF");
		boboffL2 = new JLabel("OFF");
		vodoffL2 = new JLabel("OFF");
		flowoffL2 = new JLabel("OFF");
		spaoffL2 = new JLabel("OFF");

		// 오른쪽패널 텍스트필드
		sleepT = new JTextField(10);
		noT = new JTextField(10);
		priceT = new JTextField(10);
		startT2 = new JTextField(10);
		stopT2 = new JTextField(10);
		
		// 오른쪽패널 버튼
		deleteB = new JButton("예약취소");
		modifyB = new JButton("수정");

		// 오른쪽 라디오버튼
		wifiOn2 = new JRadioButton();
		wifiOff2 = new JRadioButton();
		airOn2 = new JRadioButton();
		airOff2 = new JRadioButton();
		mealOn2 = new JRadioButton();
		bobOff2 = new JRadioButton();
		vodOn2 = new JRadioButton();
		floorON2 = new JRadioButton();
		spaOn2 = new JRadioButton();
		vodOff2 = new JRadioButton();
		floorOff2 = new JRadioButton();
		spaOff2 = new JRadioButton();

		// 라디오버튼 그룹화
		ButtonGroup g7 = new ButtonGroup();
		g7.add(wifiOn2);
		g7.add(wifiOff2);
		ButtonGroup g8 = new ButtonGroup();
		g8.add(airOn2);
		g8.add(airOff2);
		ButtonGroup g9 = new ButtonGroup();
		g9.add(mealOn2);
		g9.add(bobOff2);
		ButtonGroup g10 = new ButtonGroup();
		g10.add(vodOn2);
		g10.add(vodOff2);
		ButtonGroup g11 = new ButtonGroup();
		g11.add(floorON2);
		g11.add(floorOff2);
		ButtonGroup g12 = new ButtonGroup();
		g12.add(spaOn2);
		g12.add(spaOff2);
		JScrollPane sp = setTable();

		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(0).setWidth(30);

		table.getColumnModel().getColumn(3).setMaxWidth(40);
		table.getColumnModel().getColumn(3).setMinWidth(40);
		table.getColumnModel().getColumn(3).setWidth(40);

		table.getColumnModel().getColumn(4).setMaxWidth(40);
		table.getColumnModel().getColumn(4).setMinWidth(40);
		table.getColumnModel().getColumn(4).setWidth(40);

		table.getColumnModel().getColumn(5).setMaxWidth(40);
		table.getColumnModel().getColumn(5).setMinWidth(40);
		table.getColumnModel().getColumn(5).setWidth(40);

		table.getColumnModel().getColumn(6).setMaxWidth(35);
		table.getColumnModel().getColumn(6).setMinWidth(35);
		table.getColumnModel().getColumn(6).setWidth(35);

		table.getColumnModel().getColumn(7).setMaxWidth(40);
		table.getColumnModel().getColumn(7).setMinWidth(40);
		table.getColumnModel().getColumn(7).setWidth(40);

		table.getColumnModel().getColumn(8).setMaxWidth(35);
		table.getColumnModel().getColumn(8).setMinWidth(35);
		table.getColumnModel().getColumn(8).setWidth(35);

		table.getColumnModel().getColumn(9).setMaxWidth(35);
		table.getColumnModel().getColumn(9).setMinWidth(35);
		table.getColumnModel().getColumn(9).setWidth(35);

		table.getColumnModel().getColumn(10).setMaxWidth(40);
		table.getColumnModel().getColumn(10).setMinWidth(40);
		table.getColumnModel().getColumn(10).setWidth(40);

		table.getColumnModel().getColumn(11).setMaxWidth(0);
		table.getColumnModel().getColumn(11).setMinWidth(0);
		table.getColumnModel().getColumn(11).setWidth(0);
		
		table.getColumnModel().getColumn(12).setMaxWidth(0);
		table.getColumnModel().getColumn(12).setMinWidth(0);
		table.getColumnModel().getColumn(12).setWidth(0);
		
		table.getColumnModel().getColumn(13).setMaxWidth(0);
		table.getColumnModel().getColumn(13).setMinWidth(0);
		table.getColumnModel().getColumn(13).setWidth(0);
		
		

		// 오른쪽패널 이미지라벨
		logo = new JLabel();
		logo.setPreferredSize(new java.awt.Dimension(250, 250));

		// 오른쪽패널 시작일달력 이미지
		icon4 = new ImageIcon("resources\\main\\cal.jpg");
		cal3 = new JLabel(icon4);
		cal3.setPreferredSize(new java.awt.Dimension(30, 30));
		cal3.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// 오른쪽패널 종료일달력 이미지
		icon5 = new ImageIcon("resources\\main\\cal.jpg");
		cal4 = new JLabel(icon5);
		cal4.setPreferredSize(new java.awt.Dimension(30, 30));
		cal4.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// ==========왼쪽패널배치==============================
		// 왼쪽테이블
		JPanel leftP = new JPanel(new FlowLayout());
		
	
		leftP.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		leftP.add("Center", sp);

		// =========오른쪽패널배치============================
		// 텍스트 라벨
		JPanel textLabelP = new JPanel(new GridLayout(3, 1, 0, 10));
		textLabelP.add(sleepL);
		textLabelP.add(noL);
		textLabelP.add(priceeL);

		// 텍스트필드
		JPanel textFieldP = new JPanel(new GridLayout(3, 1, 0, 10));
		textFieldP.add(sleepT);
		textFieldP.add(noT);
		textFieldP.add(priceT);
		// 라벨 + 텍스트
		JPanel textCompP = new JPanel(new FlowLayout(FlowLayout.LEFT));

		textCompP.add("West", textLabelP);
		textCompP.add("Center", textFieldP);

		// 시작종료 텍스트필드
		JPanel rightTextP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rightTextP.add(startL2);
		rightTextP.add(startT2);
		rightTextP.add(cal3);
		rightTextP.add(stopL2);
		rightTextP.add(stopT2);
		rightTextP.add(cal4);

		// 공백 패널
		JPanel GongP = new JPanel();
		GongP.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		JPanel textCompP2 = new JPanel();
		textCompP2.add("North", rightTextP);
		textCompP2.add("Center", GongP);

		// 버튼
		JPanel btnP = new JPanel(new FlowLayout());
		btnP.add(deleteB);
		btnP.add(modifyB);

		// 이미지
		JPanel imgP = new JPanel();
		imgP.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "사진보기"));
		imgP.add(logo);

		JPanel imgCompP = new JPanel();
		imgCompP.add("Center", imgP);

		// 라디오 라벨 패널
		JPanel radioLabelP = new JPanel(new GridLayout(7, 1, 0, 5));
		radioLabelP.add(wifiL2);
		radioLabelP.add(airL2);
		radioLabelP.add(bobL2);
		radioLabelP.add(vodL2);
		radioLabelP.add(flowL2);
		radioLabelP.add(spaL2);

		// 라디오 버튼 on 라벨
		JPanel radioOnLabelP = new JPanel(new GridLayout(7, 1, 0, 2));
		radioOnLabelP.add(wifionL2);
		radioOnLabelP.add(aironL2);
		radioOnLabelP.add(bobonL2);
		radioOnLabelP.add(vodonL2);
		radioOnLabelP.add(flowonL2);
		radioOnLabelP.add(spaonL2);

		// 라디오 버튼 on
		JPanel radioOnBtnP = new JPanel(new GridLayout(7, 1, 0, 2));
		radioOnBtnP.add(wifiOn2);
		radioOnBtnP.add(airOn2);
		radioOnBtnP.add(mealOn2);
		radioOnBtnP.add(vodOn2);
		radioOnBtnP.add(floorON2);
		radioOnBtnP.add(spaOn2);

		// 라디오 on 완성
		JPanel radioOnCompP = new JPanel(new BorderLayout());
		radioOnCompP.add("West", radioOnLabelP);
		radioOnCompP.add("Center", radioOnBtnP);

		// 라디오 버튼 off 라벨
		JPanel radioOffLabelP = new JPanel(new GridLayout(7, 1, 0, 2));
		radioOffLabelP.add(wifioffL2);
		radioOffLabelP.add(airoffL2);
		radioOffLabelP.add(boboffL2);
		radioOffLabelP.add(vodoffL2);
		radioOffLabelP.add(flowoffL2);
		radioOffLabelP.add(spaoffL2);

		// 라디오 버튼 off
		JPanel radioOffBtnP = new JPanel(new GridLayout(7, 1, 0, 2));
		radioOffBtnP.add(wifiOff2);
		radioOffBtnP.add(airOff2);
		radioOffBtnP.add(bobOff2);
		radioOffBtnP.add(vodOff2);
		radioOffBtnP.add(floorOff2);
		radioOffBtnP.add(spaOff2);

		// 라디오 off 완성
		JPanel radioOffCompP = new JPanel(new BorderLayout());
		radioOffCompP.add("West", radioOffLabelP);
		radioOffCompP.add("Center", radioOffBtnP);

		// 라디오완성
		JPanel radioCompP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		radioCompP.add("West", radioLabelP);
		radioCompP.add("Center", radioOnCompP);
		radioCompP.add("East", radioOffCompP);

		// 라디오 + 텍스트
		JPanel CenterCompP = new JPanel(new BorderLayout());
		CenterCompP.add("North", textCompP);
		CenterCompP.add("Center", radioCompP);
		CenterCompP.add("South", textCompP2);

		// 오른쪽완성
		rightCompP = new JPanel(new BorderLayout());
		rightCompP.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		rightCompP.add("North", imgP);
		rightCompP.add("Center", CenterCompP);
		rightCompP.add("South", btnP);

		this.add("West", leftP);
		this.add("Center", rightCompP);

		setEvent();
		setCalEvent();
		reserveShowProc();
		rightCompP.setVisible(false);
		
		
		reader = new ClientRead() {
			@Override
			public void run(Serializable obj) {
				// TODO Auto-generated method stub

				if (obj instanceof ResponseReserve) {
					ResponseReserve res = (ResponseReserve) obj;
					ProcessReserve(res);
				}
			}
		};
		
		
		g_global.serverConnect.AddClientRead(reader);

		setSize(900, 750);
		setVisible(true);
	}

	// 테이블 만드는 함수
	public JScrollPane setTable() {
		String[] title = { "번호", "브랜드명", "대표자", "방번호", "Wifi", "에어컨", "식사",
				"VOD", "복층", "스파", "가격", "","","" };
		model = new DefaultTableModel(title, 0);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		TableEvent tableEvent = new TableEvent();
		table.addMouseListener(tableEvent);
		table.getTableHeader().setReorderingAllowed(false);
		return sp;
	}

	// 버튼 이벤트 전담 함수
	public void setEvent() {
		ButtonEvent evt1 = new ButtonEvent();
		modifyB.addActionListener(evt1);
		deleteB.addActionListener(evt1);
	}

	// 라벨이벤트 전담 함수
	public void setCalEvent() {
		ImgChange evt2 = new ImgChange();
		logo.addMouseListener(evt2);
		cal3.addMouseListener(evt2);
		cal4.addMouseListener(evt2);
	}

	public void Write(Serializable obj) {
		g_global.serverConnect.write(obj);
	}

	public void clearTable() {
		int rows = table.getRowCount();
		for (int i = 0; i < rows; i++) {
			model.removeRow(0);
		}
	}
	
	// 예약된 데이터불러오기
	public void reserveShowProc() {
	
		clearTable();
		
		/*
		sql = new ReserveSql();
		res = new ResponseRoom();

		try {
			res = sql.reserveShow(null);
			res.message = Constant.DEF_LIST;

			for (int i = 0; i < res.data.size(); i++) {
				Object[] data = new Object[12];
				data[0] = res.data.get(i).room.idx;
				data[1] = res.data.get(i).house.name;
				data[2] = res.data.get(i).house.host_name;
				data[3] = res.data.get(i).room.room_no;
				data[4] = res.data.get(i).room.wifi_select;
				data[5] = res.data.get(i).room.aircon_select;
				data[6] = res.data.get(i).room.meal_select;
				data[7] = res.data.get(i).room.vod_select;
				data[8] = res.data.get(i).room.floor_select;
				data[9] = res.data.get(i).room.spa_select;
				data[10] = res.data.get(i).room.price;
				data[11] = res.data.get(i).room.file_name;

				model.addRow(data);
			}
		} catch (Exception e) {
		}*/
		
		
		RequestReserve request = new RequestReserve();
		request.data = new TB_ReserverAll();
		request.data.reserve.user_idx = g_global.getIdx();
		request.message = ReserveConstant.DEF_IDX_LIST;

		Write(request);
		
	}
	
	
	public void ProcessReserve(ResponseReserve res) {
		if (res.message == ReserveConstant.DEF_IDX_LIST) {
			this.res = res;
			if (res.result == true) {
				for (int i = 0; i < res.data.size(); i++) {
					Object[] data = new Object[14];
					data[0] = res.data.get(i).room.idx;
					data[1] = res.data.get(i).house.name;
					data[2] = res.data.get(i).house.host_name;
					data[3] = res.data.get(i).room.room_no;
					data[4] = res.data.get(i).room.wifi_select;
					data[5] = res.data.get(i).room.aircon_select;
					data[6] = res.data.get(i).room.meal_select;
					data[7] = res.data.get(i).room.vod_select;
					data[8] = res.data.get(i).room.floor_select;
					data[9] = res.data.get(i).room.spa_select;
					data[10] = res.data.get(i).room.price;
					data[11] = res.data.get(i).room.file_name;
					data[12] = res.data.get(i).reserve.start_date;
					data[13] = res.data.get(i).reserve.end_date;
					
					System.out.println(data[12]);
					System.out.println(data[13]);

					model.addRow(data);
				}
			} else {
				System.out.println("error message : " + res.reason);
			}
		} else if (res.message == ReserveConstant.DEF_UPDATE) {
			
			if (res.result == true) {
				JOptionPane.showMessageDialog(this, "변경이 완료되었습니다.");
				rightCompP.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "다시 시도해 주세요");
			}
				
		} else if (res.message == ReserveConstant.DEF_DELETE) {
			
			if (res.result == true) {
				JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.");
				reserveShowProc();
			} else {
				JOptionPane.showMessageDialog(this, "다시 시도해 주세요");
			}
		}
		
	}

	// 수정하기
	public void modifyProc() throws Exception {
		
		
		
		int sel = JOptionPane.showConfirmDialog(this,"변경하시겠습니까?","변경",JOptionPane.YES_NO_OPTION);
		 
		if (sel != JOptionPane.YES_OPTION ) {    	
	    	return;
	    }
		
		int row = table.getSelectedRow();
		int num = (int) table.getValueAt(row, 0);

		vidx = num;
		
		String start_date = startT2.getText().trim();
		String stop_date = stopT2.getText().trim();

		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(stop_date);
		
		RequestReserve request = new RequestReserve();
		request.data = new TB_ReserverAll();
		request.data.reserve.idx = vidx;
		
		request.data.reserve.start_date = startDate;
		request.data.reserve.end_date = endDate;
		
		request.message = ReserveConstant.DEF_UPDATE;

		Write(request);
		
	}

	// 예약취소
	public void deleteProc() {
		
		int sel = JOptionPane.showConfirmDialog(this,"삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
		 
		if (sel != JOptionPane.YES_OPTION ) {    	
	    	return;
	    }
		
		int row = table.getSelectedRow();
		int num = (int) table.getValueAt(row, 0);

		vidx = num;
		
		RequestReserve request = new RequestReserve();
		request.data = new TB_ReserverAll();
		request.data.reserve.idx = vidx;
		
		request.message = ReserveConstant.DEF_DELETE;

		Write(request);
		
	}

	// 켈린더 호출 함수
	public void calSelProc() {
		calenderinfo = new CalenderInfo(this);
	}

	// 라벨클릭 이벤트
	class ImgChange extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			target = (JLabel) e.getSource();
			if (target == cal3) {
				calSelProc();
			} else if (target == cal4) {
				calSelProc();
			}
		}
	}

	// 버튼이벤트
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton target1 = (JButton) e.getSource();
			if (target1 == deleteB) {
				deleteProc();
			} else if (target1 == modifyB) {
				try {
					modifyProc();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	// 테이블 이벤트
	class TableEvent extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
			//sql = new ReserveSql();
			//res = new ResponseReserve();
			rightCompP.setVisible(true);
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			String brand_name = (String) table.getValueAt(row, 1);
			int room_num = (int) table.getValueAt(row, 3);
			int price = (int) table.getValueAt(row, 10);

			sleepT.setText(brand_name);
			noT.setText(String.valueOf(room_num));
			priceT.setText(String.valueOf(price));
			String tablewifi = (String) table.getValueAt(row, 4);
			String tableair = (String) table.getValueAt(row, 5);
			String tablemeal = (String) table.getValueAt(row, 6);
			String tablevod = (String) table.getValueAt(row, 7);
			String tablefloor = (String) table.getValueAt(row, 8);
			String tablespa = (String) table.getValueAt(row, 9);
			Date tablesD = (Date)table.getValueAt(row, 12);
			Date tableeD = (Date)table.getValueAt(row, 13);

			String startDate2 = tablesD.toString();
			String endDate2 = tableeD.toString();
			
			file_name = (String) table.getValueAt(row, 11);

			
			startT2.setText(startDate2);
			stopT2.setText(endDate2);
			
			sleepT.setEditable(false);
			noT.setEditable(false);
			priceT.setEditable(false);
			
			if (tablewifi.equals("Y")) {
				wifiOn2.doClick();
			} else {
				wifiOff2.doClick();
			}
			if (tableair.equals("Y")) {
				airOn2.doClick();
			} else {
				airOff2.doClick();
			}
			if (tablemeal.equals("Y")) {
				mealOn2.doClick();
			} else {
				bobOff2.doClick();
			}
			if (tablevod.equals("Y")) {
				vodOn2.doClick();
			} else {
				vodOff2.doClick();
			}
			if (tablefloor.equals("Y")) {
				floorON2.doClick();
			} else {
				floorOff2.doClick();
			}
			if (tablespa.equals("Y")) {
				spaOn2.doClick();
			} else {
				spaOff2.doClick();
			}
			System.out.println(file_name);
			ImageIcon icon = new ImageIcon(res.data.get(row).room.buff);
			logo.setIcon(icon);
		}
	}
}