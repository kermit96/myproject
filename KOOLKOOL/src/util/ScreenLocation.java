package util;

/*
 * frame ȣ�� �� ��ġ�� ����� �������ִ� ���α׷�
 * �ٸ� Ŭ������ import ���Ѽ� ���
 * 
 * 
 */
import java.awt.*;

public class ScreenLocation {
	
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();

	public int x;
	public int y;
	
	public ScreenLocation() {
		
	}
	
	public void setScreen() {
		x = (scr.width - 500) / 2;
		y = (scr.height - 500) / 2;
		
	}

	public static void main(String[] args) {

		new ScreenLocation();
		
	}

}
