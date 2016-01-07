package iedu.data;

import java.util.Date;

/*
 * 	�𵨰� ����̿� �ʿ��� �����͸� �ְ� �ޱ� ���� Ŭ���� ��Ȱ�� �� �����̴�.
 *	���ݱ����� �𵨿��� ����� �����͸� �信�� �ٶ� MAP���� ���� �����ߴ�.
 *	Map ��ſ� �� Ŭ������ �ִ� ������ �ʿ��� �����͸� �Է��� ����
 *	�� Ŭ������ ��ä�� �����ν� �信�� ����� �� �ֵ��� �� �����̴�.
 */
public class ReBoardData {
	//	�� �ȿ���	
	//		1.	�信�� ������ �����͸� ����� ������ �غ��ؾ� �Ѵ�.
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

	//		2.	�信���� �� �����͸� jstl ������� ����� �����̴�.
	//			����� Ŭ������ �ִ� �����͸� ����ϱ� ���ؼ���
	//			�� Ŭ������ getXxx()�� �����ؾ� �Ѵ�.
	
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
	
	//	JSTL������ ��¥�� ������ �̸��� �̿��ؼ� ����ϴ� ���� �ƴϰ�
	//	get �Լ��� �̿��ؼ� ����� �ϰ� �ȴ�.
	//	�� get �Լ��� ��ȯ�� ���� ����ϰ� �Ǵ� ���̴�.
	//	�̰��� �� �̿��ϸ� get �Լ����� �����͸� �����ؼ� �˷��ָ�
	//	��µ� ������ ����� ��µ� ���̴�.
	public String getBrbody() {
		String temp = body.replaceAll("\r\n", "<br>");
		return temp;
	}
	//	�� ����� �����ϴ� ���� Map�� �̿��ϴ� �� ���� �ξ� �۾��� ���ϴٴ�
	//	������ �ִ�.
}









