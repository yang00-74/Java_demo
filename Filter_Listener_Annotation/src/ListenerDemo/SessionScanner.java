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
//@WebListener //设置与web同周期的定时session扫描器
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
    	System.out.println(session+"被创建了");
    	
    	synchronized (lock) {//同步锁，不同位置的代码共享一把锁 即可实现同步
			list.add(session);
		}
    }
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println(arg0.getSession()+"被摧毁了");
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
		System.out.println("定时器启动了");
		synchronized (lock) {
		   	ListIterator it=list.listIterator();
		   	while(it.hasNext()){
		   		HttpSession session=(HttpSession) it.next();
		   		if(System.currentTimeMillis()-session.getLastAccessedTime()>15*1000){
		   			session.invalidate();
		   		// list.remove(session) 不能使用，迭代器遍历，迭代器修改，避免并发修改异常
		   			it.remove();
		   		}
		   	}
		}
	}
	
}
