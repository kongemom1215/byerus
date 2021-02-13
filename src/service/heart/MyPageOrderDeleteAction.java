package service.heart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.Order_Join;
import dao.heart.Order_tbDao;
import dao.heart.ShoppingUser;
import service.CommandProcess;

public class MyPageOrderDeleteAction implements CommandProcess  {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		request.setCharacterEncoding("utf-8");
		
		int oid = Integer.parseInt(request.getParameter("oid"));

		
		Order_tbDao oddao = Order_tbDao.getInstance();
		Order_Join orderjoin = new Order_Join();
		int result = oddao.delete(oid);
		System.out.println("oid ->"+oid);
		
		request.setAttribute("oid", oid);
		request.setAttribute("orderjoin", orderjoin);
		request.setAttribute("result", result);
		}catch(Exception e) {
			System.out.println("MyPageOrderDeleteAction error=> "+e.getMessage());
		} 
		return "mypageOrderdelete.jsp";
	}

}
