package Utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

//处理查询结果集的接口
interface ResultSetHandler{
	  public Object handler(ResultSet rs);
}

public class BeanHandler implements ResultSetHandler{
    private Class<?> clazz;
    public BeanHandler(Class<?> clazz){
    	this.clazz=clazz;
    }	
	public Object handler(ResultSet rs)  {
		try {
			  if(!rs.next()){
			     return null;
			  }
		
				Object bean=clazz.newInstance();
				
				ResultSetMetaData meta=rs.getMetaData();
				int count=meta.getColumnCount();
				  
				for(int i=0;i<count;i++){
					String name=meta.getColumnName(i+1);
					Object value=rs.getObject(name);
				
					 Field f= bean.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(bean,value);
				}			
				return bean;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
			
}