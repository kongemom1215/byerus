package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class IdCheckAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String semail1=request.getParameter("semail1");
			String semail2=request.getParameter("semail2");
			String semail=semail1+"@"+semail2;
			System.out.println(semail);
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			int result=sd.idCheck(semail);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("IdCheckAction -> "+e.getMessage());
		}
		
		
		return null;
	}

}
