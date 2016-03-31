package main.house;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Dao.house.HouseConstant;
import Dao.house.HouseSql;
import Dao.Constant;
import Dao.house.HouseSql;
import Dao.house.RequestHouse;
import Dao.house.ResponseHouse;
import Dao.house.TB_HOUSE;
import util.ClientBase;
import util.ClientConnect;
import util.ClientError;
import util.ClientRead;
import main.Global;
import main.house.house_add.JTextFieldLimit;

public class house_adjust extends JPanel {
	JTextField nameT;
	JTextField exponentT;
	JTextField calT, calT2, calT3;
	JTextField emailT;
	JTextField addressT;
	JTextField remarkT;
	JTextField title;
	JTextArea remark;
	JLabel logo;
	Choice telCombo, emailCombo;
	JButton listB, adjustB, deleteB;
	ImageIcon icon, icon2;
	Image img = null;
	String fname;
	String fdir;
	FileDialog fd;
	CardLayout card;
	String file_name;
	house_management main;
	mainP houseP;
	RequestHouse request;
	Global g_global = Global.getInstance();
	ClientRead reader;

	public house_adjust(house_management main2) {

		main = main2;

		this.setLayout(new BorderLayout());

		JPanel FieldP = setField();
		JPanel ButtonP = setButton();

		// 오른쪽패널 이미지라벨
		// icon = new ImageIcon("src\\pack\\b.png");
		logo = new JLabel();
		logo.setPreferredSize(new java.awt.Dimension(250, 250));
		logo.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// 이미지
		ImgChange evt = new ImgChange();
		JPanel p16 = new JPanel();
		p16.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "사진보기"));
		logo.addMouseListener(evt);
		p16.add(logo);

		JPanel p00 = new JPanel();
		p00.add("Center", p16);

		this.add("West", FieldP);
		this.add("South", ButtonP);
		this.add("Center", p00);

		// setSize(500,500);
		// setVisible(true);

		reader = new ClientRead() {
			@Override
			public void run(Object obj) {
				// TODO Auto-generated method stub

				if (obj instanceof ResponseHouse) {
					ResponseHouse res = (ResponseHouse) obj;
					ProcessHouse(res);
				}
			}
		};
		g_global.serverConnect.AddClientRead(reader);
	}

	public JPanel setField() {
		JLabel nameL = new JLabel("이름", JLabel.LEFT);
		JLabel exponentL = new JLabel("대표자", JLabel.LEFT);
		JLabel calL = new JLabel("전화번호", JLabel.LEFT);
		JLabel emailL = new JLabel("이메일", JLabel.LEFT);
		JLabel addressL = new JLabel("주소", JLabel.LEFT);
		JLabel remarkL = new JLabel("비고", JLabel.LEFT);
		JLabel title = new JLabel("숙박 시설");
		title.setHorizontalAlignment(JLabel.CENTER);
		add(title, BorderLayout.NORTH);
		// JLabel hL=new JLabel("숙박시설",JLabel.TOP);

		nameT = new JTextField(15);
		exponentT = new JTextField(15);
		calT = new JTextField();
		calT2 = new JTextField(3);
		calT3 = new JTextField(3);
		emailT = new JTextField(5);

		addressT = new JTextField(15);
		remark = new JTextArea(5, 4);
		telCombo = new Choice();
		emailCombo = new Choice();
		calT2.setDocument(new JTextFieldLimit(4));
		calT3.setDocument(new JTextFieldLimit(4));

		JScrollPane pane = new JScrollPane(remark);

		JPanel p1 = new JPanel(new GridLayout(5, 1, 15, 15));
		p1.add(nameL);
		p1.add(exponentL);
		p1.add(calL);
		p1.add(emailL);
		p1.add(addressL);
		// p1.add(remarkL);

		JPanel telPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		telCombo.addItem("010");
		telCombo.addItem("011");
		telCombo.addItem("017");
		telCombo.addItem("018");
		telPanel.add(telCombo);
		telPanel.add(new Label("-"));
		telPanel.add(calT2);
		telPanel.add(new Label("-"));
		telPanel.add(calT3);

		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		emailCombo.addItem("naver.com");
		emailCombo.addItem("hanmail.net");
		emailCombo.addItem("nate.com");
		emailPanel.add(emailT);
		emailPanel.add(new Label("@"));
		emailPanel.add(emailCombo);

		JPanel p2 = new JPanel(new GridLayout(5, 1, 15, 15));
		p2.add(nameT);
		p2.add(exponentT);
		p2.add(telPanel);
		p2.add(emailPanel);
		p2.add(addressT);
		// p2.add(remarkT);

		JPanel p00 = new JPanel(new BorderLayout());
		p00.add("West", remarkL);
		p00.add("Center", pane);

		JPanel p3 = new JPanel(new BorderLayout(10, 10));
		p3.add("West", p1);
		p3.add("Center", p2);
		p3.add("South", p00);

		JPanel p4 = new JPanel(new BorderLayout());
		p4.add("North", p3);

		return p4;
	}

	public JPanel setButton() {
		listB = new JButton("목록");
		adjustB = new JButton("수정");
		deleteB = new JButton("삭제");
		ButtonEvent evt = new ButtonEvent();
		listB.addActionListener(evt);
		adjustB.addActionListener(evt);
		deleteB.addActionListener(evt);
		JPanel p = new JPanel(new FlowLayout());
		p.add("Wast", listB);
		p.add("East", adjustB);
		p.add("Center", deleteB);
		return p;
	}

	public void openProc() {
		this.fd = new FileDialog(this.fd, "사진 불러오기", FileDialog.LOAD);
		fd.setVisible(true);
		fname = fd.getFile();
		fdir = fd.getDirectory();
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

	public void selectHouse(int idx) {

		// request 처리
		request = new RequestHouse();

		request.data = new TB_HOUSE();
		request.message = HouseConstant.DEF_IDX_LIST;
		request.data.idx = idx;

		Write(request);

	}

	// 응답받은 부분을 처리 한다

	public void ProcessHouse(ResponseHouse res) {

		if (res.message == HouseConstant.DEF_IDX_LIST) {

			if (res.result == true) {
				
				removeField();
				// reuslt가 true

				String name = res.data.get(0).name;
				String host_name = res.data.get(0).host_name;
				String host_email = res.data.get(0).host_email;
				String host_tel = res.data.get(0).host_tel;
				String address = res.data.get(0).address;
				file_name = res.data.get(0).file_name;
				String remark = res.data.get(0).home_intro;

				String[] host_telArr = host_tel.split("-");
				String[] host_emailArr = host_email.split("@");
				
				// 선택한 idx로 데이터를 조회
				nameT.setText(name);
				exponentT.setText(host_name);
				emailT.setText(host_emailArr[0]);
				telCombo.select(host_telArr[0]);
				calT2.setText(host_telArr[1]);
				calT3.setText(host_telArr[2]);
				addressT.setText(address);
				System.out.println("ffffffffffffff: "+ res.data.get(0).buff.length);
				if (file_name == null || file_name.length() == 0) {
					ImageIcon icon = new ImageIcon(
							"resources/house/default.gif");
					logo.setIcon(icon);

				} else {
					
					ImageIcon icon = new ImageIcon(res.data.get(0).buff);
					logo.setIcon(icon);

				}

				this.remark.setText(remark);
				main.houseP.clearTable();
				main.houseP.showProc();
			} else {
				JOptionPane.showMessageDialog(this, res.reason.toString());   
			}

		} else if (res.message == HouseConstant.DEF_UPDATE) {
			
			if (res.result == true) {
				
				JOptionPane.showMessageDialog(main.houseP, "수정되었습니다");
				main.houseP.titleL.setText("숙박 시설 목록");
				main.houseP.card.show(main.houseP.WriteP, "Table");
				main.houseP.showProc();
				house_adjust.this.setVisible(false);
				
			} else {
				JOptionPane.showMessageDialog(this, res.reason.toString());   

			}
			
		} else if (res.message == HouseConstant.DEF_DELETE) {
			
			if (res.result == true) {
				
				JOptionPane.showMessageDialog(main.houseP, "삭제되었습니다");
				main.houseP.titleL.setText("숙박 시설 목록");
				main.houseP.card.show(main.houseP.WriteP, "Table");
				main.houseP.showProc();
				house_adjust.this.setVisible(false);
			}
		}
		
	}

	public void modifyProc() {

		request = new RequestHouse();

		request.data = new TB_HOUSE();
		request.message = HouseConstant.DEF_UPDATE;	

		// TB_HOUSE temp = new TB_HOUSE();

		request.data.name = nameT.getText().trim();
		request.data.host_name = exponentT.getText().trim();
		request.data.host_tel = telCombo.getSelectedItem() + "-"
				+ calT2.getText().trim() + "-" + calT3.getText();
		request.data.host_email = emailT.getText().trim() + "@"
				+ emailCombo.getSelectedItem();
		request.data.address = addressT.getText().trim();
		request.data.home_intro = remark.getText().trim();

		if (fname == null) {
			request.data.file_name = main.houseP.file_name;
		} else {
			fname = fd.getFile();
			request.data.file_name = fname;
		}
		
		request.data.idx = main.houseP.houseIdx;
		uploadProc();
		Write(request);
	}

	// 파일 업로드

	public void uploadProc() {

		String currFile = fd.getFile();

		if (currFile == main.houseP.file_name || currFile.length() == 0) {
			currFile = houseP.file_name;
			System.out.println("현재 파일 :" + main.houseP.file_name);
		} else {
			currFile = fd.getFile();
		}

		File file = new File(fd.getDirectory(), currFile);

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

		File upFile = new File("resources/house/", fname);

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
		
		request = new RequestHouse();
		request.data = new TB_HOUSE();
		request.message = HouseConstant.DEF_DELETE;
		request.data.idx = main.houseP.houseIdx;
		Write(request);		
	}
	

	private void removeField() {

		nameT.setText("");
		exponentT.setText("");
		calT.setText("");
		emailT.setText("");
		addressT.setText("");
		// remarkT.setText("");
		remark.setText("");
		emailCombo.select("");
		telCombo.select("");

	}

	// 수정하는 부분

	class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			if (target == listB) {
				main.houseP.card.show(main.houseP.WriteP, "Table");
				main.houseP.titleL.setText("숙박 시설 목록");
				// removeField();
			} else if (target == adjustB) {
				int kind = JOptionPane.showConfirmDialog(main.houseP,
						"수정 하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION);

				if (kind == 0) {
					// 수정 버튼을 눌렀을 때 함수 호출
					modifyProc();
				} else {
					JOptionPane.showMessageDialog(main.houseP, "취소");
					return;
				}
				
			} else if (target == deleteB) {

				int kind = JOptionPane.showConfirmDialog(main.houseP,
						"삭제 하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);

				if (kind == 0) {

					deleteProc();
				} else {
					JOptionPane.showMessageDialog(main.houseP, "취소");
				}

				/*main.houseP.card.show(main.houseP.WriteP, "Table");
				main.houseP.showProc();*/
			}
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
