package main.reserve;

import java.awt.*;
import java.awt.event.*;

import javax.naming.NoInitialContextException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.Caret;

import oracle.sql.CHAR;
import util.ClientRead;
import Dao.Constant;
import Dao.house.ResponseHouse;
import Dao.reserve.RequestReserve;
import Dao.reserve.ReserveConstant;
import Dao.reserve.ReserveSql;
import Dao.reserve.ResponseReserve;
import Dao.reserve.TB_ReserverAll;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import main.Global;
import main.board.PMainMenu;
import main.house.room_adjust;

public class ReserveWrite extends JDialog {
	PMainMenu main;
	JComboBox minC, maxC;
	JLabel sleepL, noL, priceeL, logo, cal3, cal4, minL, maxL, wifiL, airL,
			bobL, wifionL, wifioffL, aironL, airoffL, mealONL, mealOffL,
			wifiL2, airL2, bobL2, wifionL2, aironL2, mealONL2, wifioffL2,
			airoffL2, mealOffL2, vodL, vodL2, flowL, flowL2, spaL, spaL2,
			vodonL, vodonL2, vodoffL, vodoffL2, floorONL, floorONL2, floorOffL,
			floorOffL2, spaonL, spaonL2, spaoffL, spaoffL2, startL2, stopL2,
			target;
	JRadioButton wifiOn, wifiOff, airOn, airOff, mealON, mealOff, wifiOn2,
			wifiOff2, airOn2, airOff2, mealON2, mealOff2, vodOn, vodOn2,
			floorON, floorON2, spaOn, spaOn2, vodOff, vodOff2, floorOff,
			floorOff2, spaOff, spaOff2;
	JTextField sleepT, noT, priceT, startT2, stopT2;
	JButton searchB, reserveB, cancelB;
	DefaultTableModel model;
	JTable table;
	ImageIcon basic, icon4, icon5;
	Image img = null;
	String fname;
	String fdir;
	String file_name;
	FileDialog fd;
	Dialog dlg;
	TitledBorder tborder;
	JPanel rightCompP;
	Calender calendar;
	String start_date = "";
	ButtonGroup g, g1, g2, g3, g4, g5;
	room_adjust modify;
	ReserveSql sql;
	ResponseReserve res;
	RequestReserve request;
	Global g_global = Global.getInstance();
	ClientRead reader;
	byte[] buff2;
	
	public int roomIdx =0;
	public int houseIdx = 0;

