package service.god;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.OrderDao;
import service.CommandProcess;

public class SalesYearAction implements CommandProcess {
	//https://saem-ee.tistory.com/48
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String[] sixdays=new String[6];
			String[] sixdate=new String[6];
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY년", Locale.KOREA);
			sixdays[0]=sdf.format(date);
			sdf=new SimpleDateFormat("YYYY", Locale.KOREA);
			sixdate[0]=sdf.format(date);
			
			for(int i=1;i<6;i++) {
				Calendar day=Calendar.getInstance();
				day.add(Calendar.YEAR, -i);
				sixdays[i]=new SimpleDateFormat("YYYY년").format(day.getTime());
				sixdate[i]=new SimpleDateFormat("YYYY").format(day.getTime());
			}
			
			OrderDao od=OrderDao.getInstance();
			double[] salesAmount=od.selectSales3(sixdate);
			
			
			request.setAttribute("salesAmount", salesAmount);
			request.setAttribute("sixdays", sixdays);
		} catch (Exception e) {
			System.out.println("SalesAdminAction -> "+e.getMessage());
		}
		return "salesAdminPage.jsp";
	}

}
