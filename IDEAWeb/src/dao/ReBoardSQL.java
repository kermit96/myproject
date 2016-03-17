package dao;

import javax.swing.JLabel;

/*
 * 	질의 명령을 제공할 클래스
 */
public class ReBoardSQL {

	//	이 클래스를 이용해서 필요한 질의 명령을 꺼내는 방법
	//		ReBoardSQL.getSQL(1);
	//		ReBoardSQL.getSQL(2);		이런식으로 사용할 예정이다.
	//	이렇게 사용하면 가독성에 있어서 문제가 생긴다.
	//	즉	1번 코드가 무엇인지는 모르고 있을 수 있다.
	//	결론	유지보수를 위해서	코드값을 문자값으로 처리하자.
	public static final int	GETMAXNO 			= 1;
	public static final int 	INSERTBOARD 		= 2;
	public static final int	GETTOTALCOUNT 	= 3;
	public static final int 	GETBOARDLIST		= 4;
	public static final int	GETBOARDVIEW		= 5;
	public static final int	UPDATEHIT			= 6;
	public static final int	UPDATEORDER		= 7;
	public static final int	UPDATEGOOD		= 8;
	
	public static final int	UPDATEBAD		= 9;
	
	public static final int	DELETEBOARD		= 10;
	
	//	사용할 때
	//		ReBoardSQL.getSQL(ReBoardSQL.GETMAXNO);
	//		ReBoardSQL.getSQL(ReBoardSQL.INSERTBOARD);		이런식으로 사용할 예정이다.
	
	//	참고	static 함수로 만든 이유
	//			new 시키기 싫어서
	//			즉		ReBoardSQL.getSQL(1);
	public static String getSQL(int code) {
		StringBuffer	buff = new StringBuffer();
		switch(code) {
			//	일련번호를 알아내는 질의어
			case	GETMAXNO:
				buff.append("SELECT ");
				buff.append("		NVL(MAX(rb_NO), 0) + 1 AS MAXNO ");
				buff.append("FROM ");
				buff.append("		RepleBoard");
				break;
			//	INSERT 시키는 질의어
			case	INSERTBOARD:
				buff.append("INSERT ");
				buff.append("INTO ");
				buff.append("		RepleBoard ");
				buff.append("VALUES ");
				buff.append("		(?, ?, ?, ?, SYSDATE, 0, 0, 0, ?, ?, ?) ");
				break;
			case GETTOTALCOUNT:
				buff.append("SELECT ");
				buff.append("		COUNT(*) AS TOTALC ");
				buff.append("FROM ");
				buff.append("		RepleBoard ");
				break;
			case	GETBOARDLIST:
				buff.append("SELECT	 ");
				buff.append("		rb_NO 		AS NO,  ");
				buff.append("		rb_Title 	AS TITLE, ");
				buff.append("		rb_Writer AS WRITER, ");
				buff.append("		rb_Date 	AS WDATE, ");
				buff.append("		rb_Hit 		AS HIT, ");
				buff.append("		rb_Group 	AS BGROUP, ");
				buff.append("		rb_Step 	AS BSTEP,  ");
				buff.append("		rb_Order	AS BORDER ");
				buff.append("FROM ");
				buff.append("		RepleBoard ");
				buff.append("ORDER BY ");
				//	답변형게시판의 순서를 지키기 위한 출력 순서
				buff.append("		rb_GROUP DESC, rb_ORDER ASC ");
				break;
			case	GETBOARDVIEW:
				buff.append("SELECT	 ");
				buff.append("		rb_NO 		AS NO,  ");
				buff.append("		rb_Title 	AS TITLE, ");
				buff.append("		rb_Writer	AS WRITER, ");
				buff.append("		rb_Body	AS BODY, ");
				buff.append("		rb_Date 	AS WDATE, ");
				buff.append("		rb_Hit 		AS HIT, ");
				buff.append("		rb_OK		AS OK, ");
				buff.append("		rb_Bed		AS BED, ");
				buff.append("		rb_Group 	AS BGROUP, ");
				buff.append("		rb_Step 	AS BSTEP,  ");
				buff.append("		rb_Order	AS BORDER ");
				buff.append("FROM ");
				buff.append("		RepleBoard ");
				buff.append("WHERE ");
				buff.append("		rb_NO = ?");
				break;
			case	UPDATEHIT:
				buff.append("UPDATE ");
				buff.append("		RepleBoard ");
				buff.append("SET ");
				buff.append("		rb_Hit = rb_Hit + 1 ");
				buff.append("WHERE ");
				buff.append("		rb_NO = ? ");
				break;
			case	UPDATEORDER:
				buff.append("UPDATE ");
				buff.append("		RepleBoard ");
				buff.append("SET ");
				buff.append("		rb_Order = rb_Order + 1 ");
				buff.append("WHERE	");
				buff.append("				rb_Group = ? ");
				buff.append("		AND	rb_Order >= ? ");
				break;
			case	UPDATEGOOD:
				buff.append("UPDATE ");
				buff.append("		RepleBoard ");
				buff.append("SET ");
				buff.append("		rb_OK = rb_OK + 1 ");
				buff.append("WHERE ");
				buff.append("		rb_NO = ? ");
				break;

			case 	UPDATEBAD:
				buff.append("UPDATE ");
				buff.append("		RepleBoard ");
				buff.append("SET ");
				buff.append("		rb_Bed = rb_Bed + 1 ");
				buff.append("WHERE ");
				buff.append("		rb_NO = ? ");
				break;

			case 	DELETEBOARD:
				buff.append("delete ");
				buff.append("	from 	RepleBoard ");
				buff.append("WHERE ");
				buff.append("		rb_NO = ? and rb_Writer=?  ");
				break;
				
		}
		return buff.toString();
	}
}



