package service.red;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Date;

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

public class OrderResultAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      System.out.println("장바구니->결제창에서 입력한 내용 주문DB에 insert");
      System.out.println("OrderResultAction Start!!");
      
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
         //쿠폰선택
         int cid = Integer.parseInt(request.getParameter("coupon"));
         System.out.println("OrderResultAction cid ==>"+cid);
         //주문상품총액
         
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
         Order_tbDao orderTb = Order_tbDao.getInstance();
         //주문번호
         int oid = orderTb.countOid();   
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

         //사용한 쿠폰에 사용날짜 업데이트
         CouponDao cu = CouponDao.getInstance();
         int used_couponCheck = cu.checkUsedDate(cid);
         
   
         //넣은 주문의 주문번호와 주문일시 가져옴
         Order order_selct = orderTb.select(oid);
         
         //주문상품정보
         CartnWishDao cartw = CartnWishDao.getInstance();
         ArrayList<CartnWish>list = cartw.select(session_sid);
         //오더디테일 테이블에 넣기
         int result2=0;
         int reduce=0;
         for(int i=0;i<list.size();i++) {
               System.out.println("list.size()==>"+list.size());
            list.get(i);
               System.out.println("list.get(i);==>"+list.get(i));
            int pid = list.get(i).getPid();
            int cwqty = list.get(i).getCwqty();
            
            String option = list.get(i).getCwoption();
            System.out.println("list.get(i).getPid()==>"+list.get(i).getPid());
            System.out.println("pid==>"+pid);
            System.out.println("cwqty==>"+cwqty);
            System.out.println("option==>"+option);
            
            //주문상세에 insert
            OrderDetailDao odd = OrderDetailDao.getInstance();
            result2=odd.insert(oid,pid,cwqty,option);
            //상품재고관리
            ProductDao pd = ProductDao.getInstance();
            reduce = pd.reduce(pid);

            
         }

         
         //주문성공 후 장바구니에 담긴 물건 지우기
         int del = cartw.delete(session_sid);
         
         
         
         
         
         request.setAttribute("list", list);
         request.setAttribute("result",result );
         request.setAttribute("result2",result2 );
         request.setAttribute("order_selct",order_selct );
         request.setAttribute("sum_value", sum_value);
         request.setAttribute("del", del);
         request.setAttribute("order", order);
         request.setAttribute("total_input", total_input);
         request.setAttribute("reduce", reduce);
         
         System.out.println("-----------------");
         System.out.println("list=>"+list);
         System.out.println("result=>"+result);
         System.out.println("result2=>"+result2);
         System.out.println("order_selct=>"+order_selct);
         System.out.println("sum_value=>"+sum_value);
         System.out.println("del=>"+del);
         System.out.println("total_input=>"+total_input);
         System.out.println("reduce=>"+reduce);
         System.out.println("-----------------");

         
         
         
         
      }catch(Exception e) {
         System.out.println("OrderResultAction error!!"+e.getMessage());
      }
      
      return "orderResult.jsp";
   }

}