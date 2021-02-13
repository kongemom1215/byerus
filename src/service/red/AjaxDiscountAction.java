package service.red;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.red.CouponDao;
import service.CommandProcess;

public class AjaxDiscountAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AjaxDiscountAction 확인");
		 try {
			 
		        request.setCharacterEncoding("utf-8"); 
				response.setContentType("text/html;charset=utf-8");
				
				int Vcid = Integer.parseInt(request.getParameter("Vcid"));
					System.out.println("Vcid==>"+Vcid);
				int sum_value = (int)Double.parseDouble(request.getParameter("sum_value"));
					System.out.println("sum_value==>"+sum_value);
				CouponDao cd = CouponDao.getInstance();
				int cdiscount = cd.discount(Vcid);
					System.out.println("cdiscount==>"+cdiscount);
				int sum = (int)(sum_value * (100-cdiscount)/100); 
					System.out.println("sum==>"+sum);
				int total = 0;
				if(sum < 30000) {
					total = sum + 3000;
				}else {
					total = sum + 0;
				}
				
				System.out.println("total==>"+total);
				request.setAttribute("total", total);
				
		 }catch(Exception e) {
			 System.out.println("AjaxDiscountAction error!!!"+e.getMessage());
		 }
		
		
		
		
		
		
		
		return "ajaxDiscount";
	}

}
