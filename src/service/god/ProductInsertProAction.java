package service.god;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.god.Product;
import dao.god.ProductDao;
import service.CommandProcess;

public class ProductInsertProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			int maxSize=10*1024*1024; // 10MB
			String rootPath=request.getServletContext().getRealPath("/");
			String savePath=rootPath+"pdimg/";
			MultipartRequest multi=new MultipartRequest(request,savePath,maxSize,"utf-8", new DefaultFileRenamePolicy());
			// 파일 업로드 완료
			Enumeration en=multi.getFileNames();
			// 업로드된 파일 이름을 반환
			List<String> filenames=new ArrayList<String>();
			while(en.hasMoreElements()){
				String filename1=(String)en.nextElement(); //파라미터명 ex) pthumbimg->col5->col4
				String filename=multi.getFilesystemName(filename1); //이미지명 ex)img1.png, img2.png
				String original=multi.getOriginalFileName(filename1); //전송 전 원래 이름 ex) img1.png
				String type=multi.getContentType(filename1); //전송 전 파일의 내용 타입 ex) image/png
				File file=multi.getFile(filename1);  //전송된 파일 속성이 file인 태그의 name 속성값을 이용해 객체 생성
				filenames.add(filename);
			}
			Product product=new Product();
			product.setPname(multi.getParameter("pname"));
			product.setPtype(multi.getParameter("ptype"));
			product.setPprice(Integer.parseInt(multi.getParameter("pprice")));
			product.setPdiscount(Integer.parseInt(multi.getParameter("pdiscount")));
			product.setPpublic(Integer.parseInt(multi.getParameter("ppublic")));
			product.setPoption(multi.getParameter("poption"));
			product.setPinventory(Integer.parseInt(multi.getParameter("pinventory")));
			
			product.setPthumbimg("./pdimg/"+filenames.get(0));
			if(filenames.get(1)==null)
				product.setCol3(null);
			else
				product.setCol3("./pdimg/"+filenames.get(1));
			
			if(filenames.get(2)==null)
				product.setCol2(null);
			else
				product.setCol2("./pdimg/"+filenames.get(2));
			
			if(filenames.get(3)==null)
				product.setCol1(null);
			else
				product.setCol1("./pdimg/"+filenames.get(3));
			
			
			ProductDao pd=ProductDao.getInstance();
			int result=pd.insert(product);
			System.out.println("result2 : "+result);
			
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println("ProductInsertProAction -> "+e.getMessage());
		}
		return "productInsertPro.jsp";
	}

}
