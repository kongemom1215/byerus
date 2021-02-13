package service.half;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;

public class AboutUsAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("session_sid", session.getAttribute("session_sid"));
		session.setAttribute("session_sname", session.getAttribute("session_sname"));
		session.setAttribute("session_stype", session.getAttribute("session_stype"));
		session.setAttribute("session_semail", session.getAttribute("session_semail"));
		
		return "aboutus.jsp";
	}

}
