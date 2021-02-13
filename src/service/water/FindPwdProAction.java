package service.water;

import java.io.IOException;  

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.ShoppingUser;
import dao.water.ShoppingUserDao;

import service.CommandProcess;

public class FindPwdProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ShoppingUser shoppinguser=new ShoppingUser();
			String semail=request.getParameter("semail");
			String sname=request.getParameter("sename");
			String squestion= request.getParameter("squestion");
			String sanswer = request.getParameter("sanswer");
			System.out.println("FindPwdProAction Start...");
			
			
			if( request.getParameter("semail") != null) {
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			shoppinguser=sd.findpwd(semail,sname,squestion,sanswer);
			System.out.println("shoppinguser->"+shoppinguser);
			
	
			}

			request.setAttribute("result", shoppinguser.getSpwd());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "findPwdPro.jsp";
	}
}
