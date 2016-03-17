package Dao.board;

import java.io.*;
import java.util.*;

import Dao.TB_USER;


public class ResponseBoard implements Serializable{
	public short message;  // 메시지 종류 ,
	
	public boolean result;  // 요청 성공, 실패 
	public String reason;
	
	public Vector<TB_BOARD> data;  // list 를 가져 온다.
			
	public ResponseBoard() {
	// TODO Auto-generated constructor stub
	}

	public Object getObject(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
