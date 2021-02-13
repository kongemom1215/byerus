package service.god;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Notice;
import dao.god.NoticeDao;
import service.CommandProcess;

public class NoticeAdminAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals(""))
				pageNum="1";
			int currentPage = Integer.parseInt(pageNum);
			int pageSize=10; //한페이지당 보일 게시글 수
			int blockSize=10; //페이지수
			int startRow=(currentPage-1)*pageSize+1; 
			int endRow=startRow+pageSize-1; 
			
			NoticeDao nd=NoticeDao.getInstance();
			int totalNotice=nd.totalNotice();
			int startNum=totalNotice-startRow+1; //시작 게시글번호
			List<Notice> noticelist=nd.list(startRow, endRow);
			
			int pageCnt=(int)Math.ceil((double)totalNotice/pageSize);
			int startPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=startPage+blockSize-1;
			if(endPage>pageCnt)
				endPage=pageCnt;
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("startNum", startNum);
			request.setAttribute("totalNotice", totalNotice);
			request.setAttribute("noticelist", noticelist);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		} catch (Exception e) {
			System.out.println("NoticeAdminAction -> "+e.getMessage());
		}
		return "noticeAdminPage.jsp";
	}

}
