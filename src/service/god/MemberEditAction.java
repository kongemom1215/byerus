package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ShoppingUser;
import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class MemberEditAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int sid=Integer.parseInt(request.getParameter("sid"));
			String pageNum=request.getParameter("pageNum");
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			
			ShoppingUser shoppinguser=sd.select(sid);
			
			request.setAttribute("sid", sid);
			request.setAttribute("shoppinguser", shoppinguser);
			request.setAttribute("pageNum", pageNum);
			
			String option=request.getParameter("option");
			String search_value=request.getParameter("search_value");
			if(!(option==null || option.equals(""))) {
				request.setAttribute("option", option);
				request.setAttribute("search_value", search_value);
			}
		}catch (Exception e) {
			System.out.println("MemberEditFormAction -> "+e.getMessage());
		}
		return "memberEdit.jsp";
	}
}
