package AnnotationDemo;

import java.sql.SQLException;

import javax.sql.DataSource;

public class TestFactory {
     public static void main(String[] args) throws SQLException {
//		BookDao dao=DaoFactory.createDao();
		BookDao dao=DaoFactory2.createDao();
		DataSource ds=dao.getDs();
		System.out.println(ds.getConnection());
	}
}
