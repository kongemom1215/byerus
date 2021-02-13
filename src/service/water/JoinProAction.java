package service.water;

import java.io.IOException;  

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.ShoppingUser;
import dao.water.ShoppingUserDao;

import service.CommandProcess;

public class JoinProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			/*
			 * System.out.println("JoinProAction requestPro sid->"+request.getParameter(
			 * "sid"));
			 */
			try {       
				if (request.getParameter("spwd").equals(request.getParameter("spwd2"))) {
				ShoppingUser user = new ShoppingUser();
				
				//user.setSid(Integer.parseInt(request.getParameter("sid")));
				user.setSemail(request.getParameter("semail"));
				user.setSpwd(request.getParameter("spwd"));
				user.setSname(request.getParameter("sname"));
				user.setScontact(request.getParameter("scontact"));
				user.setSaddress(request.getParameter("saddress"));
				user.setSpost(Integer.parseInt(request.getParameter("spost")));
				user.setSagree(request.getParameter("sagree"));
				user.setSquestion(request.getParameter("squestion"));
				user.setSanswer(request.getParameter("sanswer"));
				user.setStype(Integer.parseInt(request.getParameter("stype")));
				
		        System.out.println("JoinProAction requestPro semail->"+request.getParameter("semail"));
		        System.out.println("JoinProAction requestPro spwd->"+request.getParameter("spwd"));
		        System.out.println("JoinProAction requestPro sname->"+request.getParameter("sname"));
		        System.out.println("JoinProAction requestPro scontact->"+request.getParameter("scontact"));
		        System.out.println("JoinProAction requestPro squestion->"+request.getParameter("squestion"));
		        System.out.println("JoinProAction requestPro sanswer->"+request.getParameter("sanswer"));
				
				ShoppingUserDao sp = ShoppingUserDao.getInstance();//DB 
		        int result = sp.insert(user);
		        System.out.println("JoinProAction requestPro result->"+result);
				/* request.setAttribute("sid", user.getSid()); */
		        
		        request.setAttribute("result", result);
				} else {
					request.setAttribute("pwsdsame", "no");
					return "join.jsp";
				}
			} catch(Exception e) { 
				System.out.println("JoinProAction Exception->"+e.getMessage());
			}       
			return "joinPro.jsp";
		}

}
