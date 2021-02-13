package service.heart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.Board;
import dao.heart.BoardDao;
import dao.heart.Order_Join;
import dao.heart.Order_tbDao;
import service.CommandProcess;

public class ReviewWrite implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(true);
			
			int sid = (int) session.getAttribute("session_sid");
			
				System.out.println("sid ->"+sid);
				Order_tbDao oddao = Order_tbDao.getInstance();
				Order_Join orderjoin = new Order_Join();
				
			int oid = Integer.parseInt(request.getParameter("oid"));
			System.out.println("oid->"+oid);
			
		//	BoardDao bd = BoardDao.getInstance();
		//	Board board = new Board();
		//	board =bd.reviewform(sid,oid,pid);
			
			orderjoin = oddao.select2(sid,oid);
			
			request.setAttribute("sid", sid);	 
			request.setAttribute("orderjoin", orderjoin);
			request.setAttribute("oid", oid);			
		//	request.setAttribute("board", board);
		} catch (Exception e) {
			System.out.println("ReviewWrite Error->"+e.getMessage());
		}
		return "reviewWrite.jsp";
	}

}
