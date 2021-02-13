package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Product;
import dao.god.ProductDao;
import service.CommandProcess;

public class ProductEditAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum=request.getParameter("pageNum");
			int pid=Integer.parseInt(request.getParameter("pid"));
			ProductDao pd=ProductDao.getInstance();
			Product product=pd.select(pid);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("product", product);
			request.setAttribute("pid", pid);
			
			String option=request.getParameter("option");
			String search_value=request.getParameter("search_value");
			if(!(option==null || option.equals(""))) {
				request.setAttribute("option", option);
				request.setAttribute("search_value", search_value);
			}
		} catch (Exception e) {
			System.out.println("ProductEditAction -> "+e.getMessage());
		}
		return "productEdit.jsp";
	}

}