	public ReserveWrite() {
		// ���ʶ�
		minL = new JLabel("�ּҰ���");
		maxL = new JLabel("�ִ밡��");
		wifiL = new JLabel("Wifi");
		airL = new JLabel("������");
		bobL = new JLabel("��ħ�Ļ�");
		vodL = new JLabel("VOD");
		flowL = new JLabel("����");
		spaL = new JLabel("����");
		wifionL = new JLabel("ON");
		aironL = new JLabel("ON");
		mealONL = new JLabel("ON");
		vodonL = new JLabel("ON");
		floorONL = new JLabel("ON");
		spaonL = new JLabel("ON");
		wifioffL = new JLabel("OFF");
		airoffL = new JLabel("OFF");
		mealOffL = new JLabel("OFF");
		vodoffL = new JLabel("OFF");
		floorOffL = new JLabel("OFF");
		spaoffL = new JLabel("OFF");
		// �����ʶ�
		startL2 = new JLabel("������");
		stopL2 = new JLabel("������");
		sleepL = new JLabel("�귣���");
		noL = new JLabel("���ȣ");
		priceeL = new JLabel("����");
		wifiL2 = new JLabel("Wifi");
		airL2 = new JLabel("������");
		bobL2 = new JLabel("��ħ�Ļ�");
		vodL2 = new JLabel("VOD");
		flowL2 = new JLabel("����");
		spaL2 = new JLabel("����");
		wifionL2 = new JLabel("ON");
		aironL2 = new JLabel("ON");
		mealONL2 = new JLabel("ON");
		vodonL2 = new JLabel("ON");
		floorONL2 = new JLabel("ON");
		spaonL2 = new JLabel("ON");
		wifioffL2 = new JLabel("OFF");
		airoffL2 = new JLabel("OFF");
		mealOffL2 = new JLabel("OFF");
		vodoffL2 = new JLabel("OFF");
		floorOffL2 = new JLabel("OFF");
		spaoffL2 = new JLabel("OFF");

		// �����г��޺�
		Integer[] inte = { 10000, 20000, 30000 };
		minC = new JComboBox(inte);

		// �����г��޺�
		Integer[] integ = { 50000, 60000, 70000 };
		maxC = new JComboBox(integ);

		// �������г� �ؽ�Ʈ�ʵ�
		sleepT = new JTextField(10);
		noT = new JTextField(10);
		priceT = new JTextField(10);
		startT2 = new JTextField(10);
		stopT2 = new JTextField(10);

		// �����г� ��ư
		searchB = new JButton("�˻�");
		// �������г� ��ư
		reserveB = new JButton("����");
		cancelB = new JButton("���");

		// ���� ������ư
		wifiOn = new JRadioButton("", true);
		wifiOff = new JRadioButton();
		airOn = new JRadioButton("", true);
		airOff = new JRadioButton();
		mealON = new JRadioButton("", true);
		mealOff = new JRadioButton();
		vodOn = new JRadioButton("", true);
		floorON = new JRadioButton("", true);
		spaOn = new JRadioButton("", true);
		vodOff = new JRadioButton();
		floorOff = new JRadioButton();
		spaOff = new JRadioButton();

		// ������ư �׷�ȭ
		g = new ButtonGroup();
		g.add(wifiOn);
		g.add(wifiOff);
		g1 = new ButtonGroup();
		g1.add(airOn);
		g1.add(airOff);
		g2 = new ButtonGroup();
		g2.add(mealON);
		g2.add(mealOff);
		g3 = new ButtonGroup();
		g3.add(vodOn);
		g3.add(vodOff);
		g4 = new ButtonGroup();
		g4.add(floorON);
		g4.add(floorOff);
		g5 = new ButtonGroup();
		g5.add(spaOn);
		g5.add(spaOff);

		// ������ ������ư
		wifiOn2 = new JRadioButton();
		wifiOff2 = new JRadioButton();
		airOn2 = new JRadioButton();
		airOff2 = new JRadioButton();
		mealON2 = new JRadioButton();
		mealOff2 = new JRadioButton();
		vodOn2 = new JRadioButton();
		floorON2 = new JRadioButton();
		spaOn2 = new JRadioButton();
		vodOff2 = new JRadioButton();
		floorOff2 = new JRadioButton();
		spaOff2 = new JRadioButton();

		// ������ư �׷�ȭ
		ButtonGroup g7 = new ButtonGroup();
		g7.add(wifiOn2);
		g7.add(wifiOff2);
		ButtonGroup g8 = new ButtonGroup();
		g8.add(airOn2);
		g8.add(airOff2);
		ButtonGroup g9 = new ButtonGroup();
		g9.add(mealON2);
		g9.add(mealOff2);
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
		
		
		
		// �������г� �̹�����

		logo = new JLabel();
		logo.setPreferredSize(new java.awt.Dimension(250, 250));

		// �������г� �����ϴ޷� �̹���
		icon4 = new ImageIcon("resources\\main\\cal.jpg");
		cal3 = new JLabel(icon4);
		cal3.setPreferredSize(new java.awt.Dimension(30, 30));
		cal3.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// �������г� �����ϴ޷� �̹���
		icon5 = new ImageIcon("resources\\main\\cal.jpg");
		cal4 = new JLabel(icon5);
		cal4.setPreferredSize(new java.awt.Dimension(30, 30));
		cal4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// ==========�����гι�ġ==============================

		// �޺��ڽ�
		JPanel comboP = new JPanel(new FlowLayout());
		comboP.add(minL);
		comboP.add(minC);
		comboP.add(maxL);
		comboP.add(maxC);

		// ��ư �г�
		JPanel buttonP = new JPanel(new FlowLayout());
		buttonP.add(searchB);

		// ���� �� �г�
		JPanel radioLabelP = new JPanel(new GridLayout(7, 1, 0, 12));
		radioLabelP.add(wifiL);
		radioLabelP.add(airL);
		radioLabelP.add(bobL);
		radioLabelP.add(vodL);
		radioLabelP.add(flowL);
		radioLabelP.add(spaL);

		// ���� ��ư on ��
		JPanel radioOnLabelP = new JPanel(new GridLayout(7, 1, 0, 10));
		radioOnLabelP.add(wifionL);
		radioOnLabelP.add(aironL);
		radioOnLabelP.add(mealONL);
		radioOnLabelP.add(vodonL);
		radioOnLabelP.add(floorONL);
		radioOnLabelP.add(spaonL);

		// ���� ��ư on
		JPanel radioBtnOnP = new JPanel(new GridLayout(7, 1, 0, 10));
		radioBtnOnP.add(wifiOn);
		radioBtnOnP.add(airOn);
		radioBtnOnP.add(mealON);
		radioBtnOnP.add(vodOn);
		radioBtnOnP.add(floorON);
		radioBtnOnP.add(spaOn);

		// ���� on �ϼ�
		JPanel radioOnCompP = new JPanel(new BorderLayout());
		radioOnCompP.add("West", radioOnLabelP);
		radioOnCompP.add("Center", radioBtnOnP);

		// ���� ��ư off ��
		JPanel radioOffLabelP = new JPanel(new GridLayout(7, 1, 0, 10));
		radioOffLabelP.add(wifioffL);
		radioOffLabelP.add(airoffL);
		radioOffLabelP.add(mealOffL);
		radioOffLabelP.add(vodoffL);
		radioOffLabelP.add(floorOffL);
		radioOffLabelP.add(spaoffL);

		// ���� ��ư off
		JPanel radioOffBtnP = new JPanel(new GridLayout(7, 1, 0, 10));
		radioOffBtnP.add(wifiOff);
		radioOffBtnP.add(airOff);
		radioOffBtnP.add(mealOff);
		radioOffBtnP.add(vodOff);
		radioOffBtnP.add(floorOff);
		radioOffBtnP.add(spaOff);

		// ���� off �ϼ�
		JPanel radioOffCompP = new JPanel(new BorderLayout());
		radioOffCompP.add("West", radioOffLabelP);
		radioOffCompP.add("Center", radioOffBtnP);

		// �����ϼ�
		JPanel radioCompP = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		radioCompP.add("West", radioLabelP);
		radioCompP.add("Center", radioOnCompP);
		radioCompP.add("East", radioOffCompP);

		// �˻���ư + �����޺��ڽ�
		JPanel searchBtnComboP = new JPanel(new BorderLayout());
		searchBtnComboP.add("West", comboP);
		searchBtnComboP.add("East", buttonP);

		// �޷��ؽ�Ʈ�г� + (�����޺� + �˻���ư)
		JPanel northP = new JPanel(new BorderLayout());
		northP.add("Center", searchBtnComboP);

		// ���ʿϼ�
		JPanel leftCompP = new JPanel(new BorderLayout());
		leftCompP.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		leftCompP.add("North", northP);
		leftCompP.add("Center", radioCompP);
		leftCompP.add("South", sp);

		// =========�������гι�ġ============================
		// �ؽ�Ʈ ��
		JPanel textLabelP = new JPanel(new GridLayout(3, 1, 0, 10));
		textLabelP.add(sleepL);
		textLabelP.add(noL);
		textLabelP.add(priceeL);

		// �ؽ�Ʈ�ʵ�
		JPanel textFieldP = new JPanel(new GridLayout(3, 1, 0, 10));
		textFieldP.add(sleepT);
		textFieldP.add(noT);
		textFieldP.add(priceT);

		// �� + �ؽ�Ʈ
		JPanel labelTextP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelTextP.add("West", textLabelP);
		labelTextP.add("Center", textFieldP);

		// �������� �ؽ�Ʈ�ʵ�
		JPanel rightTextP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rightTextP.add(startL2);
		rightTextP.add(startT2);
		rightTextP.add(cal3);
		rightTextP.add(stopL2);
		rightTextP.add(stopT2);
		rightTextP.add(cal4);

		// ���� �г�
		JPanel GongP = new JPanel();
		GongP.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		JPanel textCompP = new JPanel();
		textCompP.add("North", rightTextP);
		textCompP.add("Center", GongP);

		// ��ư
		JPanel rightBtnP = new JPanel(new FlowLayout());
		rightBtnP.add(reserveB);
		rightBtnP.add(cancelB);

		// �̹���
		ImgChange evt = new ImgChange();
		JPanel imageP = new JPanel();
		imageP.setBorder(new TitledBorder(new LineBorder(Color.black, 2),
				"��������"));
		imageP.add(logo);

		// ���� �� �г�
		JPanel radioLabelP2 = new JPanel(new GridLayout(7, 1, 0, 5));
		radioLabelP2.add(wifiL2);
		radioLabelP2.add(airL2);
		radioLabelP2.add(bobL2);
		radioLabelP2.add(vodL2);
		radioLabelP2.add(flowL2);
		radioLabelP2.add(spaL2);

		// ���� ��ư on ��
		JPanel radioOnLabelP2 = new JPanel(new GridLayout(7, 1, 0, 2));
		radioOnLabelP2.add(wifionL2);
		radioOnLabelP2.add(aironL2);
		radioOnLabelP2.add(mealONL2);
		radioOnLabelP2.add(vodonL2);
		radioOnLabelP2.add(floorONL2);
		radioOnLabelP2.add(spaonL2);

		// ���� ��ư on
		JPanel radioBtnOnP2 = new JPanel(new GridLayout(7, 1, 0, 2));
		radioBtnOnP2.add(wifiOn2);
		radioBtnOnP2.add(airOn2);
		radioBtnOnP2.add(mealON2);
		radioBtnOnP2.add(vodOn2);
		radioBtnOnP2.add(floorON2);
		radioBtnOnP2.add(spaOn2);

		// ���� on �ϼ�
		JPanel radioOnCompP2 = new JPanel(new BorderLayout());
		radioOnCompP2.add("West", radioOnLabelP2);
		radioOnCompP2.add("Center", radioBtnOnP2);

		// ���� ��ư off ��
		JPanel radioOffLabelP2 = new JPanel(new GridLayout(7, 1, 0, 2));
		radioOffLabelP2.add(wifioffL2);
		radioOffLabelP2.add(airoffL2);
		radioOffLabelP2.add(mealOffL2);
		radioOffLabelP2.add(vodoffL2);
		radioOffLabelP2.add(floorOffL2);
		radioOffLabelP2.add(spaoffL2);

		// ���� ��ư off
		JPanel radioOffBtnP2 = new JPanel(new GridLayout(7, 1, 0, 2));
		radioOffBtnP2.add(wifiOff2);
		radioOffBtnP2.add(airOff2);
		radioOffBtnP2.add(mealOff2);
		radioOffBtnP2.add(vodOff2);
		radioOffBtnP2.add(floorOff2);
		radioOffBtnP2.add(spaOff2);

		// ���� off �ϼ�
		JPanel radioOffCompP2 = new JPanel(new BorderLayout());
		radioOffCompP2.add("West", radioOffLabelP2);
		radioOffCompP2.add("Center", radioOffBtnP2);

		// �����ϼ�
		JPanel radioCompP2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		radioCompP2.add("West", radioLabelP2);
		radioCompP2.add("Center", radioOnCompP2);
		radioCompP2.add("East", radioOffCompP2);

		// ���� + �ؽ�Ʈ
		JPanel radioTextP = new JPanel(new BorderLayout());
		radioTextP.add("North", labelTextP);
		radioTextP.add("Center", radioCompP2);
		radioTextP.add("South", textCompP);

		// �����ʿϼ�
		rightCompP = new JPanel(new BorderLayout());
		rightCompP.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		rightCompP.add("North", imageP);
		rightCompP.add("Center", radioTextP);
		rightCompP.add("South", rightBtnP);

		this.add("West", leftCompP);
		this.add("Center", rightCompP);

		setEvent();
		setCalEvent();
		showProc();
		// showProc();
		rightCompP.setVisible(false);
		setSize(900, 750);
		setVisible(true);

		reader = new ClientRead() {
			@Override
			public void run(Object obj) {
				// TODO Auto-generated method stub

				if (obj instanceof ResponseReserve) {
					ResponseReserve res = (ResponseReserve) obj;
					ProcessReserve(res);
				}
			}
		};

		/*
		 * dlg.addWindowListener(new WindowAdapter() {
		 * 
		 * @Override public void windowClosing(WindowEvent e) { // TODO
		 * Auto-generated method stub super.windowClosing(e); Close();
		 * 
		 * } });
		 */
		g_global.serverConnect.AddClientRead(reader);

		startT2.setEditable(false);
		stopT2.setEditable(false);
	}

