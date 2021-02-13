package service.water;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.Qna;
import dao.water.QnaDao;
import service.CommandProcess;

public class UpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  int sid = Integer.parseInt(request.getParameter("sid"));
		  String pageNum=request.getParameter("pageNum");
		  int qid = Integer.parseInt(request.getParameter("qid"));
		  String sname = request.getParameter("sname");
		  
		 System.out.println("수정버튼 qid = "+qid);
		try {
			
			
			  QnaDao qd = QnaDao.getInstance();
			  Qna qna = qd.select(qid);
			  
			  request.setAttribute("sname", sname);
			  request.setAttribute("sid", sid);
			  request.setAttribute("pageNum", pageNum); 
			  request.setAttribute("qna", qna);
			  System.out.println("UpdateFormAction ->"+qna.getQcontent());
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "updateForm.jsp";
	}

}
