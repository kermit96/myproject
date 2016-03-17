package main.reserve;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

class SwingCal2 extends JFrame {
	String[] days = { "일", "월", "화", "수", "목", "금", "토" };
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
		today = Calendar.getInstance(); // 디폴트의 타임 존 및 로케일을 사용해 달력을 가져옵니다.
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;// 1월의 값이 0
		panNorth = new JPanel();
		panNorth.add(btnBefore = new JButton("Before"));
		panNorth.add(txtYear = new JTextField(year + "년"));
		panNorth.add(txtMonth = new JTextField(month + "월", 3));
		txtYear.setEnabled(false);
		txtMonth.setEnabled(false);
		panNorth.add(btnAfter = new JButton("After"));
		f = new Font("Sherif", Font.BOLD, 18);
		txtYear.setFont(f);
		txtMonth.setFont(f);
		add(panNorth, "North");
		// 이놈은 달력에 날에 해당하는 부분
		panWest = new JPanel(new GridLayout(7, 7));// 격자나,눈금형태의 배치관리자
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
		 * get 및 set 를 위한 필드치로, 요일을 나타냅니다. 이 필드의
		 * 값은,SUNDAY,MONDAY,TUESDAY,WEDNESDAY ,THURSDAY,FRIDAY, 및 SATURDAY 가
		 * 됩니다. get()메소드의 의해 요일이 숫자로 반환
		 */
		int j = 0;
		int hopping = 0;
		calBtn[0].setForeground(new Color(255, 0, 0));// 일요일 "일"
		calBtn[6].setForeground(new Color(0, 0, 255));// 토요일 "토"
		for (int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++) {
			j++;
		}
		/*
		 * 일요일부터 그달의 첫시작 요일까지 빈칸으로 셋팅하기 위해
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
				// 그리는 부분이야 
				calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 0));
				
				// 하지만 타겟이 cal4일 때는 막아 줘야 하는 부분이 필요하다
				if (SwingCal2.this.info.target  == SwingCal2.this.info.cal4) {	
				String currSDate = SwingCal2.this.info.startT2.getText().trim();
				String[] SDateChk = currSDate.split("-");
				String year =  SDateChk[0];
				String month = SDateChk[1];
				String day = SDateChk[2];
				// 일은 니가 했고
				// 년
				// 월은 비포 또는 애프터를  눌렀을 때 나오는 month 와 현재 시작일의 월을 비교한다
				// 비포를 눌렀을때 현재 월보다 작으면 전부다 막는다
				// 에프터를 눌렀을대 전부다 보여준다
				String months = txtMonth.getText();
				
				int monthX  = Integer.parseInt(months.replaceAll("월", ""));
				
				
				
				
				// 년은 비포 또는 애프터를 눌렀을 때 나오는 year 와 현재 시작일의 년을 비교한다.
				String years = txtYear.getText();
				int yearX  = Integer.parseInt(years.replaceAll("년", ""));
				
				
				int day2 = Integer.parseInt(day);
				int month2 = Integer.parseInt(month);
				int year2 = Integer.parseInt(year);
				
				//   비포달이    <   현재달  ||  비포년 <= 현재년
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
				if ((i + hopping - 1) % 7 == 0) {// 일요일
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
				if ((i + hopping) % 7 == 0) {// 토요일
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
			 * 요일을 찍은 다음부터 계산해야 하니 요일을 찍은 버튼의 갯수를 더하고 인덱스가 0부터 시작이니 -1을 해준 값으로
			 * 연산을 해주고 버튼의 색깔을 변경해준다.
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

				// 일이 찍히지 않은 나머지 버튼을 비활성화 시킨다.

				calBtn[i].setEnabled(false);
			// calBtn[i].setBackground(Color.blue);

		}// end for
	}// end hideInit()

	public void gridInit() {
		// jPanel3에 버튼 붙이기

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
				txtYear.setText(year + "년");
				txtMonth.setText(month + "월");
				
			} else if (ae.getSource() == btnAfter) {
				panWest.removeAll();
				calInput(1);
				gridInit();
				panelInit();
				calSet();
				hideInit();
				txtYear.setText(year + "년");
				txtMonth.setText(month + "월");
			} else if (Integer.parseInt(ae.getActionCommand()) >= 1
					&& Integer.parseInt(ae.getActionCommand()) <= 31) {
				day = Integer.parseInt(ae.getActionCommand());
				// 버튼의 밸류 즉 1,2,3.... 문자를 정수형으로 변환하여 클릭한 날짜를 바꿔준다.
				// System.out.println(+year+"-"+month+"-"+day);
				calDate = year + "-" + month + "-" + day;
				// System.out.println(calDate);
				System.out.println("함수 호출 끝" + calDate);
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

/*					System.out.println("년 :" + chkYear);
					System.out.println("월 :" + chkMonth);
					System.out.println("일 :" + chkDay);*/

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
		// System.out.println("함수 호출");
		// SwingCal a = new SwingCal();

	}

}