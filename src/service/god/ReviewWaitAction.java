package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.god.Review;
import dao.god.ReviewDao;
import service.CommandProcess;

public class ReviewWaitAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session=request.getSession();
			String rctg=request.getParameter("rctg");
			session.setAttribute("rctg", rctg);
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals(""))
				pageNum="1";
			int currentPage = Integer.parseInt(pageNum);
			int pageSize=10; //한페이지당 보일 게시글 수
			int blockSize=10; //페이지수
			int startRow=(currentPage-1)*pageSize+1; 
			int endRow=startRow+pageSize-1; 
			
			ReviewDao rd=ReviewDao.getInstance();
			int totalWait=rd.totalWait();
			int startNum=totalWait-startRow+1; //시작 게시글번호
			List<Review> waitlist=rd.waitlist(startRow, endRow);
			
			int pageCnt=(int)Math.ceil((double)totalWait/pageSize);
			int startPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=startPage+blockSize-1;
			if(endPage>pageCnt)
				endPage=pageCnt;
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("startNum", startNum);
			request.setAttribute("totalWait", totalWait);
			request.setAttribute("waitlist", waitlist);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		} catch (Exception e) {
			System.out.println("ReviewWaitAction -> "+e.getMessage());
		}
		return "reviewWait.jsp";
	}

}
