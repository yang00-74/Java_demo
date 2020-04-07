package mysql;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import domain.empolyee;

import Utils.BeanHandler;
import Utils.BeanListHandler;
import Utils.jdbcUtils;
import Utils.pool_DBCPUtils;

public class jdbcUtilsDemo {
    @Test 
    public void test() throws SQLException{
    	empolyee e= new empolyee();
    	e.setId(5);
    	e.setName("扬名");
    	e.setBirthday("1978-12-20");
    	
    	add(e);
//    	delete(5);/
//  	update(e);
//    	empolyee k=select(5);//查找的单个结果集封装在bean中返回
//    	System.out.println(k.getName());
    	List list=selectAll();//查找的所以结果集封装在list集合中
    	System.out.println(list.size());
    }
    
    
    
	public void add(empolyee e) throws SQLException{
		String sql="insert into empolyee(id,name,birthday) values(?,?,?)";
		Object params[]={e.getId(),e.getName(),e.getBirthday()};
		pool_DBCPUtils.update(sql, params);
	}
    
    
    public void delete(int id) throws SQLException{
    	String sql="delete from empolyee where id=?";
		Object params[]={id};
		pool_DBCPUtils.update(sql, params);
    }
    
    public void update(empolyee e) throws SQLException{
    	String sql="update empolyee set name=?,birthday=? where id=?";
		Object params[]={e.getName(),e.getBirthday(),e.getId()};
		pool_DBCPUtils.update(sql, params);
    }
    
    public empolyee select(int id) throws SQLException{
        String sql="select * from empolyee where id=?";
        Object params[]={id};
        return (empolyee) pool_DBCPUtils.query(sql, params, new BeanHandler(empolyee.class));
    }
    
    public List selectAll() throws SQLException{
    	 String sql="select * from empolyee";
         Object params[]={};
         return (List)pool_DBCPUtils.query(sql, params, new BeanListHandler(empolyee.class));
    }

}
