package Dao;

import java.io.Serializable;
import java.util.*;

public class ResponseAdmin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1657491947573215194L;
	
	public int message ;
	public boolean result;
	public String reason ="";
	public Vector<TB_admin> data;			
	public ResponseAdmin() {
		// TODO Auto-generated constructor stub
	}

}
