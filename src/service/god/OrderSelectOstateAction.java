package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.god.Order;
import dao.god.OrderDao;
import service.CommandProcess;

public class OrderSelectOstateAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String ostate=request.getParameter("ostate");
			OrderDao od=OrderDao.getInstance();
			List<Order> orderlist=od.select(ostate);
			
			request.setAttribute("orderlist", orderlist);
			request.setAttribute("ostate", ostate);
		} catch (Exception e) {
			System.out.println("OrderSelectOstateAction"+e.getMessage());
		}
		return "orderSelectOstate.jsp";
	}

}
