package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ReviewDao;
import service.CommandProcess;


public class ReviewCMTEditAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			int rid=Integer.parseInt(request.getParameter("rid"));
			String pageNum=request.getParameter("pageNum");
			String rcmt=request.getParameter("rcmt");
			
			ReviewDao rd=ReviewDao.getInstance();
			int result=rd.editCMT(rid, rcmt);
			
			request.setAttribute("rid", rid);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			
			String rctg=request.getParameter("rctg");
			if(!(rctg==null || rctg.equals("")))
					request.setAttribute("rctg", rctg);
		} catch (Exception e) {
			System.out.println("ReviewCMTEditAction -> "+e.getMessage());
		}
		return "reviewCMTEditPro.jsp";
	}

}
