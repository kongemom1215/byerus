package dao.red;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderDetailDao {
   private static OrderDetailDao instance;
   private OrderDetailDao() {}
   public static OrderDetailDao getInstance() {
      if(instance == null) {
         instance = new OrderDetailDao();
      }
      return instance;
   }public Connection getConnection() {
      Connection conn = null;
      try {
         Context ctx = new InitialContext();
         DataSource ds = (DataSource)
            ctx.lookup("java:comp/env/jdbc/OracleDB");
         conn = ds.getConnection();
      }catch(Exception e) { 
         System.out.println(e.getMessage());   
         }
      return conn;
   }

//상품정보를 디테일 테이블에 넣기
   public int insert(int oid, int pid, int cwqty, String option) throws SQLException {
      int result2= 0;
      Connection conn= null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "insert into ORDERDETAIL(oid,pid,dqty,poption) values(?,?,?,?)";
         System.out.println("OrderDetailDao insert 확인1");
      try {
         
         conn=getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, oid);
         pstmt.setInt(2, pid);
         pstmt.setInt(3, cwqty);
         pstmt.setString(4, option);
         
         
         result2=pstmt.executeUpdate();
         System.out.println("OrderDetailDao insert 확인2");
         
         
      }catch(Exception e) {
         System.out.println("OrderDetailDao insert error!!!==>"+e.getMessage());
      }finally {
         if(conn != null) conn.close();
         if(pstmt != null)pstmt.close();
         if(rs != null) rs.close();
      }
      
      
      
      
      
      
      
      
      
      
      return result2;
   }
   

   
   
}