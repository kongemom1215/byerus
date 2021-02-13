package service.red;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.Order_tbDao;
import dao.red.CartnWish;
import dao.red.CartnWishDao;
import dao.red.Coupon;
import dao.red.CouponDao;
import dao.red.ShoppingUser;
import dao.red.ShoppingUserDao;
import service.CommandProcess;

public class CheckOrderAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		System.out.println("CheckOrderAction Start!!");
		
		try {
			System.out.println("확인1");
			HttpSession session = request.getSession(true);
			session.setAttribute("session_sid", session.getAttribute("session_sid"));
			session.setAttribute("session_sname", session.getAttribute("session_sname"));
			session.setAttribute("session_stype", session.getAttribute("session_stype"));
			session.setAttribute("session_semail", session.getAttribute("session_semail"));
			System.out.println("확인2");
			
			int session_sid = (int) session.getAttribute("session_sid");
			System.out.println("확인3");
			//주문자정보
			ShoppingUserDao userDao= ShoppingUserDao.getInstance();
			ShoppingUser user=userDao.select(session_sid);
			System.out.println("확인4");

			
			String[] checked = request.getParameterValues("check1");
			System.out.println("checked 배열확인==>"+checked);
			System.out.println("checked.length 배열확인==>"+checked.length);
			CartnWishDao cartw = CartnWishDao.getInstance();
			ArrayList<CartnWish> list = null;
			int count = 0;
//			double sum = Double.parseDouble(request.getParameter("sum"));
			System.out.println("확인5");
			for(int i=0;i<checked.length;i++) {
				System.out.println("checked=>"+checked[i]);
				int check_cwid = Integer.parseInt(checked[i]);
				list=cartw.checkSelect(check_cwid);
				count = cartw.countCheck(check_cwid);
				System.out.println("countCheck count==>"+count);
				
//				System.out.println("sum==>"+sum);				
			}
			
			
			System.out.println("확인6");

			CouponDao cuDao = CouponDao.getInstance();
			ArrayList<Coupon> cu = cuDao.list(session_sid);		
			
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("cu", cu);
			request.setAttribute("user", user);
//			request.setAttribute("sum", sum);

			System.out.println("user=>"+user);
			System.out.println("list=>"+list);
//			System.out.println("sum=>"+sum);
			System.out.println("count=>"+count);
			System.out.println("cu=>"+cu);

			
			
		}catch(Exception e) {
			System.out.println("CheckOrderAction error!!! "+e.getMessage());
		}
		
		
		
		
		
		return "order.jsp";
	}

}
