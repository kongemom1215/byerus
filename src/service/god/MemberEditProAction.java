package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ShoppingUser;
import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class MemberEditProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			int sid=Integer.parseInt(request.getParameter("sid"));
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			ShoppingUser shoppinguser=new ShoppingUser();
			//이름,이메일,연락처,우편번호,주소,마켓팅동의여부
			shoppinguser.setSname(request.getParameter("sname"));
			shoppinguser.setScontact(request.getParameter("scontact"));
			shoppinguser.setSpost(Integer.parseInt(request.getParameter("spost")));
			shoppinguser.setSaddress(request.getParameter("saddress"));
			shoppinguser.setSagree(request.getParameter("sagree"));
			int result=sd.updateUser(shoppinguser, sid);
			request.setAttribute("result", result);
			request.setAttribute("sid", sid);
			
			String option=request.getParameter("option");
			String search_value=request.getParameter("search_value");
			if(!(option==null || option.equals(""))) {
				request.setAttribute("option", option);
				request.setAttribute("search_value", search_value);
			}
			String pageNum=request.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
		}catch (Exception e) {
			System.out.println("MemberEditPro -> "+e.getMessage());
		}
		return "memberEditPro.jsp";
	}

}
