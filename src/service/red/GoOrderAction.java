package service.red;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.red.CartnWishDao;
import dao.red.Coupon;
import dao.red.CouponDao;
import dao.red.Product;
import dao.red.ProductDao;
import dao.red.ShoppingUser;
import dao.red.ShoppingUserDao;
import service.CommandProcess;

public class GoOrderAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GoOrderAction Start!!");
		try {

			
			HttpSession session = request.getSession(true);
			session.setAttribute("session_sid", session.getAttribute("session_sid"));
			session.setAttribute("session_sname", session.getAttribute("session_sname"));
			session.setAttribute("session_stype", session.getAttribute("session_stype"));
			session.setAttribute("session_semail", session.getAttribute("session_semail"));

			int pid = Integer.parseInt(request.getParameter("pid"));
				System.out.println("pid==>"+pid);
			String option = request.getParameter("option");
				System.out.println("option==>"+option);
			int howmany = Integer.parseInt(request.getParameter("howmany"));
				System.out.println("howmany==>"+howmany);
			int session_sid = (int)session.getAttribute("session_sid");
				System.out.println("session_sid==>"+session_sid);

			//회원정보 가져오기
			ShoppingUserDao userDao= ShoppingUserDao.getInstance();
			ShoppingUser user=userDao.select(session_sid);
				System.out.println("GoOrderAction 확인1");

			//쿠폰불러오기
			CouponDao cuDao = CouponDao.getInstance();
			ArrayList<Coupon> cu = cuDao.selectCoupon(session_sid);
				System.out.println("GoOrderAction 확인2");
			
			//상품정보 가져오기
			ProductDao pd = ProductDao.getInstance();
			Product product = pd.select(pid);
				System.out.println("GoOrderAction 확인3");
			
			request.setAttribute("product", product);
			request.setAttribute("pid", pid);
			request.setAttribute("option", option);
			request.setAttribute("howmany", howmany);
			request.setAttribute("session_sid", session_sid);
			request.setAttribute("cu", cu);
			request.setAttribute("user", user);
			
		}catch(Exception e) {
			System.out.println("GoOrderAction error"+e.getMessage());
		}
		
		return "goOrder.jsp";
	}

}
