package service.water;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.Review;
import dao.water.ReviewDao;
import service.CommandProcess;


public class ReviewDeleteForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int sid = Integer.parseInt(request.getParameter("sid"));
			String pageNum = request.getParameter("pageNum");
			int rid = Integer.parseInt(request.getParameter("rid"));
			
			ReviewDao rd = ReviewDao.getInstance();
			int result = rd.delete(rid);
			
			
			request.setAttribute("rid", request.getParameter("rid"));
			request.setAttribute("sid", sid);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "reviewDeleteForm.jsp";
	}
}
