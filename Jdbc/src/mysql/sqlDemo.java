package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlDemo {
        public static void main(String[] args) throws SQLException, ClassNotFoundException {
        	String url="jdbc:mysql:///study";//默认连接本机3306端口数据库
        	String username="root";
        	String password="root";
        	
        	Connection con=null;
        	Statement st=null;
        	ResultSet rs=null;
         try{	
        	//1.加载驱动
        	Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());//注册会导致两个对象
			//2.获取链接
			 con=DriverManager.getConnection(url,username,password);
			//3.获取sql语句的statement对象
			 st=con.createStatement();
			//4.向数据库发送sql语句，获取返回结果集
			 rs=st.executeQuery("select * from user");
			//5.从结果集获取数据
			while(rs.next()){
				System.out.println(rs.getObject("id"));
				System.out.println(rs.getObject("username"));	
				System.out.println(rs.getObject("password"));
			}
         }finally{
			//6.释放链接
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
