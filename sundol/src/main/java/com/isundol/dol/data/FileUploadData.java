package com.isundol.dol.data;

import 	org.springframework.web.multipart.MultipartFile;

public class FileUploadData {
	public	String			writer;
	public	String			title;
	public	String			body;
	//	파일이 넘어오는 부분은 MultipartFile	이라는 클래스로 받는다.
	//	이 안에는 파일의 이름과 실제 내용이 다 기억되어 진다.
	public	MultipartFile	upfile;
	//	같은 name이 여러개 존재하는 경우에는 배열로 받으면 된다.
	public MultipartFile[]	files;
	public	String			oriname;
	public	String			savename;
	public	String			path;
	public	long			len;
	public	int				no;
	public	int				dhit;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getDhit() {
		return dhit;
	}
	public void setDhit(int dhit) {
		this.dhit = dhit;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public MultipartFile getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile upfile) {
		//	이 함수가 실행되는 순간
		//	서버의 임시 메모리에 파일이 저장된다.
		this.upfile = upfile;
	}
}



