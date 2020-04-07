package cookieDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 显示选择的书籍，将其id添加至cookie 写回浏览器
 */
@WebServlet("/cookie2")
public class cookie2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String makeCookies(String id, HttpServletRequest request) {	
		String bookHistory=null;
		Cookie []cookies=request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			if(cookies[i].getName().equals("bookHistory")){
				bookHistory=cookies[i].getValue();
			}
		}
		if(bookHistory==null){
			bookHistory=id;
			
			return bookHistory;
		}
		String ids[]=bookHistory.split("_");
		//数组转链表集合检查是否已经存在当前id
		LinkedList<String> idlist=new LinkedList(Arrays.asList(ids));
		if(idlist.contains(id)){
			idlist.remove(id);
		}else{
			if(idlist.size()>=3){
				idlist.removeLast();
			}
		}
		idlist.addFirst(id);
		
		StringBuffer sb=new StringBuffer();
		for(String lid:idlist){
			sb.append(lid+"_");
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}

   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		out.print("你看的书是：<br/>");
		String id=request.getParameter("id");
		Book b=(Book) DB.getMap().get(id);
		out.print(b.getId()+"<br/>");
		out.print(b.getName()+"<br/>");
		
		String bookHistory=makeCookies(b.getId(),request);
		Cookie cookie=new Cookie("bookHistory",bookHistory);
		cookie.setMaxAge(10000);
		response.addCookie(cookie);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
