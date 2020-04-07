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
	 * ������ʵ��ͳһ����
	 * 
	 */

    private FilterConfig config;//�������ȡ��filter��ʼ������
    
    @Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config=arg0;//��ȡ��ʼ������

	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// ������������������������Ӧ ����ͳһ����
		  arg0.setCharacterEncoding("UTF-8");
		  arg1.setCharacterEncoding("UTF-8");
		  arg1.setContentType("text/html;charset=UTF-8");
		  
		  System.out.println(config.getInitParameter("charset"));//ȡ�ò���ֵ
		  
		  System.out.println("�������سɹ���");
		  
		  arg2.doFilter(arg0, arg1);//����֮�����
		  
		  //������Ӧ
		  
		  System.out.println("��Ӧ���سɹ���");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
