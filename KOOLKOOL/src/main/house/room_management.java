package main.house;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import main.Global;
import util.ClientRead;
import Dao.Constant;
import Dao.house.HouseConstant;
import Dao.house.ResponseHouse;
import Dao.room.RequestRoom;
import Dao.room.ResponseRoom;
import Dao.room.RoomConstant;
import Dao.room.RoomSql;
import Dao.room.TB_ROOM;

import java.awt.Event;
import java.awt.event.*;
import java.io.Serializable;

public class room_management {
	JFrame f;
	JTable mainT;
	DefaultTableModel model;
	JButton addB;
	CardLayout card;
	JPanel WriteP;
	mainPP roomP;

	public room_management() {

		f = new JFrame();

		card = new CardLayout();
		f.setLayout(card);

		roomP = new mainPP(this);

		f.add("mainP", roomP);

		f.setSize(600, 500);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
			}
		});
	}

	public static void main(String[] args) {
		new room_management();
	}
}

class mainPP extends JPanel {

	room_management main;
	room_adjust modify;
	JTable mainT;
	DefaultTableModel model;
	CardLayout card, mcard;
	JPanel WriteP;
	JButton addB;
	RoomSql sql;
	ResponseRoom res;
	RequestRoom request;
	JPanel titlePanel;
	JLabel titleL;
	String file_name;
	Global g_global = Global.getInstance();
	ClientRead reader;

	public static int roomIdx = 0;