	public void Close() {
		g_global.serverConnect.RemoveClientRead(reader);
		dlg.dispose();

	}

	// ���̺� ����� �Լ�
	public JScrollPane setTable() {
		String[] title = { "��ȣ", "�귣���", "��ǥ��", "���ȣ", "Wifi", "������", "�Ļ�",
				"VOD", "����", "����", "����", "", ""};
		model = new DefaultTableModel(title, 0);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		TableEvent tableEvent = new TableEvent();
		table.addMouseListener(tableEvent);
		table.getTableHeader().setReorderingAllowed(false);
		return sp;
	}

	// ��ư�̺�Ʈ ���� �Լ�
	public void setEvent() {
		ButtonEvent evt1 = new ButtonEvent();
		searchB.addActionListener(evt1);
		cancelB.addActionListener(evt1);
		reserveB.addActionListener(evt1);
	}

	// ���̺�Ʈ ���� �Լ�
	public void setCalEvent() {
		ImgChange evt2 = new ImgChange();
		logo.addMouseListener(evt2);
		cal3.addMouseListener(evt2);
		cal4.addMouseListener(evt2);
	}

	// �����ư �Լ� �����ϱ� �μ�Ʈ
	public void reservationProc() throws Exception {

		RequestReserve request = new RequestReserve();
		
		String start_date = startT2.getText().trim();
		String stop_date = stopT2.getText().trim();

		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(stop_date);

		request.data = new TB_ReserverAll();
		request.message = ReserveConstant.DEF_INSERT;
		
		// ���� �α����� ���� idx�� ����
		// int user_idx = g_global.getIdk();

		request.data.reserve.user_idx = g_global.getIdx();
		request.data.reserve.house_idx = houseIdx;
		request.data.reserve.room_idx = roomIdx;
		request.data.reserve.start_date = startDate;
		request.data.reserve.end_date = endDate;

		//Write(request);
		

		int kind = JOptionPane.showConfirmDialog(this, "���� �Ͻðڽ��ϱ�?", "�����ϱ�",
				JOptionPane.YES_NO_OPTION);
		
		if (kind == 0) {
			Write(request);
		} else  {
			return;
		}

	}

