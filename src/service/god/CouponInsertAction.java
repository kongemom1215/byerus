package service.god;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.god.Coupon;
import dao.god.CouponDao;
import service.CommandProcess;

public class CouponInsertAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			int maxSize=10*1024*1024; 
			String rootPath=request.getServletContext().getRealPath("/");
			String savePath=rootPath+"cpimg/";
			MultipartRequest multi=new MultipartRequest(request,savePath,maxSize,"utf-8", new DefaultFileRenamePolicy());
			Enumeration en=multi.getFileNames();
			List<String> filenames=new ArrayList<String>();
			while(en.hasMoreElements()){
				String filename1=(String)en.nextElement(); 
				String filename=multi.getFilesystemName(filename1);
				String original=multi.getOriginalFileName(filename1);
				String type=multi.getContentType(filename1);
				File file=multi.getFile(filename1);
				filenames.add(filename);
			}
			
			CouponDao cd=CouponDao.getInstance();
			
			String usertype=multi.getParameter("usertype");
			int cdiscount=Integer.parseInt(multi.getParameter("cdiscount"));
			String cstartdate=multi.getParameter("cstartdate").replace("-", "");
			String cenddate=multi.getParameter("cenddate").replace("-", "");
			String couponimg="./cpimg/"+filenames.get(0);
			
			String[] selectSids=null;
			if(usertype.equals("part")) {
				String sids=multi.getParameter("sids");
				selectSids=sids.split(",");
			}
			else if(usertype.equals("all")) {
				selectSids=cd.selectSid();
			}
			
			int result=cd.makeCoupons(selectSids, cstartdate, cenddate, cdiscount, couponimg);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("CouponInsertAction -> "+e.getMessage());
		}
		return "couponInsertPro.jsp";
	}

}
