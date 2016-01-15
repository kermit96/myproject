package iedu.data;

public class LoginData {
	//	1.	넘어오는 데이터를 기억할 변수를 제작한다.
	String		id;
	String		password;
	int			age;		//	넘어오는 데이터의 형태에 맞게 받을 수 있다.
	String		phone1, phone2, phone3;
	//	같은 이름이 키값이 여러개 있으면 배열로 처리해서 받을 수도 있다.
	String[]	hobby;
	
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	//	서버가 사용할 수 있는 완전한 데이터로 주는 함수를 만들자.
	public String getHobbyStr() {
		String temp = "";
		for(int i = 0; i < hobby.length; i++) {
			temp += hobby[i] + " ";
		}
		return temp;
	}

	
	//	2.	get, set 함수를 제작한다.
	public String getId() {
		return id;
	}
	public void setId(String id) {
		//	만약에 필요하다면 맍이 부분에서 무결성 검사를 진행하도록 한다.
		this.id = id;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String pass) {
		this.password = pass;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		//	이처럼 중간 단계에서 무결성 검사를 해서 전달 할 수 있다.
		if(age < 0) {
			this.age = 0;
		}
		else {
			this.age = age;
		}
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	
	//	서버에서 사용할 수 있는 가장 완전한 데이터를 제공하도록 get 함수를 추가할 수 있다.
	public String getPhone() {
		//	따로 나뉘어진 전화번호를 묶어서 제공해 주겠다.
		return phone1 + "-" + phone2 + "-" + phone3;
	}
	//	이 작업은 예를 들어 날짜를 원하는 모양을 만들어서 제공할 수도있다.
	//		SimpleDataFormat 클래스를 이용해서......
}



