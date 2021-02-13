package service.water;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.Review;
import dao.water.ReviewDao;
import service.CommandProcess;


public class ReviewContentFromAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		String pageNum = request.getParameter("pageNum");
		try {
			
			Review review = new Review();
			System.out.println("ReviewContentFromAction start......");
			ReviewDao rd = ReviewDao.getInstance();
			rd.readCount(rid);
			review = rd.find(rid);

			request.setAttribute("rid", review.getRid());
			request.setAttribute("sid", review.getSid());
			request.setAttribute("oid", review.getOid());
			request.setAttribute("rwriter", review.getRwriter());
			request.setAttribute("rtitle", review.getRtitle());
			request.setAttribute("rcontent", review.getRcontent());
			request.setAttribute("rimg", review.getRimg());
			request.setAttribute("rdate", review.getRdate());
			request.setAttribute("rhit", review.getRhit());
			request.setAttribute("rcmt", review.getRcmt());
			request.setAttribute("rcmtdate", review.getRcmtdate());
			request.setAttribute("odate", review.getOdate());
			request.setAttribute("pid", review.getPid());
			
			
			
			System.out.println("리뷰 조회수 : "+review.getRhit());
			
			 request.setAttribute("pageNum", pageNum);
			 request.setAttribute("sid", sid);
			  System.out.println("UpdateFormAction ->"+review.getRcontent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "reviewContent.jsp";
	}

}
