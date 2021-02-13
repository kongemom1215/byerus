package service.heart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.Order_Join;
import dao.heart.Order_tbDao;
import service.CommandProcess;

public class MypageOrderBy implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(true);
			
			int sid = (int) session.getAttribute("session_sid");
			int oid = Integer.parseInt(request.getParameter("oid"));
			
			System.out.println("sid ->"+sid);
			System.out.println("oid ->"+oid);
			Order_tbDao oddao = Order_tbDao.getInstance();
			Order_Join orderjoin = new Order_Join();
			
			orderjoin = oddao.select1(sid,oid);
			
			request.setAttribute("sid", sid);
			request.setAttribute("oid", oid);		 
			request.setAttribute("orderjoin", orderjoin);

		} catch (Exception e) {
			System.out.println("MypageOrderBy error=> "+e.getMessage());
		} 
		return "mypageOrderBy.jsp";
	}

}
