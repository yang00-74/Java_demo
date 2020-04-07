package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.pool_jdbcUtils;

public class pool_jdbcDemo {

	
	public static void main(String[] args) {
		Connection con=null;
    	PreparedStatement st=null;
    	ResultSet rs=null;
    	
    	try{
    		pool_jdbcUtils pool=new pool_jdbcUtils();
    		con=pool.getConnection();
    		
    		String sql="insert into empolyee values(?,?,?)";
    		st=con.prepareStatement(sql);
    		st.setString(1, "10");
    		st.setString(2, "√Ù");
    		st.setString(3, "1994-10-15");
    		    		
    		int num=st.executeUpdate();
    		if(num>0){
    			System.out.println("≤Â»Î≥…π¶");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
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

}