	public mainPP(room_management m) {
		main = m;
		this.setLayout(new BorderLayout());

		titleL = new JLabel("방 관리", JLabel.CENTER);

		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		titlePanel.add(titleL);

		mainT = new JTable();
		String[] title = { "번호", "이름", "대표자 이름", "등록일", "wifi", "에어컨", "식사",
				"VOD", "복층", "스파" };

		model = new DefaultTableModel(title, 0) {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		TableEvent evtT = new TableEvent();
		mainT = new JTable(model);
		mainT.addMouseListener(evtT);
		JScrollPane sp = new JScrollPane(mainT);

		addB = new JButton("등록");
		addB.addActionListener(new ButtonEvent());

		JPanel buttonP = new JPanel(new FlowLayout());

		buttonP.add(addB);

		JPanel homeP = new JPanel(new BorderLayout());
		homeP.add(sp);
		homeP.add("South", buttonP);

		room_add t4 = new room_add(main);
		card = new CardLayout();

		modify = new room_adjust(main);
		mcard = new CardLayout();

		WriteP = new JPanel();
		WriteP.setLayout(card);

		WriteP.add("Table", homeP);
		WriteP.add("Edit", t4);
		WriteP.add("Modify", modify);
		card.first(WriteP);

		this.add("North", titlePanel);
		this.add("Center", WriteP);

		this.setSize(500, 600);
		this.setVisible(true);

		reader = new ClientRead() {
			@Override
			public void run(Object obj) {
				// TODO Auto-generated method stub

				if (obj instanceof ResponseRoom) {
					res = (ResponseRoom) obj;
					ProcessRoom(res);
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

	public void Close() {
		g_global.serverConnect.RemoveClientRead(reader);
		main.f.dispose();

	}

	public void clearTable() {
		int rows = mainT.getRowCount();
		for (int i = 0; i < rows; i++) {
			model.removeRow(0);
		}
	}

	public void Write(Serializable obj) {
		g_global.serverConnect.write(obj);
	}

	public void showProc() {

		// db 연결해서 리스트 출력

		request = new RequestRoom();
		request.data = new TB_ROOM();
		request.message = RoomConstant.DEF_LIST;

		Write(request);

	}

	// 방 조회

	public void ProcessRoom(ResponseRoom res) {

		if (res.message == RoomConstant.DEF_LIST) {

			if (res.result == true) {
				clearTable();
				for (int i = 0; i < res.data.size(); i++) {

					Object[] data = new Object[10];

					data[0] = res.data.get(i).idx;
					data[1] = res.data.get(i).name;
					data[2] = res.data.get(i).host_name;
					data[3] = res.data.get(i).reg_date;
					data[4] = res.data.get(i).wifi_select;
					data[5] = res.data.get(i).aircon_select;
					data[6] = res.data.get(i).meal_select;
					data[7] = res.data.get(i).vod_select;
					data[8] = res.data.get(i).floor_select;
					data[9] = res.data.get(i).spa_select;

					model.addRow(data);
				}
			} else {
				JOptionPane.showMessageDialog(this, "List 를  가져 오는데 실패했습니다."
						+ res.message);
			}

		} else if (res.message == RoomConstant.DEF_IDX_LIST) {

			// select

			if (res.result == true) {

				String name = res.data.get(0).name;
				int room_no = res.data.get(0).room_no;
				int price = res.data.get(0).price;
				String wifi_select = res.data.get(0).wifi_select;
				String aircon_select = res.data.get(0).aircon_select;
				String meal_select = res.data.get(0).meal_select;
				String vod_select = res.data.get(0).vod_select;
				String floor_select = res.data.get(0).floor_select;
				String spa_select = res.data.get(0).spa_select;
				file_name = res.data.get(0).file_name;

				int pos = res.data.get(0).house_idx;

				System.out.println(pos);
				modify.houseList.select(pos - 1);
				modify.nameText.setText(name);
				modify.roomsnumberText.setText(String.valueOf(room_no));
				modify.priceText.setText(String.valueOf(price));

				if (wifi_select.equals("Y")) {
					modify.wifion.doClick();
				} else {
					modify.wifioff.doClick();
				}

				if (aircon_select.equals("Y")) {
					modify.airon.doClick();
				} else {
					modify.airoff.doClick();
				}

				if (meal_select.equals("Y")) {
					modify.foodon.doClick();
				} else {
					modify.foodoff.doClick();
				}

				if (vod_select.equals("Y")) {
					modify.vodon.doClick();
				} else {
					modify.vodoff.doClick();
				}

				if (floor_select.equals("Y")) {
					modify.duplexon.doClick();
				} else {
					modify.duplexoff.doClick();
				}

				if (spa_select.equals("Y")) {
					modify.sparon.doClick();
				} else {
					modify.sparoff.doClick();
				}

				if (file_name == null || file_name.length() == 0) {
					ImageIcon icon = new ImageIcon(
							"resources/house/default.gif");
					modify.logo.setIcon(icon);

				} else {
					ImageIcon icon = new ImageIcon("resources/room/"
							+ file_name);
					modify.logo.setIcon(icon);
				}

			} else {
				System.out.println(res.reason);
			}

		}

	}

	public void selectRoom(int idx) {

		removeField();

		request = new RequestRoom();
		request.data = new TB_ROOM();
		request.message = RoomConstant.DEF_IDX_LIST;
		request.data.idx = idx;

		Write(request);

		// select 된 다음
	}

	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();

			CardLayout card = (CardLayout) (WriteP.getLayout());
			if (target == addB) {
				// System.out.println("adfs");
				main.roomP.titleL.setText("방 등록");
				card.show(WriteP, "Edit");
			}
		}
	}

	class TableEvent extends MouseAdapter {
		public void mouseClicked(MouseEvent eM) {
			int row = mainT.getSelectedRow();
			if (row == -1) {
				return;
			}

			// 마우스 더블클릭 했을 때 이벤트 발생

			if (eM.getClickCount() == 2) {

				int no = (int) mainT.getValueAt(row, 0);

				roomIdx = no;
				selectRoom(roomIdx);

				main.roomP.titleL.setText("방 수정");
				main.roomP.card.show(WriteP, "Modify");

			}
		}
	}

	private void removeField() {

		modify.nameText.setText("");
		modify.priceText.setText("");
		modify.roomsnumberText.setText("");

	}
}
