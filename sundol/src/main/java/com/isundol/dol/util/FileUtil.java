package com.isundol.dol.util;

import java.io.File;

public class FileUtil {
	/*
	 * 	이름이 중복된 경우 파일의 이름을 바꿔주는 함수
	 */
	public static String rename(String path, String name) {
		/*
		 * 	나는	이름이 같으면 원래 이름 뒤에 "_숫자"	를 붙여서 이름을 변경하도록 할 예정이다. 
		 */
		String	tempName = name;
		File		file = new File(path, tempName);
		int		count = 0;			//	뒤에 붙일 숫자를 제어할 변수
		while(file.exists()) {
			count = count + 1;	
			//	파일이름의 실제 이름과 확장자를 분리한다.
			//	왜?		test.txt,	test_1.txt, 	test_2.txt	도 존재한다.
			//			name			test.txt
			//			tempName		test_1.txt
			int	index = name.lastIndexOf(".");
			
			String	rName = name.substring(0, index);
			String	ext = name.substring(index + 1);
			rName = rName + "_" + count;		
			tempName = rName + "." + ext;		
			file = new File(path, tempName);
		}
		
		return tempName;
	}
}




