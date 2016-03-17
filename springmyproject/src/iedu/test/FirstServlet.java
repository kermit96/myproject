package iedu.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/First.do")
public class FirstServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doGet(req, resp);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		// 2. 응답을 하기 위해서는 out 이라는  객체가 필요하다.
		PrintWriter out = resp.getWriter();

		out.println("한글 나라 Hello World");
	}
	
	public void ss()
	{
		JSONObject obj = new JSONObject();
    	obj.put("name", "mkyong.com");
    	obj.put("age", new Integer(100));
     
    	JSONArray list = new JSONArray();
    	list.add("msg 1");
    	list.add("msg 2");
    	list.add("msg 3");
     
    	obj.put("messages", list);
     
    	try {
     
    		FileWriter file = new FileWriter("c:\\test.json");
    		file.write(obj.toJSONString());
    		file.flush();
    		file.close();
     
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doPost(req, resp);
		
		resp.setContentType("text/html");
		 resp.setCharacterEncoding("utf-8");
		// 2. 응답을 하기 위해서는 out 이라는  객체가 필요하다.
		PrintWriter out = resp.getWriter();
		out.println("한글 나라 Hello World");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

}
