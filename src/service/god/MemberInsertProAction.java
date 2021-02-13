package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ShoppingUser;
import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class MemberInsertProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String semail=request.getParameter("semail1")+"@"+request.getParameter("semail2");
			ShoppingUser shoppinguser=new ShoppingUser();
			shoppinguser.setSname(request.getParameter("sname"));
			shoppinguser.setSemail(semail);
			shoppinguser.setSpwd(request.getParameter("spwd"));
			shoppinguser.setScontact(request.getParameter("scontact"));
			shoppinguser.setSpost(Integer.parseInt(request.getParameter("spost")));
			shoppinguser.setSaddress(request.getParameter("saddress")+" "+request.getParameter("saddress2"));
			shoppinguser.setSagree(request.getParameter("sagree"));
			shoppinguser.setSquestion(request.getParameter("squestion"));
			shoppinguser.setSanswer(request.getParameter("sanswer"));
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			int result=sd.insertUser(shoppinguser);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("MemberInsertProAction -> "+e.getMessage());
		}
		return "memberInsertPro.jsp";
	}

}
