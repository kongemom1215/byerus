package service.water; 

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.water.Review;
import dao.water.ReviewDao;
import service.CommandProcess;


public class ReviewUpdateForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * HttpSession session = request.getSession(true);
		 * session.setAttribute("session_sid", session.getAttribute("session_sid"));
		 * session.setAttribute("session_sname", session.getAttribute("session_sname"));
		 * session.setAttribute("session_stype", session.getAttribute("session_stype"));
		 * session.setAttribute("session_semail",
		 * session.getAttribute("session_semail"));
		 */

		  int sid = Integer.parseInt(request.getParameter("sid"));
		  int rid = Integer.parseInt(request.getParameter("rid"));
		  String pageNum=request.getParameter("pageNum");
		  String sname = request.getParameter("sname");
		  String rwriter = request.getParameter("rwriter");
		  
		  System.out.println("리뷰 sname = " + sname);
		  
		try {
			
			ReviewDao rd = ReviewDao.getInstance();
			Review review = rd.select(rid);
			
			
			request.setAttribute("rwriter", rwriter);
			 request.setAttribute("sname", sname);
			  request.setAttribute("sid", sid);
			  request.setAttribute("pageNum", pageNum); 
			  request.setAttribute("review", review);
			  System.out.println("ReviewUpdateForm ->"+review.getRcontent());
			 
			 
		} catch (Exception e) {
			System.out.println("리뷰업데이트 오류 form :"+e.getMessage() );
		}
		return "reviewUpdateForm.jsp";
	}

}
