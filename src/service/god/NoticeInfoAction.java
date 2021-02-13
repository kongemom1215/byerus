package service.god;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Notice;
import dao.god.NoticeDao;
import service.CommandProcess;

public class NoticeInfoAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum=request.getParameter("pageNum");
			int nid=Integer.parseInt(request.getParameter("nid"));
			
			NoticeDao nd=NoticeDao.getInstance();
			Notice notice=nd.select(nid);
			String preNotice=nd.getTitle(nid-1);
			String nextNotice=nd.getTitle(nid+1);
			request.setAttribute("nid", nid);
			request.setAttribute("notice", notice);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("preNotice", preNotice);
			request.setAttribute("nextNotice", nextNotice);
			if(notice.getNfile()!=null) {
				String nfile=notice.getNfile().substring(13);
				request.setAttribute("nfile", nfile);
			}
			
		} catch (Exception e) {
			System.out.println("NoticeInfoAction -> "+e.getMessage());
		}
		return "noticeInfo.jsp";
	}

}
