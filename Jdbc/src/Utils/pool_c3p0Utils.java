package Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class pool_c3p0Utils {
    private static ComboPooledDataSource ds=null;
    static{
    	try{
    		ds=new ComboPooledDataSource();
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
}
