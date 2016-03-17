package Server;
import javax.swing.*;

import java.awt.*;
import java.lang.reflect.Field;

// listen�� ��  port ���� 
public class Setting extends JDialog {

	public int select =0; 

	public int port  = 0;
	/**
	 * @return the port
	 */

	private  String SaveDir = "";
	
	public int getPort() {
		return port;
	}

	public String getSaveDir() {		
		return SaveDir;
	}

	public void setSaveDir(String value) {		
		SaveDir = value;
		dirfield.setText(value);
	}



	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
		portfield.setText(Integer.toString(port));

	}

	public static final int OK =1;
	public static final int Cancel =0;
	public JTextField  portfield ;
	public JTextField  hostfield;

	private JTextField dirfield;
	public Setting(JFrame frame, String title) {
		
		super(frame,title,true);						
		JPanel panel = new JPanel(new FlowLayout());

		JButton ok = new JButton("Ȯ��");
		JButton cancel = new JButton("���");

		panel.add(ok);
		panel.add(cancel);

		JPanel panel2 = new JPanel(new FlowLayout());

		JLabel label = new JLabel("           Port        ");

		portfield = new JTextField(15);

		panel2.add(label);
		panel2.add(portfield);


		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel label3 = new JLabel("���� ���丮");
		dirfield = new JTextField(15);

		panel3.add(label3);
		panel3.add(dirfield);

		portfield.setText(Integer.toString(port));


		ok.addActionListener(x->{
			RetOk();
		});


		cancel.addActionListener(x->{
			select = this.Cancel;
			this.setVisible(false);
		});

		//	this.setSize(180, 100);

		this.add(panel,"South");
		this.add(panel3,"Center");
		this.add(panel2,"North");

		pack();

		setResizable(false);
		// this.setVisible(true);
	}


	public void RetOk()
	{

		try {
			port = Integer.parseInt(portfield.getText());
		} catch(Exception ex) {

			JOptionPane.showMessageDialog(this, "���ڸ� �Է��ؾ� �մϴ�");
			return;

		}

		String dir = this.dirfield.getText();

		if (dir.isEmpty()) {
			JOptionPane.showMessageDialog(this, "���� ���丮�� �Է��ϼ���");
			return;			 			  
		}

		select = OK;
		
		this.SaveDir = dir;
		this.setVisible(false);

	}

	public static void main(String[] args) 
	{

		Setting set = new Setting(null,"���� ����");

		set.setPort(3000);
		set.setSaveDir("d:\\aa");

		set.setVisible(true);

		
		System.out.println(set.getPort());
		System.out.println(set.getSaveDir());
		
		set.dispose();
		return ;

	}

}
