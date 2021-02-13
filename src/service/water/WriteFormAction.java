package service.water;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

public class WriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNum = request.getParameter("pageNum");
		System.out.println("WriteFormAction2 시작...");
		try {
			
			request.setAttribute("pid", request.getParameter("pid"));
			request.setAttribute("pageNum", pageNum);
				} catch (Exception e) {
					
				}
		return "writeForm.jsp";
	}

}
