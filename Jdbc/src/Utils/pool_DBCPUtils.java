package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class pool_DBCPUtils {

	private static Properties p=new Properties();
	private static DataSource ds=null;
	static{
		try{
			p.load(pool_DBCPUtils.class.getClassLoader().getResourceAsStream("Utils/pool_Dbcp.properties"));
			 
			BasicDataSourceFactory f=new BasicDataSourceFactory();
			ds=f.createDataSource(p);
		
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
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
	    		con=pool_DBCPUtils.getConnection();
	    		st=con.prepareStatement(sql);
	    		for(int i=0;i<params.length;i++){
	    			st.setObject(i+1, params[i]);
	    		}
	    		st.executeUpdate();
	    		
	    	}finally{
	    		pool_DBCPUtils.release(con, st, rs);
	    	}
		}
		//数据库查找操作工具方法
		public static Object query(String sql,Object params[],ResultSetHandler handler) throws SQLException{
			Connection con=null;
	    	PreparedStatement st=null;
	    	ResultSet rs=null;
	    	
	    	try{
	    		con=pool_DBCPUtils.getConnection();
	    		st=con.prepareStatement(sql);
	    		for(int i=0;i<params.length;i++){
	    			st.setObject(i+1, params[i]);
	    		}
	    		rs=st.executeQuery();
	    		return handler.handler(rs);
	    		
	    	}finally{
	    		pool_DBCPUtils.release(con, st, rs);
	    	}
		}
}

