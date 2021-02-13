package service.water;

import java.io.IOException; 
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.ShoppingUser;
import dao.water.ShoppingUserDao;

import service.CommandProcess;


public class JoinFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ShoppingUser shoppinguser=new ShoppingUser();
			int sid = 0;
			System.out.println("JoinFormAction Start...");
			//회원가입 작업
			if(request.getParameter("sid") != null) {
				sid = Integer.parseInt(request.getParameter("sid"));
				ShoppingUserDao sp = ShoppingUserDao.getInstance();
				shoppinguser = sp.select(sid);

				
			}
			request.setAttribute("semail", shoppinguser.getSemail());
			request.setAttribute("sid", sid);
			//회원가입 추가
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "join.jsp";
		}
}
