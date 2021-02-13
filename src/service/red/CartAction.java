package service.red;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.red.CartnWish;
import dao.red.CartnWishDao;
import service.CommandProcess;

public class CartAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("cart TB 불러오기=>cat.jsp로 장바구니 리스트 보여주기");
		System.out.println("CartAction Start!!");
		
		try {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("session_sid", session.getAttribute("session_sid"));
			session.setAttribute("session_sname", session.getAttribute("session_sname"));
			session.setAttribute("session_stype", session.getAttribute("session_stype"));
			session.setAttribute("session_semail", session.getAttribute("session_semail"));
			//세션 로그인 유지
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			
			String session_semail = (String) session.getAttribute("session_semail");
			int session_sid = (int) session.getAttribute("session_sid");
			
			CartnWishDao cartw= CartnWishDao.getInstance();
			CartnWish cart = new CartnWish();
			//장바구니에 담긴 상품에 대한 정보
			ArrayList<CartnWish> list = cartw.select(session_sid);
			//장바구니에 담긴 상품 갯수(상품개수)
			int count = cartw.count(session_sid);		
			
			System.out.println("--------CartAction 파라미터----------------");
			System.out.println("list ArrayList배열=>"+list);
			System.out.println("count=>"+count);
			
			request.setAttribute("list",list); //장바구니에 담긴 상품들 정보
			request.setAttribute("count",count);//장바구니에 담긴 상품 개수
			
			
			
		}catch(Exception e) {
			System.out.println("CartAction Error !! "+e.getMessage());
		}
		return "cart.jsp";
	}

}
