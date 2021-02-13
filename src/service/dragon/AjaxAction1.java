package service.dragon;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dragon.ShoppingUserDao;
import service.CommandProcess;
public class AjaxAction1 implements CommandProcess {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		
		String semail = request.getParameter("semail");
		
		System.out.println("AjaxAction1 Start...");
		System.out.println("AjaxAction1 semail-->"+semail);
		System.out.println(semail);

		String retStr = "";
		
		boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",semail.trim());

			ShoppingUserDao shoppinguserdao = ShoppingUserDao.getInstance();
			
			int result;
			System.out.println("AjaxAction1 try before ...");
		
			try {
				result = shoppinguserdao.confirm(semail);
				System.out.println("AjaxAction1 result->"+result);
			
				if (b) {
					result = shoppinguserdao.confirm(semail);
					if(result == 0){
						retStr = "사용할 수 있는 아이디 입니다.";
					}else{
						retStr = "이미 있는 아이디 입니다. 다른 것을 사용해주십시오.";
					}
				} else {
					retStr = "이메일이 아닙니다.";
				}
				
				if (semail.equals(null) || semail.equals("")) {
					retStr = "";
					}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		System.out.println("AjaxAction1 retStr->"+retStr);
		request.setAttribute("retStr", retStr);

		return retStr; // 의미는 없는데 양식에 맞춰서 쓰는거라고 함.
	}
	
}