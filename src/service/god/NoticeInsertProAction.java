package service.god;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.god.Notice;
import dao.god.NoticeDao;
import service.CommandProcess;

public class NoticeInsertProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			int maxSize=10*1024*1024;
			String rootPath=request.getServletContext().getRealPath("/");
			String savePath=rootPath+"noticefile/";
			MultipartRequest multi=new MultipartRequest(request,savePath,maxSize,"utf-8", new DefaultFileRenamePolicy());
			Enumeration en=multi.getFileNames();
			List<String> filenames=new ArrayList<String>();
			while(en.hasMoreElements()){
				String filename1=(String)en.nextElement(); //파라미터명 ex) nfile
				String filename=multi.getFilesystemName(filename1); //파일명
				String original=multi.getOriginalFileName(filename1); //전송 전 원래 이름 
				String type=multi.getContentType(filename1); //전송 전 파일의 내용 타입 ex) image/png
				File file=multi.getFile(filename1);  //전송된 파일 속성이 file인 태그의 name 속성값을 이용해 객체 생성
				filenames.add(filename);
			}
			
			Notice notice=new Notice();
			notice.setNtitle(multi.getParameter("ntitle"));
			notice.setNcontent(multi.getParameter("ncontent"));
			if(filenames.get(0)==null)
				notice.setNfile(null);
			else
				notice.setNfile("./noticefile/"+filenames.get(0));
				
			notice.setNpublic(Integer.parseInt(multi.getParameter("npublic")));
			
			NoticeDao nd=NoticeDao.getInstance();
			int result=nd.insert(notice);
			
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println("NoticeInsertProAction -> "+e.getMessage());
		}
		return "noticeInsertPro.jsp";
	}

}
