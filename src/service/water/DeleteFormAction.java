package service.water;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.water.Qna;
import dao.water.QnaDao;
import service.CommandProcess;

public class DeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("문의 삭제 시작...");
			int sid = Integer.parseInt(request.getParameter("sid"));
	        String pageNum = request.getParameter("pageNum");
	        int qid = Integer.parseInt(request.getParameter("qid"));
	        System.out.println("삭제 pid = "+qid);
			QnaDao qd = QnaDao.getInstance();
			int result = qd.delete(qid);
			System.out.println("삭제 결과=" +result);
			
			
			request.setAttribute("qid", request.getParameter("qid"));
			request.setAttribute("result", result);
			request.setAttribute("sid", sid);
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println("삭제 오류="+e.getMessage());
		}
		return "deleteForm.jsp";
	}
}
