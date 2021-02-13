package service.dragon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dragon.CartnWish;
import dao.dragon.Product;
import dao.dragon.ProductDao;
import dao.red.CartnWishDao;
import service.CommandProcess;

public class JJimGoCartAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		CartnWishDao cartnwidshdao = CartnWishDao.getInstance();
		ProductDao pro = ProductDao.getInstance();
		
		
		try {
			 String[] sizes = request.getParameterValues("wish");
			 for (int i = 0; i < sizes.length; i ++) {
				 cartnwidshdao.insert(Integer.parseInt(sizes[i]), (int) session.getAttribute("session_sid"), Integer.parseInt(request.getParameter(String.valueOf(i+1))), null);
				 pro.jjimdel((int) session.getAttribute("session_sid"), Integer.parseInt(sizes[i]));
			 }
			 
			 
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}
		
		return "jjimForm.do";
	}

}
