package iedu.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.dao.BoardDao;
import iedu.data.BoardComment;
import iedu.data.BoardData;

/**
 * Servlet implementation class InsertComment
 */
@WebServlet("/InsertComment")
public class InsertComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
       BoardComment data = new BoardComment();
       data.Content  = request.getParameter("content");
       data.boardseq  = Integer.parseInt(request.getParameter("boardseq"));
	   data.Writerseq  =  (int)request.getSession().getAttribute("usernum");
	   
	   BoardDao dao = new BoardDao(); 
	   
	   dao.InsertCommnet(data);
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
