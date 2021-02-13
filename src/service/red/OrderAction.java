package service.red;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.red.CartnWish;
import dao.red.CartnWishDao;
import dao.red.Coupon;
import dao.red.CouponDao;
import dao.red.Order_tbDao;
import dao.red.ShoppingUser;
import dao.red.ShoppingUserDao;

import service.CommandProcess;

public class OrderAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("OrderAction Start !!");
		System.out.println("장바구니->주문하기 클릭시 발생Action");
		try {
			

			HttpSession session = request.getSession(true);
			session.setAttribute("session_sid", session.getAttribute("session_sid"));
			session.setAttribute("session_sname", session.getAttribute("session_sname"));
			session.setAttribute("session_stype", session.getAttribute("session_stype"));
			session.setAttribute("session_semail", session.getAttribute("session_semail"));
			System.out.println("확인1");
			String session_semail = (String)request.getSession().getAttribute("session_semail");
			int session_sid=(int)session.getAttribute("session_sid");
			System.out.println("확인2");
			
			double sum = Double.parseDouble(request.getParameter("sum"));

			System.out.println("확인3");
			//회원정보 가져오기
			ShoppingUserDao userDao= ShoppingUserDao.getInstance();
			ShoppingUser user=userDao.select(session_sid);
			System.out.println("확인4");
			//장바구니 가져오기
			CartnWishDao cartw=CartnWishDao.getInstance();
			ArrayList<CartnWish> list = cartw.select(session_sid);
			int count=cartw.count(session_sid);
			System.out.println("확인5"); 
			//쿠폰불러오기
			CouponDao cuDao = CouponDao.getInstance();
			ArrayList<Coupon> cu = cuDao.selectCoupon(session_sid);
			System.out.println("확인6"); 
			//List<Coupon> coupon = cuDao.list(sid);		
			
			//주문번호 가져오기
			Order_tbDao orderTb = Order_tbDao.getInstance();
			
			
			request.setAttribute("user", user);
			request.setAttribute("list", list);
			request.setAttribute("sum", sum);
			request.setAttribute("count", count);
			request.setAttribute("cu", cu);
			//request.setAttribute("coupon", coupon);
			
			System.out.println("user=>"+user);
			System.out.println("list=>"+list);
			System.out.println("sum=>"+sum);
			System.out.println("count=>"+count);
			System.out.println("cu=>"+cu);
			//System.out.println("coupon=>"+coupon);
			
		}catch(Exception e) {
			System.out.println("OrderAction error=>"+e.getMessage());
		}
		
		return "order.jsp";
	}

}
