package DButils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import Utils.pool_DBCPUtils;

public class dbcp_pool {

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
	
	public static DataSource getConnection(){
		return ds;
	}
}
