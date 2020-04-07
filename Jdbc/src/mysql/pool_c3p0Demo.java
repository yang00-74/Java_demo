package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.pool_c3p0Utils;

public class pool_c3p0Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con=null;
    	PreparedStatement st=null;
    	ResultSet rs=null;
    	
    	try{
    		
    		con=pool_c3p0Utils.getConnection();
    		System.out.println(con.getClass().getName());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		pool_c3p0Utils.release(con, st, rs);
    	}
	}

}
