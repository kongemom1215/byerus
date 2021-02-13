package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Order;
import dao.god.OrderDao;
import service.CommandProcess;

public class OrderAllAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OrderDao od=OrderDao.getInstance();
			List<Order> orderlist=od.select();
			
			request.setAttribute("orderlist", orderlist);
		} catch (Exception e) {
			System.out.println("OrderAllAction -> "+e.getMessage());
		}
		return "orderAllPage.jsp";
	}

}
