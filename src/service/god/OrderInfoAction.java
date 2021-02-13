package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Order;
import dao.god.OrderDao;
import dao.god.OrderDetail;
import dao.god.OrderDetailDao;
import service.CommandProcess;

public class OrderInfoAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int oid=Integer.parseInt(request.getParameter("oid"));
			
			OrderDao od=OrderDao.getInstance();
			Order order=od.select2(oid);
			OrderDetailDao odd=OrderDetailDao.getInstance();
			List<OrderDetail> orderdetaillist=odd.select(oid);
			request.setAttribute("orderdetaillist", orderdetaillist);
			request.setAttribute("order", order);	
		} catch (Exception e) {
			System.out.println("OrderInfoAction -> "+e.getMessage());
		}
		return "orderInfo.jsp";
	}

}
