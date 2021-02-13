package service.dragon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dragon.Product;
import dao.dragon.ProductDao;
import service.CommandProcess;

public class JJimFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(true);
			int sid = (int) session.getAttribute("session_sid");
			
			ProductDao productdao = ProductDao.getInstance();
			List<Product> list = new ArrayList<Product>();
			
			list = productdao.jjimForm(sid);
			
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "jjim.jsp";
	}

}
