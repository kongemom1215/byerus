package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Product;
import dao.god.ProductDao;
import service.CommandProcess;

public class ProductSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum=request.getParameter("pageNum");
			String option=request.getParameter("option");
			String search_value=request.getParameter("search_value");
			if(pageNum==null || pageNum.equals("pageNum"))
				pageNum="1";
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=4, blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=startRow+pageSize-1;
			
			ProductDao pd=ProductDao.getInstance();
			List<Product> productlist=pd.searchlist(option,search_value, startRow, endRow);
			int totalProduct=pd.searchtotal(option, search_value);
			
			int pageCnt=(int)Math.ceil((double)totalProduct/pageSize);
			int startPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=startPage+blockSize-1;
			if(endPage>pageCnt)
				endPage=pageCnt;
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage); 
			request.setAttribute("productlist", productlist); 
			request.setAttribute("totalProduct", totalProduct); 
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt); 
			request.setAttribute("startPage", startPage); 
			request.setAttribute("endPage", endPage); 
			request.setAttribute("option", option);
			request.setAttribute("search_value", search_value);
		} catch (Exception e) {
			System.out.println("ProductSearchAction -> "+e.getMessage());
		}
		return "productSearchPage.jsp";
	}

}
