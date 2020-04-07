package FilterDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 输出压缩后的内容，需要增强respone中的方法，
 * 将其内容写入ByteArrayOutputStream 内存流
 */
public class GzipFilter implements Filter {
 
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        
        MyResponse myresponse=new MyResponse(response);//创建自定义的respone类
        //放行
		chain.doFilter(request, myresponse);
		//经过过滤器放行后数据写入了内存流，需要取出后压缩
		byte[] out=myresponse.getBuffer();
		System.out.println("压缩之前："+out.length);
		
		byte gzipout[]=gzip(out);
		System.out.println("压缩之后："+gzipout.length);
		
		response.setHeader("content-encoding", "gzip");
		response.setHeader("content-length", gzipout.length+"");
		response.getOutputStream().write(gzipout);		
	}
    
    public byte[] gzip(byte b[]) throws IOException{
    	ByteArrayOutputStream bout=new ByteArrayOutputStream();
    	GZIPOutputStream gout=new GZIPOutputStream(bout);
    	gout.write(b);
    	gout.close();
    	
    	return bout.toByteArray();
    }
    
    
    class MyResponse extends HttpServletResponseWrapper{
    	private ByteArrayOutputStream bout=new ByteArrayOutputStream();
    	private PrintWriter pw;
    	
        private HttpServletResponse response;
        
		public MyResponse(HttpServletResponse response) {
			super(response);
			this.response=response;
		}
		
        @Override
		public ServletOutputStream getOutputStream() throws IOException {
			
        	return new MyServletOutputStream(bout);
		}   	
    	public PrintWriter getWriter() throws IOException {
    		//包装解决中文乱码问题
    	    pw=new PrintWriter(new OutputStreamWriter(bout,response.getCharacterEncoding()));
    		return pw;	
    	}
    	
    	public byte[] getBuffer(){
    		if(pw!=null){
    			pw.close();
    		}
    		return bout.toByteArray();
    	}
		
    }
    class MyServletOutputStream extends ServletOutputStream{
    	private ByteArrayOutputStream bout;
    	public MyServletOutputStream(ByteArrayOutputStream bout) {
			this.bout = bout;
		}
		@Override
		public void write(int b) throws IOException {
			bout.write(b);
		}	
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
