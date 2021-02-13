package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.QnaDao;
import service.CommandProcess;

public class QnaCMTDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int qid=Integer.parseInt(request.getParameter("qid"));
			String pageNum=request.getParameter("pageNum");
			QnaDao qd=QnaDao.getInstance();
			int result=qd.deleteCMT(qid);
			
			request.setAttribute("result", result);
			request.setAttribute("qid", qid);
			request.setAttribute("pageNum", pageNum);
			
			String qctg=request.getParameter("qctg");
			if(!(qctg==null || qctg.equals("")))
					request.setAttribute("qctg", qctg);
		} catch (Exception e) {
			System.out.println("QnaCMTDeleteProAction -> "+e.getMessage());
		}
		return "qnaCMTDeletePro.jsp";
	}

}
