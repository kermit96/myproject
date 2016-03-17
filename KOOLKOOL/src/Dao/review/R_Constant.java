package Dao.review;

//Review 보기에서 서버에 요청시 기본 약속
public class R_Constant {		
	
	//TB_REVIEW의 기본값(house_idx,title,user_name,star) 읽기
	public static final int DEF_READ_BASIC_LIST =1;
	//TB_REVIEW 에서 content 읽기
	public static final int DEF_READ_CONTENT_LIST =2;	
	//TB_HOUSE 에서 name 읽기
	public static final int DEF_READ_HOUSENAME_LIST =3;	
	//TB_REVIEW 에서 신규 리뷰 값 등록
	public static final int DEF_INSERT_REVIEW = 4;
	//TB_REVIEW 에서 title,content,star 값 업데이트
	public static final int DEF_UPDATE_REVIEW= 5;
	//TB_REVIEW 에서 idx 값에 대항하는 row 값 제거
	public static final int DEF_DELETE_REVIEW = 6;
	
	
}