	// ��ҹ�ư �Լ�
	public void cancelProc() {
		rightCompP.setVisible(false);
	}

	// �˻���ư �Լ�
	public void searchProc() {
		table.setVisible(true);
	}

	// ���̺� Ŭ����
	public void clearTable() {
		int rows = table.getRowCount();
		for (int i = 0; i < rows; i++) {
			model.removeRow(0);
		}
	}

	// �ؽ�Ʈ Ŭ����
	public void clearField() {
		sleepT.setText("");
		noT.setText("");
		priceT.setText("");
	}

	public void Write(Serializable obj) {
		g_global.serverConnect.write(obj);
	}

	// ��ü ���̺� �ҷ�����
	public void showProc() {

		clearTable();

		RequestReserve request = new RequestReserve();
		request.data = new TB_ReserverAll();
		request.message = ReserveConstant.DEF_LIST;

		Write(request);
	}

	public void ProcessReserve(ResponseReserve res) {

		if (res.message == ReserveConstant.DEF_LIST) {
			this.res = res;
			if (res.result == true) {

				for (int i = 0; i < res.data.size(); i++) {

					Object[] data = new Object[13];

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
					data[12] = res.data.get(i).room.house_idx;
					
					model.addRow(data);
				}

				//buff2 = res.data.get(0).room.buff;
				
			}
		} else if (res.message == ReserveConstant.DEF_LIST_CONDITION) {
			
			if (res.result == true) {
				
				 if (res.data.size() == 0) {
						System.out.println(res.data.size());
						JOptionPane.showMessageDialog(this, "�˻������ �����ϴ�. �ٽ� �˻����ּ���");
						return;
					}
				
				for (int i = 0; i < res.data.size(); i++) {

					Object[] data = new Object[13];

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
					data[12] = res.data.get(i).room.house_idx;
				
					if (res.data.size() > 0) {
						model.addRow(data);
					} 
				} 
			} 
		} else if (res.message == ReserveConstant.DEF_INSERT) {
			
			if (res.result == true) {
					JOptionPane.showMessageDialog(this, "������ �Ϸ� �Ǿ����ϴ�.");
				} else {
					JOptionPane.showMessageDialog(this, "�ٽ� �õ��� �ּ���.");
				}
			}
		}

