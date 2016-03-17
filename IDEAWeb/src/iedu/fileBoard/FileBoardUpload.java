package iedu.fileBoard;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.FileBoardDAO;
import iedu.data.FileBoardData;

public class FileBoardUpload extends FileBoardMain {
	
	public String service(HttpServletRequest req, HttpServletResponse resp){
		//	이제 이 모델에서는 파일을 업로드하고
		//	1.	MultipartRequest를 생성한다.
		//		생성방법
		//		new MultipartRequest(req, 
		//								"저장경로", 
		//								파일의 최대크기, 
		//								"엔코딩방식", 
		//								같은 이름이 존재할 경우 처리 방식)
		//	같은 이름이 존재할 경우 처리 방식
		//		유일하게	DefaultFileRenamePolicy만 존재한다.
		//		이 방식은 같은 이름의 파일이 존재하면 뒤에 일련번호를 붙이는 방식으로
		//		파일의이름을 변경하는 방식을 말한다.
		
		//	파일을 업로드하는 위치에 대한 문제
		//	1.	업로드된 파일을 오직 다운로드 용으로만 사용하는 경우
		//		==>	서버 시스템에 아무곳에나 저장하면 된다.
		//			이 경우에는 파일의 경로를 풀 경로로 지정하면 된다.
		//	2.	업로드된 파일을 HTML 문서에서 사용하는 경우
		//		(이미지를 업로드 한 후에 HTML 문서에서 그 이미지를 보여주는 경우)
		//		==>	반드시 WebContent에 있는 폴더에 저장해야 한다.
		//			실제 저장된 위치는 우리가보는 WebContent가 아닌
		//			서버가 사용하는 경로에 저장이 된다.
		//	이 경우에는 서버가 사용하는 경로를 알아와서 지정해야 한다.
		//	예>		WebConten/images		라는 폴더에 저장할 예정이면.....
		//			String	path = req.getSession().getServletContext().getRealPath("images");
		
		String	savePath = "D:\\FileUpload";
		int		max = 1024 * 1024 * 1024;
		String	enc = "EUC-KR";
		DefaultFileRenamePolicy	policy = new DefaultFileRenamePolicy();
		
		MultipartRequest	mr = null;
		try {
			mr = new MultipartRequest(req, savePath, max, enc, policy);
			//	라이브러리가 좋다는 것은 
			//	벌써 업로드가 끝났다.......................
			
			//	넘어온 모든 정보는 mr이 가지고 있다.
			//	예를 들어		title을 알고 싶으면
			//		String title = mr.getParameter("title");
		}
		catch(Exception e) {
			System.out.println("파일 업로드 에러" + e);
		}
		//	업로드된 결과를 데이터베이스에 기록하면 된다.
		//	기록하기 위해서는 당연히 다른 정보도 알아야 한다.
		String	title = mr.getParameter("title");
		String	body = mr.getParameter("body");
		String	oriName = mr.getOriginalFileName("upfile");
		String	realName = mr.getFilesystemName("upfile");
		//	우리는 파일의 크기도 기억하도록 해 놓았다.
		//	저장된 파일의 정보를 얻어서
		//	그 중 파일의 크기를 알아낸다.
		File		fileInfo = mr.getFile("upfile");
		long	len = fileInfo.length();
		
		HttpSession	session = req.getSession();
		String			writer = (String) session.getAttribute("ID");
		
		//	이 정보를 데이터베이스에 기록하도록 한다.
		//	우리는 약속 	이 정보를 데이터빈 클래스로 묶은 후
		//	dao 클래스에 전달해줌으로써 데이터베이스에 기록하는 것을 해결하고자 한다.
		
		FileBoardData data = new FileBoardData();
		data.body 		= body;
		data.len 			= len;
		data.oriName 		= oriName;
		data.path 			= savePath;
		data.saveName 	= realName;
		data.title 			= title;
		data.writer 		= writer;
		
		FileBoardDAO	dao = new FileBoardDAO();
		dao.insertBoard(data);
		dao.closeCON();
		
		return "../FileBoard/FileBoardUpload.jsp";
	}
}
