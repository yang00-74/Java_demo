package sessionDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/session1")
public class session1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		out.print("Êé¼®ÈçÏÂ£º<br/>");
		Map<String,Book> m=DB.getMap();
		for(Map.Entry<String, Book> en:m.entrySet()){
			Book b=en.getValue();
			out.print(b.getName()+"<a href='/CookieSession/session2?id="+
			b.getId()+"'>¹ºÂò</a><br/>");			
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


