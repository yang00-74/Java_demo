package ListenerDemo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionScanner
 *
 */
//@WebListener //������webͬ���ڵĶ�ʱsessionɨ����
public class SessionScanner implements ServletContextListener, HttpSessionListener {
	
   private List<HttpSession> list=Collections.synchronizedList(new LinkedList<HttpSession>());
   private Object lock=new Object();
    
   public SessionScanner() {
        
    }
    public void contextInitialized(ServletContextEvent arg0) {
        Timer timer=new Timer();
        timer.schedule(new MyTask(list, lock), 0, 15*1000);
    }

    public void sessionCreated(HttpSessionEvent arg0) {
    	HttpSession session=arg0.getSession();
    	System.out.println(session+"��������");
    	
    	synchronized (lock) {//ͬ��������ͬλ�õĴ��빲��һ���� ����ʵ��ͬ��
			list.add(session);
		}
    }
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println(arg0.getSession()+"���ݻ���");
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }	
}
class MyTask extends TimerTask{
	private List list;
	private Object lock;
	public MyTask(List list,Object lock){
		this.list=list;
		this.lock=lock;
	}
	public void run(){
		System.out.println("��ʱ��������");
		synchronized (lock) {
		   	ListIterator it=list.listIterator();
		   	while(it.hasNext()){
		   		HttpSession session=(HttpSession) it.next();
		   		if(System.currentTimeMillis()-session.getLastAccessedTime()>15*1000){
		   			session.invalidate();
		   		// list.remove(session) ����ʹ�ã��������������������޸ģ����Ⲣ���޸��쳣
		   			it.remove();
		   		}
		   	}
		}
	}
	
}
