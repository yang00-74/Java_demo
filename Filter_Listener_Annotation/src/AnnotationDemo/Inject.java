package AnnotationDemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//Ԫע�� �涨ע���������
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

	String dirverClass() default "com.mysql.jdbc.Driver";

	String jdbcUrl() default "jdbc:mysql://localhost:3306/bookstore";

	String user()default "root";

	String password() default "root";

}
