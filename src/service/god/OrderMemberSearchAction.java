package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.god.Order;
import dao.god.OrderDao;
import dao.god.OrderDetail;
import dao.god.OrderDetailDao;
import dao.god.ShoppingUser;
import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class OrderMemberSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session=request.getSession();
			String option=request.getParameter("option");
			String search_value=request.getParameter("search_value");
			int result=0;
			if(option.equals("sid")) {
				int sid=Integer.parseInt(search_value);
				ShoppingUserDao sd=ShoppingUserDao.getInstance();
				ShoppingUser shoppinguser=sd.select(sid);
				if(shoppinguser.getSemail()!=null ||shoppinguser.getSemail()!="") {
					OrderDao od=OrderDao.getInstance();
					List<Order> orderlist=od.select(sid);
					if(orderlist.size()>0) {
						request.setAttribute("sname", shoppinguser.getSname());
						request.setAttribute("orderlist", orderlist);
						result=1;
					}
					else
						result=0;
				}
				else
					result=-1;
			}
			else if(option.equals("orderCode")) {
				String odate=search_value.substring(0,8);
				int oid=Integer.parseInt(search_value.substring(8,13));
				OrderDao od=OrderDao.getInstance();
				Order order=od.select(odate, oid);
				if(order.getOid()!=0) {
					OrderDetailDao odd=OrderDetailDao.getInstance();
					List<OrderDetail> orderdetaillist=odd.select(oid);
					System.out.println("orderdetaillist.size() ->"+orderdetaillist.size() );
					request.setAttribute("orderdetaillist", orderdetaillist);
					request.setAttribute("order", order);
					result=1;
				}
				else
					result=-1;
			}
			
			session.setAttribute("option", option);
			session.setAttribute("search_value", search_value);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("OrderMemberSearchAction -> "+e.getMessage());
		}
		return "orderMemberSearch.jsp";
	}

}
