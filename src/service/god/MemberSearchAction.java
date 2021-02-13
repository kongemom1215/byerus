package service.god;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.ShoppingUser;
import dao.god.ShoppingUserDao;
import service.CommandProcess;

public class MemberSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum=request.getParameter("pageNum");
			String option=request.getParameter("option");
			String search_value=request.getParameter("search_value");
			System.out.println("pageNum-> "+pageNum);
			if(pageNum==null || pageNum.equals("pageNum"))
				pageNum="1";
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=10, blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=startRow+pageSize-1;
			System.out.println("startRow-> "+startRow);
			System.out.println("endRow-> "+endRow);
			ShoppingUserDao sd=ShoppingUserDao.getInstance();
			List<ShoppingUser> userlist=sd.searchlist(option, search_value, startRow, endRow);
			System.out.println("userlist.size->"+userlist.size());
			int totalUser=sd.searchtotal(option, search_value);
			System.out.println("totalUser -> "+totalUser);
			System.out.println("여기?");
			int pageCnt=(int)Math.ceil((double)totalUser/pageSize);
			int startPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=startPage+blockSize-1;
			if(endPage>pageCnt)
				endPage=pageCnt;
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage); //현 페이지 번호
			request.setAttribute("userlist", userlist); //유저목록
			request.setAttribute("totalUser", totalUser); //검색된 회원 수
			request.setAttribute("blockSize", blockSize); //페이지제한수
			request.setAttribute("pageCnt", pageCnt); //페이지개수
			request.setAttribute("startPage", startPage); //시작 페이지
			request.setAttribute("endPage", endPage); //끝페이지
			request.setAttribute("option", option);
			request.setAttribute("search_value", search_value);
			
		} catch (Exception e) {
			System.out.println("MemberSearchAction -> "+e.getMessage());
		}
		return "memberSearchPage.jsp";
	}

}
