package servletcontentlistener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.CoordinateService;

@WebListener
public class MainListener implements ServletContextListener {

    
    public MainListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
		
    }

    
    public void contextInitialized(ServletContextEvent sce)  { 
//      // 서버가 시작 할때
 	
    	try {	
			Class.forName("com.mysql.cj.jdbc.Driver");	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MainListener >> contextInitilized() >> Class.forNmae() >>fail cannot get jdbc dirver ");
		}
    
    	Map<String, String> siteCodeMap = new HashMap<String, String>();
    	siteCodeMap.put("종로구", "JR");
    	siteCodeMap.put("중구", "JG");
    	siteCodeMap.put("용산구", "YS");
    	siteCodeMap.put("성동구", "SD");
    	siteCodeMap.put("광진구", "GJ");
    	siteCodeMap.put("동대문구", "DD");
    	siteCodeMap.put("종로구", "JN");
    	siteCodeMap.put("성북구", "SB");
    	siteCodeMap.put("강북구", "GB");
    	siteCodeMap.put("도봉구", "DB");
    	siteCodeMap.put("노원구", "NW");
    	siteCodeMap.put("은평구", "EP");
    	siteCodeMap.put("서대문구", "SM");
    	siteCodeMap.put("마포구", "MP");
    	siteCodeMap.put("양천구", "YC");
    	siteCodeMap.put("강서구", "GS");
    	siteCodeMap.put("구로구", "GR");
    	siteCodeMap.put("금천구", "GC");
    	siteCodeMap.put("영등포구", "YD"); 
    	siteCodeMap.put("동작구", "DJ");
    	siteCodeMap.put("관악구", "GA");
    	siteCodeMap.put("서초구", "SC");
    	siteCodeMap.put("강남구", "GN");
    	siteCodeMap.put("송파구", "SP");
    	siteCodeMap.put("강동구", "GD");
    	sce.getServletContext().setAttribute("siteCodeMap", siteCodeMap);
    	
    	new CoordinateService();
    	
//    	//open port goole email
//    	int port = 465;  // 열고자 하는 포트 번호
//        String command = "netsh advfirewall firewall add rule name=\"Open Port " + port + "\" dir=in action=allow protocol=TCP localport=" + port;
//
//        try {
//            Process process = Runtime.getRuntime().exec(command);
//            process.waitFor();
//
//            // 명령어 실행 결과 출력
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//
//            System.out.println(port+"port open");
//
//        } catch (IOException | InterruptedException e) {
//            System.err.println("fail to open port");
//            e.printStackTrace();
//        }
    }
	
}
