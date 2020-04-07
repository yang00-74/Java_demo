package sessionDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 将购买的东西添加至session list集合中
 */
@WebServlet("/session2")
public class session2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		Book b=(Book) DB.getMap().get(id);
		HttpSession session=request.getSession();
		
//		利用cookie实现session一定时间内有效		
		Cookie cookie=new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(30*60);
		cookie.setPath("/CookieSession");
		response.addCookie(cookie);
		
		List list=(List) session.getAttribute("list");//获取session中原有的list属性
		if(list==null){
			list=new ArrayList();
			session.setAttribute("list", list);
		}
		list.add(b);
//		//转发方式会导致刷新时重复上次的操作
//		request.getRequestDispatcher("/session3").forward(request, response);
		
//		请求重定向方式
		response.sendRedirect("/CookieSession/session3");
		
//      URL重写 解决禁用cookie后的session共享问题，但所有链接都要进行URL重写
//		String url=response.encodeRedirectUrl("/CookieSession/session3");
//		response.sendRedirect(url);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
