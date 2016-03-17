package main.reserve;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

class SwingCal2 extends JFrame {
	String[] days = { "��", "��", "ȭ", "��", "��", "��", "��" };
	int year, month, day, todays, memoday = 0;
	Font f;
	Color bc, fc;
	Calendar today;
	Calendar cal;
	JButton btnBefore, btnAfter, target1;
	JButton[] calBtn = new JButton[49];
	JLabel thing;
	JLabel time;
	JPanel panWest;
	JPanel panSouth;
	JPanel panNorth;
	JTextField txtMonth, txtYear;
	JTextField txtTime;
	BorderLayout bLayout = new BorderLayout();

	ReserveInfo info;
	ButtonEvent evt1;
	

	String calDate = "";

	public SwingCal2(ReserveInfo i) {
		info = i;
		today = Calendar.getInstance(); // ����Ʈ�� Ÿ�� �� �� �������� ����� �޷��� �����ɴϴ�.
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;// 1���� ���� 0
		panNorth = new JPanel();
		panNorth.add(btnBefore = new JButton("Before"));
		panNorth.add(txtYear = new JTextField(year + "��"));
		panNorth.add(txtMonth = new JTextField(month + "��", 3));
		txtYear.setEnabled(false);
		txtMonth.setEnabled(false);
		panNorth.add(btnAfter = new JButton("After"));
		f = new Font("Sherif", Font.BOLD, 18);
		txtYear.setFont(f);
		txtMonth.setFont(f);
		add(panNorth, "North");
		// �̳��� �޷¿� ���� �ش��ϴ� �κ�
		panWest = new JPanel(new GridLayout(7, 7));// ���ڳ�,���������� ��ġ������
		f = new Font("Sherif", Font.BOLD, 12);

		ButtonEvent evt = new ButtonEvent();

		gridInit();
		calSet();
		hideInit();
		add(panWest, "Center");

		btnBefore.addActionListener(evt);
		btnAfter.addActionListener(evt);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Swing");
		setBounds(200, 200, 445, 350);
		setVisible(true);
	}// end constuctor

