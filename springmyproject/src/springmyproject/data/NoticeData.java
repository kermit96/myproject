package springmyproject.data;



	import java.text.SimpleDateFormat;
	import java.util.Date;

//		이 클래스는 우리가 아는 데이터 빈 클래스를 만드는 방식으로 제작하면 된다.
	public class NoticeData {
		public		int			no;		//	게시물의 실제 번호
		public		int			rno;	//	나타나는 순서
		public		String		kind;
		public 	String		writer;
		public		String		title;
		public		String		body;
		public		Date		date;		//	작성일
		//	★★
		//	만약 보여줄때 시간까지 보여주고 싶다면
		//	시간을 기억할 변수도 따로 만들어 주어야 한다.
		//	왜냐하면
		//		날짜안에 있는 시간은 12시로 고정되어 있다.(쉽게 말해 시간을 알아내지 못한다.)0
		public		Date		time;		//	작성시간
		public String getKind() {
			return kind;
		}
		public void setKind(String kind) {
			this.kind = kind;
		}
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			this.writer = writer;
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
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
		public int getRno() {
			return rno;
		}
		public void setRno(int rno) {
			this.rno = rno;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		//	데이터빈에서 사용하는 데이터를 약간의 가공을 해서 알려줄 필요가 있으면
		//	getXXX()를 만들어서 원하는 형태로 가공해서 알려줄 수 있다.
		//	따로 구분된 날짜와 시간을 묶어서 알려줄 함수
		//	
		public String getDatetime() {
			SimpleDateFormat form1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat form2 = new SimpleDateFormat("hh:mm:ss");
			String	t1 = form1.format(date);
			String t2 = form2.format(time);
			
			return t1 + " " + t2;
		}
		//	긴급, 일반이 1, 2로 입력되는데 출력은 [긴급], [일반]의 형태로 출력해야 한다.
		//	이것을 알려줄 함수를 만들자
		public String getStrkind() {
			if(kind.equals("1")) {
				return "[긴급]";
			}
			else {
				return "[일반]";
			}
		}
		
//		본문을 출력할 때 줄 바꿈 기호를 처리할 함수
		public String getBodybr() {
			String	temp = body.replaceAll("\r\n", "<br>");
			return temp; 
		}

				
}

