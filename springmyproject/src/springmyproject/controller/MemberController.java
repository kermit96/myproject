package springmyproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import springmyproject.dao.MemberDAO;
import springmyproject.dao.MemberMDAO;
import springmyproject.dao.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberMDAO memberImpl;
				
	/*
		 * 	회원 가입 폼 요청 모델 함수
		 */
		@RequestMapping("/Member/joinForm")
		public ModelAndView joinForm() {
			//	할일 없고
			
			ModelAndView		mv = new ModelAndView();
			mv.setViewName("Member/JoinForm");
			return mv;
		}
		
		

		/*
		 * 	우편번호 검색 폼 요청 함수
		 */
		@RequestMapping("/Member/ZipSearchForm")
		public ModelAndView zipSeaechForm() {
			ModelAndView		mv = new ModelAndView();
			//	할일
			//		데이터베이스에서 시도를 구해서 뷰에게 알려준다.
			MemberDAO	dao = new MemberDAO();
			System.out.println("start do==>");
			ArrayList	list = dao.getSIDO();
			dao.close();
			System.out.println("do==>"+list.size());
			mv.addObject("SIDO", list);
//			mv.setViewName("Member/ZipSearchForm");
			mv.setViewName("Member/ZipSearchForm");
			return mv;
		}
		
		/*
		 * 	구군 검색 요청 처리 함수
		 */
		@RequestMapping(value ="/Member/ZipSearchGugun")
		public ResponseEntity<String>    zipSeaechGugun(HttpServletRequest req,HttpServletResponse resp) {

			//	할일
			//		넘어온 시도를 알아낸다.
			String	sido = req.getParameter("sido");
			//		데이터베이스에서 그 시도의 구군을 검색한다.
			MemberDAO	dao = new MemberDAO();
			ArrayList		list = dao.getGugun(sido);
			dao.close();

			//		뷰에게 알려준다.
//			mv.addObject("GUGUN", list);
//			mv.setViewName("Member/ZipSearchGugun");
			
	//		System.out.println(sido);
						
			JSONArray array = new JSONArray();			
			
			
			for(Object tempobj:list) {
		      array.add(tempobj.toString() );			
			}
			
			JSONObject obj = new JSONObject();
			
			obj.put("gugun", array);
						
			// return mv;
			
//			resp.setContentType("text/html");
//			resp.setCharacterEncoding("utf-8");
			
//			System.out.println(obj.toJSONString());
	//		return obj.toJSONString();
			
			HttpHeaders responseHeaders = new HttpHeaders(); 
		    responseHeaders.add("Content-Type", "application/json; charset=utf-8"); 
		    return new ResponseEntity<String>(obj.toJSONString(), responseHeaders, HttpStatus.CREATED);

		}
		
		/*
		 * 	읍면동 검색 요청
		 */
		@RequestMapping("/Member/ZipSearchDong")
		public ModelAndView zipSeaechDong(HttpServletRequest req) {
			ModelAndView		mv = new ModelAndView();
			String	sido = req.getParameter("sido");
			String	gugun = req.getParameter("gugun");
			
			MemberDAO	dao = new MemberDAO();
			ArrayList	list = dao.getDong(sido, gugun);
			dao.close();
			
			mv.addObject("DONG", list);
			mv.setViewName("Member/ZipSearchDong");
			return mv;
		}
		
		/*
		 * 	거리 정보
		 */
		@RequestMapping("/Member/ZipSearchRoad")
		public ModelAndView zipSeaechRoad(HttpServletRequest req) {
			ModelAndView		mv = new ModelAndView();
			String	sido = req.getParameter("sido");
			String	gugun = req.getParameter("gugun");
			String	dong = req.getParameter("dong");
			
			MemberDAO	dao = new MemberDAO();
			ArrayList	list = dao.getRoad(sido, gugun,dong);
			dao.close();
			
			mv.addObject("ROAD", list);
			mv.setViewName("Member/ZipSearchRoad");
			return mv;
		}
	
		/*
		 * 	우편번호 검색 요청
		 */
		@RequestMapping("/Member/ZipSearch")
		//		도메인을 제외한 요청 부분을 쓴다.
		//		요청 내용	http://localhost:8080/SunWeb/Member/ZipSearch.sun
		//		도메인		http://localhost:8080/SunWeb    
		public void zipSeaech(HttpServletRequest req,HttpServletResponse resp) {
			ModelAndView		mv = new ModelAndView();
			//	할일
			//		넘어온 파라메터 받는다.
			String sido = req.getParameter("sido");
			String	gugun = req.getParameter("gugun");
			String	dong = req.getParameter("dong");
			String	road = req.getParameter("road");
			//	이제 이 데이터를 이용해서 우편번호를 검색한다.
			//		우편번호		서울시 종로구 신영동 세검정로6나길 빌딩이름
			MemberDAO	dao = new MemberDAO();
			ArrayList		list = dao.getZipSearch(sido, gugun, dong, road);
			dao.close();

			
			Document doc = new Document();  
			Element codes= new Element("codes");
			
			
			JsonArray jsonarray = new JsonArray();
			
		    for(Object temp :list) {
		      //
		    	
		        JsonObject obj = new JsonObject();
		    	
		    	Element code = new Element("code");
		    	HashMap map = (HashMap)temp;
	
     	    	  Element zip = new Element("zip");
     	    	  Element addr = new Element("addr");
                
     	    	 zip.setText((String)map.get("ZIPCODE")) ;
     	    	
     	    	 String addrstr = map.get("SIDO")+" "+ map.get("GUGUN")+" "+ map.get("DONG")+" "+
     	    	 map.get("ROAD")+" "+ map.get("BLG")+" "+ map.get("JIBUN");
     	    	 
     	    	 addr.setText(addrstr) ;
     	    	      	    	     	    	  
     	    	 code.addContent(zip);     	    	  
     	    	 code.addContent(addr);     	    	  
    	    
     	    	 
     	    	 
     	    	 codes.addContent(code);
     	    	 
     	    	 obj.addProperty("zip", map.get("ZIPCODE").toString());
     	    	 obj.addProperty("addr", addrstr);
//				<zip>${temp.ZIPCODE}</zip>
//				<addr>${temp.SIDO} ${temp.GUGUN} ${temp.DONG} ${temp.ROAD} ${temp.BLG} ${temp.JIBUN}</addr>
     	    	jsonarray.add(obj);
		    }
			
		    

			
			doc.addContent(codes);
			
			//doc.get
			
			
			resp.reset();
			resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");            
                        
            
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding("UTF-8")) ;

            String str = outputter.outputString(doc);
            
            System.out.println(str);
                      
            Gson gson = new Gson();
            
            String jsonstr = gson.toJson(list);
            
            try {
				// resp.getWriter().print(str);
				resp.getWriter().print(jsonarray.toString());
				
		          resp.getWriter().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


     //       System.out.println(jsonarray.toString());            
          

            
            			
	//		mv.addObject("ZIP", list);
			//	prefix에서 지정한 부분을 제외한다.
			//		원래 뷰 이름		/WEB-INF/Member/ZipSearch.jsp
			//		prefix				/WEB-INF/
	//		mv.setViewName("Member/ZipSearch");
//			return mv;
			
		}


		   
		@RequestMapping("/Member2/*") 
		public void getCount(HttpServletResponse resp  ) {
			int	count = memberImpl.getCount();
			System.out.println(count);
			
	//		resp.reset();
			resp.setContentType("text/html");
           resp.setCharacterEncoding("utf-8");            
        	try {
				resp.getWriter().print("현재 count="+count);
				resp.getWriter().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	          
		}
		
		
}
