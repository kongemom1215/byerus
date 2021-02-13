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

public class OrderSelectOdateAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String datepicker=request.getParameter("datepicker").replace("-", "");
			String datepicker2=request.getParameter("datepicker2").replace("-", "");
			OrderDao od=OrderDao.getInstance();
			List<Order> orderlist=od.select(datepicker, datepicker2);
			
			request.setAttribute("orderlist", orderlist);
			request.setAttribute("datepicker", request.getParameter("datepicker"));
			request.setAttribute("datepicker2", request.getParameter("datepicker2"));
		} catch (Exception e) {
			System.out.println("OrderSelectOdateAction -> "+e.getMessage());
		}
		return "orderSelectOdate.jsp";
	}

}
