package iedu.data;

import java.util.Date;

/*
 * 	모델과 뷰사이에 필요한 데이터를 주고 받기 위한 클래스 역활을 할 예정이다.
 *	지금까지는 모델에서 생산된 데이터를 뷰에게 줄때 MAP으로 만들어서 제공했다.
 *	Map 대신에 이 클래스에 있는 변수에 필요한 데이터를 입력해 놓고
 *	이 클래스를 통채로 줌으로써 뷰에서 사용할 수 있도록 할 예정이다.
 */
public class ReBoardData {
	//	이 안에는	
	//		1.	뷰에게 전달할 데이터를 기억할 변수를 준비해야 한다.
	public		int		no;
	public		String	title;
	public		String	writer;
	public		String	body;
	public		int		group;
	public		int		step;
	public		int		order;
	public		Date	date;
	public		int		hit;
	public		int		ok;
	public		int		bed;

	//		2.	뷰에서는 이 데이터를 jstl 방식으로 사용할 예정이다.
	//			사용자 클래스에 있는 데이터를 사용하기 위해서는
	//			그 클래스에 getXxx()가 존재해야 한다.
	
	public int getOk() {
		return ok;
	}

	public void setOk(int ok) {
		this.ok = ok;
	}

	public int getBed() {
		return bed;
	}

	public void setBed(int bed) {
		this.bed = bed;
	}

	public int getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	//	JSTL에서는 어짜피 변수을 이름을 이용해서 출력하는 것이 아니고
	//	get 함수를 이용해서 출력을 하게 된다.
	//	즉 get 함수가 반환한 값을 출력하게 되는 것이다.
	//	이것을 역 이용하면 get 함수에서 데이터를 가공해서 알려주면
	//	출력도 가공된 결과가 출력될 것이다.
	public String getBrbody() {
		String temp = body.replaceAll("\r\n", "<br>");
		return temp;
	}
	//	이 기능이 존재하는 것이 Map을 이용하는 것 보다 훨씬 작업이 편하다는
	//	이점이 있다.
}









