package AnnotationDemo;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BookDao {
	@Inject private ComboPooledDataSource ds;
	
   @Inject
   public void setDs(ComboPooledDataSource ds){
	   this.ds=ds;
   }

public ComboPooledDataSource getDs() {
	return ds;
} 
  
      
}
