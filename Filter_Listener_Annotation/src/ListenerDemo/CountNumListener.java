package ListenerDemo;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class CountNumListener
 *
 */
@WebListener
public class CountNumListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public CountNumListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    	ServletContext con=arg0.getSession().getServletContext();
    	Integer count=(Integer) con.getAttribute("count");
    	if(count==null){
    		count=1;
    		con.setAttribute("count", count);
    	}else{
    		count++;
    		con.setAttribute("count", count);
    	}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    	ServletContext con=arg0.getSession().getServletContext();
    	Integer count=(Integer) con.getAttribute("count");	
    		count--;
    		con.setAttribute("count", count);
    	
    }
	
}
