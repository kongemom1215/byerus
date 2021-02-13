package service.god;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Product;
import dao.god.ProductDao;
import service.CommandProcess;

public class MainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao pd=ProductDao.getInstance();
		List<Product> main_img = new ArrayList<Product>();
		try {
			main_img = pd.main_img();
			request.setAttribute("main_img", main_img);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main.jsp";
	}

}
