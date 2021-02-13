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

public class MypageOrderAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(true);
			
			int sid = (int) session.getAttribute("session_sid");
			
		
			System.out.println("sid ->"+sid);
			Order_tbDao oddao = Order_tbDao.getInstance();
			Order_Join orderjoin = new Order_Join();
			
			String orderdate_select  = request.getParameter("orderdate_select");
			request.setAttribute("orderdate_select", orderdate_select);
			try {
				if (orderdate_select.equals("oneM")) {
					request.setAttribute("selected1", "selected");
				} else if (orderdate_select.equals("threeM")) {
					request.setAttribute("selected2", "selected");
				} else if (orderdate_select.equals("sixM")) {
					request.setAttribute("selected3", "selected");
				} else {
				}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				System.out.println("select : " + orderdate_select);
	
			
			orderjoin = oddao.select(sid);
			List<Order_Join> list = oddao.list_order(sid, orderdate_select);
			
			request.setAttribute("sid", sid);
			request.setAttribute("list", list);		 
			request.setAttribute("orderjoin", orderjoin);

		} catch (Exception e) {
			System.out.println("MypageOrderAction error=> "+e.getMessage());
		} 
		
		return "mypageOrder.jsp";
	}

}
