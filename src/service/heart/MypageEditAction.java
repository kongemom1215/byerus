package service.heart;

import java.io.IOException; 
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.ShoppingUser;
import dao.heart.ShoppingUserDao;
import service.CommandProcess;

public class MypageEditAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			int sid=Integer.parseInt(request.getParameter("sid"));
			
			ShoppingUserDao su = ShoppingUserDao.getInstance();
			ShoppingUser shoppinguser = new ShoppingUser();
			
			shoppinguser.setSid(Integer.parseInt(request.getParameter("sid")));
			shoppinguser.setSemail(request.getParameter("semail"));
			shoppinguser.setSpwd(request.getParameter("spwd"));
			shoppinguser.setSname(request.getParameter("sname"));
			shoppinguser.setScontact(request.getParameter("scontact"));
			shoppinguser.setSaddress(request.getParameter("saddress"));
			shoppinguser.setSpost(Integer.parseInt(request.getParameter("spost")));
			shoppinguser.setSagree(request.getParameter("sagree"));
			shoppinguser.setSquestion(request.getParameter("squestion"));
			shoppinguser.setSanswer(request.getParameter("sanswer"));
			System.out.println("UpdateProAction shoppinguser.getSemail() -> "+shoppinguser.getSemail());
			System.out.println("UpdateProAction shoppinguser.getSpwd() -> "+shoppinguser.getSpwd());
			System.out.println("UpdateProAction shoppinguser.getSaddress() -> "+shoppinguser.getSaddress());
			System.out.println("UpdateProAction shoppinguser.getSpost() -> "+shoppinguser.getSpost());
			System.out.println("UpdateProAction shoppinguser.getSagree() -> "+shoppinguser.getSagree());
			
			int result = su.edit(shoppinguser,sid);
			System.out.println("UpdateProAction result -> "+result);
			
			request.setAttribute("sid", sid);
			request.setAttribute("shoppinguser", shoppinguser);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return "mypageEdit.jsp";
	}

}
