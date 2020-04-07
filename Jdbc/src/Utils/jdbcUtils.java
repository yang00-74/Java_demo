package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//负责连接数据库的工具类
public class jdbcUtils {
	private static Properties p=new Properties();
	static{
		try{
			p.load(jdbcUtils.class.getClassLoader().getResourceAsStream("Utils/config.properties"));
			 Class.forName(p.getProperty("driver"));
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
     //获取数据库链接 
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));		
	}
	//释放资源
	public static void release(Connection con,Statement st,ResultSet rs){
		 if(rs!=null){
		     try{rs.close();}
		     catch(Exception e){e.printStackTrace();}
		}
    	 if(st!=null){
		     try{st.close();}
		     catch(Exception e){e.printStackTrace();}
		}
    	 if(con!=null){
		     try{con.close();}
		     catch(Exception e){e.printStackTrace();}
		}
	}
	//数据库更改操作工具方法
	public static void update(String sql,Object params[]) throws SQLException{
		Connection con=null;
    	PreparedStatement st=null;
    	ResultSet rs=null;
    	
    	try{
    		con=jdbcUtils.getConnection();
    		st=con.prepareStatement(sql);
    		for(int i=0;i<params.length;i++){
    			st.setObject(i+1, params[i]);
    		}
    		st.executeUpdate();
    		
    	}finally{
    		jdbcUtils.release(con, st, rs);
    	}
	}
	//数据库查找操作工具方法
	public static Object query(String sql,Object params[],ResultSetHandler handler) throws SQLException{
		Connection con=null;
    	PreparedStatement st=null;
    	ResultSet rs=null;
    	
    	try{
    		con=jdbcUtils.getConnection();
    		st=con.prepareStatement(sql);
    		for(int i=0;i<params.length;i++){
    			st.setObject(i+1, params[i]);
    		}
    		rs=st.executeQuery();
    		return handler.handler(rs);
    		
    	}finally{
    		jdbcUtils.release(con, st, rs);
    	}
	}
}

  




