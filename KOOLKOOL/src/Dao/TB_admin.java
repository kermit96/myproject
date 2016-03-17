package Dao;

import java.io.Serializable;

public class TB_admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 711658294709592638L;
	public String adminid ="";
	public String name ="";
	public String password ="";
	public TB_admin() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
