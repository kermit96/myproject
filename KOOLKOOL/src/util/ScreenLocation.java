package util;

/*
 * frame 호출 시 위치를 가운데로 설정해주는 프로그램
 * 다른 클래스에 import 시켜서 사용
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
