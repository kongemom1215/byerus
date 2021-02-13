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

public class OrderSelectInfoAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int oid=Integer.parseInt(request.getParameter("oid"));
			String ostate=request.getParameter("ostate");
			if(ostate==null || ostate.equals("")) {
				ostate="";
			}
			else {
				request.setAttribute("ostate", ostate);
			}
			String datepicker=request.getParameter("datepicker");
			String datepicker2=request.getParameter("datepicker2");
			if(datepicker==null || datepicker.equals("")) {
				datepicker="";
			}
			else {
				request.setAttribute("datepicker", datepicker);
				request.setAttribute("datepicker2", datepicker2);
			}
			String pid=request.getParameter("pid");
			if(pid==null || pid.equals("")) {
				
			}
			else {
				request.setAttribute("pid", pid);
			}
			OrderDao od=OrderDao.getInstance();
			Order order=od.select2(oid);
			OrderDetailDao odd=OrderDetailDao.getInstance();
			List<OrderDetail> orderdetaillist=odd.select(oid);
			request.setAttribute("orderdetaillist", orderdetaillist);
			request.setAttribute("order", order);	
		} catch (Exception e) {
			System.out.println("OrderSelectInfoAction -> "+e.getMessage());
		}
		return "orderSelectInfo.jsp";
	}

}
