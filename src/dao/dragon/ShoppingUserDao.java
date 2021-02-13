package dao.dragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ShoppingUserDao {
	
	private static ShoppingUserDao instance;
	private ShoppingUserDao() {}
	public static ShoppingUserDao getInstance() {
		if (instance == null) {	
			instance = new ShoppingUserDao();		
			}
		return instance;
	}
	private Connection getConnection() {
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
	
	
	public String covid_19_live(String start, String end) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
        String service_key = "zQQiK%2BrG5QvJ3DIxw2tSn18c0ifbEX009htHuFt%2ByZHTkE6FCTh5heo2xjYkpzlJ9fUPTMvaS9ZPmnalruFNbg%3D%3D";
        String page_num = "1";
        String date_count = "1";
        String start_day = start;
        String end_day = end;
        
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + service_key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(page_num, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(date_count, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(start_day, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(end_day, "UTF-8")); /*검색할 생성일 범위의 종료*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
		/* System.out.println("Response code: " + conn.getResponseCode()); */
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }
	
	
	public int confirm(String semail) throws SQLException {
		Connection conn = null;
		PreparedStatement psmt = null;
		int result = 0;
		
		System.out.println("semail : " + semail);
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM shoppinguser where semail = ?");
			psmt.setString(1, semail);
			ResultSet rs = psmt.executeQuery();
			if(rs.next() == true) result=1;
			else result = 0;
			System.out.println("result : " + result);
		}catch(SQLException e) {
			System.err.println(" * Error 이유 : Database 구문 오류, "+e.getMessage());			
		}catch(Exception e) {
			System.err.println(" * Error 이유 : "+e.getMessage());
		}finally {
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
			
		}
		
		return result;
		
	}
	
}
