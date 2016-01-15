package iedu.fileBoard;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DefaultFileItemFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileBoardUpload extends FileBoardMain  {
@Override
 public String service(HttpServletRequest req, HttpServletResponse res) {
	// TODO Auto-generated method stub
	
	String savePath = "D:\\mystype\\upload";
	int max =  1024*1024*1024*10;
	String enc="UTF-8";
	
	DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
	
	MultipartRequest mr = null;
	
	try {
		mr = new MultipartRequest(req,savePath,max,enc,policy);
	
	} catch (Exception ex)
	{
		ex.printStackTrace();
		
	}
	// 
	
	
	HttpSession  sesstion  = req.getSession();
	
	String id = (String)sesstion.getAttribute("id");
	
	String title = mr.getParameter("title");
	String body = mr.getParameter("body");
	
    String orinalfile = mr.getOriginalFileName("upfile");
  
    String realName = mr.getFilesystemName("upfile");
    File fileinfo = mr.getFile("upfile");
    
	
	return "/FileBoard/FileBoardList.jsp";
 }
}
