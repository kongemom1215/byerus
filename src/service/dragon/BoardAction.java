package service.dragon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dragon.Board;
import dao.dragon.BoardDao;
import dao.water.Notice;
import dao.water.NoticeDao;
import dao.water.Qna;
import dao.water.QnaDao;
import dao.water.Review;
import dao.water.ReviewDao;
import service.CommandProcess;

public class BoardAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("-- service.dragon.BoardAction --");
		 request.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("session_sid", session.getAttribute("session_sid"));
		session.setAttribute("session_sname", session.getAttribute("session_sname"));
		session.setAttribute("session_stype", session.getAttribute("session_stype"));
		session.setAttribute("session_semail", session.getAttribute("session_semail"));
		
		String pageNum = "";	
		int currentPage = 0;
		int pageSize = 0, blockSize = 0;
		int startRow = 0;
		int endRow = 0;
		int totCnt = 0;
		int startNum = 0;
		int pageCnt = 0;
		int startPage = 0;
		int endPage = 0;	
		
		String type = request.getParameter("type");
		
		BoardDao boarddao = BoardDao.getInstance();
		
		QnaDao qna = QnaDao.getInstance();
		ReviewDao rd = ReviewDao.getInstance();
		NoticeDao nd = NoticeDao.getInstance();
		
		List<Board> list = new ArrayList<Board>();
		
		try {
		if (request.getParameter("nid") != null) {
			
		}
		
		} catch (Exception e) {
			System.out.println("오류 : "+ e.getMessage());
		}
		
		try {
		if (type == null || type == "") {
			type="notice";
		}
			
		if (type.equals("notice")) {
			try {
				try {
					NoticeDao noticedao = NoticeDao.getInstance();
					noticedao.readCount(Integer.parseInt(request.getParameter("nid")));
				} catch (Exception e) {
					// TODO: handle exception
				}
				pageNum = request.getParameter("pageNum");	
				if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
				currentPage = Integer.parseInt(pageNum);	
				pageSize  = 10;
				blockSize = 10;
				startRow = (currentPage - 1) * pageSize + 1;
				endRow =  startRow + pageSize - 1;
				totCnt = nd.getTotalCnt();
				startNum = totCnt - startRow + 1;   // 38
				List<Notice> list_notice = nd.list(startRow , endRow); 
				System.out.println("ListAction list.size()-->" + list_notice.size());        // /och16/list.do
				 System.out.println("ListAction list.get(0).getContent()-->" + list_notice.get(0).getNcontent()); // /och16/list.do
				pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				endPage = startPage + blockSize -1;	
				System.out.println("endpage : " + endPage);
				if (endPage > pageCnt) endPage = pageCnt;	
				
				
				request.setAttribute("list",  list_notice);
				
				request.setAttribute("sid", request.getParameter("sid"));
				
				request.setAttribute("nid", request.getParameter("nid"));
				request.setAttribute("ntitle", request.getParameter("ntitle"));
				request.setAttribute("ncontent", request.getParameter("ncontent"));
				request.setAttribute("ndate", request.getParameter("ndate"));
				request.setAttribute("nfile", request.getParameter("nfile"));
				request.setAttribute("nhit", request.getParameter("nhit"));
				request.setAttribute("nid", request.getParameter("nid"));
				
			} catch (Exception e) {
				
			}
		} else if (type.equals("review")) {
			try {
				try {
					ReviewDao reviewdao = ReviewDao.getInstance();
					reviewdao.readCount(Integer.parseInt(request.getParameter("rid")));
				} catch (Exception e) {
					// TODO: handle exception
				}
				pageNum = request.getParameter("pageNum");	
				if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
				currentPage = Integer.parseInt(pageNum);	
				pageSize  = 10;
				blockSize = 10;
				startRow = (currentPage - 1) * pageSize + 1;
				endRow =  startRow + pageSize - 1;
				totCnt = rd.getTotalCnt();
				startNum = totCnt - startRow + 1;   // 38
				List<Review> list_review = rd.list(startRow , endRow); 
				System.out.println("ListAction list.size()-->" + list_review.size());        // /och16/list.do
				 System.out.println("ListAction list.get(0).getContent()-->" + list_review.get(0).getRcontent()); // /och16/list.do
				pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				endPage = startPage + blockSize -1;	
				if (endPage > pageCnt) endPage = pageCnt;	
				
				
				request.setAttribute("list",  list_review);
				
				request.setAttribute("sid", request.getParameter("sid"));
				
				request.setAttribute("rid", request.getParameter("rid"));
				request.setAttribute("rwriter", request.getParameter("rwriter"));
				request.setAttribute("rtitle", request.getParameter("rtitle"));
				request.setAttribute("rcontent", request.getParameter("rcontent"));
				request.setAttribute("rimg", request.getParameter("rimg"));
				request.setAttribute("rdate", request.getParameter("rdate"));
				request.setAttribute("rhit", request.getParameter("rhit"));
				request.setAttribute("rcmt", request.getParameter("rcmt"));
				request.setAttribute("rcmtdate", request.getParameter("rcmtdate"));
				request.setAttribute("odate", request.getParameter("odate"));
				request.setAttribute("pid", request.getParameter("pid"));
				
				int reivew_num = Integer.parseInt(request.getParameter("reivew_num"));
				
				if (request.getParameter("reivew_num") != null) {
					Board board = new Board();
					board = boarddao.select_reivew(reivew_num);
					request.setAttribute("sid", session.getAttribute("session_sid"));
					request.setAttribute("rid", board.getRid());
					request.setAttribute("rwriter", board.getRwriter());
					request.setAttribute("rtitle", board.getRtitle());
					request.setAttribute("rcontent", board.getRcontent());
					request.setAttribute("rimg", board.getRimg());
					request.setAttribute("rdate", board.getRdate());
					request.setAttribute("rhit", board.getRhit());
					request.setAttribute("rcmt", board.getRcmt());
					request.setAttribute("rcmtdate", board.getRcmtdate());
					request.setAttribute("odate", board.getOdate());
					request.setAttribute("pid", board.getPid());
					
				}
			} catch (Exception e) {
				
			}
		} else if (type.equals("qna")) {
			try {
				pageNum = request.getParameter("pageNum");	
				if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
				currentPage = Integer.parseInt(pageNum);	
				pageSize  = 10;
				blockSize = 10;
				startRow = (currentPage - 1) * pageSize + 1;
				endRow =  startRow + pageSize - 1;
				totCnt = qna.getTotalCnt();
				startNum = totCnt - startRow + 1;   // 38
				List<Qna> list_qna = qna.list(startRow , endRow); 
				System.out.println("ListAction list.size()-->" + list_qna.size());        // /och16/list.do
				System.out.println("ListAction list.get(0).getContent()-->" + list_qna.get(0).getQcontent());        // /och16/list.do
				pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				endPage = startPage + blockSize -1;	
				if (endPage > pageCnt) endPage = pageCnt;	
				
				request.setAttribute("list",  list_qna);
				
				request.setAttribute("sid", request.getParameter("sid"));
				request.setAttribute("qid", request.getParameter("qid"));
				request.setAttribute("sname", request.getParameter("sname"));
				request.setAttribute("qdate", request.getParameter("qdate"));
				request.setAttribute("qctg", request.getParameter("qctg"));
				request.setAttribute("qcontent", request.getParameter("qcontent"));
				request.setAttribute("qcmt", request.getParameter("qcmt"));
				request.setAttribute("qcmtdate", request.getParameter("qcmtdate"));
				request.setAttribute("qfile", request.getParameter("qfile"));
			
				int qid_num = Integer.parseInt(request.getParameter("qid_num"));
				
				if (request.getParameter("qid_num") != null) {
					Board board = new Board();
					board = boarddao.select_qna(qid_num);
					request.setAttribute("sid", session.getAttribute("session_sid"));
					request.setAttribute("qid", qid_num);
					request.setAttribute("sname", board.getSname());
					request.setAttribute("qdate", board.getQdate());
					request.setAttribute("qctg", board.getQctg());
					request.setAttribute("qcontent", board.getQcontent());
					request.setAttribute("qcmt", board.getQcmt());
					request.setAttribute("qcmtdate", board.getQcmtdate());
					request.setAttribute("qfile", board.getQfile());
					
				}
				
			} catch (Exception e) {
				
			}
		}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("type", request.getParameter("type"));
		
		request.setAttribute("totCnt",   totCnt);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startNum", startNum);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		return "board.jsp";
	}

}
