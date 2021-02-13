package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ReviewDao;
import service.CommandProcess;

public class ReviewCMTDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int rid=Integer.parseInt(request.getParameter("rid"));
			String pageNum=request.getParameter("pageNum");
			ReviewDao rd=ReviewDao.getInstance();
			int result=rd.deleteCMT(rid);
			
			request.setAttribute("result", result);
			request.setAttribute("rid", rid);
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println("ReviewCMTDeleteProAction -> "+e.getMessage());
		}
		return "reviewCMTDeletePro.jsp";
	}

}
