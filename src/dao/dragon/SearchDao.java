// compare - if (isInitialSound(search_text.charAt(0))) 통해서 search_text (검색할 단어) 가 자음인지 판단

package dao.dragon;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;

public class SearchDao {
	private static SearchDao instance;
	private SearchDao() {}
	public static SearchDao getInstance() {
		if (instance == null) {	
			instance = new SearchDao();		
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
	
	private static final long serialVersionUID = 1L;
	
	List<Search> al_initial = new ArrayList<Search>();
	
	private static final char HANGUL_BEGIN_UNICODE = 44032; // 가
	private static final char HANGUL_LAST_UNICODE = 55203; // 힣
	private static final char HANGUL_BASE_UNIT = 588;//각자음 마다 가지는 글자수
	
	// 유니코드 10진수로 표현했을 때, ㄱ ㄲ ㄴ … ㅍ ㅎ 각 자음이 가지는 수가 588 가지
	//자음
	
	private static final char[] INITIAL_SOUND = { 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 
			'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
			'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ',
			'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };

	// 해당 문자가 INITIAL_SOUND 인지 검사
	
	private static boolean isInitialSound(char searchar) {
		for(char c:INITIAL_SOUND) {
			if(c == searchar) {
				return true;
			}
		}
		return false;
	}
	 
	// 자음 얻기
	
	private static char getInitialSound(char c) {
		int hanBegin = (c - HANGUL_BEGIN_UNICODE);
		int index = hanBegin / HANGUL_BASE_UNIT;
		return INITIAL_SOUND[index];
	}
	 
	// 한글 검사
	 private static boolean isHangul(char c) {
			return HANGUL_BEGIN_UNICODE <= c && c <= HANGUL_LAST_UNICODE;
	}
		 
	 public void init(String sql) throws SQLException {
		 		al_initial = new ArrayList<Search>();
				Connection conn = null;	
				PreparedStatement pstmt= null;
				ResultSet rs = null;
				System.out.println("sql문 : " + sql);
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						do {
						Search search = new Search();
						search.setPname(rs.getString("pname"));
						search.setPprice(rs.getInt("pprice"));
						search.setPthumbimg(rs.getString("pthumbimg"));
						search.setPid(rs.getInt("pid"));
						search.setPdiscount(rs.getInt("pdiscount"));
						al_initial.add(search);
						} while (rs.next());
					}
				} catch (Exception e) {
					
				} finally {
						if (rs !=null) rs.close();
						if (pstmt != null) pstmt.close();
						if (conn !=null) conn.close();
					} 
			}
	 
	 public static boolean matchString(String value, String search) {
			int t = 0;
			int seof = value.length() - search.length();
			int slen = search.length();
			if (seof <0) { return false; }
			for (int i = 0; i <= seof; i ++) {
				t = 0;
				while (t <slen) {
				if (isInitialSound(search.charAt(t)) == true && isHangul(value.charAt(i+t))) {
					// 만약 현재 char 이 초성이고 value 가 한글이면
					if (getInitialSound(value.charAt(i+t))==search.charAt(t)) { t++; }
					// 각각의 초성끼리 같은지 비교한다
					else { break; }
				} else {
					// char 이 초성이 아니라면
					if (value.charAt(i+t) == search.charAt(t)) { t++; }
					else { break; }
				}
				
				if (t == slen) { return true; }
				}
			}
			return false;
		}
	 
	 public List<Search> compare(String search_text, String shopping_select) {
		 System.out.println("Search_Initial.java 입력");
		 System.out.println("검색어 : " + search_text);
		 
		 List<Search> al = new ArrayList<Search>();
		 
		 try {
				int add_i = 0;
				
				String sql = "SELECT pname, (pprice - TRUNC (pprice * (PDISCOUNT/100))) pprice, PTHUMBIMG, pid, PDISCOUNT from product where PPUBLIC = 1 ORDER by psell desc";
				
				try {
					if (shopping_select.equals("sell_hit")) {
						sql = "SELECT pname, (pprice - TRUNC (pprice * (PDISCOUNT/100))) pprice, PTHUMBIMG, pid, PDISCOUNT from product where PPUBLIC = 1 ORDER by psell desc";
					} else if (shopping_select.equals("price_high")) {
						sql = "SELECT pname, (pprice - TRUNC (pprice * (PDISCOUNT/100))) pprice, PTHUMBIMG, pid, PDISCOUNT from product where PPUBLIC = 1 ORDER by pprice desc";
					} else if (shopping_select.equals("price_low")) {
						sql = "SELECT pname, (pprice - TRUNC (pprice * (PDISCOUNT/100))) pprice, PTHUMBIMG, pid, PDISCOUNT from product where PPUBLIC = 1 ORDER by pprice";
					} else if (shopping_select.equals("pregdate")) {
						sql = "SELECT pname, (pprice - TRUNC (pprice * (PDISCOUNT/100))) pprice, PTHUMBIMG, pid, PDISCOUNT from product where PPUBLIC = 1 ORDER by pregdate desc";
					} else {
						sql = "SELECT pname, (pprice - TRUNC (pprice * (PDISCOUNT/100))) pprice, PTHUMBIMG, pid, PDISCOUNT from product where PPUBLIC = 1 ORDER by psell desc";
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				init(sql);
				
				if (isInitialSound(search_text.charAt(0))) {
				for (int i=0; i < al_initial.size(); i++) {
				if (matchString(al_initial.get(i).getPname(), search_text)) {
					al.add(add_i, al_initial.get(i));
					add_i ++;
						} 
					}
				} else {
					for (int i=0; i < al_initial.size(); i++) {
						if (al_initial.get(i).getPname().contains(search_text)) {
							Search search = new Search();
							search.setPname(al_initial.get(i).getPname());
							search.setPprice(al_initial.get(i).getPprice());
							search.setPthumbimg(al_initial.get(i).getPthumbimg());
							search.setPid(al_initial.get(i).getPid());
							al.add(search);
						}
					}
				}
				
				} catch (Exception e) {
				} 
		 
		 return al;
			
		}
		 
}
