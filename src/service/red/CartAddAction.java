package service.red;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.red.CartnWish;
import dao.red.CartnWishDao;
import service.CommandProcess;

public class CartAddAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CartAddAction Start!!!");
		System.out.println("장바구니에 넣기 버튼을 누르면 장바구니DB로 insert");
		
		try {
			//선택한 옵션값 받기
			int pid = Integer.parseInt(request.getParameter("pid"));
			String option = request.getParameter("option");
			int howmany = Integer.parseInt(request.getParameter("howmany"));
			
//			int sumDiscount = Integer.parseInt(request.getParameter("sum"));
			
			System.out.println("cart pid check ->" + pid );
			System.out.println("cart howmany check ->" + howmany );
			System.out.println("cart option->" + option );

			HttpSession session = request.getSession(true);
			session.setAttribute("session_sid", session.getAttribute("session_sid"));
			session.setAttribute("session_sname", session.getAttribute("session_sname"));
			session.setAttribute("session_stype", session.getAttribute("session_stype"));
			session.setAttribute("session_semail", session.getAttribute("session_semail"));
			//세션 로그인 유지
			System.out.println("확인1");
			//세션으로 로그인한 회원의 이메일과 회원번호
			String session_semail = (String) session.getAttribute("session_semail");
			int session_sid = (int) session.getAttribute("session_sid");
			System.out.println("확인2");
			System.out.println("session_semail=>"+session_semail);
			System.out.println("session_sid=>"+session_sid);
			
			
			CartnWishDao cartw = CartnWishDao.getInstance();
			int result=0;
			if (option == null) option = " ";
	       
            result=cartw.insert(pid, session_sid, howmany, option);

			
			
					
			System.out.println("--------CartAddAction 파라미터 확인--------------");
			System.out.println("cart pid check ->" + pid );
			System.out.println("cart howmany(number) check ->" + howmany );
			System.out.println("cart option->" + option );
			System.out.println("session_semail=>"+session_semail);
			System.out.println("session_sid=>"+session_sid);
			System.out.println("pid=>"+pid);
			System.out.println("result=>"+result);
			System.out.println("pid=>"+pid);
			System.out.println("-----------------------------------------------");
			//값 보내기
			request.setAttribute("result", result);
			request.setAttribute("pid", pid);
//			request.setAttribute("sumDiscount", sumDiscount);
			
			
		}catch(Exception e) {
			System.out.println("CartAddAction 에러!!=>"+e.getMessage());
			
		
		}
		
		return "cartAddResult.jsp";
	}

}
