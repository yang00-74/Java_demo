package cookieDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 显示全部书籍页面
 */
@WebServlet("/cookie1")
public class cookie1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		out.print("书籍如下：<br/>");
		Map<String,Book> m=DB.getMap();
		for(Map.Entry<String, Book> en:m.entrySet()){
			Book b=en.getValue();
			out.print("<a href='/CookieSession/cookie2?id="+
			b.getId()+"' target='_blank'>"+b.getName()+"</a><br/>");			
		}
		
		out.print("你曾经看过：<br/>");
		Cookie cookie=null;
		Cookie cookies[]=request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			if(cookies[i].getName().equals("bookHistory")){
				cookie=cookies[i];
			}
		}
		
		if(cookie!=null){
			String bookHistory=cookie.getValue();
			String ids[]=bookHistory.split("\\_");
			for(String id:ids){
				Book b=(Book) DB.getMap().get(id);
				out.print(b.getName()+"<br/>");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
}	
class DB{
	   private static Map<String,Book> m=new HashMap();
	   static{
			 m.put("1", new Book("1","java se"));
			 m.put("2", new Book("2","java web"));
			 m.put("3", new Book("3","c++"));
			 m.put("4", new Book("4","php"));
		}
	   public static Map getMap(){
		   return m;
	   }
	}
class Book{
	private String id;
	private String name;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

