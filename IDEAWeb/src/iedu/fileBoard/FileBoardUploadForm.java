package iedu.fileBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileBoardUploadForm extends FileBoardMain {
	public String service(HttpServletRequest req, HttpServletResponse resp){
		//	할일은 없고
		//	있다면 권한 검사정도?.....
		
		
		return "/FileBoard/FileBoardUploadForm.jsp";
	}
}
