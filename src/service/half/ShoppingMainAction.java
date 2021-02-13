package service.half;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.half.Product;
import dao.half.ProductDao;
import service.CommandProcess;

public class ShoppingMainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("session_sid", session.getAttribute("session_sid"));
		session.setAttribute("session_sname", session.getAttribute("session_sname"));
		session.setAttribute("session_stype", session.getAttribute("session_stype"));
		session.setAttribute("session_semail", session.getAttribute("session_semail"));
		session.setAttribute("parray", session.getAttribute("parray"));
		
		
		ProductDao pd = ProductDao.getInstance();

		try {

			// 하단 디스플레이 뭘로 할지 결정함
			request.setCharacterEncoding("UTF-8");
			String displayoption = request.getParameter("display_select");

			if (displayoption == null || displayoption.equals("")) {
				displayoption = "hot";
			}
			System.out.println("displayoption -> " + displayoption);

			String cate = request.getParameter("cate");

			if (cate == null || cate.equals("")) {
				cate = "total";
			}
			System.out.println("cate -> " + cate);

			// 판매량순 베스트3 정렬, 기본 12개 정렬

			List<Product> bestproducts = pd.bestproducts();
			request.setAttribute("BESTPRODUCTS", bestproducts);
			System.out.println("ShoppingMainAction bestproducts.size()->" + bestproducts.size());

			// 하단 디스플레이 정렬 -- 디폴트는 판매량
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 12, blockSize = 4;
			int startRow = (currentPage - 1) * pageSize + 1; // 첫글
			int endRow = startRow + pageSize - 1; // 끝글

			int totCnt = 0;

			if (cate.equals("total")) {
				totCnt = pd.getTotalCnt();
			} else if (cate.equals("cheon")) {
				totCnt = pd.getCheonCnt();
			} else if (cate.equals("sodok")) {
				totCnt = pd.getSodokCnt();
			} else if (cate.equals("alco")) {
				totCnt = pd.getAlcoCnt();
			} else if (cate.equals("mask")) {
				totCnt = pd.getMaskCnt();
			} else if (cate.equals("bangdok")) {
				totCnt = pd.getBangdokCnt();
			} else if (cate.equals("sejung")) {
				totCnt = pd.getSejungCnt();
			} else if (cate.equals("home")) {
				totCnt = pd.getHomeCnt();
			} else if (cate.equals("etc")) {
				totCnt = pd.getEtcCnt();
			} else if (cate.equals("sale")) {
				totCnt = pd.getSaleCnt();
			} else {
				totCnt = pd.getTotalCnt();
			}

			int startNum = totCnt - startRow + 1;

			List<Product> hotlist = pd.hotlist(startRow, endRow, displayoption, cate);

			int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if (endPage > pageCnt) {
				endPage = pageCnt;
			}

			request.setAttribute("hotlist", hotlist);

			request.setAttribute("totCnt", pd.getTotalCnt());
			request.setAttribute("cheonCnt", pd.getCheonCnt());
			request.setAttribute("alcoCnt", pd.getAlcoCnt());
			request.setAttribute("sodokCnt", pd.getSodokCnt());
			request.setAttribute("maskCnt", pd.getMaskCnt());
			request.setAttribute("bangdokCnt", pd.getBangdokCnt());
			request.setAttribute("sejungCnt", pd.getSejungCnt());
			request.setAttribute("homeCnt", pd.getHomeCnt());
			request.setAttribute("etcCnt", pd.getEtcCnt());
			request.setAttribute("saleCnt", pd.getSaleCnt());

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			System.out.println("-----------------------------------------------"); // /och16/list.do
			System.out.println("startNum-->" + startNum); // /och16/list.do
			System.out.println("totCnt-->" + totCnt); // /och16/list.do
			System.out.println("currentPage-->" + currentPage); // /och16/list.do
			System.out.println("blockSize-->" + blockSize); // /och16/list.do
			System.out.println("pageSize-->" + pageSize); // /och16/list.do
			System.out.println("pageCnt-->" + pageCnt); // /och16/list.do
			System.out.println("startPage-->" + startPage); // /och16/list.do
			System.out.println("endPage-->" + endPage); // /och16/list.do

			request.setAttribute("display_select", displayoption);
			request.setAttribute("cate", cate);

		} catch (Exception e) {
			System.out.println("shoppingmainaction error :" + e.getMessage());
		}

		return "ShoppingMain.jsp";
	}

}
