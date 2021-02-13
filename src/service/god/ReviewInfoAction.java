package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Review;
import dao.god.ReviewDao;
import service.CommandProcess;

public class ReviewInfoAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int rid=Integer.parseInt(request.getParameter("rid"));
			String pageNum=request.getParameter("pageNum");
			String rctg=request.getParameter("rctg");
			
			ReviewDao rd=ReviewDao.getInstance();
			rd.upHit(rid);
			Review review=rd.select(rid);
			request.setAttribute("rid", rid);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("rctg", rctg);
			request.setAttribute("review", review);
		} catch (Exception e) {
			System.out.println("ReviewInfoAction -> "+e.getMessage());
		}
		return "reviewInfo.jsp";
	}

}