	public void calSet() {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));
		cal.set(Calendar.DATE, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		/*
		 * get �� set �� ���� �ʵ�ġ��, ������ ��Ÿ���ϴ�. �� �ʵ���
		 * ����,SUNDAY,MONDAY,TUESDAY,WEDNESDAY ,THURSDAY,FRIDAY, �� SATURDAY ��
		 * �˴ϴ�. get()�޼ҵ��� ���� ������ ���ڷ� ��ȯ
		 */
		int j = 0;
		int hopping = 0;
		calBtn[0].setForeground(new Color(255, 0, 0));// �Ͽ��� "��"
		calBtn[6].setForeground(new Color(0, 0, 255));// ����� "��"
		for (int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++) {
			j++;
		}
		/*
		 * �Ͽ��Ϻ��� �״��� ù���� ���ϱ��� ��ĭ���� �����ϱ� ����
		 */
		hopping = j;
		for (int kk = 0; kk < hopping; kk++) {
			calBtn[kk + 7].setText("");
		}
		for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal
				.getMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			if (cal.get(Calendar.MONTH) != month - 1) {
				break;
			}

			todays = i;
			if (memoday == 1) {
				calBtn[i + 6 + hopping].setForeground(new Color(0, 255, 0));
			} 
			else {
				// �׸��� �κ��̾� 
				calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 0));
				
				// ������ Ÿ���� cal4�� ���� ���� ��� �ϴ� �κ��� �ʿ��ϴ�
				if (SwingCal2.this.info.target  == SwingCal2.this.info.cal4) {	
				String currSDate = SwingCal2.this.info.startT2.getText().trim();
				String[] SDateChk = currSDate.split("-");
				String year =  SDateChk[0];
				String month = SDateChk[1];
				String day = SDateChk[2];
				// ���� �ϰ� �߰�
				// ��
				// ���� ���� �Ǵ� �����͸�  ������ �� ������ month �� ���� �������� ���� ���Ѵ�
				// ������ �������� ���� ������ ������ ���δ� ���´�
				// �����͸� �������� ���δ� �����ش�
				String months = txtMonth.getText();
				
				int monthX  = Integer.parseInt(months.replaceAll("��", ""));
				
				
				
				
				// ���� ���� �Ǵ� �����͸� ������ �� ������ year �� ���� �������� ���� ���Ѵ�.
				String years = txtYear.getText();
				int yearX  = Integer.parseInt(years.replaceAll("��", ""));
				
				
				int day2 = Integer.parseInt(day);
				int month2 = Integer.parseInt(month);
				int year2 = Integer.parseInt(year);
				
				//   ��������    <   �����  ||  ������ <= �����
				if(target1  ==  btnAfter){
					if(this.year > year2) {
						calBtn[i + 6 + hopping].setEnabled(true);
					}
					
				} else if (target1 == btnBefore) {
					if (this.month < month2 || this.year < year2) {
						calBtn[i + 6 + hopping].setEnabled(false);
					}
				}
				
				
				/*else if(target1 == btnAfter || monthX > this.month) {
					calBtn[i + 6 + hopping].setEnabled(true);
				}*/
				
					if (i < day2) {
						calBtn[i + 6 + hopping].setEnabled(false);
						
						if (monthX <month2)	 {
							
							for(int k= 0; k < 31; k++) {
								calBtn[i + 6 + hopping].setEnabled(false);
							}
						}
						
						
						/*if (this.year < year2) {
							
							for (int n = 0; n < 31; n++) {
								calBtn[i + 6 + hopping].setEnabled(true);
							}
						}*/
						
						
					}
				}
				
				//calBtn[i+ 6 + hopping].setEnabled(false);
				if ((i + hopping - 1) % 7 == 0) {// �Ͽ���
					calBtn[i + 6 + hopping].setForeground(new Color(255, 0, 0));
					
	
					
					if (SwingCal2.this.info.target  == SwingCal2.this.info.cal4) {
						
						String currSDate = SwingCal2.this.info.startT2.getText().trim();
						
						String[] SDateChk = currSDate.split("-");
						
						String year =  SDateChk[0];
						String month = SDateChk[1];
						String day = SDateChk[2];
						
						int day2 = Integer.parseInt(day);
							
							if (i < day2) {
								calBtn[i + 6 + hopping].setEnabled(false);
							}
						}
				}
				if ((i + hopping) % 7 == 0) {// �����
					calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 255));
					
					if (SwingCal2.this.info.target  == SwingCal2.this.info.cal4) {
						
						String currSDate = SwingCal2.this.info.startT2.getText().trim();
						
						String[] SDateChk = currSDate.split("-");
						
						String year =  SDateChk[0];
						String month = SDateChk[1];
						String day = SDateChk[2];
						
						int day2 = Integer.parseInt(day);
							
							if (i < day2) {
								calBtn[i + 6 + hopping].setEnabled(false);
							}
						}
				}
			}
			/*
			 * ������ ���� �������� ����ؾ� �ϴ� ������ ���� ��ư�� ������ ���ϰ� �ε����� 0���� �����̴� -1�� ���� ������
			 * ������ ���ְ� ��ư�� ������ �������ش�.
			 */
			calBtn[i + 6 + hopping].setText((i) + "");
		}// for
	}// end Calset()

	public String sendDate() {
		return calDate;
	}

	public void hideInit() {
		for (int i = 0; i < calBtn.length; i++) {
			if ((calBtn[i].getText()).equals(""))

				// ���� ������ ���� ������ ��ư�� ��Ȱ��ȭ ��Ų��.

				calBtn[i].setEnabled(false);
			// calBtn[i].setBackground(Color.blue);

		}// end for
	}// end hideInit()

	public void gridInit() {
		// jPanel3�� ��ư ���̱�

		evt1 = new ButtonEvent();

		
		for (int i = 0; i < days.length; i++) {
			
			panWest.add(calBtn[i] = new JButton(days[i]));
			calBtn[i].setContentAreaFilled(false);
			calBtn[i].setBorderPainted(false);
			
		}
		for (int i = days.length; i < 49; i++) {
			panWest.add(calBtn[i] = new JButton(""));
			calBtn[i].addActionListener(evt1);
		}
	}// end gridInit()
	
	
	public void panelInit() {
		GridLayout gridLayout1 = new GridLayout(7, 7);
		panWest.setLayout(gridLayout1);
	}// end panelInit()

	public void calInput(int gap) {
		month += (gap);
		if (month <= 0) {
			month = 12;
			year = year - 1;
		} else if (month >= 13) {
			month = 1;
			year = year + 1;
		}
	}// end calInput()

	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			target1 = (JButton) ae.getSource();
			if (ae.getSource() == btnBefore) {
				panWest.removeAll();
				calInput(-1);
				gridInit();
				panelInit();
				calSet();
				hideInit();
				txtYear.setText(year + "��");
				txtMonth.setText(month + "��");
				
			} else if (ae.getSource() == btnAfter) {
				panWest.removeAll();
				calInput(1);
				gridInit();
				panelInit();
				calSet();
				hideInit();
				txtYear.setText(year + "��");
				txtMonth.setText(month + "��");
			} else if (Integer.parseInt(ae.getActionCommand()) >= 1
					&& Integer.parseInt(ae.getActionCommand()) <= 31) {
				day = Integer.parseInt(ae.getActionCommand());
				// ��ư�� ��� �� 1,2,3.... ���ڸ� ���������� ��ȯ�Ͽ� Ŭ���� ��¥�� �ٲ��ش�.
				// System.out.println(+year+"-"+month+"-"+day);
				calDate = year + "-" + month + "-" + day;
				// System.out.println(calDate);
				System.out.println("�Լ� ȣ�� ��" + calDate);
				String currDate = calDate;
				// System.out.println(currDate);

				

				if (SwingCal2.this.info.target == SwingCal2.this.info.cal3) {
					SwingCal2.this.info.startT2.setText(calDate);
				} else if (SwingCal2.this.info.target == SwingCal2.this.info.cal4) {

					

					String currSDate = SwingCal2.this.info.startT2.getText();

					String[] chkStart_date = currSDate.split("-");
					String chkYear = chkStart_date[0];
					String chkMonth = chkStart_date[1];
					String chkDay = chkStart_date[2];

					int max = Integer.parseInt(chkDay);

/*					System.out.println("�� :" + chkYear);
					System.out.println("�� :" + chkMonth);
					System.out.println("�� :" + chkDay);*/

					for (int i = 9; i < 39; i++) {
						String a = calBtn[i].getText();
						int b = (Integer.parseInt(a));

						if (b < max) {
							calBtn[i].setEnabled(false);
						}

						/*
						 * if ((Integer.parseInt(calBtn[i].getText()) < max )) {
						 * calBtn[i].setBackground(Color.red); }
						 */

						// calBtn[i].setEnabled(false);
						// calBtn[i].setBackground(Color.blue);

					}// end for

					SwingCal2.this.info.stopT2.setText(calDate);
				}

				// setVisible(false);
				// sendDate();
				// calSet();

			}
		}// end actionperformed()
	}// end class
}

public class CalenderInfo {

	CalenderInfo main;

	public CalenderInfo(ReserveInfo i) {
		SwingCal2 a = new SwingCal2(i);

	}

	public static void main(String[] args) {
		// System.out.println("�Լ� ȣ��");
		// SwingCal a = new SwingCal();

	}

}