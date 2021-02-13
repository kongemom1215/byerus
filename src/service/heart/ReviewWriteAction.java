package service.heart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.heart.Board;
import dao.heart.BoardDao;
import dao.heart.Order_Join;
import service.CommandProcess;

public class ReviewWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(true);
			
			int sid = (int) session.getAttribute("session_sid");
			int oid = Integer.parseInt(request.getParameter("oid"));
			int pid = Integer.parseInt(request.getParameter("pid"));

			int maxSize=10*1024*1024; // 10MB
			String rootPath=request.getServletContext().getRealPath("/");
			String savePath=rootPath+"reviewfile/";
			MultipartRequest multi=new MultipartRequest(request,savePath,maxSize,"utf-8", new DefaultFileRenamePolicy());
			// 파일 업로드 완료
			Enumeration en=multi.getFileNames();
			// 업로드된 파일 이름을 반환
			List<String> filenames=new ArrayList<String>();
			while(en.hasMoreElements()){
				String filename1=(String)en.nextElement(); //파라미터명 ex) pthumbimg->col5->col4
				String filename=multi.getFilesystemName(filename1); //이미지명 ex)img1.png, img2.png
				String original=multi.getOriginalFileName(filename1); //전송 전 원래 이름 ex) img1.png
				String type=multi.getContentType(filename1); //전송 전 파일의 내용 타입 ex) image/png
				File file=multi.getFile(filename1);  //전송된 파일 속성이 file인 태그의 name 속성값을 이용해 객체 생성
				filenames.add(filename);
			}
			
			Board board = new Board();
			board.setRtitle(multi.getParameter("rtitle"));
			board.setRcontent(multi.getParameter("rcontent"));
			board.setRimg("./reviewfile/"+filenames.get(0));
			
			BoardDao bd = BoardDao.getInstance();
			int result =bd.review(board,sid,oid,pid); 
			
			
			
			
			request.setAttribute("sid", sid);
			request.setAttribute("oid", oid);	
			request.setAttribute("pid", pid);
			System.out.println("ReviewWriteAction sid->"+sid);
			System.out.println("ReviewWriteAction oid->"+oid);
			request.setAttribute("board", board);			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("ReviewWriteAction Error->"+e.getMessage());
		}
		return "reviewWriteAction.jsp";
	}

}
