package FilterDemo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo1 implements Filter {
	/**
	 * 拦截器实现统一编码
	 * 
	 */

    private FilterConfig config;//定义对象取得filter初始化参数
    
    @Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config=arg0;//获取初始化参数

	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// 配合拦截器拦截所有请求和响应 设置统一编码
		  arg0.setCharacterEncoding("UTF-8");
		  arg1.setCharacterEncoding("UTF-8");
		  arg1.setContentType("text/html;charset=UTF-8");
		  
		  System.out.println(config.getInitParameter("charset"));//取得参数值
		  
		  System.out.println("请求拦截成功！");
		  
		  arg2.doFilter(arg0, arg1);//拦截之后放行
		  
		  //捕获响应
		  
		  System.out.println("响应拦截成功！");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
