package service.dragon;

import java.io.IOException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dragon.Product;
import dao.dragon.ProductDao;
import dao.dragon.ShoppingUserDao;
import dao.god.NoticeDao;
import dao.half.Notice;
import service.CommandProcess;

public class MainAction implements CommandProcess  {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("-- service.dragon.MainAction --");
		
		
		try {
			HttpSession session = request.getSession(true);
			
			dao.half.NoticeDao ntcdao = dao.half.NoticeDao.getInstance();
			Notice ntc = new Notice();
			ntc = ntcdao.getlatest();

			session.setAttribute("ntc", ntc);
		} catch (Exception e) {
			System.out.println("mainaction popup error : " + e.getMessage());
		}
		
		
		
		try {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("session_sid", session.getAttribute("session_sid"));
			session.setAttribute("session_sname", session.getAttribute("session_sname"));
			session.setAttribute("session_stype", session.getAttribute("session_stype"));
			session.setAttribute("session_semail", session.getAttribute("session_semail"));
			
			if (request.getParameter("logout").equals("logout")) {
				request.setAttribute("session_stype", 3);
				session.invalidate();
			}
			
			String session_stype = (String) session.getAttribute("session_stype");
			String session_sid = (String) session.getAttribute("session_sid");
			
			request.setAttribute("session_sid", session_sid);
			request.setAttribute("session_stype", session_stype);
			
		} catch (Exception e) {
			System.out.println("오류 1 : "+ e.getMessage());
		}
		
		ProductDao productdao = ProductDao.getInstance();
		List<Product> main_img = new ArrayList<Product>();
		try {
			main_img = productdao.main_img();
			request.setAttribute("main_img", main_img);
		} catch (SQLException e) {
			System.out.println("오류 2 : "+ e.getMessage());
		}
		
