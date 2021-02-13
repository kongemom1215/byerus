package service.heart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.heart.Order_Join;
import dao.heart.Order_tbDao;
import service.CommandProcess;

public class MypageOrderHwakjeongAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			int oid = Integer.parseInt(request.getParameter("oid"));
			System.out.println("oid ->"+oid);
			Order_tbDao oddao = Order_tbDao.getInstance();
			Order_Join orderjoin = new Order_Join();
			int result = oddao.hwakjeong(oid);
		
			request.setAttribute("oid", oid);
			request.setAttribute("orderjoin", orderjoin);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println("MypageOrderHwakjeongAction Error->"+e.getMessage());
		}
				return "mypageOrderhwakjeong.jsp";
	}

}
