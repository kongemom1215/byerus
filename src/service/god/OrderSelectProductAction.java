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

public class OrderSelectProductAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int pid=Integer.parseInt(request.getParameter("pid"));
			
			OrderDao od=OrderDao.getInstance();
			List<Order> orderlist=od.selectPid(pid);

			request.setAttribute("pid", pid);
			request.setAttribute("orderlist", orderlist);
		} catch (Exception e) {
			System.out.println("OrderSelectProductAction -> "+e.getMessage());
		}
		return "orderSelectProduct.jsp";
	}

}
