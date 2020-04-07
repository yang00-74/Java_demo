package FilterDemo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GzipFilterDemo
 */
@WebServlet("/GzipFilterDemo")
public class GzipFilterDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
 //        String data="Hello,word!!kljkljkljklhkjh";
//         String data="ÖÐ¹ú!!";
//         response.getOutputStream().write(data.getBytes("UTF-8"));
//         response.getWriter().write(data);
		 request.getRequestDispatcher("/index.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
