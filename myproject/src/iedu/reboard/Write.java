package iedu.reboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Write implements ReboardInterfeace {
@Override
public String Service(HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	
	 HttpSession session  = request.getSession();
	 String id = (String)session.getAttribute("ID");
	
	 if (id == null || id.length()==0) {
		 
		 return ",,/member/login.do";
	 }
	 
	// 
	return "../ReBoard/BoardWriterForm.jsp";
}
}
