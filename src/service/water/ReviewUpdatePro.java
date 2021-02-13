package service.water;

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

import dao.water.Review;
import dao.water.ReviewDao;
import service.CommandProcess;


public class ReviewUpdatePro implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("리뷰업데이트 start...");
			request.setCharacterEncoding("utf-8");
			
			 int maxSize=10*1024*1024;
			   String rootPath=request.getServletContext().getRealPath("/");
			   String savePath=rootPath+"reviewfile/";
			   MultipartRequest multi=new MultipartRequest(request,savePath,maxSize,"utf-8", new DefaultFileRenamePolicy());
			   Enumeration en=multi.getFileNames();
			   List<String> filenames=new ArrayList<String>();
			   while(en.hasMoreElements()){
			   String filename1=(String)en.nextElement(); //파라미터명 ex) pthumbimg->col5->col4
			   System.out.println("filename1 ->"+filename1);
			   String filename=multi.getFilesystemName(filename1); //이미지명 ex)img1.png, img2.png
			   
			   
			   System.out.println("filename -> "+filename);
			   String original=multi.getOriginalFileName(filename1); //전송 전 원래 이름 ex) img1.png
			   String type=multi.getContentType(filename1); //전송 전 파일의 내용 타입 ex) image/png
			   System.out.println("type -> "+type);
			   File file=multi.getFile(filename1);  //전송된 파일 속성이 file인 태그의 name 속성값을 이용해 객체 생성
			   filenames.add(filename);
			   
			   
			   System.out.println("savePath->"+savePath);
			   System.out.println("filenames -> "+filename);
		}
			   
			   
			   
			   
			 String pageNum = request.getParameter("pageNum");
			 Review review = new Review();
			 
			 
			 System.out.println("리뷰수정버튼 Rid = "+multi.getParameter("rid"));
			 System.out.println("리뷰수정버튼 sid = "+multi.getParameter("sid"));
			 review.setRid(Integer.parseInt(multi.getParameter("rid")));
			 review.setSid(Integer.parseInt(multi.getParameter("sid")));
			 review.setRtitle(multi.getParameter("rtitle"));
			 review.setRcontent(multi.getParameter("rcontent"));
			 review.setRimg("./reviewfile/"+filenames.get(0));
			 
			 ReviewDao rd = ReviewDao.getInstance();
			 int result = rd.update(review);
			 
			 
			 System.out.println("리뷰수정 result = "+result);
			 request.setAttribute("review", review);
			 request.setAttribute("result", result);
			 request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println("리뷰업데이트 오류 pro :"+e.getMessage() );
		}
		return "reviewUpdatePro.jsp";
	}

}
