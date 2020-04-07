package AnnotationDemo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.sql.DataSource;

public class DaoFactory2 {
	/**
	 * 从字段注入
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
   public static BookDao createDao(){
	   BookDao dao=new BookDao();
	   
	   Field field[]=dao.getClass().getDeclaredFields();//获取所有字段
	   for(int i=0;field!=null&&i<field.length;i++){
		   //查看字段上是否有注解
		   Field f=field[i];
		   f.setAccessible(true);
		   Inject inject=f.getAnnotation(Inject.class);
		   if(inject==null){
			   continue;
		   }
		  try {
			DataSource ds= (DataSource) f.getType().newInstance();
			//用注解信息创建连接池
			inject2Datasource(inject,ds);
			
			f.set(dao, ds);
			
		} catch (Exception e) {			
			e.printStackTrace();
		} 		   
	   }
	return dao;
   }
    //注解信息配置连接池
	private static void inject2Datasource(Inject inject, DataSource ds) {
		
		Method method[]=inject.getClass().getMethods();
		for(Method m:method){
			String methodName=m.getName();//获取注解中的属性名称
			
			//通过属性描述器，比较ds上所需的属性与注解中属性名是否相同
			PropertyDescriptor pd=null;
			try {			
			     pd=new PropertyDescriptor(methodName,ds.getClass());
			     
			     //得到注解中属性的值
			     Object value=m.invoke(inject, null);
			     //将值填入到所需的bean(ds)的对应属性上
			     pd.getWriteMethod().invoke(ds, value);
			     
			} catch (Exception e) {
				continue;
			}	
		}	
	}
}
