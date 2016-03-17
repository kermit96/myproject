package main;
import javax.swing.*;

import util.ScreenLocation;

import java.awt.*;

// listen인 할  port 설정 
public class Setting extends JDialog {

	public int select =0; 
	
	public int port  = 0;
	
	private  String server ="";
	
	public String getServer() { 
		return hostfield.getText();
	}


	public void setServer(String server) {
		this.server = server;
		this.hostfield.setText(server);
	}


	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
		portfield.setText(Integer.toString(port));
		
	}
	
	
	public void setStringt(String server) {
		this.server = server;
		portfield.setText(server);
		
	}


	public static final int OK =1;
	public static final int Cancel =0;
	public JTextField  portfield ;
	public JTextField  hostfield ;
	
	
	public void Init()
	{
		JPanel panel = new JPanel(new FlowLayout());
		
		JButton ok = new JButton("확인");
		JButton cancel = new JButton("취소");

		
		panel.add(ok);
		panel.add(cancel);
		
		JPanel panel2 = new JPanel(new GridLayout(2,2));
		
		JLabel Serverlabel = new JLabel("Host");
		
		JLabel label = new JLabel("Port");
		
		hostfield = new JTextField(10);
		
		
		portfield = new JTextField(10);
		
		portfield.setText(Integer.toString(port));
		
		ok.addActionListener(x->{
			RetOk();
		});
		

		cancel.addActionListener(x->{
			select = this.Cancel;
			this.setVisible(false);
		});
		
		
		panel2.add(Serverlabel);
		panel2.add(hostfield);
		
		panel2.add(label);
		panel2.add(portfield);
		
	
		
		this.add(panel,"South");
		this.add(panel2,"Center");
		
		pack();

		this.setModal(true);
		
		ScreenLocation screen = new ScreenLocation();
		screen.setScreen();
		setLocation(screen.x, screen.y);

		
	}
	
	public Setting(JFrame frame, String title) {
		super(frame,title,true);
		Init();	
	}
	
	public Setting(JDialog frame, String title) {
		super(frame,title,true);
		Init();	
	}
	
	
	
	public void RetOk()
	{
		
		try {
		  port = Integer.parseInt(portfield.getText());
		} catch(Exception ex) {
		
			JOptionPane.showMessageDialog(this, "숫자만 입력해야 합니다");
			return;
		}
		
		select = OK;
		this.setVisible(false);
	
	}
	
	
	
	public static void main(String[] args) 
	{
		JFrame frame = null;
		Setting set = new Setting(frame,"환경 설정");						
		set.setPort(3000);
		set.setVisible(true);						
		System.out.println(set.getPort());		
		return ;
		
	}
	

}
