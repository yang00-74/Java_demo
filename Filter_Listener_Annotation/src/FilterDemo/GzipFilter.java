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
 * ���ѹ��������ݣ���Ҫ��ǿrespone�еķ�����
 * ��������д��ByteArrayOutputStream �ڴ���
 */
public class GzipFilter implements Filter {
 
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        
        MyResponse myresponse=new MyResponse(response);//�����Զ����respone��
        //����
		chain.doFilter(request, myresponse);
		//�������������к�����д�����ڴ�������Ҫȡ����ѹ��
		byte[] out=myresponse.getBuffer();
		System.out.println("ѹ��֮ǰ��"+out.length);
		
		byte gzipout[]=gzip(out);
		System.out.println("ѹ��֮��"+gzipout.length);
		
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
    		//��װ���������������
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
