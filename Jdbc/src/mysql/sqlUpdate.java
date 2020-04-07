package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import Utils.jdbcUtils;

public class sqlUpdate {
	
	@Test
    public void insert() throws SQLException{
    	Connection con=null;
    	PreparedStatement st=null;
    	ResultSet rs=null;
    	
    	try{
    		con=jdbcUtils.getConnection();
    		
    		String sql="insert into user values(?,?,?)";
    		st=con.prepareStatement(sql);//‘§±‡“Î
    		st.setString(1, "10");
    		st.setString(2, "√Ù");
    		st.setString(3, "123457");
    		    		
    		int num=st.executeUpdate();
    		if(num>0){
    			System.out.println("≤Â»Î≥…π¶");
    		}
    	}finally{
    		jdbcUtils.release(con, st, rs);
    	}
    }
}
