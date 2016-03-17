package springmyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//	이 클래스는 지정한 패키지 밑에 만들었으므로 모델의 역활을 할 수 있다.
//	이 클래스가 실제 모델의 역활을 하기 위해서는
//	이 클래스가 모델 클래스임을 밝혀주어야 한다.
//	방법		@Controller		 를 선언해 주면 된다.
@Controller
public class TestController {
	//	지금까지는 하나의 클래스가 하나의 함수만 이용할 수 있었다.
	//	하지만 스프링은 여러개의 함수를 모델 함수로 만들 수 있다.
	//	이말은
	//	하나의 클래스 안에 모델 역활을 할 함수를 여러개 만들 수 있다.

	//	문제
	//		어떤 모델이 실행되는지는 요청이 무엇인가에 따라 달라진다.
	//		즉, 스프링에서는 요청에 따라 실행할 함수가 선택되어야 한다. 
	//	결론
	//		이 함수가 어떤 요청이 왔을때 실행할 함수인지를 지정해야 한다.
	//		마치 우리가 프로퍼티스 파일에 요청이 있을때 실행할 모델 클래스를 
	//		등록해 놓은것과 같은 원리
	//	방법	@RequestMapping("요청내용")		
	@RequestMapping("/test")
	//			이것은 이 testModel이라는 함수는
	//			http://localhost:8080/SunWeb/test.sun이라는 요청이 오면
	//			실행될 함수임을 밝히는 것이다.
	public ModelAndView testModel() {
		System.out.println("나는 test이다.");
		//	이전에는 우리가 배운것처럼 그 모델이 해야할 일을 처리하고....
		
		ModelAndView		mv = new ModelAndView();
		mv.addObject("", "");		//	모델을 등록하는 함수
		mv.addObject("", "");
		//		우리로 말하면 req.setAttribute()에 해당하는 함수
		//	우리는 /WEB-INF/Test/TestResult.jsp라는 뷰를 선택하고자한다.
		//	그러면 뷰는 약속된 prefix, suffix를 제외한 나머지 부분만 등록해야 한다.
		mv.setViewName("Test/TestResult");		//	뷰를 등록하는 함수
		return mv;
	}
	
	@RequestMapping("/Board/View")
	//			http://localhost:8080/SunWeb/Board/View.sun
	public void sampleModel() {
		System.out.println("나는 sample이다2.");
		/*
		ModelAndView		mv = new ModelAndView();
		mv.addObject("", "");		//	모델을 등록하는 함수
		mv.addObject("", "");
		//		우리로 말하면 req.setAttribute()에 해당하는 함수
		//	우리는 /WEB-INF/Test/TestResult.jsp라는 뷰를 선택하고자한다.
		//	그러면 뷰는 약속된 prefix, suffix를 제외한 나머지 부분만 등록해야 한다.
		mv.setViewName("Board/View");		//	뷰를 등록하는 함수
		*/
		//return mv;
	}
	//	뿐만 아니라 일반 함수도 만들어서 사용할 수 있다.

}







