package FilterDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 动态代理实现全站压缩
 */
@WebFilter("/GzipFilter2")
public class GzipFilter2 implements Filter {

  	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
  		 HttpServletRequest request=(HttpServletRequest) req;
         HttpServletResponse response=(HttpServletResponse) resp;
         
         ResponseProxy p=new ResponseProxy(response); 
		 chain.doFilter(request, p.createProxy());
		 
		 byte[] out=p.getBuffer();//获取目标资源的输出
		 System.out.println("压缩之前2："+out.length);
			
			byte gzipout[]=gzip(out);
			System.out.println("压缩之后2："+gzipout.length);
			
			response.setHeader("content-encoding", "gzip");
			response.setHeader("content-length", gzipout.length+"");
			response.getOutputStream().write(gzipout);
	}
  	
  	private byte[] gzip(byte[] out) throws IOException {
  		ByteArrayOutputStream bout=new ByteArrayOutputStream();
    	GZIPOutputStream gout=new GZIPOutputStream(bout);
    	gout.write(out);
    	gout.close();
    	
    	return bout.toByteArray();
	}

	class ResponseProxy{
  		
  		private ByteArrayOutputStream bout=new ByteArrayOutputStream();
  		private PrintWriter pw=null;
  		
  		public byte[] getBuffer(){
  			if(pw!=null){
  				pw.close();
  			}
  			return bout.toByteArray();
  		}
  		
  		private HttpServletResponse response;
  		
		public ResponseProxy(HttpServletResponse response){
				this.response=response;
			}	
  		public HttpServletResponse createProxy(){
  					
  			return (HttpServletResponse) Proxy.newProxyInstance(GzipFilter2.class.getClassLoader(), 
  					response.getClass().getInterfaces(), new InvocationHandler() {
  						
  						@Override
  						public Object invoke(Object proxy, Method method, Object[] args)
  								throws Throwable {
  							if(!method.getName().equals("getWriter")&&!method.getName().equals("getOutputStream")){
  								method.invoke(response, args);
  							}
  							if(method.getName().equals("getWriter")){
  								pw= new PrintWriter(new OutputStreamWriter(bout,"UTF-8"));
  								return pw;
  							}
  							if(method.getName().equals("getOutputStream")){
  								return new ServletOutputStream() {
									@Override
									public void write(int b) throws IOException {
										bout.write(b);
									}
								};
  							}
  							return null;
  						}
  					});
  		}
  	}
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
