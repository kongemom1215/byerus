package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class MemberDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int sid=Integer.parseInt(request.getParameter("sid"));
			
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			int result=sd.deleteUser(sid);
			
			request.setAttribute("result", result);
			
			String option=request.getParameter("option");
			String search_value=request.getParameter("search_value");
			if(!(option==null || option.equals(""))) {
				request.setAttribute("option", option);
				request.setAttribute("search_value", search_value);
			}
			String pageNum=request.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println("MemberDeleteProAction -> "+e.getMessage());
		}
		return "memberDeletePro.jsp";
	}

}
