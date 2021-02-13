package service.water;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.Review;
import dao.water.ReviewDao;
import service.CommandProcess;

public class ReviewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewDao rd = ReviewDao.getInstance();
		try {
			String pageNum = request.getParameter("pageNum");	
			if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
			int currentPage = Integer.parseInt(pageNum);	
			int pageSize  = 10, blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow =  startRow + pageSize - 1;
			int totCnt = rd.getTotalCnt();
			int startNum = totCnt - startRow + 1;   // 38
			List<Review> list = rd.list(startRow , endRow); 
			System.out.println("ListAction list.size()-->" + list.size());        // /och16/list.do
			
			 System.out.println("ListAction list.get(0).getContent()-->" + list.get(0).getRcontent()); // /och16/list.do
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize -1;	
			if (endPage > pageCnt) endPage = pageCnt;	
			
			request.setAttribute("totCnt",   totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list",     list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			System.out.println("-----------------------------------------------");  // /och16/list.do
			System.out.println("startNum-->" + startNum);        // /och16/list.do
			System.out.println("totCnt-->" + totCnt);            // /och16/list.do
			System.out.println("currentPage-->" + currentPage);  // /och16/list.do
			System.out.println("blockSize-->" + blockSize);      // /och16/list.do
			System.out.println("pageSize-->" + pageSize);        // /och16/list.do
			System.out.println("pageCnt-->" + pageCnt);          // /och16/list.do
			System.out.println("startPage-->" + startPage);      // /och16/list.do
			System.out.println("endPage-->" + endPage);          // /och16/list.do
			
		} catch (Exception e) {
			
		}
		return "review.jsp";
	}
}
