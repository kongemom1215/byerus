package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Product;
import dao.god.ProductDao;
import dao.god.ShoppingUser;
import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class ProductAdminAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals(""))
				pageNum="1";
			int currentPage = Integer.parseInt(pageNum);
			int pageSize=4; //한페이지당 보일 게시글 수
			int blockSize=10; //페이지수
			int startRow=(currentPage-1)*pageSize+1; //시작 번호 //2*10+1=21
			int endRow=startRow+pageSize-1; //끝 번호 //21+10-1=30
			
			ProductDao pd=ProductDao.getInstance();
			int totalProduct=pd.getTotalProduct(); //상품 수
			List<Product> productlist=pd.list(startRow, endRow);
			int pageCnt=(int)Math.ceil((double)totalProduct/pageSize);
			int startPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=startPage+blockSize-1;
			if(endPage>pageCnt)
				endPage=pageCnt;
			
			request.setAttribute("pageNum", pageNum); //현 페이지 번호
			request.setAttribute("currentPage", currentPage); //현 페이지 번호
			request.setAttribute("totalProduct", totalProduct); //상품 수
			request.setAttribute("productlist", productlist); //상품목록
			request.setAttribute("blockSize", blockSize); //페이지제한수
			request.setAttribute("pageCnt", pageCnt); //페이지개수
			request.setAttribute("startPage", startPage); //시작 페이지
			request.setAttribute("endPage", endPage); //끝페이지
		} catch (Exception e) {
			System.out.println("ProductAdminAction -> "+e.getMessage());
		}
		return "productAdminPage.jsp";
	}

}
