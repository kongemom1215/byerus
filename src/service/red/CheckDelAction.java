package service.red;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.red.CartnWish;
import dao.red.CartnWishDao;
import service.CommandProcess;

public class CheckDelAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("CheckDelAction Start!!!");
		
		try {
			
			String[] checked = request.getParameterValues("check1");
			int result=0;
			for(int i=0;i<checked.length;i++) {
				System.out.println("checked=>"+checked[i]);
				CartnWish cart = new CartnWish();
				cart.setCwid(Integer.parseInt(checked[i]));
			
				CartnWishDao cartw = CartnWishDao.getInstance();
				result = cartw.CheckDelete(cart);
			
			}
			
			
		request.setAttribute("result", result);
		
		
		}catch(Exception e ) {
			System.out.println("CheckDelAction error =>"+e.getMessage());
		}
		 		

		return "cartCheckDelResult.jsp" ;
	}

}
