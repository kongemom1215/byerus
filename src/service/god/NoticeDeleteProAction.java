package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.NoticeDao;
import service.CommandProcess;

public class NoticeDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int nid=Integer.parseInt(request.getParameter("nid"));
			
			NoticeDao nd=NoticeDao.getInstance();
			int result=nd.delete(nid);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("NoticeDeleteProAction -> "+e.getMessage());
		}
		return "noticeDeletePro.jsp";
	}

}
