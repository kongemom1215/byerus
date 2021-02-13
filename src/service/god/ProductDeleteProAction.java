package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ProductDao;
import service.CommandProcess;

public class ProductDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("아");
			int pid=Integer.parseInt(request.getParameter("pid"));
			ProductDao pd=ProductDao.getInstance();
			int result=pd.deleteProduct(pid);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("ProductDeleteProAction -> "+e.getMessage());
		}
		return "productDeletPro.jsp";
	}

}
