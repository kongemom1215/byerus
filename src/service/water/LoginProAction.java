package service.water;

import java.io.IOException; 
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.water.ShoppingUser;
import dao.water.ShoppingUserDao;

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
			
			HttpSession session = request.getSession(true);
			session.setAttribute("session_sid", shoppinguser.getSid());
			session.setAttribute("session_sname", shoppinguser.getSname());
			session.setAttribute("session_stype", shoppinguser.getStype());
			session.setAttribute("session_semail", shoppinguser.getSemail());
			
			System.out.println(shoppinguser.getStype());
			request.setAttribute("sid", shoppinguser.getSid());
			request.setAttribute("stype", shoppinguser.getStype());
			request.setAttribute("result", result);
			
			System.out.println(request.getParameter("url"));
			request.setAttribute("url", request.getParameter("url"));
			request.setAttribute("pid", request.getParameter("pid"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "loginPro.jsp";
	}

}
