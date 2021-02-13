package service.dragon;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dragon.ProductDao;
import service.CommandProcess;

public class JJimAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-- service.dragon.JJimAction --");
		
		HttpSession session = request.getSession(true);

		int pid = Integer.parseInt(request.getParameter("pid"));
		
		try {
			
		int sid = (int) session.getAttribute("session_sid");
		
		ProductDao productdao = ProductDao.getInstance();
		
		try {
			int same = productdao.jjim(sid, pid);
		} catch (SQLException e) {
			System.out.println("오류 : "+ e.getMessage());
		}
		
//		System.out.println(before_address);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		redirect:productDetail.do
        
		 request.setAttribute("pid",pid);
		    return "jjimPro.jsp";

	}

}
