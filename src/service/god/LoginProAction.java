package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ShoppingUser;
import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class LoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String semail=request.getParameter("semail");
			String spwd=request.getParameter("spwd");
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			int result=sd.login(semail, spwd);
			ShoppingUser shoppinguser=new ShoppingUser();
			System.out.println("result -> "+result);
			
			if(result==1)
				shoppinguser=sd.select(semail);		
			
			
			request.setAttribute("sid", shoppinguser.getSid());
			request.setAttribute("stype", shoppinguser.getStype());
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "loginPro.jsp";
	}

}
