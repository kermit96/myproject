package dao;

public class FileBoardSQL {
	//	final 변수는 대문자로 구성한다.
	public static final int 	INSERTBOARD = 1;
	public static final int	GETTOTAL = 2;
	public static final int	GETBOARDLIST = 3;
	public	static final int	GETSEARCHTOTAL = 4;
	public static final int	GETBOARDSEARCHLIST = 5;
	
	public static String getSQL(int code) {
		StringBuffer	buff = new StringBuffer();
		switch(code) {
		case	INSERTBOARD:
			buff.append("INSERT ");
			buff.append("INTO ");
			buff.append("		FileBoard ");
			buff.append("VALUES ");
			buff.append("		((SELECT NVL(MAX(fb_NO), 0) + 1 FROM FileBoard), ?, ?, ?, ?, ?, ?, ?) ");
			break;
		case	GETTOTAL:
			buff.append("SELECT ");
			buff.append("		COUNT(*) AS TOTAL ");
			buff.append("FROM ");
			buff.append("		FileBoard ");
			break;
		case	GETBOARDLIST:
			buff.append("SELECT ");
			buff.append("		fb_NO AS NO, ");
			buff.append("		fb_Title AS TITLE, ");
			buff.append("		fb_Writer AS WRITER, ");
			buff.append("		fb_FileLength AS LEN ");
			buff.append("FROM ");
			buff.append("		FileBoard ");
			buff.append("ORDER BY ");
			buff.append("		fb_NO DESC ");
			break;
			//	이 질의 명령은 다이나믹 질의 방식으로 처리하고자 한다.
			//	다이나믹 질의란?
			//	질의의 내용을 변화시켜서 사용하는 방법
			//	변화되는 부분을 특수문자로 지정한 후
			//	사용 시점에 가서 특수 문자 부분을 원하는 질의로 변환시켜서 사용한다.
		case	GETSEARCHTOTAL:
			buff.append("SELECT ");
			buff.append("		COUNT(*) AS TOTAL ");
			buff.append("FROM ");
			buff.append("		FileBoard ");
			buff.append("WHERE ");
			buff.append("		%%% ");
			break;
		case	GETBOARDSEARCHLIST:
			buff.append("SELECT ");
			buff.append("		fb_NO AS NO, ");
			buff.append("		fb_Title AS TITLE, ");
			buff.append("		fb_Writer AS WRITER, ");
			buff.append("		fb_FileLength AS LEN ");
			buff.append("FROM ");
			buff.append("		FileBoard ");
			buff.append("WHERE ");
			buff.append("		%%% ");
			buff.append("ORDER BY ");
			buff.append("		fb_NO DESC ");
			break;
		}
		return buff.toString();
	}
}




