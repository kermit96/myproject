package Dao.board;

import java.io.*;
import java.util.*;

import Dao.TB_USER;


public class ResponseBoard implements Serializable{
	public short message;  // �޽��� ���� ,
	
	public boolean result;  // ��û ����, ���� 
	public String reason;
	
	public Vector<TB_BOARD> data;  // list �� ���� �´�.
			
	public ResponseBoard() {
	// TODO Auto-generated constructor stub
	}

	public Object getObject(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
