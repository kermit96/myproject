package com.isundol.dol.data;

import java.text.SimpleDateFormat;
import 	java.util.Date;

import com.isundol.dol.util.StringUtil;

public class RBoardData {
	public	String	id;
	public String 	title;
	public String 	body;
	public String 	pw;
	public int		no;
	public int		hit;
	//	일반 DB 작업에서는 날짜와 시간을 따로 받아서 처리해야 하는데
	//	myBatis는 내부적으로 날짜와 시간을 다 받아서 묶어서 처리해 준다.
	//	그러므로 myBatis를 사용할 때는 굳이 날짜와 시간을 따로 받을 필요가 없다.
	//	대신에 myBatis는 날짜와 시간을 풀네임으로 출력하므로 문제가 생길 수 있다.
	public Date	wdate;
	public Date	mdate;
	public String	nick;
	public	int		good;
	public int		nowPage;
	public int		rno;
	//	변수의 이름은 일부러 폼의 name 속성과 같게 했다.
	//	그래야 set 함수가 자동으로 만들어진다.

	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	//	날짜와 시간을 원하는 형태로 변경해서 출력할 수 있는 getXXX함수를
	//	제작해 주어야 한다.
	//	이 이론은 출력을 다른 방식으로 하고자 하면 getXXX함수를 만들어서
	//	사용한다...	라는 이론에 따른것이다.
	public String getDatetime() {
		return StringUtil.getDateTimeStr(wdate);
	}
	public String getDate() {
		return StringUtil.getDateStr(wdate);
	}
	public String getBrbody() {
		return StringUtil.setBr(body);
	}
}




