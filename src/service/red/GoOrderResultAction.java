package service.red;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.red.CartnWish;
import dao.red.CartnWishDao;
import dao.red.CouponDao;
import dao.red.Order;
import dao.red.OrderDetailDao;
import dao.red.Order_tbDao;
import dao.red.ProductDao;
import service.CommandProcess;

public class GoOrderResultAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      System.out.println("바로구매->결제창에서 입력한 내용 주문DB에 insert");
      System.out.println("GoOrderResultAction Start!!");
      
      try {
         //response.setCharacterEncoding("UTF-8");
         request.setCharacterEncoding("utf-8");
         //주문자정보
            String buyerName= request.getParameter("buyerName");
            int spost= Integer.parseInt(request.getParameter("spost"));
            String saddress= request.getParameter("saddress");
            String buyerTel= request.getParameter("buyerTel");
            String buyerEmail =(String)request.getSession().getAttribute("session_semail");
            int session_sid = (int) request.getSession().getAttribute("session_sid");
            System.out.println("확인1");   
         //신용카드정보
         String credit = request.getParameter("credit_chk");
         String creditNum = request.getParameter("creditNum");
         String creditPass =request.getParameter("creditPass");   
            System.out.println("확인2");
         //은행
         String bank = request.getParameter("bank"); 
         String backNum = request.getParameter("backNum");
            System.out.println("확인3");
         //상품정보
         int pid = Integer.parseInt(request.getParameter("pid"));
            System.out.println("확인4");      
         String option = request.getParameter("option");
            System.out.println("확인5");
         int howmany = Integer.parseInt(request.getParameter("howmany"));
            System.out.println("확인6");   
         //주문총액   
         double sum_value =Double.parseDouble(request.getParameter("sum_value"));
            System.out.println("OrderResultAction sum_value ==>"+sum_value);
         //결제 총액
         int total_input = Integer.parseInt(request.getParameter("total_input"));
         //double totalmsg = Double.parseDouble(request.getParameter("msg"));
            System.out.println("OrderResultAction total_input ==>"+total_input);   
         //택배비
         int sumPost =0;
         if(sum_value>30000) {
               sumPost = 0;
         }else {
               sumPost = 3000;
         }
            System.out.println("확인8");      
         
            Order_tbDao orderTb = Order_tbDao.getInstance();
            //주문번호
            int oid = orderTb.countOid();   
            //쿠폰선택
            int cid = Integer.parseInt(request.getParameter("coupon"));
            System.out.println("OrderResultAction cid ==>"+cid);
            //주문테이블에 들어갈 값
            Order order = new Order();
            order.setOid(oid);
            order.setSid(session_sid);
            order.setCid(cid);
            order.setOname(buyerName);
              order.setOpost(spost);
              order.setOaddress(saddress);
            order.setOcontact(buyerTel);
            order.setOamount(total_input);//결제총액
            order.setOdeliverey(sumPost);//택배비
            if(backNum == null || bank.equals("해당사항없음")) {
               //신용카드 결제
               order.setOpay(2); //신용카드결제
               order.setOstate(2); //결제완료
            }else if(credit.equals("해당사항없음") || creditNum == null || creditPass == null  ) {
               //무통장 결제
               order.setOpay(1); //무통장결제
               order.setOstate(1); //결제대기
            }
            
         
         //Order DB에 넣기
         int result = orderTb.insert(order);
         
         //상품재고관리
         ProductDao pd = ProductDao.getInstance();
         int reduce = pd.reduce(pid);
         
         //사용한 쿠폰에 사용날짜 업데이트
         CouponDao cu = CouponDao.getInstance();
         int used_couponCheck = cu.checkUsedDate(cid);

         
         //넣은 주문의 주문번호와 주문일시 가져옴
         Order order_selct = orderTb.select(oid);
         
         
         //orderDetail... 
         OrderDetailDao odd = OrderDetailDao.getInstance();
         int result2 = odd.insert(oid, pid, howmany, option);
         
         
         
         
         
         request.setAttribute("result",result );
         request.setAttribute("result2",result2 );
         request.setAttribute("order_selct",order_selct );
         request.setAttribute("reduce", reduce);
         request.setAttribute("order", order);
         
         System.out.println("-----------------");
         System.out.println("result=>"+result);
         System.out.println("result2=>"+result2);
         System.out.println("order_selct=>"+order_selct);
         System.out.println("reduce=>"+reduce);
         
         
         
         
      }catch(Exception e) {
         System.out.println("GoOrderResultAction error!!"+e.getMessage());
      }

      return "orderResult.jsp";
   }

}