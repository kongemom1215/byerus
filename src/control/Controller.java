package control;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;

// @WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//     /list.do        service.ListAction
	//     /updateForm.do  service.UpdateFormAction
	private Map<String,    Object> commandMap =  new HashMap<String, Object>(); 
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
	   	//web.xml에서 propertyConfig에 해당하는 init-param 의 값을 읽어옴
		String props = config.getInitParameter("config");
//		System.out.println("Controller init props->"+props);
		//명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		try {
			String configFilePath = config.getServletContext().getRealPath(props);
//			System.out.println("Controller init configFilePath->"+configFilePath);
			f = new FileInputStream(configFilePath);
			// command.properties파일의 정보를  Properties객체에 저장
			pr.load(f);
			
		} catch (IOException  e) {
			 throw new ServletException(e);
		} finally {
		  if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		
		//Iterator객체는 Enumeration객체를 확장시킨 개념의 객체	
		// /list.do /updateForm.do
		Iterator keyIter = pr.keySet().iterator();
		 //객체를 하나씩 꺼내서 그 객체명으로 Properties객체에 저장된 객체에 접근
	     while( keyIter.hasNext() ) {
	          String command = (String)keyIter.next();    // /list.do /updateForm.do
              // className <= service.ListAction
	          // service.ListAction   service.UpdateFormAction
	          String className = pr.getProperty(command); 
	          // ListAction      la = new ListAction();
	          // UpdateFormAction ufa = new UpdateFormAction();
	          try {
	               Class commandClass = Class.forName(className);//해당 문자열을 클래스로 만든다.
	               Object commandInstance = commandClass.newInstance();//해당클래스의 객체를 생성
	               commandMap.put(command, commandInstance); // Map객체인 commandMap에 객체 저장
	               // commandMap   command,           commandInstance
	               //               /list.do           service.ListAction
	               //               /updateForm.do     service.UpdateFormAction
	          } catch (Exception e) {
	               throw new ServletException(e);
	          }		
		
	     }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   requestPro(request, response);
	}

	//시용자의 요청을 분석해서 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		String view = null;
	    CommandProcess com=null;
	    String command = request.getRequestURI();
	    
	   
	    
	    try {	
//				System.out.println("requestPro command 1=>"+ command);  // /och16/list.do
	              command = command.substring(request.getContextPath().length());
	           // ListAction      com = new ListAction();      
	          com = (CommandProcess)commandMap.get(command);  
//			  System.out.println("requestPro  command 2=>"+ command);  // /och16/com
//			  System.out.println("requestPro com=> "+ com);            // /och16/com
			  // service.ListAction.requestPro(request, response)
	          view = com.requestPro(request, response);                // list.jsp
//			  System.out.println("requestPro view=> "+ view);          // /och16/com
			  
	    } catch(Throwable e) {  
	    	throw new ServletException(e); 
	    } 
	    
	    if(command.contains("ajaxDiscount")) {
	    	System.out.println("ajaxDiscount controller start!!!");
	    	String total = request.getAttribute("total").toString();
	    	System.out.println("con_total : " + total);
	    	PrintWriter pw = response.getWriter();
	    	pw.write(total);
	    	pw.flush();
	    } else if (command.contains("ajaxSemail")) {
			System.out.println("ajaxSemail ajax String->"+command);  // /ch16/list.do
			String retStr =  (String) request.getAttribute("retStr");
			PrintWriter pw = response.getWriter();
			pw.write(retStr);
			pw.flush();

	    } else {	
	    RequestDispatcher dispatcher =   request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
	    }
		
	}
	
}
