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
 * ������Ķ��������session list������
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
		
//		����cookieʵ��sessionһ��ʱ������Ч		
		Cookie cookie=new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(30*60);
		cookie.setPath("/CookieSession");
		response.addCookie(cookie);
		
		List list=(List) session.getAttribute("list");//��ȡsession��ԭ�е�list����
		if(list==null){
			list=new ArrayList();
			session.setAttribute("list", list);
		}
		list.add(b);
//		//ת����ʽ�ᵼ��ˢ��ʱ�ظ��ϴεĲ���
//		request.getRequestDispatcher("/session3").forward(request, response);
		
//		�����ض���ʽ
		response.sendRedirect("/CookieSession/session3");
		
//      URL��д �������cookie���session�������⣬���������Ӷ�Ҫ����URL��д
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
