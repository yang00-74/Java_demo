package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlDemo {
        public static void main(String[] args) throws SQLException, ClassNotFoundException {
        	String url="jdbc:mysql:///study";//Ĭ�����ӱ���3306�˿����ݿ�
        	String username="root";
        	String password="root";
        	
        	Connection con=null;
        	Statement st=null;
        	ResultSet rs=null;
         try{	
        	//1.��������
        	Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());//ע��ᵼ����������
			//2.��ȡ����
			 con=DriverManager.getConnection(url,username,password);
			//3.��ȡsql����statement����
			 st=con.createStatement();
			//4.�����ݿⷢ��sql��䣬��ȡ���ؽ����
			 rs=st.executeQuery("select * from user");
			//5.�ӽ������ȡ����
			while(rs.next()){
				System.out.println(rs.getObject("id"));
				System.out.println(rs.getObject("username"));	
				System.out.println(rs.getObject("password"));
			}
         }finally{
			//6.�ͷ�����
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