		try {
			ShoppingUserDao shoppinguserdao = ShoppingUserDao.getInstance();
			
			SimpleDateFormat format = new SimpleDateFormat ("yyyyMMdd");
			SimpleDateFormat format2 = new SimpleDateFormat ("yyyy-MM-dd");
			
			Date time = new Date();
			String now_time = format.format(time); // 오늘 날짜 
			String now_time_format2 = format2.format(time);
			
			String covid_19_live = shoppinguserdao.covid_19_live(now_time, now_time);

			time = new Date(time.getTime()+(1000*60*60*24*-1));
			String yesterday_time = format.format(time); // 어제 날짜
			String covid_19_yesterday = shoppinguserdao.covid_19_live(yesterday_time, yesterday_time);

			time = new Date(time.getTime()+(1000*60*60*24*-1));
			String two_days_ago_time = format.format(time); // 이틀전 날짜
			String covid_19_two_days_ago = shoppinguserdao.covid_19_live(two_days_ago_time, two_days_ago_time);

			time = format.parse("20200204");
			String date_first_time = format.format(time); // 데이터 api 제공 날짜
			String date_first_time_format2 = format2.format(time);
			String covid_19_date_first_time = shoppinguserdao.covid_19_live(date_first_time, date_first_time);

			Calendar now_calendar = new GregorianCalendar();
			Calendar start_calendar = new GregorianCalendar(2020,Calendar.FEBRUARY,04);

			time = new Date(start_calendar.getTimeInMillis() + ((now_calendar.getTimeInMillis() - start_calendar.getTimeInMillis()))/7);
			String calendar_1_7 = format.format(time);
			String calendar_1_7_format2 = format2.format(time);
			String covid_calendar_1_7 = shoppinguserdao.covid_19_live(calendar_1_7, calendar_1_7);

			time = new Date(start_calendar.getTimeInMillis() + ((now_calendar.getTimeInMillis() - start_calendar.getTimeInMillis()))/7*2);
			String calendar_2_7 = format.format(time);
			String calendar_2_7_format2 = format2.format(time);
			String covid_calendar_2_7 = shoppinguserdao.covid_19_live(calendar_2_7, calendar_2_7);
			System.out.println(covid_calendar_2_7);

			time = new Date(start_calendar.getTimeInMillis() + ((now_calendar.getTimeInMillis() - start_calendar.getTimeInMillis()))/7*3);
			String calendar_3_7 = format.format(time);
			String calendar_3_7_format2 = format2.format(time);
			String covid_calendar_3_7 = shoppinguserdao.covid_19_live(calendar_3_7, calendar_3_7);
			
			time = new Date(start_calendar.getTimeInMillis() + ((now_calendar.getTimeInMillis() - start_calendar.getTimeInMillis()))/7*4);
			String calendar_4_7 = format.format(time);
			String calendar_4_7_format2 = format2.format(time);
			if (calendar_4_7.equals("20200817")) { calendar_4_7 = "20200816"; calendar_4_7_format2 = "2020-08-16"; }
			String covid_calendar_4_7 = shoppinguserdao.covid_19_live(calendar_4_7, calendar_4_7);
			
			time = new Date(start_calendar.getTimeInMillis() + ((now_calendar.getTimeInMillis() - start_calendar.getTimeInMillis()))/7*5);
			String calendar_5_7 = format.format(time);
			String calendar_5_7_format2 = format2.format(time);
			String covid_calendar_5_7 = shoppinguserdao.covid_19_live(calendar_5_7, calendar_5_7);

			time = new Date(start_calendar.getTimeInMillis() + ((now_calendar.getTimeInMillis() - start_calendar.getTimeInMillis()))/7*6);
			String calendar_6_7 = format.format(time);
			String calendar_6_7_format2 = format2.format(time);
			String covid_calendar_6_7 = shoppinguserdao.covid_19_live(calendar_6_7, calendar_6_7);

			String start = null; //태그명
			String end = null;
			int target_start = 0;
			int target_end = 0;
			String result = null; //태그안 내용

			String day = null;

			try {
				// --

				start = "<createDt>"; //기준일
				end = "</createDt>";

				target_start = covid_19_live.indexOf(start) + start.length() ; 
				target_end = covid_19_live.indexOf(end); 
				result = covid_19_live.substring(target_start,target_end);
				day = result;
//				System.out.println("업데이트 시간 : " + day); 

				target_start = covid_19_yesterday.indexOf(start) + start.length() ; 
				target_end = covid_19_yesterday.indexOf(end); 
				String result_yesterday = covid_19_yesterday.substring(target_start,target_end);
				String yesterday = result_yesterday;
//				System.out.println("업데이트 전날 : " + yesterday); 
				
			} catch(Exception e) {
				
				
				System.out.println(now_time + " 기준 업데이트가 되지 않았습니다.");
				
				start = "<createDt>"; //기준일
				end = "</createDt>";
				
				target_start = covid_19_yesterday.indexOf(start) + start.length() ; 
				target_end = covid_19_yesterday.indexOf(end); 
				result = covid_19_yesterday.substring(target_start,target_end);
				day = result;
//				System.out.println("업데이트 시간 : " + day); 

				target_start = covid_19_two_days_ago.indexOf(start) + start.length() ; 
				target_end = covid_19_two_days_ago.indexOf(end); 
				String result_yesterday = covid_19_two_days_ago.substring(target_start,target_end);
				String yesterday = result_yesterday;
//				System.out.println("업데이트 전날 : " + yesterday); 
				
				covid_19_live = covid_19_yesterday;
				covid_19_yesterday = covid_19_two_days_ago;
				
			}

			//--
			
				start = "<decideCnt>"; // 총 확진자
				end = "</decideCnt>";

				target_start = covid_19_live.indexOf(start) + start.length() ; 
				target_end = covid_19_live.indexOf(end); 
				result = covid_19_live.substring(target_start,target_end);
				String confirmed = result;
				System.out.println("확진환자 : " + confirmed); 

				target_start = covid_19_date_first_time.indexOf(start) + start.length() ; 
				target_end = covid_19_date_first_time.indexOf(end); 
				result = covid_19_date_first_time.substring(target_start,target_end);
				String confirmed_date_first_time = result;

				target_start = covid_calendar_1_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_1_7.indexOf(end); 
				result = covid_calendar_1_7.substring(target_start,target_end);
				String confirmed_covid_calendar_1_7 = result;

				target_start = covid_calendar_2_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_2_7.indexOf(end); 
				result = covid_calendar_2_7.substring(target_start,target_end);
				String confirmed_covid_calendar_2_7 = result;

				target_start = covid_calendar_3_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_3_7.indexOf(end); 
				result = covid_calendar_3_7.substring(target_start,target_end);
				String confirmed_covid_calendar_3_7 = result;
				
				target_start = covid_calendar_4_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_4_7.indexOf(end); 
				result = covid_calendar_4_7.substring(target_start,target_end);
				String confirmed_covid_calendar_4_7 = result;

				target_start = covid_calendar_5_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_5_7.indexOf(end); 
				result = covid_calendar_5_7.substring(target_start,target_end);
				String confirmed_covid_calendar_5_7 = result;

				target_start = covid_calendar_6_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_6_7.indexOf(end); 
				result = covid_calendar_6_7.substring(target_start,target_end);
				String confirmed_covid_calendar_6_7 = result;

				target_start = covid_19_yesterday.indexOf(start) + start.length() ; 
				target_end = covid_19_yesterday.indexOf(end); 
				result = covid_19_yesterday.substring(target_start,target_end);
				String confirmed_yesterday = result;
//				System.out.println("하루 확진 : " + (Integer.parseInt(confirmed) - Integer.parseInt(confirmed_yesterday))); 
				int confirmed_oneday = Integer.parseInt(confirmed) - Integer.parseInt(confirmed_yesterday);
				
				// --

				start = "<examCnt>"; // 검사 중
				end = "</examCnt>";

				target_start = covid_19_live.indexOf(start) + start.length() ; 
				target_end = covid_19_live.indexOf(end); 
				result = covid_19_live.substring(target_start,target_end);
				String check = result;
//				System.out.println("검사중 : " + check); 

				target_start = covid_19_yesterday.indexOf(start) + start.length() ; 
				target_end = covid_19_yesterday.indexOf(end); 
				result = covid_19_yesterday.substring(target_start,target_end);
				String check_yesterday = result;
//				System.out.println("하루 검사 : " + (Integer.parseInt(check) - Integer.parseInt(check_yesterday))); 
				int check_oneday = Integer.parseInt(check) - Integer.parseInt(check_yesterday);
				
				//--

				start = "<deathCnt>"; //총 사망자
				end = "</deathCnt>";

				target_start = covid_19_live.indexOf(start) + start.length() ; 
				target_end = covid_19_live.indexOf(end); 
				result = covid_19_live.substring(target_start,target_end);
				String death = result;
//				System.out.println("사망자 : " + death);

				target_start = covid_19_yesterday.indexOf(start) + start.length() ; 
				target_end = covid_19_yesterday.indexOf(end); 
				result = covid_19_yesterday.substring(target_start,target_end);
				String check_death = result;
//				System.out.println("하루 사망 : " + (Integer.parseInt(death) - Integer.parseInt(check_death))); 
				int death_oneday = Integer.parseInt(death) - Integer.parseInt(check_death);
				
				//--

				start = "<clearCnt>"; //총 격리해제
				end = "</clearCnt>";

				target_start = covid_19_live.indexOf(start) + start.length() ; 
				target_end = covid_19_live.indexOf(end); 
				result = covid_19_live.substring(target_start,target_end);
				String clear = result;
//				System.out.println("격리해제 : " + clear);
				
				target_start = covid_19_date_first_time.indexOf(start) + start.length() ; 
				target_end = covid_19_date_first_time.indexOf(end); 
				result = covid_19_date_first_time.substring(target_start,target_end);
				String clear_date_first_time = result;

				target_start = covid_calendar_1_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_1_7.indexOf(end); 
				result = covid_calendar_1_7.substring(target_start,target_end);
				String clear_covid_calendar_1_7 = result;

				target_start = covid_calendar_2_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_2_7.indexOf(end); 
				result = covid_calendar_2_7.substring(target_start,target_end);
				String clear_covid_calendar_2_7 = result;

				target_start = covid_calendar_3_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_3_7.indexOf(end); 
				result = covid_calendar_3_7.substring(target_start,target_end);
				String clear_covid_calendar_3_7 = result;

				target_start = covid_calendar_4_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_4_7.indexOf(end); 
				result = covid_calendar_4_7.substring(target_start,target_end);
				String clear_covid_calendar_4_7 = result;

				target_start = covid_calendar_5_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_5_7.indexOf(end); 
				result = covid_calendar_5_7.substring(target_start,target_end);
				String clear_covid_calendar_5_7 = result;

				target_start = covid_calendar_6_7.indexOf(start) + start.length() ; 
				target_end = covid_calendar_6_7.indexOf(end); 
				result = covid_calendar_6_7.substring(target_start,target_end);
				String clear_covid_calendar_6_7 = result;

				target_start = covid_19_yesterday.indexOf(start) + start.length() ; 
				target_end = covid_19_yesterday.indexOf(end); 
				result = covid_19_yesterday.substring(target_start,target_end);
				String check_clear = result;
//				System.out.println("하루 격리해제 : " + (Integer.parseInt(clear) - Integer.parseInt(check_clear))); 
				int clear_oneday = Integer.parseInt(clear) - Integer.parseInt(check_clear);
				
				request.setAttribute("date_first_time_format2", date_first_time_format2);
				request.setAttribute("calendar_1_7_format2", calendar_1_7_format2);
				request.setAttribute("calendar_2_7_format2", calendar_2_7_format2);
				request.setAttribute("calendar_3_7_format2", calendar_3_7_format2);
				request.setAttribute("calendar_4_7_format2", calendar_4_7_format2);
				request.setAttribute("calendar_5_7_format2", calendar_5_7_format2);
				request.setAttribute("calendar_6_7_format2", calendar_6_7_format2);
				request.setAttribute("now_time_format2", now_time_format2);
				
				request.setAttribute("confirmed_date_first_time", confirmed_date_first_time);
				request.setAttribute("confirmed_covid_calendar_1_7", confirmed_covid_calendar_1_7);
				request.setAttribute("confirmed_covid_calendar_2_7", confirmed_covid_calendar_2_7);
				request.setAttribute("confirmed_covid_calendar_3_7", confirmed_covid_calendar_3_7);
				request.setAttribute("confirmed_covid_calendar_4_7", confirmed_covid_calendar_4_7);
				request.setAttribute("confirmed_covid_calendar_5_7", confirmed_covid_calendar_5_7);
				request.setAttribute("confirmed_covid_calendar_6_7", confirmed_covid_calendar_6_7);
				request.setAttribute("confirmed", confirmed);
				
				request.setAttribute("clear_date_first_time", clear_date_first_time);
				request.setAttribute("clear_covid_calendar_1_7", clear_covid_calendar_1_7);
				request.setAttribute("clear_covid_calendar_2_7", clear_covid_calendar_2_7);
				request.setAttribute("clear_covid_calendar_3_7", clear_covid_calendar_3_7);
				request.setAttribute("clear_covid_calendar_4_7", clear_covid_calendar_4_7);
				request.setAttribute("clear_covid_calendar_5_7", clear_covid_calendar_5_7);
				request.setAttribute("clear_covid_calendar_6_7", clear_covid_calendar_6_7);
				request.setAttribute("clear", clear);
				
				request.setAttribute("check", check);
				request.setAttribute("death", death);
				
				request.setAttribute("check_oneday", check_oneday);
				request.setAttribute("death_oneday", death_oneday);
				request.setAttribute("clear_oneday", clear_oneday);
				request.setAttribute("confirmed_oneday", confirmed_oneday);
				
				request.setAttribute("day", day);
				
			} catch (Exception e) {
				System.out.println("오류 3 : "+ e.getMessage());
			}
		
		return "main.jsp";
	}

}
