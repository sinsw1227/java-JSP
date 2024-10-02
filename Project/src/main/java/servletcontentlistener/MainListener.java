package servletcontentlistener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import repository.LoginRepository;
import repository.MapRepository;

@WebListener
public class MainListener implements ServletContextListener {

    private LoginRepository loginRepository;
    private MapRepository mapRepository;
    
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
// 	loginRepository = new LoginRepository();
// 	sce.getServletContext().setAttribute("loginRepository", loginRepository);
    	//동시성 문제로 일단은 요청이 들어올때마다 생성하는걸로 =>> 나중에 시간 남으면 해결
 	
    	try {	
			Class.forName("com.mysql.cj.jdbc.Driver");	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("MainListener >> contextInitilized() >> Class.forNmae() >>fail cannot get jdbc dirver ");
		}
    
    	try {
    		mapRepository = new MapRepository();
    		sce.getServletContext().setAttribute("mapRepository", mapRepository);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
}
