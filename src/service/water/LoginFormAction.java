package service.water;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

public class LoginFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			request.setAttribute("url", request.getParameter("url"));
			request.setAttribute("pid", request.getParameter("pid"));
			
		} catch (Exception e) {
			
		}
		return "login.jsp";
	}

}
