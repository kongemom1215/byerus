package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Qna;
import dao.god.QnaDao;
import service.CommandProcess;

public class QnaInfoAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int qid=Integer.parseInt(request.getParameter("qid"));
			String pageNum=request.getParameter("pageNum");
			String qctg=request.getParameter("qctg");
			
			QnaDao qd=QnaDao.getInstance();
			Qna qna=qd.select(qid);
			
			request.setAttribute("qid", qid);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("qna", qna);
			request.setAttribute("qctg", qctg);
			if(qna.getQfile()!=null) {
				String qfile=qna.getQfile().substring(10);
				request.setAttribute("qfile", qfile);
			}
		} catch (Exception e) {
			System.out.println("QnaInfoAction -> "+e.getMessage());
		}
		return "qnaInfo.jsp";
	}

}
