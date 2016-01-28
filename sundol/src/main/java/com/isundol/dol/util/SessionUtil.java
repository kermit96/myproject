package com.isundol.dol.util;

import javax.servlet.http.HttpSession;

/*
 * 	세션에 관련된 유틸리티 클래스
 */
public class SessionUtil {
	/*
	 * 	세션이 있는지 여부를 판단해주는 함수
	 */
	public static boolean isSession(HttpSession session) {
		if(StringUtil.isNull((String)session.getAttribute("ID"))) {
			return false;
		}
		else {
			return true;
		}
	}
}
