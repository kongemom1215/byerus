package service.heart;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.ShoppingUser;
import dao.heart.ShoppingUserDao;
import service.CommandProcess;

public class MypageDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession(true);
			
			int sid = (int) session.getAttribute("session_sid");
			
			String spwd = request.getParameter("spwd");
			
			ShoppingUserDao su = ShoppingUserDao.getInstance();
			ShoppingUser shoppinguser = new ShoppingUser();
			shoppinguser.setSid(Integer.parseInt(request.getParameter("sid")));
	        System.out.println("MypageDeleteAction sid->"+sid);
	        System.out.println("MypageDeleteAction spwd->"+spwd);
			int result = su.delete(sid, spwd);
	        System.out.println("MypageDeleteAction result->"+result);
			
	        if (result > 0) {
	            session.invalidate();
	         }
	        
			request.setAttribute("sid", sid);
			request.setAttribute("spwd", spwd);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "mypageDelete.jsp";
	}

}
