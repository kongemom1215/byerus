package service.water;

import java.io.IOException;  

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.ShoppingUser;
import dao.water.ShoppingUserDao;
import service.CommandProcess;

public class FindEmailProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ShoppingUser shoppinguser=new ShoppingUser();
			String sname=request.getParameter("sname");
			String squestion= request.getParameter("squestion");
			String sanswer = request.getParameter("sanswer");
			System.out.println("FindEmailProAction Start...");
			
			if(request.getParameter("sname") != null) {
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			shoppinguser=sd.findem(sname,squestion,sanswer);
			
			
			
			}
			
			request.setAttribute("result", shoppinguser.getSemail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "findEmailPro.jsp";
	}
}
