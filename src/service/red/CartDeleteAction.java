package service.red;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.red.CartnWishDao;
import oracle.net.aso.s;
import service.CommandProcess;

public class CartDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CartDeletAction Start!!");
		System.out.println("cart.jsp 에서 전체삭제 버튼 누르면 삭제 실행");
		
		try {
			//전체삭제
			int session_sid = (int) request.getSession().getAttribute("session_sid");

			CartnWishDao cartw=CartnWishDao.getInstance();
			int result=cartw.delete(session_sid);
			
			request.setAttribute("result", result);
			
		}catch(Exception e) {
			System.out.println("CartDeletAction error!!!"+e.getMessage());
		}
		
		
		return "cartDelResult.jsp";
	}

}
