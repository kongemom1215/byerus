package service.dragon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dragon.Search;
import dao.dragon.SearchDao;
import service.CommandProcess;

public class SearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("-- service.dragon.SearchAction --");
		
		try {
		HttpSession session = request.getSession(true);
		session.setAttribute("session_sid", session.getAttribute("session_sid"));
		session.setAttribute("session_sname", session.getAttribute("session_sname"));
		session.setAttribute("session_stype", session.getAttribute("session_stype"));
		session.setAttribute("session_semail", session.getAttribute("session_semail"));
		} catch (Exception e) {
			System.out.println("오류 : "+ e.getMessage());
		}
		
		String search_text = request.getParameter("search_text");
		SearchDao searchdao = SearchDao.getInstance();
		
		String shopping_select = request.getParameter("shopping_select");
		request.setAttribute("shopping_select", shopping_select);
		try {
		if (shopping_select.equals("sell_hit")) {
			request.setAttribute("selected1", "selected");
		} else if (shopping_select.equals("pregdate")) {
			request.setAttribute("selected2", "selected");
		} else if (shopping_select.equals("price_high")) {
			request.setAttribute("selected3", "selected");
		} else if (shopping_select.equals("price_low")) {
			request.setAttribute("selected4", "selected");
		} else {
		}
		} catch (Exception e) {
			System.out.println("오류 : "+ e.getMessage());
		}
		
//		System.out.println("select box : " + shopping_select);
		
		List<Search> main_img = new ArrayList<Search>();
		main_img = searchdao.compare(search_text, shopping_select);
		
		request.setAttribute("search_text", search_text);
		request.setAttribute("main_img", main_img);
 		request.setAttribute("page", request.getParameter("page"));
		
		int select_page = 0;
		try {
		select_page = Integer.parseInt(request.getParameter("page"))-1;
		System.out.println(select_page);
		} catch (Exception e) {
		select_page = 0;
		}
		request.setAttribute("select_page", select_page);
		
		int page_full_num = main_img.size();
		request.setAttribute("page_full_num", page_full_num);
		
		System.out.println("확인용 : " + page_full_num);
		
		double page_num_1 = (double)page_full_num/16;
		
		if (page_num_1 <= 0) {
			page_num_1 = 0;
		} else if (page_num_1 >0) {
			page_num_1 = Math.ceil(page_num_1);
		}
		
		for (int i = 1; i <= page_num_1; i += 10) {
			System.out.println(i);
		}
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 16, blockSize = 10;
		int startRow = (currentPage - 1) * pageSize + 1; // 첫글
		int endRow = startRow + pageSize - 1; // 끝글
		int totCnt = (int)page_full_num; // 총 갯수
		int startNum = totCnt - startRow + 1;
		int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
		int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
		int endPage = startPage + blockSize - 1;
		if (endPage > pageCnt) {
			endPage = pageCnt;
		}
		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startNum", startNum);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		return "search.jsp";
	}

}
