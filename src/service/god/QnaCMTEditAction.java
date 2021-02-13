package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Qna;
import dao.god.QnaDao;
import service.CommandProcess;

public class QnaCMTEditAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			int qid=Integer.parseInt(request.getParameter("qid"));
			String pageNum=request.getParameter("pageNum");
			String qcmt=request.getParameter("qcmt");
			
			QnaDao qd=QnaDao.getInstance();
			int result=qd.editCMT(qid, qcmt);
			
			request.setAttribute("qid", qid);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			
			String qctg=request.getParameter("qctg");
			if(!(qctg==null || qctg.equals("")))
					request.setAttribute("qctg", qctg);
		} catch (Exception e) {
			System.out.println("QnaCMTEditAction -> "+e.getMessage());
		}
		return "qnaCMTEditPro.jsp";
	}

}
