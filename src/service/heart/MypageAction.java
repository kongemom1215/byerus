package service.heart;

import java.io.IOException; 
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.CouponDao;
import dao.heart.Order_Join;
import dao.heart.Order_tbDao;
import dao.heart.ShoppingUser;
import dao.heart.ShoppingUserDao;
import service.CommandProcess;

public class MypageAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageAction START....");
		
		try {
			
			HttpSession session = request.getSession(true);
			int sid = (int) session.getAttribute("session_sid");
			
			System.out.println("MypageAction sid BEFORE");
			CouponDao cp = CouponDao.getInstance();
			System.out.println("MypageAction sid->"+sid);
			int count = cp.getCount(sid);
			System.out.println("MypageAction count->"+count);
			ShoppingUserDao su = ShoppingUserDao.getInstance();
			ShoppingUser shoppinguser = su.select(sid);
			Order_tbDao oddao = Order_tbDao.getInstance();
			List<Order_Join> list_main = oddao.list_main(sid);
			
			int count_Order = oddao.getCount(sid);
			System.out.println("MypageAction count_Order->"+count_Order);
			
			request.setAttribute("count", count);
			request.setAttribute("sid", sid);
			request.setAttribute("shoppinguser", shoppinguser);
			request.setAttribute("count_Order", count_Order);
			request.setAttribute("list_main", list_main);
		} catch (SQLException e) {
			System.out.println("MypageAction.java Error ->"+e.getMessage());
		}
		return "mypage.jsp";
	}

}
