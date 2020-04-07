package AnnotationDemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//元注解 规定注解的作用域
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

	String dirverClass() default "com.mysql.jdbc.Driver";

	String jdbcUrl() default "jdbc:mysql://localhost:3306/bookstore";

	String user()default "root";

	String password() default "root";

}
