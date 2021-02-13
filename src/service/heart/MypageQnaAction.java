package service.heart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.heart.Board;
import dao.heart.BoardDao;
import service.CommandProcess;

public class MypageQnaAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(true);
			
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals(""))
				pageNum="1";
			int currentPage = Integer.parseInt(pageNum);
			int pageSize=10; //한페이지당 보일 게시글 수
			int blockSize=10; //페이지수
			int startRow=(currentPage-1)*pageSize+1; //시작 번호 //2*10+1=21
			int endRow=startRow+pageSize-1; //끝 번호 //21+10-1=30

			int sid = (int) session.getAttribute("session_sid");
			
			System.out.println("sid ->"+sid);
			BoardDao bd = BoardDao.getInstance();
			Board board = new Board();

			List<Board> list = bd.qna_list(sid,startRow, endRow);
			int cntQna = bd.getCount_Q(sid);
			System.out.println("cntQna->"+cntQna);
			
			int pageCnt=(int)Math.ceil((double)cntQna/pageSize);
			int startPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=startPage+blockSize-1;
			if(endPage>pageCnt)
				endPage=pageCnt;

			request.setAttribute("pageNum", pageNum); //현 페이지 번호
			request.setAttribute("currentPage", currentPage); //현 페이지 번호
			request.setAttribute("blockSize", blockSize); //페이지제한수
			request.setAttribute("pageCnt", pageCnt); //페이지개수
			request.setAttribute("startPage", startPage); //시작 페이지
			request.setAttribute("endPage", endPage); //끝페이지
			request.setAttribute("sid", sid);
			request.setAttribute("board", board);
			request.setAttribute("list", list);	
			request.setAttribute("cntQna", cntQna);
		} catch (Exception e) {
			System.out.println("MypageQnaAction error=> "+e.getMessage());
		} 
		
		return "mypageQna.jsp";
	}

}
