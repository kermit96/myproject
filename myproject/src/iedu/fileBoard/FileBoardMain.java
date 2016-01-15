package iedu.fileBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  이 클래스는 모든 모델 클래스의 상위 클래스 역활을 할 예정이다.
 * 
 */

public  abstract  class    FileBoardMain {
    //     
	//    
	
	  public abstract String service(HttpServletRequest req, HttpServletResponse res); 	  
	
}