	// ���ǿ� �´� ���̺� �ҷ�����
	public void conditionProc() {
		clearTable();

		String wifi_select = "";
		if (wifiOn.isSelected()) {
			wifi_select = "Y";
		} else {
			wifi_select = "N";
		}
		String aircon_select = "";
		if (airOn.isSelected()) {
			aircon_select = "Y";
		} else if (airOff.isSelected()) {
			aircon_select = "N";
		}
		String meal_select = "";
		if (mealON.isSelected()) {
			meal_select = "Y";
		} else {
			meal_select = "N";
		}
		String vod_select = "";
		if (vodOn.isSelected()) {
			vod_select = "Y";
		} else {
			vod_select = "N";
		}
		String floor_select = "";
		if (floorON.isSelected()) {
			floor_select = "Y";
		} else {
			floor_select = "N";
		}
		String spa_select = "";
		if (spaOn.isSelected()) {
			spa_select = "Y";
		} else {
			spa_select = "N";
		}

		int price = (int) minC.getSelectedItem();
		int don = (int) maxC.getSelectedItem();

		RequestReserve request = new RequestReserve();
		request.data = new TB_ReserverAll();
		request.message = ReserveConstant.DEF_LIST_CONDITION;

		request.data.room.wifi_select = wifi_select;
		request.data.room.aircon_select = aircon_select;
		request.data.room.meal_select = meal_select;
		request.data.room.vod_select = vod_select;
		request.data.room.floor_select = floor_select;
		request.data.room.spa_select = spa_select;
		request.data.room.price = price;
		request.data.room.max_price = don;

		Write(request);

	}

