package Dao;

import java.io.*;
import java.util.*;
public class ResponseRegister implements Serializable{
	
	public short message;
	
	public boolean result;
	public String reason;
	
	public Vector<TB_REGISTER> data;

	public ResponseRegister() {
		// TODO Auto-generated constructor stub
	}

}
