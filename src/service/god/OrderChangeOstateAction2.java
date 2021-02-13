package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Order;
import dao.god.OrderDao;
import service.CommandProcess;

public class OrderChangeOstateAction2 implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int oid=Integer.parseInt(request.getParameter("oid"));
			
			OrderDao od=OrderDao.getInstance();
			int result=od.updateOstate(oid);
			
			request.setAttribute("result", result);
			request.setAttribute("oid", oid);
		} catch (Exception e) {
			System.out.println("OrderChangeOstateAction -> "+e.getMessage());
		}
		return "orderChangeOstatePro2.jsp";
	}

}
