package util;

import java.awt.*;
import java.awt.event.*;
public class MyFrame extends Frame {

	public MyFrame() {
		this("");
	}
	
	
	public MyFrame(String title) {
		super(title);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public static void main(String[] args) {
		
		new MyFrame().setVisible(true);
	}

}
