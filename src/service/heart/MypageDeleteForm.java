package service.heart;

import java.io.IOException; 
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.ShoppingUser;
import dao.heart.ShoppingUserDao;
import service.CommandProcess;

public class MypageDeleteForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession(true);
			
			int sid = (int) session.getAttribute("session_sid");
			
			System.out.println("sid ->"+sid);
			ShoppingUserDao su = ShoppingUserDao.getInstance();
			ShoppingUser shoppinguser = new ShoppingUser();
			shoppinguser = su.select(sid);
			
			request.setAttribute("sid", sid);
			request.setAttribute("shoppinguser", shoppinguser);
		} catch (Exception e) {
			System.out.println("MypageDeleteForm error=> "+e.getMessage());
		} 
		return "mypageDeleteForm.jsp";
	}

}
