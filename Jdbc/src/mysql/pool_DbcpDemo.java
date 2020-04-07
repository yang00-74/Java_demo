package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.pool_DBCPUtils;

public class pool_DbcpDemo {
	
	public static void main(String[] args) {
		Connection con=null;
    	PreparedStatement st=null;
    	ResultSet rs=null;
    	
    	try{
    		
    		con=pool_DBCPUtils.getConnection();
    		System.out.println(con.getClass().getName());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		pool_DBCPUtils.release(con, st, rs);
    	}

	}

}