	// �̸��� ȣ�� �Լ�
	public void calSelProc() {
		calendar = new Calender(this);
	}

	// ��ư �̺�Ʈ
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton target1 = (JButton) e.getSource();
			if (target1 == reserveB) {
				try {
					reservationProc();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (target1 == cancelB) {
				cancelProc();
			} else if (target1 == searchB) {
				conditionProc();
			}
		}
	}

	// ���̺�Ŭ���̺�Ʈ
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
			roomIdx = (int)table.getValueAt(row, 0);
			houseIdx = (int)table.getValueAt(row, 12);
			
			System.out.println("zzzzzzz :" + roomIdx);
			System.out.println("kkkkkkk :" + houseIdx);
			
			
			sleepT.setText(brand_name);
			noT.setText(String.valueOf(room_num));
			priceT.setText(String.valueOf(price));

			
			sleepT.setEditable(false);
			priceT.setEditable(false);
			noT.setEditable(false);
			
			String tablewifi = (String) table.getValueAt(row, 4);
			String tableair = (String) table.getValueAt(row, 5);
			String tablemeal = (String) table.getValueAt(row, 6);
			String tablevod = (String) table.getValueAt(row, 7);
			String tablefloor = (String) table.getValueAt(row, 8);
			String tablespa = (String) table.getValueAt(row, 9);
			file_name = (String) table.getValueAt(row, 11);

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
				mealON2.doClick();
			} else {
				mealOff2.doClick();
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
			System.out.println("ss="+res.message);
			System.out.println("afafafafafafa fafaf a : " + res.data.get(row).room.buff);
			
			ImageIcon icon = new ImageIcon(res.data.get(row).room.buff);
			logo.setIcon(icon);

		}
	}

	// ��Ŭ�� �̺�Ʈ
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
}