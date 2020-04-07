package DButils;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import domain.empolyee;

public class dbutilsDemo {
	   @Test
        public void insert() throws SQLException{
        	QueryRunner run=new QueryRunner(dbcp_pool.getConnection());
        	String sql="insert into empolyee(id,name,birthday) values(?,?,?)";
        	Object params[]={1,"°×°×","1974-12-23"};
        	run.update(sql, params);
        	
        }
	   
	    @Test
	    public void queryInfo() {
	    	ArrayList<empolyee> list = new ArrayList<>();
	    	QueryRunner run = new QueryRunner(dbcp_pool.getConnection());
	    	String sql = "select * from empolyee";
	    	try {
				list = run.query(sql, new BeanListHandler(empolyee.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	for(empolyee e:list){
	    		System.out.println(e.getName());
	    	}
	    }
}
