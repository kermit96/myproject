package iedu.reboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iedu.data.ReBoardData;

public class WriteForm implements ReboardInterfeace {
	@Override
	public String Service(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		 HttpSession session  = request.getSession();
		 String id = (String)session.getAttribute("ID");
		
		 if (id == null || id.length()==0) {
			 
			 return ",,/member/login.do";
		 }
          
		 String tilte= request.getParameter("titie");
		 String content= request.getParameter("content");
		 
       if (tilte == null || tilte.length()==0) {
			 
			 return ",,/member/login.do";
		 }
       if (content == null || content.length()==0) {
			 
			 return ",,/member/login.do";
		 }
         
       ReBoardData data = new ReBoardData();
		 
		// 
 		return "../ReBoard/BoardWriterForm.jsp";
	}
	
}
